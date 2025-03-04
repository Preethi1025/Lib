package org.preethi.lib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LibraryController {

    @FXML
    private ComboBox<String> searchCriteriaBox;
    @FXML
    private TableView<Book> booksTable;
    @FXML
    private TableColumn<Book, Integer> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> pubDateColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;
    @FXML
    private TableColumn<Book, Boolean> availableColumn;

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @FXML
    public void initialize() {
        // Populate ComboBox with search criteria
        searchCriteriaBox.setItems(FXCollections.observableArrayList("Book Title", "Author", "Publication Date", "Genre", "Publisher"));

        // Set up table columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        pubDateColumn.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
    }

    @FXML
    private void handleSearch() {
        String selectedCriteria = searchCriteriaBox.getValue();
        if (selectedCriteria == null) {
            showAlert("Please select a search criteria.");
            return;
        }

        // Open a dialog box to get search input from the user
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Search");
        inputDialog.setHeaderText("Enter " + selectedCriteria + " to search:");
        inputDialog.setContentText(selectedCriteria + ":");
        inputDialog.showAndWait().ifPresent(input -> fetchBooks(selectedCriteria, input));
    }

    private void fetchBooks(String criteria, String value) {
        String columnName = switch (criteria) {
            case "Book Title" -> "title";
            case "Author" -> "author";
            case "Publication Date" -> "publication_date";
            case "Genre" -> "genre";
            case "Publisher" -> "publisher";
            default -> throw new IllegalArgumentException("Invalid search criteria");
        };

        ObservableList<Book> booksList = FXCollections.observableArrayList();
        String query = "SELECT * FROM books WHERE " + columnName + " LIKE ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + value + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                booksList.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("publication_date"),
                        resultSet.getString("genre"),
                        resultSet.getBoolean("available")
                ));
            }

            booksTable.setItems(booksList);
        } catch (SQLException e) {
            showAlert("Error fetching records: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    @FXML
    private void openNewForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/preethi/lib/new-book-form.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            stage.setTitle("Add New Book");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
