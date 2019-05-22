package it.polito.tdp.porto;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
    	txtResult.clear();
    	txtResult.setText(""+model.coautori(boxPrimo.getValue().getId()));
    	
    	this.setComboBox2();
    }

    @FXML
    void handleSequenza(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
		this.setComboBox1();
		
	}
	private void setComboBox1() {
		boxPrimo.getItems().addAll(model.tuttiAutori());
		
	}
	private void setComboBox2() {
		if(boxPrimo.getValue()==null) {
			txtResult.setText("Selezionare un autore nel primo menu");
			return;
		}
		if(model.nonCoautori(boxPrimo.getValue().getId())== null)
			txtResult.setText("Non esistono autori che non siano coautori di quello selezionato");
		else
			boxSecondo.getItems().addAll(model.nonCoautori(boxPrimo.getValue().getId()));
	}
	
}
