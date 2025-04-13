module com.agencia.project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;
    requires static lombok;

    opens com.agencia.project to javafx.fxml;
    exports com.agencia.project;
    exports com.agencia.project.controller.client;
    opens com.agencia.project.controller.client to javafx.fxml;
}