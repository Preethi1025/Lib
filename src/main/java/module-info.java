module org.preethi.lib {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires kernel;
    requires layout;


    opens org.preethi.lib to javafx.fxml;
    exports org.preethi.lib;
}