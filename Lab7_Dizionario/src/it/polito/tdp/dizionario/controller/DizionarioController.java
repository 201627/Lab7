package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.DizionarioGrafo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

	int length;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtNumLettere;

	@FXML
	private TextField txtParola;
	@FXML
	private TextField txtParolaArrivo;
	@FXML
	private Button btnGenera;

	@FXML
	private Button btnTrovaVicini;

	@FXML
	private Button btnTrovaConnessi;

	@FXML
	private TextArea txtRisposta;

	private DizionarioGrafo model;

	@FXML
	void doTrovaCammino(ActionEvent event) {
		
		String s1 = txtParola.getText();
		String s2 = txtParolaArrivo.getText();
		
		if(!model.isInGraph(s1) || !model.isInGraph(s2) || s1 == null || s2 == null){
			txtRisposta.setText("Errore: formato dati errato!!");
		}else{
			List<String> cammino = model.getCammino(s1,s2);
			
			txtRisposta.appendText("****"+ s1 +"---->"+ s2 + "**** passi: " + cammino.size() + "\n");
			
			for(String s : cammino){
				txtRisposta.appendText(" " + s);
			}
			
		}
		
		
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {

		// model.generaGrafo

		try {
			length = Integer.parseInt(txtNumLettere.getText());

		} catch (Exception e) {
			txtRisposta.setText("Errore: formato dati errato!!");
			return;
		}

		model.generaGrafo(length);

		// abilita i pulsanti

		btnTrovaVicini.setDisable(false);
		btnTrovaConnessi.setDisable(false);

		txtRisposta.setText("Grafo generato\n");
	}

	@FXML
	void doReset(ActionEvent event) {

		txtNumLettere.clear();
		txtParola.clear();
		btnTrovaVicini.setDisable(true);
		btnTrovaConnessi.setDisable(true);
		btnGenera.setDisable(false);
		txtRisposta.clear();
	}

	@FXML
	void doTrovaConnessi(ActionEvent event) {
		String parola = txtParola.getText();

		if (parola.length() != length) {
			txtRisposta.setText("Errore: la parola deve essere lunga" + length + "lettere!!");

		} else if (model.isInGraph(parola) == false)
			txtRisposta.setText("Errore: la parola non è nel dizionario!!");
		else {
			List<String> res = model.trovaConnessi(parola);

			for (String s : res) {
				txtRisposta.appendText(s + "\n");
			}
		}
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {

		// System.out.println("Ho premuto Trova Vicini");

		String parola = txtParola.getText();

		if (parola.length() != length) {
			txtRisposta.setText("Errore: la parola deve essere lunga" + length + "lettere!!");

		} else if (model.isInGraph(parola) == false)
			txtRisposta.setText("Errore: la parola non è nel dizionario!!");
		else {
			List<String> res = model.trovaVicini(parola);

			for (String s : res) {
				txtRisposta.appendText(s + "\n");
			}

		}

	}

	@FXML
	void initialize() {
		assert txtNumLettere != null : "fx:id=\"txtNumLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGenera != null : "fx:id=\"btnGenera\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaConnessi != null : "fx:id=\"btnTrovaConnessi\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert txtRisposta != null : "fx:id=\"txtRisposta\" was not injected: check your FXML file 'Dizionario.fxml'.";

	}

	public void setModel(DizionarioGrafo model) {
		// TODO Auto-generated method stub
		this.model = model;
	}
}
