package controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.LivetubeChannel;
import service.FavoriteService;
import service.LivetubeService;
import service.TableViewService;
import timeline.UpdateTimeline;
import application.SettingDialogApp;
import dto.MainTableViewData;


public class MainController implements Initializable {

    private static MainController _instance;

    /*========================================================================
     * Livetubeタブ
     ========================================================================*/
    @FXML
    private TableView<MainTableViewData> tableView;

    @FXML
    private TableColumn<MainTableViewData, String> author;

    @FXML
    private TableColumn<MainTableViewData, String> title;

    @FXML
    private TableColumn<MainTableViewData, String> created;

    @FXML
    private Label async;

    /*========================================================================
     * お気に入りタブ
     ========================================================================*/
    @FXML
    private TableView<MainTableViewData> tableViewFavorite;

    @FXML
    private TableColumn<MainTableViewData, String> authorFavorite;

    @FXML
    private TableColumn<MainTableViewData, String> titleFavorite;

    @FXML
    private TableColumn<MainTableViewData, String> createdFavorite;

    /*========================================================================
     * 内部メンバ
     ========================================================================*/
    private UpdateTimeline _timer;

    List<TableViewService> _services;

    public static MainController getInstance() {
        return _instance;
    }

    // 初期化
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        _instance = this;
        _services = new LinkedList<>();
        _services.add(new LivetubeService(tableView, author, title, created));
        _services.add(new FavoriteService(tableViewFavorite, authorFavorite, titleFavorite, createdFavorite));
        _timer = new UpdateTimeline(async);

        LivetubeChannel.load();

        draw();
        _timer.timeline();
    }

    private void draw() {
        for (TableViewService service : _services) {
            service.draw();
        }
    }

    private void reset() {
        for (TableViewService service : _services) {
            service.reset();
        }
    }

    @FXML
    // 更新ボタン
    public void handleUpdate(ActionEvent event) {
        _timer.stop();

        LivetubeChannel.load();

        reset();
        draw();

        _timer.timeline();
    }

    @FXML
    // 閉じるボタン
    private void handleClose(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }


    @FXML
    // お気に入り追加ボタン
    public void handleAddFavorite(ActionEvent event) {
        System.out.println(event);
    }

    @FXML
    // 設定ボタン
    public void handleSetting(ActionEvent event) {
        new SettingDialogApp().start();
    }
}