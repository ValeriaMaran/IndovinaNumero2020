package it.polito.tdp.indovinanumero;

import javafx.application.Application;
import static javafx.application.Application.launch;

import it.polito.tdp.indovinanumero.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {
	
    @Override
    public void start(Stage stage) throws Exception {
    	//modifico questa classe dopo aver creato il model facendo così:
    	//1- DEVO RECUPERARE UN RIFERIMENTO AL CONTROLLER 
    	Model model = new Model();
    	//2- AVERE IL CONTROLLER E QUI FARE IL SET MODEL 
    	FXMLController controller;
    	//3- RECUPERO UN RIFERIMENTO AL CONTROLLER
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));//METTO NELLA PARENTESI QUELLO CHE AVEVO IN Parent root
    	// Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
    	// 4- LA RADICE DELL'INTERFACCIA ORA CHE HO RIF AL LOADER LA OTTENGO DAL LOADER:
    	Parent root = loader.load();
    	
    	//5-CREO LA SCENE PRIMA DI OTTENERE IL CONTROLLER
    	
    	Scene scene = new Scene(root);
    	
    	//6-DAL LOADER RECUPERO IL CONTROLLER.
    	controller = loader.getController();
    	
    	//7- AVENDO IL CONTROLLER POSSO FARE SET MODEL 
    	controller.setModel(model);
    	
    	//8- CREIAMO UNA SCENA A PARTIRE DALLA RADICE:
    	
        //scene.getStylesheets().add("/styles/Styles.css"); // non usandolo possiamo  anche cancellare il css
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
