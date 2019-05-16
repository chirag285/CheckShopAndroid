package com.example.checkshop;

import android.view.View;

public class RowItemCart {
    private String title;
    private String desc;
    private String res_DateFrom;
    private String cost;
    private String availability;
    private String contactPhoto;
    private String name;
    private String company;
    private String index;
    boolean selected = false;
    private View.OnClickListener requestBtnClickListener;
    public RowItemCart(String title, String desc, String res_DateFrom, String cost, String availability,
                       String contactPhoto, String name, String company, String index, boolean selected) {
        super();
        this.title = title;
        this.desc = desc;
        this.res_DateFrom = res_DateFrom;
        this.cost = cost;
        this.availability = availability;
        this.contactPhoto = contactPhoto;
        this.name = name;
        this.company = company;
        this.index = index;
        this.selected = selected;
    }
    public RowItemCart() {
        this.title = title;
        this.desc = desc;
        this.res_DateFrom = res_DateFrom;
        this.cost = cost;
        this.availability = availability;
        this.contactPhoto = contactPhoto;
        this.name = name;
        this.company = company;
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRes_DateFrom() {
        return res_DateFrom;
    }

    public void setRes_DateFrom(String res_DateFrom) {
        this.res_DateFrom = res_DateFrom;
    }



    public String getCost() {
        return cost;
    }

    public String getAvailability() {
        return availability;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getContactPhoto() {
        return contactPhoto;
    }

    public void setContactPhoto(String contactPhoto) {
        this.contactPhoto = contactPhoto;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIndex() {
        return index;
    }

    public void setindex(String index) {
        this.index = index;
    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}