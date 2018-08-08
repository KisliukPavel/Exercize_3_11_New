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

	private Stage mainStage;

	private ObservableList<Account> accCollection = FXCollections.observableArrayList();

	private NewAccount_Controller NA_Controller;
	private Edit_Controller E_Controller;


	@FXML
	void onMouseClicked_NewAccount(MouseEvent event) {
		startDialogueWindow("NewAccountDialogueWindow.fxml");
		if(!(NA_Controller.isCancelPressed())) {
			setNewAccount(NA_Controller.getNameTextField(), NA_Controller.getBalanceTextField());
		}
	}

	@FXML
	void onMouseClicked_Edit(MouseEvent event) {
		startDialogueWindow("EditDialogueWindow.fxml");
		TableView_Table.refresh();
	}

	@FXML
	void onMouseClicked_Delete(MouseEvent event) {
		accCollection.remove((Account)TableView_Table.getSelectionModel().getSelectedItem());

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

			switch (FXMLFile) {
				case "NewAccountDialogueWindow.fxml":
					NA_Controller = fxmlLoaderDialogue.getController();
					break;
				case "EditDialogueWindow.fxml":
					E_Controller = fxmlLoaderDialogue.getController();
					E_Controller.setMainStage(dialogueStage);
					E_Controller.setOldAccount(this.TableView_Table.getSelectionModel().getSelectedItem());
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

		}
	}

	public void setNewAccount(String name, String balance)
	{
		accCollection.add(new Account(name, Double.valueOf(balance)));
		TableView_Table.setItems(accCollection);


		TableColumn_Name.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));
		TableColumn_Balance.setCellValueFactory(new PropertyValueFactory<Account, Double>("balance"));

	}

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