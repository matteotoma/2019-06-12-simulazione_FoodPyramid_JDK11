package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.model.Condiment;
import it.polito.tdp.food.model.Ingrediente;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtCalorie;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<Condiment> boxIngrediente;

    @FXML
    private Button btnDietaEquilibrata;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaDieta(ActionEvent event) {
    	txtResult.clear();
    	Condiment c = boxIngrediente.getValue();
    	if(c == null) {
    		txtResult.appendText("Devi selezionare un condimento!\n");
    		return;
    	}
    	List<Condiment> list = model.trovaDieta(c);
    	txtResult.appendText("Calorie tot: "+model.getMax()+"\n");
    	for(Condiment temp: list)
    		txtResult.appendText(String.format("%d\n", temp.getCondiment_id()));
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	Double c;
    	try {
    		c = Double.parseDouble(this.txtCalorie.getText());
    	} catch(NumberFormatException e) {
    		txtResult.appendText("Devi inserire un numero!\n");
    		return;
    	}
    	model.creaGrafo(c);
    	txtResult.appendText(String.format("Grafo creato con %d vertici e %d archi!\n", model.nVertici(), model.nArchi()));
    	txtResult.appendText("\n");
    	for(Ingrediente d: model.getIngredienti())
    		txtResult.appendText(String.format("%d %f %d \n", d.getC().getCondiment_id(), d.getC().getCondiment_calories(), d.getPeso()));
    	this.boxIngrediente.getItems().addAll(model.getVertici());
    }

    @FXML
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxIngrediente != null : "fx:id=\"boxIngrediente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDietaEquilibrata != null : "fx:id=\"btnDietaEquilibrata\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
	}
}
