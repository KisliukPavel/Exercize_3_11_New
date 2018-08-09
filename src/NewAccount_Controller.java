import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class NewAccount_Controller {

	@FXML
	private TextField textField_Name;

	@FXML
	private TextField textField_Balance;

	@FXML
	private Button Button_OK;
	//------------------------------------------------------------------
	private boolean cancelPressed; //определяет, были ли введены данные, или же из окна вышли и ничего добавлять не нужно
	private int nameLengthCapacity; //определяет длинну строки в поле name
	private int balanceLengthCapacity; //определяет длинну строки в поле balance
	private int flagForBlockOKButton; //переменная для определения необходимости делать Button_OK disable
	//------------------------------------------------------------------

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	@FXML
	void onAction_Cancel(ActionEvent  event) {
		event.consume();
		Stage stage = (Stage) textField_Name.getScene().getWindow();
		stage.close();
	}

	@FXML
	void onAction_OK(ActionEvent  event) {
		cancelPressed = false;
		onAction_Cancel(event);
	}
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	//------------------------------------------------------------------
	public String getNameTextField()
	{
		return textField_Name.getText();
	}

	public String getBalanceTextField()
	{
		return textField_Balance.getText();
	}

	boolean isCancelPressed()
	{
		return cancelPressed;
	}
	//------------------------------------------------------------------

	@FXML
	void initialize() {
		this.cancelPressed = true;
		this.nameLengthCapacity = 25;
		this.balanceLengthCapacity = 15;

		/*определение метки:
		* если метка равна 0 - OK not disable
		* если метка равна 1 или 2 - OK disable */
		this.flagForBlockOKButton = 0;
		//******************************************************************************************************
		//listen textField_Name
		//------------------------------------------------------------------
		textField_Name.textProperty().addListener((observable, oldValue, newValue) -> {
			//------------------------------------------------------------------
			if (newValue.length() > this.nameLengthCapacity) { //проверка на превышение допустимой длинны строки
				textField_Name.setText(oldValue);
			}
			//Если какое либо из полей не заполнено, присваиваем метке 1 и делаем OK disable
			//------------------------------------------------------------------

			if(isEmptyField(textField_Name.getText()) || isEmptyField(textField_Balance.getText())) {
				flagForBlockOKButton = 1;
			}
			//Если OK disable из-за неправильно введённых данных (метка равна 2), то оставляем метку равной 2
			//Иначе метка равна 0 и OK not disable
			else {
				flagForBlockOKButton = (2 == flagForBlockOKButton) ? 2 : 0;
			}
			blockOKButton(flagForBlockOKButton);
		});
		//******************************************************************************************************

		//******************************************************************************************************
		//listen textField_Balance
		//------------------------------------------------------------------
		textField_Balance.textProperty().addListener((observable, oldValue, newValue) -> {
			//------------------------------------------------------------------
			if (newValue.length() > this.balanceLengthCapacity) { //проверка на превышение допустимой длинны строки
				textField_Balance.setText(oldValue);
			}


			//------------------------------------------------------------------
			if(isDigit_TextField(newValue)){ //если в balance верное значение
				textField_Balance.setStyle("-fx-control-inner-background: white;");
				flagForBlockOKButton = 0;
			}
			else {//если в balance значение не double
				textField_Balance.setStyle("-fx-control-inner-background: pink;");
				flagForBlockOKButton = 2;
			}

			//Если какое либо из полей не заполнено, присваиваем метке 1 и делаем OK disable
			//------------------------------------------------------------------
			if(isEmptyField(textField_Name.getText()) || isEmptyField(textField_Balance.getText())) {
				flagForBlockOKButton = 2;
			}
			//Если OK disable из-за неправильно введённых данных (метка равна 2), то оставляем метку равной 2
			//Иначе метка равна 0 и OK not disable
			else {
				flagForBlockOKButton = (2 == flagForBlockOKButton) ? 2 : 0;
			}
			blockOKButton(flagForBlockOKButton);
			//******************************************************************************************************
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

	private boolean isEmptyField(String textField)
	{
		return textField.length() == 0;
	}

	private void blockOKButton(int flag)
	{
		if(flag != 0)
			Button_OK.setDisable(true);
		else
			Button_OK.setDisable(false);
	}

}
