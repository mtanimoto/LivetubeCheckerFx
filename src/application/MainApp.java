package application;

import java.util.Locale;
import java.util.TimeZone;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Locale.setDefault(Locale.US);
            TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainUI.fxml"));
            // Scene の作成・登録
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("ｿﾞｲ₍₍ (ง ˘ω˘ )ว ⁾⁾ｿﾞｲ");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
