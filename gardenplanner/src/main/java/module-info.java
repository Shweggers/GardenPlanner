module com.gardenplanner.gardenplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires java.desktop;
    requires java.net.http;
    requires com.google.gson;

    opens com.gardenplanner.gardenplanner to javafx.fxml;
    exports com.gardenplanner.gardenplanner;
}