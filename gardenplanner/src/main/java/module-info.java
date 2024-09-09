module com.gardenplanner.gardenplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires java.desktop;

    opens com.gardenplanner.gardenplanner to javafx.fxml;
    exports com.gardenplanner.gardenplanner;
}