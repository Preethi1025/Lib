package org.preethi.lib;

import javafx.beans.property.*;

public class ListBook {
    private final IntegerProperty no;
    private final IntegerProperty accNo;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty publisher;
    private final DoubleProperty classNo;
    private final StringProperty volume;
    private final StringProperty library;
    private final StringProperty status;
    private final StringProperty myUnknownColumn;

    // Constructor
    public ListBook(int no, int accNo, String title, String author, String publisher,
                    double classNo, String volume, String library, String status, String myUnknownColumn) {
        this.no = new SimpleIntegerProperty(no);
        this.accNo = new SimpleIntegerProperty(accNo);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.publisher = new SimpleStringProperty(publisher);
        this.classNo = new SimpleDoubleProperty(classNo);
        this.volume = new SimpleStringProperty(volume);
        this.library = new SimpleStringProperty(library);
        this.status = new SimpleStringProperty(status);
        this.myUnknownColumn = new SimpleStringProperty(myUnknownColumn);
    }

    // Getters
    public int getNo() { return no.get(); }
    public int getAccNo() { return accNo.get(); }
    public String getTitle() { return title.get(); }
    public String getAuthor() { return author.get(); }
    public String getPublisher() { return publisher.get(); }
    public double getClassNo() { return classNo.get(); }
    public String getVolume() { return volume.get(); }
    public String getLibrary() { return library.get(); }
    public String getStatus() { return status.get(); }
    public String getMyUnknownColumn() { return myUnknownColumn.get(); }

    // JavaFX Property Getters
    public IntegerProperty noProperty() { return no; }
    public IntegerProperty accNoProperty() { return accNo; }
    public StringProperty titleProperty() { return title; }
    public StringProperty authorProperty() { return author; }
    public StringProperty publisherProperty() { return publisher; }
    public DoubleProperty classNoProperty() { return classNo; }
    public StringProperty volumeProperty() { return volume; }
    public StringProperty libraryProperty() { return library; }
    public StringProperty statusProperty() { return status; }
    public StringProperty myUnknownColumnProperty() { return myUnknownColumn; }
}
