package org.preethi.lib;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class NewBookController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private DatePicker publicationDateField;
    @FXML
    private TextField genreField;
    @FXML
    private CheckBox availableCheckBox;

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";  // Change if necessary
    private static final String PASSWORD = "Preethi1002@";  // Change if necessary

    @FXML
    private void saveBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String publicationDate = (publicationDateField.getValue() != null) ? publicationDateField.getValue().toString() : null;
        String genre = genreField.getText();
        boolean available = availableCheckBox.isSelected();

        if (title.isEmpty() || author.isEmpty()) {
            showAlert("Title and Author are required!");
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO books (title, author, publication_date, genre, available) VALUES (?, ?, ?, ?, ?)")) {

            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, publicationDate);
            statement.setString(4, genre);
            statement.setBoolean(5, available);
            statement.executeUpdate();

            showAlert("Book added successfully!");
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error saving book!");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Library Management");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        titleField.clear();
        authorField.clear();
        publicationDateField.setValue(null);
        genreField.clear();
        availableCheckBox.setSelected(false);
    }
}
