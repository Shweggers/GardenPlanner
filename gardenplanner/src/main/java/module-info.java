module com.gardenplanner.gardenplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires java.desktop;
    requires java.net.http;
    requires com.google.gson;
    requires java.naming;
    requires org.apache.commons.compress;

    opens com.gardenplanner.gardenplanner to javafx.fxml;
    exports com.gardenplanner.gardenplanner;
    exports com.gardenplanner.gardenplanner.controller;
    opens com.gardenplanner.gardenplanner.controller to javafx.fxml;
    exports com.gardenplanner.gardenplanner.model;
    opens com.gardenplanner.gardenplanner.model to javafx.fxml;
    exports com.gardenplanner.gardenplanner.model.DAO;
    opens com.gardenplanner.gardenplanner.model.DAO to javafx.fxml;
}