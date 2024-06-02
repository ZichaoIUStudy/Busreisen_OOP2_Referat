module iuinformatik.busreisen.busreisen_oop2_referat {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires spring.beans;
    requires spring.jdbc;
    requires java.sql;
    requires org.yaml.snakeyaml;

    opens iuinformatik.busreisen.busreisen_oop2_referat to javafx.fxml;
    exports iuinformatik.busreisen.busreisen_oop2_referat;
    exports iuinformatik.busreisen.busreisen_oop2_referat.database;
    opens iuinformatik.busreisen.busreisen_oop2_referat.database to javafx.fxml;
}