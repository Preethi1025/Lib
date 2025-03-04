package org.preethi.lib;

public class Book {
    private int id;
    private String semester;
    private String enggMba;
    private int year;
    private String month;
    private String dateOfInvoice;
    private String purchaseType;
    private String invoiceNo;
    private String departmentSubject;
    private int bookAccnNoFrom;
    private int bookAccnNoTo;
    private int noOfBooks;
    private int noOfBooksPurchased;
    private int noOfBooksDonated;
    private String accRegNo;
    private int accnRegisterPageNoFrom;
    private int accnRegisterPageNoTo;
    private double discountPercentage;
    private double grossInvoiceAmount;
    private double discountAmount;
    private double netAmount;

    // Constructor
    public Book(int id, String semester, String enggMba, int year, String month, String dateOfInvoice,
                String purchaseType, String invoiceNo, String departmentSubject, int bookAccnNoFrom, int bookAccnNoTo,
                int noOfBooks, int noOfBooksPurchased, int noOfBooksDonated, String accRegNo, int accnRegisterPageNoFrom,
                int accnRegisterPageNoTo, double discountPercentage, double grossInvoiceAmount,
                double discountAmount, double netAmount) {
        this.id = id;
        this.semester = semester;
        this.enggMba = enggMba;
        this.year = year;
        this.month = month;
        this.dateOfInvoice = dateOfInvoice;
        this.purchaseType = purchaseType;
        this.invoiceNo = invoiceNo;
        this.departmentSubject = departmentSubject;
        this.bookAccnNoFrom = bookAccnNoFrom;
        this.bookAccnNoTo = bookAccnNoTo;
        this.noOfBooks = noOfBooks;
        this.noOfBooksPurchased = noOfBooksPurchased;
        this.noOfBooksDonated = noOfBooksDonated;
        this.accRegNo = accRegNo;
        this.accnRegisterPageNoFrom = accnRegisterPageNoFrom;
        this.accnRegisterPageNoTo = accnRegisterPageNoTo;
        this.discountPercentage = discountPercentage;
        this.grossInvoiceAmount = grossInvoiceAmount;
        this.discountAmount = discountAmount;
        this.netAmount = netAmount;
    }

    // Getters
    public int getId() { return id; }
    public String getSemester() { return semester; }
    public String getEnggMba() { return enggMba; }
    public int getYear() { return year; }
    public String getMonth() { return month; }
    public String getDateOfInvoice() { return dateOfInvoice; }
    public String getPurchaseType() { return purchaseType; }
    public String getInvoiceNo() { return invoiceNo; }
    public String getDepartmentSubject() { return departmentSubject; }
    public int getBookAccnNoFrom() { return bookAccnNoFrom; }
    public int getBookAccnNoTo() { return bookAccnNoTo; }
    public int getNoOfBooks() { return noOfBooks; }
    public int getNoOfBooksPurchased() { return noOfBooksPurchased; }
    public int getNoOfBooksDonated() { return noOfBooksDonated; }
    public String getAccRegNo() { return accRegNo; }
    public int getAccnRegisterPageNoFrom() { return accnRegisterPageNoFrom; }
    public int getAccnRegisterPageNoTo() { return accnRegisterPageNoTo; }
    public double getDiscountPercentage() { return discountPercentage; }
    public double getGrossInvoiceAmount() { return grossInvoiceAmount; }
    public double getDiscountAmount() { return discountAmount; }
    public double getNetAmount() { return netAmount; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setSemester(String semester) { this.semester = semester; }
    public void setEnggMba(String enggMba) { this.enggMba = enggMba; }
    public void setYear(int year) { this.year = year; }
    public void setMonth(String month) { this.month = month; }
    public void setDateOfInvoice(String dateOfInvoice) { this.dateOfInvoice = dateOfInvoice; }
    public void setPurchaseType(String purchaseType) { this.purchaseType = purchaseType; }
    public void setInvoiceNo(String invoiceNo) { this.invoiceNo = invoiceNo; }
    public void setDepartmentSubject(String departmentSubject) { this.departmentSubject = departmentSubject; }
    public void setBookAccnNoFrom(int bookAccnNoFrom) { this.bookAccnNoFrom = bookAccnNoFrom; }
    public void setBookAccnNoTo(int bookAccnNoTo) { this.bookAccnNoTo = bookAccnNoTo; }
    public void setNoOfBooks(int noOfBooks) { this.noOfBooks = noOfBooks; }
    public void setNoOfBooksPurchased(int noOfBooksPurchased) { this.noOfBooksPurchased = noOfBooksPurchased; }
    public void setNoOfBooksDonated(int noOfBooksDonated) { this.noOfBooksDonated = noOfBooksDonated; }
    public void setAccRegNo(String accRegNo) { this.accRegNo = accRegNo; }
    public void setAccnRegisterPageNoFrom(int accnRegisterPageNoFrom) { this.accnRegisterPageNoFrom = accnRegisterPageNoFrom; }
    public void setAccnRegisterPageNoTo(int accnRegisterPageNoTo) { this.accnRegisterPageNoTo = accnRegisterPageNoTo; }
    public void setDiscountPercentage(double discountPercentage) { this.discountPercentage = discountPercentage; }
    public void setGrossInvoiceAmount(double grossInvoiceAmount) { this.grossInvoiceAmount = grossInvoiceAmount; }
    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }
    public void setNetAmount(double netAmount) { this.netAmount = netAmount; }

    // toString method
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", semester='" + semester + '\'' +
                ", enggMba='" + enggMba + '\'' +
                ", year=" + year +
                ", month='" + month + '\'' +
                ", dateOfInvoice='" + dateOfInvoice + '\'' +
                ", purchaseType='" + purchaseType + '\'' +
                ", invoiceNo='" + invoiceNo + '\'' +
                ", departmentSubject='" + departmentSubject + '\'' +
                ", bookAccnNoFrom=" + bookAccnNoFrom +
                ", bookAccnNoTo=" + bookAccnNoTo +
                ", noOfBooks=" + noOfBooks +
                ", noOfBooksPurchased=" + noOfBooksPurchased +
                ", noOfBooksDonated=" + noOfBooksDonated +
                ", accRegNo='" + accRegNo + '\'' +
                ", accnRegisterPageNoFrom=" + accnRegisterPageNoFrom +
                ", accnRegisterPageNoTo=" + accnRegisterPageNoTo +
                ", discountPercentage=" + discountPercentage +
                ", grossInvoiceAmount=" + grossInvoiceAmount +
                ", discountAmount=" + discountAmount +
                ", netAmount=" + netAmount +
                '}';
    }
}
