module iuinformatik.busreisen.busreisen_oop2_referat {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires spring.beans;
    requires spring.jdbc;
    requires java.sql;

    opens iuinformatik.busreisen.busreisen_oop2_referat to javafx.fxml;
    exports iuinformatik.busreisen.busreisen_oop2_referat;
}