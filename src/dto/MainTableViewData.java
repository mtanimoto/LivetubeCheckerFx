package dto;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;

public class MainTableViewData {
    private String author;
    private String title;
    private String created;
    private String id;

    /**
     * authorを取得します。
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * authorを設定します。
     * @param author author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * titleを取得します。
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * titleを設定します。
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * createdを取得します。
     * @return created
     */
    public String getCreated() {
        return created;
    }

    /**
     * createdを設定します。
     * @param created created
     */
    public void setCreated(String created) {
        this.created = formatForView(created);
    }

    private String formatForView(String created) {
        try {
            Date date = DateUtils.parseDate(created, "EEE, dd MMM yyyy HH:mm:ss 'GMT'");
            long createDate = date.getTime();
            long nowDate = new Date().getTime();
            return DurationFormatUtils.formatPeriod(createDate, nowDate, "HH:mm");
        } catch (ParseException e) {
            return "00:00";
        }
    }

    /**
     * idを取得します。
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * idを設定します。
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }
}
