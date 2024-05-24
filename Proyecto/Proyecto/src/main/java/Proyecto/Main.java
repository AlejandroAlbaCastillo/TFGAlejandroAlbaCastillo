/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Proyecto;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Molina
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        

        
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/vistaAdministrador1.fxml"));

        Scene scene = new Scene(root);
         //Cargar el archivo CSS
        
        //scene.getStylesheets().add(getClass().getResource("/estilos/inmotion.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Sportify");
        stage.show(); 
        
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
