package service;

import java.util.List;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import org.apache.commons.lang3.StringUtils;

import util.PropertyUtil;
import dto.MainTableViewData;

public abstract class TableViewService {

    protected final TableView<MainTableViewData> tableView;

    protected final TableColumn<MainTableViewData, String> author;

    protected final TableColumn<MainTableViewData, String> title;

    protected final TableColumn<MainTableViewData, String> created;

    private List<String> favoriteAuthors;

    public TableViewService(  TableView<MainTableViewData> tableView
                            , TableColumn<MainTableViewData, String> author
                            , TableColumn<MainTableViewData, String> title
                            , TableColumn<MainTableViewData, String> created
                            , List<String> favoriteAuthors) {
        this.tableView = tableView;
        this.author    = author;
        this.title     = title;
        this.created   = created;
        this.favoriteAuthors = favoriteAuthors;
    }

    public void reset() {
        tableView.getItems().clear(); // クリア
        tableView.layout();           // リフレッシュ
    }

    public abstract void draw();

    protected <T, S> void setTableColumnItem(TableColumn<T, String> tableColumn, String tableColumnId) {
        tableColumn.setCellValueFactory(new PropertyValueFactory<>(tableColumnId));

        tableColumn.setCellFactory(arg0 -> {
            TableCell<T, String> tableCell = new TableCell<T, String>() {
                @Override
                protected void updateItem(final String item, final boolean empty) {
                    super.updateItem(item, empty);

                    setText(item);
                    if (!empty) {
                        // スタイル設定済のことを考慮し削除する
                        getTableRow().getStyleClass().remove("favorite");
                        if(favoriteAuthors.contains(author.getCellData(getIndex())))  {
                            getTableRow().getStyleClass().add("favorite");
                        }
                    }
                }
            };
            return tableCell;
        });
    }

    //
    // 行選択のイベントを作成
    protected <T> void setRowEvent(TableView<T> tableView) {
        tableView.setRowFactory(tv -> {
            TableRow<T> row = new TableRow<T>();
            row.setOnMouseClicked(event -> { doubleClickPrimary(event, row); });
            return row;
        });
    }

    // 左ダブルクリック
    private void doubleClickPrimary(MouseEvent event, TableRow<?> row) {
        boolean isDoubleClick = event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2;
        if (isDoubleClick) {
            MainTableViewData tmpDto = (MainTableViewData) row.getItem();

            StringBuffer moviePath = new StringBuffer();
            String toolpath = PropertyUtil.loadPropertyValue("LivetubeCheckerFx.properties", "playerpath");
            if (StringUtils.isNotBlank(toolpath)) {
                moviePath.append("\"" + toolpath + "\"");         // ツール
                moviePath.append(" http://livetube.cc/stream/" + tmpDto.getId()  + ".flv"); // 第１引数 ストリームurl
                moviePath.append(" \""  + tmpDto.getAuthor() + "\"" );                      // 第２引数 配信者名
                try {
                    Runtime.getRuntime().exec(moviePath.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
