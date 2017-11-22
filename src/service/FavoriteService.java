package service;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.LivetubeChannel;
import dto.MainTableViewData;

public class FavoriteService extends TableViewService {

    public FavoriteService( TableView<MainTableViewData> tableView
                          , TableColumn<MainTableViewData, String> author
                          , TableColumn<MainTableViewData, String> title
                          , TableColumn<MainTableViewData, String> created) {
        super(tableView, author, title, created, LivetubeChannel.getFavoriteAuthors());
    }

    @Override
    public void draw() {

        // グリッド用のデータを作成
        ObservableList<MainTableViewData> livetubeData = LivetubeChannel.empty();
        if (LivetubeChannel.isLoad()) {
            livetubeData = LivetubeChannel.favoriteYp();
        }

        // セット
        tableView.setItems(livetubeData);

        // データと紐づけ
        // 第2引数はTableViewDataの変数名と合わせる
        setTableColumnItem(author  , "author");    // チャンネル名
        setTableColumnItem(title   , "title");     // タイトル
        setTableColumnItem(created , "created");   // 配信時間

        // ダブルクリックのイベントを作成
        setRowEvent(tableView);
    }
}
