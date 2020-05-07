package it.polito.tdp.indovinanumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.indovinanumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private Model model;
	//aver dichiarato la variabile non vuol dire avere il modello, il modello viene creato nella classe entry point che ha i metodi chiamati con il run
	
	//DEVO USARE MODEL PER FARE IN MODO CHE IL MIO CONTROLLER USI LA LOGICA DEL GIOCO CHE HO IN MODEL
	
	
	/*private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;*/

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnNuova;

    @FXML
    private TextField txtRimasti;

    @FXML
    private HBox layoutTentativo;

    @FXML
    private TextField txtTentativi;

    @FXML
    private Button btnProva;

    @FXML
    void doNuova(ActionEvent event) {
    	//comunico al modello che sto iniziando una nuova partita:
    	this.model.nuovaPartita();
    	//gestione dell'interfaccia
    	layoutTentativo.setDisable(false);
    	txtRisultato.clear();
    	txtRimasti.setText(Integer.toString(model.getTMAX()));

    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//leggere l'input dell'utente
    	String ts = txtTentativi.getText();
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	} catch (NumberFormatException e) {
    		txtRisultato.appendText("Devi inserire un numero!\n");
    		return;
    	}
    	//passo il tentativo dell'utente estraggo il ris e controllo:
    	int risultato = -1;
    	try {
    		risultato = this.model.tentativo(tentativo);
    	}
    	catch (IllegalStateException se) {
    		txtRisultato.appendText(se.getMessage());
    		return;
    	}
    	catch(InvalidParameterException pe) {
    		txtRisultato.appendText(pe.getMessage());
    		return;
    	}
    
    	if(risultato == 0) {
    		txtRisultato.appendText("HAI VINTO!!! in "+ model.getTentativiFatti()+"tentativi \n");
    		layoutTentativo.setDisable(true);
    	}
    	
    	if(risultato == -1) {
    		txtRisultato.appendText("Tentativo troppo BASSO\n");
    	}
    	if(risultato==1) {
    		txtRisultato.appendText("Tentativo troppo ALTO\n");
        	
    	}
    	
    	if(model.getTentativiFatti() == model.getTMAX()) {
    		//Ho esaurito i tentativi -> HO PERSO
    		txtRisultato.appendText("HAI PERSO!!! Il numero segreto era: " + model.getSegreto());
    		layoutTentativo.setDisable(true);
    		return;
    	}
    	
    	//informare l'utente se il tentativo è troppo alto o troppo basso
    	
    	txtRimasti.setText(Integer.toString((model.getTMAX())-(model.getTentativiFatti())));
    }

    @FXML
    void initialize() {
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert layoutTentativo != null : "fx:id=\"layoutTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        
        //this.model = new Model(); //funziona ma è rischioso, perchè ho relazione tra controller e modello, il controller userà il model della classe model e non un altro
        //ma io voglio tenere i concetti separati e cambiare model, senza cambiare controller, non creo qua il model ma all'esterno, poi verrà assegnato al controller con un 
        //metodo public set model

    }
    
    //metto il set model
    public void setModel(Model model) {
    	this.model = model;
    }
}
