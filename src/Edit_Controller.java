import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Edit_Controller {

	@FXML
	private Button Button_Cancel;

	private Account oldAccount;

	private Stage mainStage;
	private EditName_Controller EN_Controller;

	@FXML
	void onAction_Change(ActionEvent event) {
		startDialogueWindow("EditName.fxml");
		oldAccount.setName(EN_Controller.getNewName());
		this.onAction_Cancel(event);
	}

	@FXML
	void onAction_Deposit(ActionEvent event) {
		this.onAction_Cancel(event);
	}

	@FXML
	void onAction_Withdrawal(ActionEvent event) {
		this.onAction_Cancel(event);
	}

	@FXML
	void onAction_Cancel(ActionEvent event) {
		Stage stage = (Stage) this.Button_Cancel.getScene().getWindow();
		stage.close();
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public void setOldAccount(Account oldAccount)
	{
		this.oldAccount = oldAccount;
	}

	public Account getOldAccount()
	{
		return oldAccount;
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
				case "EditName.fxml":
					this.EN_Controller = fxmlLoaderDialogue.getController();
					EN_Controller.setTextField_OldName(oldAccount.getName());
					break;
				/*case "EditDialogueWindow.fxml":
					E_Controller = fxmlLoaderDialogue.getController();
					E_Controller.setMainStage(dialogueStage);
					break;*/
			}

			//modality adjustment
			//-----------------------------------------------
			dialogueStage.initModality(Modality.WINDOW_MODAL);
			dialogueStage.initOwner(this.mainStage);

			//start-up window
			//-----------------------------------------------
			Scene SDialogue = new Scene(fxmlDialogue);
			dialogueStage.setScene(SDialogue);
			dialogueStage.showAndWait();

		}catch(IOException e) {

		}
	}

}