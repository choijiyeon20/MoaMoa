package com.cookandroid.moamoa;

import android.widget.ImageView;

public class Post_comment_Item {

    private int iconDrawable;
    private String IdStr;
    private String contentStr;
    private String dateStr;
    private int moreDrawable;

    public void setIconDrawable(int icon) {
        iconDrawable = icon;
    }

    public void setIdStr(String id) {
        IdStr = id;
    }

    public void setContentStr(String content) {
        contentStr = content;
    }

    public void setDateStr(String date) {
        dateStr = date;
    }

    public void setMoreDrawable(int more) {
        moreDrawable = more;
    }

    public int getIconDrawable() {
        return this.iconDrawable;
    }

    public String getIdStr() {
        return this.IdStr;
    }

    public String getContentStr() {
        return this.contentStr;
    }

    public String getDateStr() {
        return this.dateStr;
    }

    public int getMoreDrawable() {
        return this.moreDrawable;
    }
}
