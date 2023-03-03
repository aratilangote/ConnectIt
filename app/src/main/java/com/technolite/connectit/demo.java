package com.technolite.connectit;

public class demo {
    // string course_name for storing course_name
    // and imgid for storing image id.
    private String card_name;
    private int imgid;

    public demo(String card_name, int imgid) {
        this.card_name = card_name;
        this.imgid = imgid;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}
