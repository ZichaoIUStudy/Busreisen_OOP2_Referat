package iuinformatik.busreisen.busreisen_oop2_referat;

import iuinformatik.busreisen.busreisen_oop2_referat.database.DB;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view.fxml"));

        VBox menu = new VBox();
        Label description = new Label("Type the name of new table: ");
        TextField createInput = new TextField();
        createInput.setMaxWidth(100);

        Button submit = new Button("Add New Table");
        submit.setOnMouseClicked(mouseEvent -> {
            String name = String.valueOf(createInput.getText());
            try (var conn =  DB.connect()){
                System.out.println("_________Connected to the PostgreSQL database__________");
                try {
                    conn.setAutoCommit(false);
                    DB.create(conn, name);
                    conn.commit();
                } catch (SQLException e) {
                    conn.rollback();
                } finally {
                    conn.setAutoCommit(true);
                    conn.close();
                }

                System.out.println("_________Successfully create table in the database__________");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        });

        TextField dropInput = new TextField();
        dropInput.setMaxWidth(100);

        Button drop = new Button("Drop Table");
        drop.setOnMouseClicked(mouseEvent -> {
            String name = String.valueOf(dropInput.getText());
            try (var conn =  DB.connect()){
                System.out.println("_________Connected to the PostgreSQL database__________");
                try {
                    conn.setAutoCommit(false);
                    DB.drop(conn, name);
                    conn.commit();
                } catch (SQLException e) {
                    conn.rollback();
                } finally {
                    conn.setAutoCommit(true);
                    conn.close();
                }
                System.out.println("_________Successfully drop table in the database__________");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        });
        menu.getChildren().addAll(description, createInput, submit, dropInput, drop);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menu);


        Scene scene = new Scene(borderPane, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}