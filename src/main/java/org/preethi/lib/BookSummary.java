package org.preethi.lib;

public class BookSummary {
    private String category;
    private int noOfBooks;
    private int specimens;
    private int purchased;
    private int totalBooks;
    private int totalAmount;
    private String tallyStatus;  // Add this

    // Constructor with tallyStatus
    public BookSummary(String category, int year, int noOfBooks, int specimens, int purchased, int totalBooks, int totalAmount) {
        this.category = category;
        this.noOfBooks = noOfBooks;
        this.specimens = specimens;
        this.purchased = purchased;
        this.totalBooks = totalBooks;
        this.totalAmount = totalAmount;
        this.tallyStatus = ""; // Default empty
    }

    // Overloaded constructor to include tallyStatus
    public BookSummary(String category, int accnNoFrom, int accnNoTo, int calculatedBooks, int actualTotalBooks, String tallyStatus) {
        this.category = category;
        this.noOfBooks = accnNoFrom;  // Using as accnNoFrom
        this.specimens = accnNoTo;    // Using as accnNoTo
        this.purchased = calculatedBooks;
        this.totalBooks = actualTotalBooks;
        this.totalAmount = 0;         // No monetary value, so setting to 0
        this.tallyStatus = tallyStatus;
    }

    public String getCategory() { return category; }
    public int getNoOfBooks() { return noOfBooks; }
    public int getSpecimens() { return specimens; }
    public int getPurchased() { return purchased; }
    public int getTotalBooks() { return totalBooks; }
    public int getTotalAmount() { return totalAmount; }
    public String getTallyStatus() { return tallyStatus; }  // Getter for tally status
}
