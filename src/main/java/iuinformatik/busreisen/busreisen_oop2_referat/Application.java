package iuinformatik.busreisen.busreisen_oop2_referat;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDB;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenMethoden;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables.BusseTable;
import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.tables.Table;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view.fxml"));

        VBox menu = new VBox();
        //Label description = new Label("Type the name of new table: ");

        Button create_default_table = new Button("create default table");
        create_default_table.setOnMouseClicked(mouseEvent -> {

            BusreisenMethoden.buildTableBase();

        });


        Button init_data = new Button("init data");
        init_data.setOnMouseClicked(mouseEvent -> {

            BusreisenMethoden.insertDefaultData();

        });

        Button select_data = new Button("select data");
        select_data.setOnMouseClicked(mouseEvent -> {

            BusreisenMethoden.selectData();

        });


        TextField dropInput = new TextField();
        dropInput.setMaxWidth(100);

        Button drop = new Button("Drop Table");


        menu.getChildren().addAll(create_default_table, init_data, select_data,
                dropInput, drop);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menu);

        Scene scene = new Scene(borderPane, 640, 320);
        stage.setTitle("Busreisen System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}