package model;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.PropertyUtil;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import dto.MainTableViewData;

public class LivetubeChannel {
    private static final String LIVETUBE_JSON_URL = "http://livetube.cc/index.live.json";

    private static List<MainTableViewData> _channelList;

    private static List<MainTableViewData> _favoriteChannelList;

    private static boolean _isLoad;

    private static int count;

    private static int favoriteCount;

    private LivetubeChannel() {}

    public static void load() {
        // load
        setChennel();

        if (_channelList.isEmpty()) {
            _channelList = empty();
        } else {
            // お気に入り
            setFavorite();
        }
        _isLoad = true;
    }

    private static void setChennel() {
        _channelList = new ArrayList<MainTableViewData>();
        try(JsonReader reader = new JsonReader(new InputStreamReader(new URL(LIVETUBE_JSON_URL).openStream(), "UTF-8"))) {
            reader.beginArray();
            Gson gson = new Gson();
            while (reader.hasNext()) {
                MainTableViewData tmpDto = gson.fromJson(reader, MainTableViewData.class);
                tmpDto.setCreated(tmpDto.getCreated());
                _channelList.add(tmpDto);
            }
            reader.endArray();

            count = _channelList.size();

        } catch (Exception e) {
            // 何もしない
            e.printStackTrace();
        }
    }

    private static void setFavorite() {
        // お気に入り
        _favoriteChannelList = new ArrayList<>();
        List<String> favoriteAuthors = PropertyUtil.loadSettingFile("favorite.ini");
        for (MainTableViewData data : _channelList) {
            if (favoriteAuthors.contains(data.getAuthor())) _favoriteChannelList.add(data);
        }
        favoriteCount = _favoriteChannelList.size();
    }

    public static ObservableList<MainTableViewData> request() {
        return FXCollections.observableArrayList(new ArrayList<>(_channelList));
    }

    public static ObservableList<MainTableViewData> empty() {
        MainTableViewData emptyData = new MainTableViewData() {
            {
//                setAuthor("なし");
                setTitle("配信はありません。");
//                setCreated("00:00");
            }
        };
        return FXCollections.observableArrayList(emptyData);
    }

    public static ObservableList<MainTableViewData> favoriteYp() {
        if (_favoriteChannelList.isEmpty()) {
            return empty();
        }
        return FXCollections.observableArrayList(_favoriteChannelList);
    }

    public static List<String> getFavoriteAuthors() {
        return PropertyUtil.loadSettingFile("favorite.ini");
    }

    public static boolean isLoad() {
        return _isLoad;
    }

    public static int getCount() {
        return count;
    }

    public static int getFavoriteCount() {
        return favoriteCount;
    }
}
