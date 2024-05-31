package iuinformatik.busreisen.busreisen_oop2_referat;

import iuinformatik.busreisen.busreisen_oop2_referat.database.busreisendb.BusreisenDBVerwaltung;
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

            BusreisenDBVerwaltung.buildTableBase();

        });


        Button init_data = new Button("init data");
        init_data.setOnMouseClicked(mouseEvent -> {

            BusreisenDBVerwaltung.insertDefaultData();

        });

        TextField vorname = new TextField();
        vorname.setPromptText("Vorname");
        vorname.setMaxWidth(80);
        TextField name = new TextField();
        name.setPromptText("Name");
        name.setMaxWidth(80);

        Button get_driver_id = new Button("get driver id");
        get_driver_id.setOnMouseClicked(mouseEvent -> {

            BusreisenVerwaltung.showDriverId(vorname.getText(), name.getText());

        });

        menu.getChildren().addAll(create_default_table, init_data,
                vorname, name, get_driver_id);

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