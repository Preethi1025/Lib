module org.preethi.lib {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.preethi.lib to javafx.fxml;
    exports org.preethi.lib;
}