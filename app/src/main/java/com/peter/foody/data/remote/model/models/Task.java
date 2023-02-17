package com.peter.foody.data.remote.model.models;

import java.util.ArrayList;

public class Task<t> {
    public int State;
    public String Message;
    ArrayList<t>data;
    t Item;

    public t getItem() {
        return Item;
    }

    public void setItem(t item) {
        Item = item;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public ArrayList<t> getData() {
        return data;
    }

    public void setData(ArrayList<t> data) {
        this.data = data;
    }
}
