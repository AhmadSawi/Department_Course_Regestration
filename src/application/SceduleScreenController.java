package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SceduleScreenController implements Initializable{

	Data data = null;
	
    @FXML Button exit;
    @FXML TextField generationsTF;
    @FXML TextField showTF;
    @FXML TextField hardTF;
    @FXML TextField softTF;
    
    @FXML TableView<TableClass> table;
    @FXML TableColumn<TableClass, String> classIDCol;
	@FXML TableColumn<TableClass, String> instCol;
	@FXML TableColumn<TableClass, String> courseCol;
	@FXML TableColumn<TableClass, String> sectionCol;
	@FXML TableColumn<TableClass, String> timeCol;
	@FXML TableColumn<TableClass, String> roomCol;
    
	ObservableList<TableClass> tableData = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		classIDCol.setCellValueFactory(new PropertyValueFactory<TableClass, String>("classID"));
		instCol.setCellValueFactory(new PropertyValueFactory<TableClass, String>("instructorName"));
		courseCol.setCellValueFactory(new PropertyValueFactory<TableClass, String>("CourseID"));
		sectionCol.setCellValueFactory(new PropertyValueFactory<TableClass, String>("section"));
		timeCol.setCellValueFactory(new PropertyValueFactory<TableClass, String>("timeSlot"));
		roomCol.setCellValueFactory(new PropertyValueFactory<TableClass, String>("Room"));
		
		try {
			data = new Data();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exitPressed(ActionEvent e) throws IOException{
		Stage stage = (Stage) exit.getScene().getWindow();
		stage.close();
	}
	
		
	public void runPressed(ActionEvent e) throws IOException{
		tableData.clear();
		ArrayList<TableClass> last = Main.lastScheduel(data, Integer.parseInt(generationsTF.getText()));
		for(int i=0; i < last.size(); i++)
			tableData.add(last.get(i));

		table.setItems(tableData);
		
		hardTF.setText("" + Main.hardFitness);
		softTF.setText("" + Main.softFitness);
	}
	
	public void showPressed(ActionEvent e) throws IOException{ 
		
		if(tableData.isEmpty()){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("No DATA");
			alert.setHeaderText(null);
			alert.setContentText("No Schedules have been generated!");
			alert.showAndWait();
		}
		
		if(showTF.getText().equals("")){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Generation");
			alert.setHeaderText(null);
			alert.setContentText("Eneter Generation to show schedule for!");
			alert.showAndWait();
		}
		
		if(showTF.getText() != null && !tableData.isEmpty()){
			tableData.clear();
			int gen = Integer.parseInt(showTF.getText());
//			for(int i=0; i < Main.generationData.get(gen).getSolution().size(); i++)
//				tableData.add(Main.generationData.get(gen).getSolution().get(i));
			
			tableData.addAll(Main.generationData.get(gen).getSolution());
			hardTF.setText("" + Main.generationData.get(gen).hardFitness);
			softTF.setText("" + Main.generationData.get(gen).softFitness);
			table.setItems(tableData);
			System.out.println("----------------- SHOWING " + Main.generationData.get(gen).generationID + "-----------------");
		}
			
	}
	
}
