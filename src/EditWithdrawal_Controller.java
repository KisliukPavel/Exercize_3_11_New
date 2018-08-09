import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class EditWithdrawal_Controller {

	@FXML
	private Button Button_OK;

	@FXML
	private TextField TextField_CurrentBalance;

	@FXML
	private TextField TextField_Withdrawal;

	//------------------------------------------------------------------
	private boolean cancelFlag;//определяет, были ли введены данные, или же из окна вышли и ничего добавлять не нужно
	private Double oldBalance;
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
	void setOldBalance(Double oldBalance)
	{
		this.oldBalance = oldBalance;
	}

	public Double getWithdraw() //возвращаем данные в зависимости от того, был ли нажат Cancel или OK
	{
		if(this.cancelFlag){
			return 0.0;
		}
		else {
			return Double.valueOf(TextField_Withdrawal.getText());
		}
	}

	public void setTextField_CurrentBalance(String oldName)
	{
		this.TextField_CurrentBalance.setText(oldName);
	}
	//------------------------------------------------------------------

	@FXML
	void initialize() {
		cancelFlag = true;
		TextField_Withdrawal.textProperty().addListener((observable, oldValue, newValue) -> {
			if(0 == newValue.length()) { //проверка на пустую строку
				this.Button_OK.setDisable(true);
				this.TextField_Withdrawal.setStyle("-fx-control-inner-background: white;");
			}
			else if(!(isDigit_TextField(newValue))) { //проверка на правильное значение
				this.Button_OK.setDisable(true);
				this.TextField_Withdrawal.setStyle("-fx-control-inner-background: pink;");
			}
			else if(Double.valueOf(newValue).compareTo(oldBalance) > 0) { //проверка на превышение
				this.Button_OK.setDisable(true);
				this.TextField_Withdrawal.setStyle("-fx-control-inner-background: pink;");
			}
			else { //когда всё верно
				this.Button_OK.setDisable(false);
				this.TextField_Withdrawal.setStyle("-fx-control-inner-background: white;");
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
