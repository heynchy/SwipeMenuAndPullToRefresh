package com.chy.swipemenuandpulltorefresh.Entity;

import java.util.List;

/**
 * StringEntity
 *
 * @author lenovo  2017/5/4.
 *         Function Describe
 * @modify lenovo  2017/5/4.
 * Function Describe
 */
public class StringEntity {
    private String title;
    private List<String> childListStr;

    public StringEntity(String title, List<String> childListStr) {
        this.title = title;
        this.childListStr = childListStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getChildListStr() {
        return childListStr;
    }

    public void setChildListStr(List<String> childListStr) {
        this.childListStr = childListStr;
    }
}
