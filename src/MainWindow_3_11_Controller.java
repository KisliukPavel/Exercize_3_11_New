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
import javafx.scene.control.Button;
import java.io.IOException;

public class MainWindow_3_11_Controller {

	@FXML
	private Button Button_Edit;

	@FXML
	private Button Button_Delete;

	@FXML
	private TableView<Account> TableView_Table;

	@FXML
	private TableColumn<Account, String> TableColumn_Name;

	@FXML
	private TableColumn<Account, Double> TableColumn_Balance;

	//------------------------------------------------------------------
	private Stage mainStage; //данная сцена - главная
	private ObservableList<Account> accCollection = FXCollections.observableArrayList(); //тут храним аккаунты
	private NewAccount_Controller NA_Controller;
	private Edit_Controller E_Controller;
	private Delete_Controller D_Controller;
	//------------------------------------------------------------------

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@FXML
	void onMouseClicked_NewAccount(MouseEvent event) {
		//event.consume();
		startDialogueWindow("NewAccountDialogueWindow.fxml");
		if(!(NA_Controller.isCancelPressed())) {
			setNewAccount(NA_Controller.getNameTextField(), NA_Controller.getBalanceTextField());
		}
	}

	@FXML
	void onMouseClicked_Edit(MouseEvent event) {
		event.consume();
		startDialogueWindow("EditDialogueWindow.fxml");
		if(!E_Controller.isCancelPressed()) {
			TableView_Table.refresh();
		}
	}

	@FXML
	void onMouseClicked_Delete(MouseEvent event) {
		event.consume();
		startDialogueWindow("DeleteDialogueWindow.fxml");
		if(!(D_Controller.isCancelPressed())) {
			accCollection.remove(TableView_Table.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	void onMouseClicked_Exit(MouseEvent event) {
		event.consume();
		Stage stage = (Stage) TableView_Table.getScene().getWindow();
		stage.close();
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	//------------------------------------------------------------------
	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	private void startDialogueWindow(String FXMLFile)
	{
		try {
			//Stage adjustment
			//-----------------------------------------------
			Stage dialogueStage = new Stage();
			dialogueStage.setResizable(false);
			dialogueStage.sizeToScene();
			dialogueStage.setTitle(FXMLFile.substring(0, FXMLFile.length() - 5));
			dialogueStage.centerOnScreen();

			//FXML adjustment
			//-----------------------------------------------
			FXMLLoader fxmlLoaderDialogue = new FXMLLoader();
			fxmlLoaderDialogue.setLocation(getClass().getResource(FXMLFile));
			Parent fxmlDialogue = fxmlLoaderDialogue.load();

			switch (FXMLFile) {
				case "NewAccountDialogueWindow.fxml":
					NA_Controller = fxmlLoaderDialogue.getController();
					break;
				case "EditDialogueWindow.fxml":
					E_Controller = fxmlLoaderDialogue.getController();
					E_Controller.setMainStage(dialogueStage);
					E_Controller.setOldAccount(this.TableView_Table.getSelectionModel().getSelectedItem());
					break;
				case "DeleteDialogueWindow.fxml":
					D_Controller = fxmlLoaderDialogue.getController();
				default:
					break;
			}

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
			e.printStackTrace();
		}
	}

	private void setNewAccount(String name, String balance)
	{
		accCollection.add(new Account(name, Double.valueOf(balance)));
		TableView_Table.setItems(accCollection);


		TableColumn_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn_Balance.setCellValueFactory(new PropertyValueFactory<>("balance"));

	}
	//------------------------------------------------------------------

	@FXML
	void initialize() {
		TableView_Table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				Button_Edit.setDisable(false);
				Button_Delete.setDisable(false);
			}
			else {
				Button_Edit.setDisable(true);
				Button_Delete.setDisable(true);
			}
		});
	}
}