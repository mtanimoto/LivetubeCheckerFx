package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.PropertyUtil;

public class SettingDialogController implements Initializable {

    @FXML
    private TextField textFieldPlayerPath;

    @FXML
    private Button buttonOk;

    @FXML
    private Button buttonCancel;

    private final String PROPERTY_FILENAME = "LivetubeCheckerFx.properties";

    private final String PROPERTY_KEY = "playerpath";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 設定用ファイル読み込み
        String value = PropertyUtil.loadPropertyValue(PROPERTY_FILENAME, PROPERTY_KEY);
        textFieldPlayerPath.setText(value);
    }

    @FXML
    public void handleUpdate(ActionEvent event) {
        String text = textFieldPlayerPath.getText();
        if (PropertyUtil.write(PROPERTY_FILENAME, PROPERTY_KEY, text)) {
            // 閉じる
            ((Node)event.getSource()).getScene().getWindow().hide();
            return;
        }
        // 失敗した場合エラーメッセージを出す
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        // 閉じる
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
