package org.preethi.lib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
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
import java.util.List;
import java.util.stream.Collectors;

public class LibraryController {



    private ObservableList<Book> searchResults = FXCollections.observableArrayList();

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
private MenuButton semesterMenu, filterYearMenu, purchaseMenu, departmentMenu, supplierMenu;

    @FXML
    private Label selectedCriteriaLabel;

 // Ensure this is linked to your TableView





    @FXML
    private void handleSearch() {
        List<String> criteriaList = new ArrayList<>();
        List<String> conditions = new ArrayList<>();
        List<String> values = new ArrayList<>();

        // Update button text dynamically based on selected items
        updateMenuButtonText(semesterMenu);
        updateMenuButtonText(filterYearMenu);
        updateMenuButtonText(purchaseMenu);
        updateMenuButtonText(departmentMenu);
        updateMenuButtonText(supplierMenu);

        // Add selected criteria
        addSelectedCriteria(criteriaList, conditions, values, "Semester", semesterMenu, "semester");
        addSelectedCriteria(criteriaList, conditions, values, "Year", filterYearMenu, "year");
        addSelectedCriteria(criteriaList, conditions, values, "Purchase Type", purchaseMenu, "purchase_type");
        addSelectedCriteria(criteriaList, conditions, values, "Department", departmentMenu, "department_subject");
        addSelectedCriteria(criteriaList, conditions, values, "Book Supplier Name", supplierMenu, "name_of_the_book_supplier");

        selectedCriteriaLabel.setText(criteriaList.isEmpty() ? "None" : String.join(", ", criteriaList));

        if (conditions.isEmpty()) {
            showAlert("Please select at least one filter.");
            return;
        }

        // Construct the SQL query
        String query = "SELECT * FROM 2023_2024_data";
        if (!conditions.isEmpty()) {
            query += " WHERE " + String.join(" AND ", conditions);
        }

        searchResults.clear();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (int i = 0; i < values.size(); i++) {
                statement.setString(i + 1, values.get(i));
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                searchResults.add(new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("semester"),
                        resultSet.getString("engg_mba"),
                        resultSet.getInt("year"),
                        resultSet.getString("month"),
                        resultSet.getString("date_of_invoice"),
                        resultSet.getString("purchase_type"),
                        resultSet.getString("invoice_no"),
                        resultSet.getString("name_of_the_book_supplier"),
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

            booksTable.setItems(searchResults);
        } catch (SQLException e) {
            showAlert("Error fetching records: " + e.getMessage());
        }
    }

    private void addSelectedCriteria(List<String> criteriaList, List<String> conditions, List<String> values,
                                     String label, MenuButton menu, String dbColumn) {
        if (menu == null) {
            System.out.println("Error: " + label + " MenuButton is null!");
            return;
        }

        List<String> selected = getSelectedFromMenu(menu);
        if (!selected.isEmpty()) {
            criteriaList.add(label + ": " + String.join("/", selected));

            // Ensure SQL query placeholders match the number of selected values
            String placeholders = String.join(", ", Collections.nCopies(selected.size(), "?"));
            conditions.add(dbColumn + " IN (" + placeholders + ")");
            values.addAll(selected);
        }
    }

    private void updateMenuButtonText(MenuButton menuButton) {
        if (menuButton == null) {
            System.out.println("Error: MenuButton is null!");
            return;
        }

        List<String> selectedItems = getSelectedFromMenu(menuButton);
        if (selectedItems.isEmpty()) {
            menuButton.setText("Select " + menuButton.getId()); // Dynamic text
        } else {
            menuButton.setText(String.join(", ", selectedItems));
        }
    }

    private List<String> getSelectedFromMenu(MenuButton menu) {
        List<String> selected = new ArrayList<>();

        if (menu == null) {
            System.out.println("Error: MenuButton is null!");
            return selected;
        }

        for (MenuItem item : menu.getItems()) {
            if (item instanceof CheckMenuItem checkItem && checkItem.isSelected()) {
                selected.add(checkItem.getText());
            }
        }
        return selected;
    }



//    private void fetchBooks(String criteria, String value) {
//        String columnName = switch (criteria) {
//            case "Semester" -> "semester";
//            case "Year" -> "year";
//            case "Purchase Type" -> "purchase_type";
//            case "Invoice No" -> "invoice_no";
//            case "Book Supplier Name" -> "name_of_the_book_supplier";
//            case "Department Subject" -> "department_subject";
//            default -> throw new IllegalArgumentException("Invalid search criteria");
//        };
//
//        ObservableList<Book> booksList = FXCollections.observableArrayList();
//        String query = "SELECT * FROM 2023_2024_data WHERE " + columnName + " LIKE ?";
//
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setString(1, "%" + value + "%");
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                booksList.add(new Book(
//                        resultSet.getInt("id"),
//                        resultSet.getString("semester"),
//                        resultSet.getString("engg_mba"),
//                        resultSet.getInt("year"),
//                        resultSet.getString("month"),
//                        resultSet.getString("date_of_invoice"),
//                        resultSet.getString("purchase_type"),
//                        resultSet.getString("invoice_no"),
//                        resultSet.getString("name_of_the_book_supplier"), // New Field
//                        resultSet.getString("department_subject"),
//                        resultSet.getInt("book_accn_no_from"),
//                        resultSet.getInt("book_accn_no_to"),
//                        resultSet.getInt("no_of_books"),
//                        resultSet.getInt("no_of_books_purchased"),
//                        resultSet.getInt("no_of_books_donated"),
//                        resultSet.getString("acc_reg_no"),
//                        resultSet.getInt("accn_register_page_no_from"),
//                        resultSet.getInt("accn_register_page_no_to"),
//                        resultSet.getDouble("discount_percentage"),
//                        resultSet.getInt("gross_invoice_amount"),
//                        resultSet.getDouble("discount_amount"),
//                        resultSet.getInt("net_amount")
//                ));
//            }
//
//            booksTable.setItems(booksList);
//        } catch (SQLException e) {
//            showAlert("Error fetching records: " + e.getMessage());
//        }
//    }

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
    private MenuItem year2023, year2024, purchasedOption, donationOption,odd,even;

    @FXML
    private void handleYearSelection(ActionEvent event) {
        MenuItem selected = (MenuItem) event.getSource();
        String year = selected.getText();
        fetchBook("Year", year);
    }

    @FXML
    private void handleSemSelection(ActionEvent event) {
        MenuItem selected = (MenuItem) event.getSource();
        String semester = selected.getText(); // Get selected semester (ODD or EVEN)
        fetchBook("Sem", semester); // Pass the selected semester to fetchBook
    }
    @FXML
    private void handleAllYearsSelection(ActionEvent event) {
        System.out.println("All years selected");
        fetchBook("Sem", "All");
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
        String columnName;
        String query;

        if ("All".equals(value)) {
            // Fetch all records when "All" is selected (no WHERE condition)
            query = "SELECT engg_mba AS category, SUM(no_of_books) AS no_of_books, " +
                    "SUM(no_of_books_donated) AS specimens, SUM(no_of_books_purchased) AS purchased, " +
                    "SUM(no_of_books_purchased + no_of_books_donated) AS total_books, " +
                    "SUM(net_amount) AS total_amount " +
                    "FROM 2023_2024_data GROUP BY engg_mba";
        } else {
            // Map criteria to the corresponding column in the database
            columnName = switch (criteria) {
                case "Year" -> "year";
                case "Purchase Type" -> "purchase_type";
                case "Sem" -> "semester";
                default -> throw new IllegalArgumentException("Invalid criteria: " + criteria);
            };

            query = "SELECT engg_mba AS category, SUM(no_of_books) AS no_of_books, " +
                    "SUM(no_of_books_donated) AS specimens, SUM(no_of_books_purchased) AS purchased, " +
                    "SUM(no_of_books_purchased + no_of_books_donated) AS total_books, " +
                    "SUM(net_amount) AS total_amount " +
                    "FROM 2023_2024_data WHERE " + columnName + " = ? GROUP BY engg_mba";
        }

        // Query to fetch access number-wise book count
        String accessNumberQuery = "SELECT engg_mba AS category, " +
                "MIN(book_accn_no_from) AS accn_no_from, " +
                "MAX(book_accn_no_to) AS accn_no_to, " +
                "(MAX(book_accn_no_to) - MIN(book_accn_no_from) + 1) AS calculated_books " +
                "FROM 2023_2024_data GROUP BY engg_mba";

        // Additional query for time-based summary
        String timePeriodQuery = "SELECT " +
                "CASE " +
                "WHEN month BETWEEN 6 AND 12 AND year = 2023 THEN 'June to Dec 2023' " +
                "WHEN month BETWEEN 1 AND 5 AND year = 2024 THEN 'Jan to May 2024' " +
                "END AS category, " +
                "SUM(no_of_books) AS no_of_books, " +
                "SUM(no_of_books_donated) AS specimens, " +
                "SUM(no_of_books_purchased) AS purchased, " +
                "SUM(no_of_books_purchased + no_of_books_donated) AS total_books, " +
                "SUM(net_amount) AS total_amount " +
                "FROM 2023_2024_data " +
                "WHERE year IN (2023, 2024) " +
                "GROUP BY category " +
                "UNION ALL " +
                "SELECT 'Total' AS category, " +
                "SUM(no_of_books), SUM(no_of_books_donated), SUM(no_of_books_purchased), " +
                "SUM(no_of_books_purchased + no_of_books_donated), SUM(net_amount) " +
                "FROM 2023_2024_data WHERE year IN (2023, 2024);";

        ObservableList<BookSummary> summaryList = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement1 = connection.prepareStatement(query);
             PreparedStatement statement2 = connection.prepareStatement(timePeriodQuery);
             PreparedStatement statement3 = connection.prepareStatement(accessNumberQuery)) {

            // If not "All", set the parameter in the query
            if (!"All".equals(value)) {
                statement1.setString(1, value);
            }

            ResultSet resultSet1 = statement1.executeQuery();
            int totalNoOfBooks = 0, totalSpecimens = 0, totalPurchased = 0, totalBooks = 0, totalAmount = 0;

            // Process first query results (grouped by engg_mba)
            while (resultSet1.next()) {
                String category = resultSet1.getString("category");
                int noOfBooks = resultSet1.getInt("no_of_books");
                int specimens = resultSet1.getInt("specimens");
                int purchased = resultSet1.getInt("purchased");
                int totalBookCount = resultSet1.getInt("total_books");
                int totalAmt = resultSet1.getInt("total_amount");

                summaryList.add(new BookSummary(category, 0, noOfBooks, specimens, purchased, totalBookCount, totalAmt));

                // Aggregate total summary
                totalNoOfBooks += noOfBooks;
                totalSpecimens += specimens;
                totalPurchased += purchased;
                totalBooks += totalBookCount;
                totalAmount += totalAmt;
            }

            // Ensure first query results are included before adding totals
            if (!summaryList.isEmpty()) {
                summaryList.add(new BookSummary("Total Books", 0, totalNoOfBooks, totalSpecimens, totalPurchased, totalBooks, totalAmount));
            }

            ResultSet resultSet2 = statement2.executeQuery();
            boolean firstEntry = true;

            // Process second query results (grouped by time period)
            while (resultSet2.next()) {
                if (firstEntry) {
                    //summaryList.add(new BookSummary("", 0, 0, 0, 0, 0, 0)); // Blank row before "June to Dec 2023"
                    firstEntry = false;
                }

                String category = resultSet2.getString("category");
                int noOfBooks = resultSet2.getInt("no_of_books");
                int specimens = resultSet2.getInt("specimens");
                int purchased = resultSet2.getInt("purchased");
                int totalBookCount = resultSet2.getInt("total_books");
                int totalAmt = resultSet2.getInt("total_amount");

                summaryList.add(new BookSummary(category, 0, noOfBooks, specimens, purchased, totalBookCount, totalAmt));
            }

           // summaryList.add(new BookSummary("", , 0, 0, 0, 0, 0)); // Blank row after "Total"

            ResultSet resultSet3 = statement3.executeQuery();

            // Process access number-wise book calculation
            while (resultSet3.next()) {
                String category = resultSet3.getString("category");
                int accnNoFrom = resultSet3.getInt("accn_no_from");
                int accnNoTo = resultSet3.getInt("accn_no_to");
                int calculatedBooks = resultSet3.getInt("calculated_books");

                // Find the corresponding actual total_books value
                int actualTotalBooks = summaryList.stream()
                        .filter(book -> book.getCategory().equals(category))
                        .mapToInt(BookSummary::getTotalBooks)
                        .findFirst().orElse(0);

                String tallyStatus = (calculatedBooks == actualTotalBooks) ? "Tallied" : "Mismatch";

                summaryList.add(new BookSummary(category + " (Accn_no_from and Accn_no_to)", accnNoFrom, accnNoTo, calculatedBooks, actualTotalBooks, tallyStatus));
            }

            // Open new window and show summarized data
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


