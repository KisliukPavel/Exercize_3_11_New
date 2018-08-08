/*(Modified Account Class) Modify class Account (Fig. 3.8) to provide a method called withdraw that withdraws money from
an Account. Ensure that the withdrawal amount does not exceed
the Account’s balance. If it does, the balance should be left unchanged and the method should print
a message indicating "Withdrawal amount exceeded account balance." */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Exercize_3_11_New extends Application {

	private String title;

	public Exercize_3_11_New()
	{
		this.title = "Modified Account Class";
	}

	public static void main(String[] args)
	{
		Application.launch(args);
	}

	public void start(Stage primaryStage) throws Exception
	{
		//primaryStage adjustment
		//-----------------------------------------------
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		primaryStage.setTitle(title);
		primaryStage.centerOnScreen();

		//FXML adjustment
		//-----------------------------------------------
		FXMLLoader fxmlLoader_3_11 = new FXMLLoader();
		fxmlLoader_3_11.setLocation(getClass().getResource("MainWindow_3_11.fxml"));
		Parent fxmlMainWindow = fxmlLoader_3_11.load();

		//controller adjustment
		//-----------------------------------------------
		MainWindow_3_11_Controller mainController = fxmlLoader_3_11.getController();
		mainController.setMainStage(primaryStage);

		//start-up window
		//-----------------------------------------------
		Scene S_3_11 = new Scene(fxmlMainWindow);
		primaryStage.setScene(S_3_11);
		primaryStage.show();
	}
}
