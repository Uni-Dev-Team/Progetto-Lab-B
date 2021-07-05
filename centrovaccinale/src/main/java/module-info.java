module com.unidevteam.centrovaccinale {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com.unidevteam.centrovaccinale to javafx.fxml;
    exports com.unidevteam.centrovaccinale;
}