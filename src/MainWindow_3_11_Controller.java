import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow_3_11_Controller {

	@FXML
	void onMouseClicked_NewAccount(MouseEvent event) {
		startDialogueWindow("NewAccountDialogueWindow");
	}

	@FXML
	void onMouseClicked_Edit(MouseEvent event) {

	}

	@FXML
	void onMouseClicked_Delete(MouseEvent event) {

	}

	@FXML
	void onMouseClicked_Exit(MouseEvent event) {

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

			//start-up window
			//-----------------------------------------------
			Parent fxmlDialogue = fxmlLoaderDialogue.load();
			Scene SDialogue = new Scene(fxmlDialogue);
			dialogueStage.setScene(SDialogue);
			dialogueStage.showAndWait();

		}catch(IOException e) {

		}
	}

}