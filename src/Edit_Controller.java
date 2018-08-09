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
	//------------------------------------------------------------------
	private Stage mainStage;
	private EditName_Controller EN_Controller;
	private EditDeposit_Controller ED_Controller;
	private EditWithdrawal_Controller EW_Controller;
	private boolean cancelPressed = true; //определяет, был ли нажат Cancel
	//------------------------------------------------------------------

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@FXML
	void onAction_Change(ActionEvent event) {
		cancelPressed = false;
		startDialogueWindow("EditName.fxml");
		this.oldAccount.setName(EN_Controller.getNewName());
		this.onAction_Cancel(event);
	}

	@FXML
	void onAction_Deposit(ActionEvent event) {
		cancelPressed = false;
		startDialogueWindow("EditDeposit.fxml");
		this.oldAccount.deposit(this.ED_Controller.getDeposit());
		this.onAction_Cancel(event);
	}

	@FXML
	void onAction_Withdrawal(ActionEvent event) {
		cancelPressed = false;
		startDialogueWindow("EditWithdrawal.fxml");
		this.oldAccount.withdraw(EW_Controller.getWithdraw());
		this.onAction_Cancel(event);
	}

	@FXML
	void onAction_Cancel(ActionEvent event) {
		event.consume();
		Stage stage = (Stage) this.Button_Cancel.getScene().getWindow();
		stage.close();
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	//------------------------------------------------------------------
	void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	void setOldAccount(Account oldAccount)
	{
		this.oldAccount = oldAccount;
	}

	boolean isCancelPressed()
	{
		return cancelPressed;
	}

	private void startDialogueWindow(String FXMLFile)
	{
		try {
			//Stage adjustment
			//-----------------------------------------------
			Stage dialogueStage = new Stage();
			dialogueStage.setResizable(false);
			dialogueStage.sizeToScene();
			dialogueStage.setTitle(FXMLFile);
			dialogueStage.centerOnScreen();

			//FXML adjustment
			//-----------------------------------------------
			FXMLLoader fxmlLoaderDialogue = new FXMLLoader();
			fxmlLoaderDialogue.setLocation(getClass().getResource(FXMLFile));
			Parent fxmlDialogue = fxmlLoaderDialogue.load();

			switch (FXMLFile) {
				case "EditName.fxml":
					this.EN_Controller = fxmlLoaderDialogue.getController();
					this.EN_Controller.setTextField_OldName(oldAccount.getName());
					break;
				case "EditDeposit.fxml":
					this.ED_Controller = fxmlLoaderDialogue.getController();
					break;
				case "EditWithdrawal.fxml":
					this.EW_Controller = fxmlLoaderDialogue.getController();
					this.EW_Controller.setOldBalance(this.oldAccount.getBalance());
					this.EW_Controller.setTextField_CurrentBalance(this.oldAccount.getBalance().toString());
					break;
				default:
					break;
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
			e.printStackTrace();
		}
	}
	//------------------------------------------------------------------
}