package com.zjh.experiments;

import java.io.Serializable;

public class Bean {
    private String textName;
    private int imgId;

    public Bean(String textName, int imgId) {
        this.textName = textName;
        this.imgId = imgId;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
