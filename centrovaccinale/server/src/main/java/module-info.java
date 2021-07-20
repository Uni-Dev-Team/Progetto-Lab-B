module com.unidevteam.centrovaccinale.server {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com.unidevteam.centrovaccinale.server to javafx.fxml;
    exports com.unidevteam.centrovaccinale.server;
}