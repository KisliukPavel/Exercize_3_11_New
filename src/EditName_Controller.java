import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class EditName_Controller {

	@FXML
	private Button Button_OK;

	@FXML
	private TextField TextField_OldName;

	@FXML
	private TextField TextField_NewName;

	//------------------------------------------------------------------
	private boolean cancelFlag;//определяет, были ли введены данные, или же из окна вышли и ничего добавлять не нужно
	//------------------------------------------------------------------

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@FXML
	void OnAction_OK(ActionEvent event) {
		cancelFlag = false;
		this.OnAction_Cancel(event);
	}

	@FXML
	void OnAction_Cancel(ActionEvent event) {
		event.consume();
		Stage stage = (Stage) this.Button_OK.getScene().getWindow();
		stage.close();
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	//------------------------------------------------------------------
	public String getNewName() //возвращаем данные в зависимости от того, был ли нажат Cancel или OK
	{
		if(this.cancelFlag){
			return this.TextField_OldName.getText();
		}
		else {
			return this.TextField_NewName.getText();
		}
	}

	public void setTextField_OldName(String oldName)
	{
		this.TextField_OldName.setText(oldName);
	}
	//------------------------------------------------------------------

	@FXML
	void initialize() {
		cancelFlag = true;
		this.TextField_NewName.textProperty().addListener((observable, oldValue, newValue) -> {
			if(0 == this.TextField_NewName.getText().length()) { //проверка на пустую строку
				Button_OK.setDisable(true);
			}
			else {
				Button_OK.setDisable(false);
			}
		});
	}

}
