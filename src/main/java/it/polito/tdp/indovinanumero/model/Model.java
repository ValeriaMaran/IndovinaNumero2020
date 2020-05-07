package it.polito.tdp.indovinanumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	//Classe model che deve essere messa in relazione con la vista dal controller, vado nel controller a richiamare il modello
	
	//dopo aver lavorato in entry point lavoro su model cercando di estrarre dal controller la logica portandola nel model, le variabili dello stato del gioco le 
	//riporto nel model
	

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco;
	private Set<Integer> tentativiInseriti;
	//dal momento che è una classe java model devo avere un costruttore: dove metto le operazioni che voglio fare con la new model:
	
	public Model() {
		this.inGioco = false;
		this.tentativiFatti = 0;
		//possiamo anche creare qua il segreto o direttamente quando inzia una nuova partita
	}
	//l'utente può fare due azioni: iniziare una nuova partita oppure fare un tentativo
	//avrò due metodi uno relativo a quando clicco il bottone nuova partita e uno relativo ai tentativi: 
	
	//METODO NUOVA PARTITA
	
	public void nuovaPartita() {
		//questo metodo fa quello previsto in doTentativo: trova il segreto imposta a 0 i tentativi e dice che siamo in gioco
	 	//gestione dell'inizio di una nuova partita - Logica del gioco
		this.tentativiInseriti = new HashSet<Integer>();
    	this.segreto = (int)(Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0;
    	this.inGioco = true; 
    	
	}
	//il controller usa questo metodo per fare un nuovo tentativo e deve sapere il risultato del metodo
	public int tentativo(int tentativo) {
		//controllo se la partita è in corso:
		if(inGioco != true) {
			throw new IllegalStateException("La partita è già terminata, non siamo più in gioco");
		}
		//controllo l'input vedendo se il tentativo messo dall'utente è un num valido, ma lo faccio già nel controller e va bene lì
		//siccome il metodo accetta solo int vedo se il numero messo è nel range giusto
		if(tentativoValido( tentativo) == false) {
			throw new InvalidParameterException("Devi inserire un numero non ancora utilizzato tra 1 e "+ NMAX+"\n");
		}
		//UNA VOLTA QUI SO CHE IL TENTATIVO è VALIDO DUNQUE POSSO PROCEDERE CON LA LOGICA QUINDI:
		//1-incremento i tentativi
		this.tentativiFatti ++;
		this.tentativiInseriti.add(tentativo);
		//se ho esaurito i tentativi chiudo la partita. ho tutta la logica nel modello.
		
		if(this.tentativiFatti==TMAX) {
			this.inGioco=false;
		}
		if(tentativo==this.segreto) {
			//HO INDOVINATO!
    		
			this.inGioco = false;
    		
			return 0;
		}
		if(tentativo<this.segreto) {
			return-1;
		}
		else
			return 1;
    	
		//se il tentativo è troppo alto return 1
		//se il tentativo troppo basso return -1
		//se il tentativo è giusto return 0
		
	}
	private boolean tentativoValido(int tentativo) {
		if(tentativo<1 || tentativo > NMAX) {
			return false;
		}
		else {
			if(this.tentativiInseriti.contains(tentativo)){
				return false;
			}
			return true;
		}
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
	
}
