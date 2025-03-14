package org.preethi.lib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import java.util.*;

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
                "Semester", "Year", "Purchase Type", "Invoice No","Book Supplier Name", "Department Subject"
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
        //statementButton.setOnAction(event -> showStatement());

    }
    //    @FXML private TableView<Book> booksTable;
// Reference to TableView
    @FXML
    private MenuButton statementButton;

    @FXML
    private MenuButton statementMenuButton;
    @FXML
    private TableView<BookData> mainTable;  // This is the main book table in your UI




    private void showStatement() {
        Stage statementStage = new Stage();
        statementStage.setTitle("Library Book Statement");

        TableView<BookData> table = new TableView<>();

        // Define table columns
        TableColumn<BookData, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<BookData, Integer> booksCol = new TableColumn<>("No of Books");
        booksCol.setCellValueFactory(new PropertyValueFactory<>("books"));

        TableColumn<BookData, Integer> specimensCol = new TableColumn<>("Specimens");
        specimensCol.setCellValueFactory(new PropertyValueFactory<>("specimens"));

        TableColumn<BookData, Integer> purchasedCol = new TableColumn<>("Purchased");
        purchasedCol.setCellValueFactory(new PropertyValueFactory<>("purchased"));

        TableColumn<BookData, Integer> totalBooksCol = new TableColumn<>("Total Books");
        totalBooksCol.setCellValueFactory(new PropertyValueFactory<>("totalBooks"));

        TableColumn<BookData, Double> amountCol = new TableColumn<>("Total Amount");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        table.getColumns().addAll(categoryCol, booksCol, specimensCol, purchasedCol, totalBooksCol, amountCol);

        // Load data into table
        ObservableList<BookData> data = FXCollections.observableArrayList(
                new BookData("ENGINEERING", 457, 224, 233, 457, 307197),
                new BookData("MBA", 105, 105, 0, 105, 0),
                new BookData("Total", 562, 329, 233, 562, 307197)
        );

        table.setItems(data);

        VBox vbox = new VBox(table);
        Scene scene = new Scene(vbox, 800, 400);
        statementStage.setScene(scene);
        statementStage.show();
    }

    // Model Class for BookData
    public static class BookData {
        private final String category;
        private final int books;
        private final int specimens;
        private final int purchased;
        private final int totalBooks;
        private final double amount;

        public BookData(String category, int books, int specimens, int purchased, int totalBooks, double amount) {
            this.category = category;
            this.books = books;
            this.specimens = specimens;
            this.purchased = purchased;
            this.totalBooks = totalBooks;
            this.amount = amount;
        }

        public String getCategory() { return category; }
        public int getBooks() { return books; }
        public int getSpecimens() { return specimens; }
        public int getPurchased() { return purchased; }
        public int getTotalBooks() { return totalBooks; }
        public double getAmount() { return amount; }
    }
    @FXML
    private void handleGenerateReport() {
        // Create a new stage (popup window)
        Stage chartStage = new Stage();
        chartStage.setTitle("Books Data Visualization");

        // Create PieChart and BarChart
        PieChart pieChart = createPieChart();
        BarChart<String, Number> barChart = createBarChart();

        // Add charts to VBox
        VBox vbox = new VBox(20, pieChart, barChart);
        Scene scene = new Scene(vbox, 800, 600);

        chartStage.setScene(scene);
        chartStage.show();
    }

    private PieChart createPieChart() {
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Books by Department");

        // Get data from TableView
        ObservableList<Book> bookList = booksTable.getItems();

        // Count books per department
        Map<String, Integer> departmentCount = new HashMap<>();
        for (Book book : bookList) {
            departmentCount.put(book.getDepartmentSubject(),
                    departmentCount.getOrDefault(book.getDepartmentSubject(), 0) + 1);
        }

        // Add data to PieChart with tooltip
        for (Map.Entry<String, Integer> entry : departmentCount.entrySet()) {
            PieChart.Data slice = new PieChart.Data(entry.getKey(), entry.getValue());
            pieChart.getData().add(slice);

            // Create tooltip
            Tooltip tooltip = new Tooltip(entry.getKey() + ": " + entry.getValue() + " books");
            Tooltip.install(slice.getNode(), tooltip);

            // Update tooltip dynamically when data changes
            slice.nodeProperty().addListener((obs, oldNode, newNode) -> {
                if (newNode != null) {
                    Tooltip.install(newNode, tooltip);
                }
            });
        }

        return pieChart;
    }

    private BarChart<String, Number> createBarChart() {
        // Create axes
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Department");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("No. of Books");

        // Create BarChart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Books by Department");

        // Create Series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Books Count");

        // Get data from TableView
        ObservableList<Book> bookList = booksTable.getItems();

        // Count books per department
        Map<String, Integer> departmentCount = new HashMap<>();
        for (Book book : bookList) {
            departmentCount.put(book.getDepartmentSubject(),
                    departmentCount.getOrDefault(book.getDepartmentSubject(), 0) + 1);
        }

        // Add data to BarChart with tooltip
        for (Map.Entry<String, Integer> entry : departmentCount.entrySet()) {
            XYChart.Data<String, Number> dataPoint = new XYChart.Data<>(entry.getKey(), entry.getValue());
            series.getData().add(dataPoint);

            // Create tooltip
            Tooltip tooltip = new Tooltip(entry.getKey() + ": " + entry.getValue() + " books");

            // Add tooltip when hovering over bars
            dataPoint.nodeProperty().addListener((obs, oldNode, newNode) -> {
                if (newNode != null) {
                    Tooltip.install(newNode, tooltip);

                    // Highlight bar on hover
                    newNode.setOnMouseEntered(e -> newNode.setStyle("-fx-opacity: 0.7;"));
                    newNode.setOnMouseExited(e -> newNode.setStyle("-fx-opacity: 1;"));
                }
            });
        }

        barChart.getData().add(series);
        return barChart;
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
            case "Book Supplier Name" -> "name_of_the_book_supplier";
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


    @FXML
    private void openHtmlPage() {
        try {
            // Specify the path to your generated HTML file
            File htmlFile = new File("C:\\Users\\preet\\Downloads\\Lib1\\Lib1\\src\\main\\resources\\org\\preethi\\lib\\list_of_books.html"); // Change this path to match your HTML file location

            // Check if Desktop is supported
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(htmlFile.toURI()); // Open in default web browser
            } else {
                showAlerts("Desktop browsing is not supported on this system.");
            }
        } catch (IOException e) {
            showAlerts("Error opening the HTML file: " + e.getMessage());
        }
    }

    // Utility function to show alerts
    private void showAlerts(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private MenuItem year2023, year2024, purchasedOption, donationOption;

    @FXML
    private void handleYearSelection(ActionEvent event) {
        MenuItem selected = (MenuItem) event.getSource();
        String year = selected.getText();
        fetchBook("Year", year);
    }

    @FXML
    private void handlePurchaseSelection() {
        fetchBook("Purchase Type", "purchase");
    }

    @FXML
    private void handleDonationSelection() {
        fetchBook("Purchase Type", "donation");
    }

    private void fetchBook(String criteria, String value) {
        String columnName = switch (criteria) {
            case "Year" -> "year";
            case "Purchase Type" -> "purchase_type";
            default -> throw new IllegalArgumentException("Invalid criteria");
        };

        ObservableList<BookSummary> summaryList = FXCollections.observableArrayList();
        String query = "SELECT year, no_of_books, no_of_books_donated AS specimens, no_of_books_purchased AS purchased, (no_of_books_purchased + no_of_books_donated) AS total_books, net_amount AS total_amount FROM 2023_2024_data WHERE " + columnName + " = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                summaryList.add(new BookSummary(
                        resultSet.getInt("year"),
                        resultSet.getInt("no_of_books"),
                        resultSet.getInt("specimens"),
                        resultSet.getInt("purchased"),
                        resultSet.getInt("total_books"),
                        resultSet.getInt("total_amount")
                ));
            }

            // Open new window and show data
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/preethi/lib/book_summary.fxml"));
            Parent root = loader.load();

            BookSummaryController summaryController = loader.getController();
            summaryController.setSummaryData(summaryList);

            Stage stage = new Stage();
            stage.setTitle("Summary Report");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            showAlert("Error fetching records: " + e.getMessage());
        }
    }


}


