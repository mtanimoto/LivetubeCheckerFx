package timeline;

import java.util.Date;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.util.Duration;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import controller.MainController;

public class UpdateTimeline {

    private Label async;

    private Timeline timer;

    public UpdateTimeline(Label async) {
        this.async = async;
    }

    public void timeline() {
        timer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            int i = 601000; // 10分1秒
            Date date = new Date(i);
            String dispDate = null;

            @Override
            public void handle(ActionEvent event) {
                if (date.compareTo(new Date(0)) == 0) {
                    // 配信情報更新
                    MainController ic = MainController.getInstance();
                    ic.handleUpdate(event);

                    // 初期化
                    date = new Date(i);
                    dispDate = null;
                }
                date = DateUtils.addSeconds(date, -1);
                dispDate = DateFormatUtils.format(date, "mm:ss");
                async.setText(dispDate);
            }
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void stop() {
        timer.stop();
    }
}
