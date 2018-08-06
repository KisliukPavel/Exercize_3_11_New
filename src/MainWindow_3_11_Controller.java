import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow_3_11_Controller {

	@FXML
	private TableView<Account> TableView_Table;

	@FXML
	private TableColumn<Account, String> TableColumn_Name;

	@FXML
	private TableColumn<Account, Double> TableColumn_Balance;

	private Stage mainStage;

	private ObservableList<Account> accCollection = FXCollections.observableArrayList();

	private NewAccount_Controller NA_Controller;

	@FXML
	void onMouseClicked_NewAccount(MouseEvent event) {
		startDialogueWindow("NewAccountDialogueWindow.fxml");
		if(!(NA_Controller.isCancelPressed())) {
			setNewAccount(NA_Controller.getNameTextField(), NA_Controller.getBalanceTextField());
		}
	}

	@FXML
	void onMouseClicked_Edit(MouseEvent event) {

	}

	@FXML
	void onMouseClicked_Delete(MouseEvent event) {

	}

	@FXML
	void onMouseClicked_Exit(MouseEvent event) {
		Stage stage = (Stage) TableView_Table.getScene().getWindow();
		stage.close();
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public void startDialogueWindow(String FXMLFile)
	{
		try {
			//Stage adjustment
			//-----------------------------------------------
			Stage dialogueStage = new Stage();
			String title = FXMLFile;
			dialogueStage.setResizable(false);
			dialogueStage.sizeToScene();
			dialogueStage.setTitle(title);
			dialogueStage.centerOnScreen();

			//FXML adjustment
			//-----------------------------------------------
			FXMLLoader fxmlLoaderDialogue = new FXMLLoader();
			fxmlLoaderDialogue.setLocation(getClass().getResource(FXMLFile));
			Parent fxmlDialogue = fxmlLoaderDialogue.load();
			NA_Controller = fxmlLoaderDialogue.getController();

			//modality adjustment
			//-----------------------------------------------
			dialogueStage.initModality(Modality.WINDOW_MODAL);
			dialogueStage.initOwner(mainStage);

			//start-up window
			//-----------------------------------------------
			Scene SDialogue = new Scene(fxmlDialogue);
			dialogueStage.setScene(SDialogue);
			dialogueStage.showAndWait();

		}catch(IOException e) {

		}
	}

	public void setNewAccount(String name, String balance)
	{
		accCollection.add(new Account(name, Double.valueOf(balance)));
		TableView_Table.setItems(accCollection);


		TableColumn_Name.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));
		TableColumn_Balance.setCellValueFactory(new PropertyValueFactory<Account, Double>("balance"));

	}


}