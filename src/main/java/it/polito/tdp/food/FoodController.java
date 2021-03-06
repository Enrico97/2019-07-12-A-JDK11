/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Model;
import it.polito.tdp.food.model.Simulator;
import it.polito.tdp.food.model.vicini;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPorzioni"
    private TextField txtPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalorie"
    private Button btnCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="boxFood"
    private ComboBox<Food> boxFood; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	int x = 0;
    	txtResult.appendText("Creazione grafo...\n");
    	try {
    		x = Integer.parseInt(txtPorzioni.getText());
    	} catch (Exception e) {
    		txtResult.appendText("inserire un numero di porzioni valido");
    		return;
    	}
    	if (model.creaGrafo(x).vertexSet()==null) {
    		txtResult.appendText("inserire un numero di porzioni esistente");
    		return;
    		}
    	else {
    		txtResult.appendText("grafo creato con "+model.creaGrafo(x).vertexSet().size()+" vertici e "+model.creaGrafo(x).edgeSet().size()+" archi\n");
    		boxFood.getItems().addAll(model.creaGrafo(x).vertexSet());
    	}
    }
    
    @FXML
    void doCalorie(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Analisi calorie...\n");
    	for(vicini v : model.calorieMax(boxFood.getValue()))
    		txtResult.appendText(v.toString()+"\n");
    	
    }

    @FXML
    void doSimula(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Simulazione...\n");
    	int k=0;
    	try {
    		k = Integer.parseInt(txtK.getText());
    	} catch (NumberFormatException e) {
    		txtResult.appendText("Errore nell'inserimento di k");
    		return;
    	}
    	if (k<=10 && k>=1) {
    		model.simula(model.creaGrafo(Integer.parseInt(txtPorzioni.getText())), boxFood.getValue(), Integer.parseInt(txtPorzioni.getText()), k);
    		txtResult.appendText("sono stati preparati "+model.cibiPreparati()+" in "+model.tempoTotale()+" minuti");
    	}
    	else {
    		txtResult.appendText("k deve essere un numero intero compreso tra 1 e 10");
    		return;
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPorzioni != null : "fx:id=\"txtPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCalorie != null : "fx:id=\"btnCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxFood != null : "fx:id=\"boxFood\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
