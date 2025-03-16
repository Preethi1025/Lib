package org.preethi.lib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookSummaryController {
    @FXML
    private TableView<BookSummary> summaryTable;

    @FXML
    private TableColumn<BookSummary, String> categoryColumn;
//    @FXML
//    private TableColumn<BookSummary, Integer> yearColumn;
    @FXML
    private TableColumn<BookSummary, Integer> noOfBooksColumn;
    @FXML
    private TableColumn<BookSummary, Integer> specimensColumn;
    @FXML
    private TableColumn<BookSummary, Integer> purchasedColumn;
    @FXML
    private TableColumn<BookSummary, Integer> totalBooksColumn;
    @FXML
    private TableColumn<BookSummary, Integer> totalAmountColumn;

    private final ObservableList<BookSummary> summaryList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
//        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        noOfBooksColumn.setCellValueFactory(new PropertyValueFactory<>("noOfBooks"));
        specimensColumn.setCellValueFactory(new PropertyValueFactory<>("specimens"));
        purchasedColumn.setCellValueFactory(new PropertyValueFactory<>("purchased"));
        totalBooksColumn.setCellValueFactory(new PropertyValueFactory<>("totalBooks"));
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        summaryTable.setItems(summaryList);
    }

    public void setSummaryData(ObservableList<BookSummary> data) {
        summaryList.setAll(data);
    }
}
