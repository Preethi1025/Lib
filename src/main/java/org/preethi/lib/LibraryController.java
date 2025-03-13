package org.preethi.lib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private TableColumn<Book, String> semesterColumn;
    @FXML
    private TableColumn<Book, String> enggMbaColumn;
    @FXML
    private TableColumn<Book, Integer> yearColumn;
    @FXML
    private TableColumn<Book, Integer> monthColumn;
    @FXML
    private TableColumn<Book, String> dateOfInvoiceColumn;
    @FXML
    private TableColumn<Book, String> purchaseTypeColumn;
    @FXML
    private TableColumn<Book, String> invoiceNoColumn;
    @FXML
    private TableColumn<Book, String> nameOfTheBookSupplierColumn; // New Column
    @FXML
    private TableColumn<Book, String> departmentSubjectColumn;
    @FXML
    private TableColumn<Book, Integer> bookAccnNoFromColumn;
    @FXML
    private TableColumn<Book, Integer> bookAccnNoToColumn;
    @FXML
    private TableColumn<Book, Integer> noOfBooksColumn;
    @FXML
    private TableColumn<Book, Integer> noOfBooksPurchasedColumn;
    @FXML
    private TableColumn<Book, Integer> noOfBooksDonatedColumn;
    @FXML
    private TableColumn<Book, String> accRegNoColumn;
    @FXML
    private TableColumn<Book, Integer> accnRegisterPageNoFromColumn;
    @FXML
    private TableColumn<Book, Integer> accnRegisterPageNoToColumn;
    @FXML
    private TableColumn<Book, Double> discountPercentageColumn;
    @FXML
    private TableColumn<Book, Integer> grossInvoiceAmountColumn;
    @FXML
    private TableColumn<Book, Double> discountAmountColumn;
    @FXML
    private TableColumn<Book, Integer> netAmountColumn;

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "Preethi1002@";

    @FXML
    public void initialize() {
        searchCriteriaBox.setItems(FXCollections.observableArrayList(
                "Semester", "Year", "Purchase Type", "Invoice No", "Department Subject"
        ));

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        enggMbaColumn.setCellValueFactory(new PropertyValueFactory<>("enggMba"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        dateOfInvoiceColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfInvoice"));
        purchaseTypeColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseType"));
        invoiceNoColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        nameOfTheBookSupplierColumn.setCellValueFactory(new PropertyValueFactory<>("nameOfTheBookSupplier")); // New Column
        departmentSubjectColumn.setCellValueFactory(new PropertyValueFactory<>("departmentSubject"));
        bookAccnNoFromColumn.setCellValueFactory(new PropertyValueFactory<>("bookAccnNoFrom"));
        bookAccnNoToColumn.setCellValueFactory(new PropertyValueFactory<>("bookAccnNoTo"));
        noOfBooksColumn.setCellValueFactory(new PropertyValueFactory<>("noOfBooks"));
        noOfBooksPurchasedColumn.setCellValueFactory(new PropertyValueFactory<>("noOfBooksPurchased"));
        noOfBooksDonatedColumn.setCellValueFactory(new PropertyValueFactory<>("noOfBooksDonated"));
        accRegNoColumn.setCellValueFactory(new PropertyValueFactory<>("accRegNo"));
        accnRegisterPageNoFromColumn.setCellValueFactory(new PropertyValueFactory<>("accnRegisterPageNoFrom"));
        accnRegisterPageNoToColumn.setCellValueFactory(new PropertyValueFactory<>("accnRegisterPageNoTo"));
        discountPercentageColumn.setCellValueFactory(new PropertyValueFactory<>("discountPercentage"));
        grossInvoiceAmountColumn.setCellValueFactory(new PropertyValueFactory<>("grossInvoiceAmount"));
        discountAmountColumn.setCellValueFactory(new PropertyValueFactory<>("discountAmount"));
        netAmountColumn.setCellValueFactory(new PropertyValueFactory<>("netAmount"));
    }

    @FXML
    private void handleSearch() {
        String selectedCriteria = searchCriteriaBox.getValue();
        if (selectedCriteria == null) {
            showAlert("Please select a search criteria.");
            return;
        }

        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Search");
        inputDialog.setHeaderText("Enter " + selectedCriteria + " to search:");
        inputDialog.setContentText(selectedCriteria + ":");
        inputDialog.showAndWait().ifPresent(input -> fetchBooks(selectedCriteria, input));
    }

    private void fetchBooks(String criteria, String value) {
        String columnName = switch (criteria) {
            case "Semester" -> "semester";
            case "Year" -> "year";
            case "Purchase Type" -> "purchase_type";
            case "Invoice No" -> "invoice_no";
            case "Department Subject" -> "department_subject";
            default -> throw new IllegalArgumentException("Invalid search criteria");
        };

        ObservableList<Book> booksList = FXCollections.observableArrayList();
        String query = "SELECT * FROM 2023_2024_data WHERE " + columnName + " LIKE ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + value + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                booksList.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("semester"),
                        resultSet.getString("engg_mba"),
                        resultSet.getInt("year"),
                        resultSet.getString("month"),
                        resultSet.getString("date_of_invoice"),
                        resultSet.getString("purchase_type"),
                        resultSet.getString("invoice_no"),
                        resultSet.getString("name_of_the_book_supplier"), // New Field
                        resultSet.getString("department_subject"),
                        resultSet.getInt("book_accn_no_from"),
                        resultSet.getInt("book_accn_no_to"),
                        resultSet.getInt("no_of_books"),
                        resultSet.getInt("no_of_books_purchased"),
                        resultSet.getInt("no_of_books_donated"),
                        resultSet.getString("acc_reg_no"),
                        resultSet.getInt("accn_register_page_no_from"),
                        resultSet.getInt("accn_register_page_no_to"),
                        resultSet.getDouble("discount_percentage"),
                        resultSet.getInt("gross_invoice_amount"),
                        resultSet.getDouble("discount_amount"),
                        resultSet.getInt("net_amount")
                ));
            }

            booksTable.setItems(booksList);
        } catch (SQLException e) {
            showAlert("Error fetching records: " + e.getMessage());
        }
    }

    @FXML
    private void openNewForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/preethi/lib/new-book-form.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Print full error details in the console
            showError("Error opening form: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Library Management");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Library Management");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
