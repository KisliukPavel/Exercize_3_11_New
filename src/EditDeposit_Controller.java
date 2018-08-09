import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class EditDeposit_Controller {

	@FXML
	private Button Button_OK;

	@FXML
	private TextField TextField_Deposit;

	//------------------------------------------------------------------
	private boolean cancelFlag;//определяет, были ли введены данные, или же из окна вышли и ничего добавлять не нужно
	//------------------------------------------------------------------

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@FXML
	void onAction_OK(ActionEvent event) {
		cancelFlag = false;
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
	public Double getDeposit()
	{
		if(cancelFlag){
			return 0.0;
		}
		else {
			return Double.valueOf(this.TextField_Deposit.getText());
		}
	}
	//------------------------------------------------------------------

	@FXML
	void initialize() {
		cancelFlag = true;
		TextField_Deposit.textProperty().addListener((observable, oldValue, newValue) -> {
			if(0 == newValue.length()) { //проверка на пустую строку
				this.Button_OK.setDisable(true);
				this.TextField_Deposit.setStyle("-fx-control-inner-background: white;");
			}
			else if(!(isDigit_TextField(newValue))) { //проверка на правильное значение
				this.Button_OK.setDisable(true);
				this.TextField_Deposit.setStyle("-fx-control-inner-background: pink;");
			}
			else { //когда всё верно
				this.Button_OK.setDisable(false);
				this.TextField_Deposit.setStyle("-fx-control-inner-background: white;");
			}
		});
	}

	private boolean isDigit_TextField (String text)
	{
		int countOfPeriods = 0;//количество точек
		char[] chArr = text.toCharArray();
		for(char ch : chArr) {
			if(!(Character.isDigit(ch))) {
				if(ch != '.') {
					return false;
				}
				else {
					countOfPeriods++;
				}
			}
		}
		//если количество точек больше 1 - это не число
		return (countOfPeriods <= 1);
	}
}
