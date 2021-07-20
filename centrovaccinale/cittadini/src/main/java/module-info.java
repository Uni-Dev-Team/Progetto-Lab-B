module com.unidevteam.centrovaccinale.cittadini {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com.unidevteam.centrovaccinale.cittadini to javafx.fxml;
    exports com.unidevteam.centrovaccinale.cittadini;
}