package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SettingDialogApp {

    public void start()  {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/SettingDialogUI.fxml"));
            Scene scene = new Scene(root);
            Stage settingDialog = new Stage(StageStyle.UTILITY);
            settingDialog.setScene(scene);
            settingDialog.setResizable(false);
            settingDialog.initModality(Modality.APPLICATION_MODAL);
            settingDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
