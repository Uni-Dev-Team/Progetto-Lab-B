module com.unidevteam.centrovaccinale.centrovaccinale {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com.unidevteam.centrovaccinale.centrovaccinale to javafx.fxml;
    exports com.unidevteam.centrovaccinale.centrovaccinale;
}