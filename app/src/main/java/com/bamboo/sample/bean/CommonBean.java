package com.bamboo.sample.bean;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-02-10 17:31
 * 描述：
 */
public  class CommonBean {
    private int resId;
    private String title;
    private String description;
    private String type;

    public CommonBean(String title) {
        this.title = title;
    }

    public CommonBean(String title, String type) {
        this.title = title;
        this.type = type;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
