import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class Delete_Controller {

	@FXML
	private Button Button_OK;

	//------------------------------------------------------------------
	private boolean cancelPressed = true; //определяет, был ли нажат Cancel
	//------------------------------------------------------------------

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@FXML
	void onAction_OK(ActionEvent event) {
		cancelPressed = false;
		this.onAction_Cancel(event);
	}

	@FXML
	void onAction_Cancel(ActionEvent event) {
		event.consume();
		Stage stage = (Stage) this.Button_OK.getScene().getWindow();
		stage.close();
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	//------------------------------------------------------------------
	public boolean isCancelPressed() {
		return cancelPressed;
	}
	//------------------------------------------------------------------
}