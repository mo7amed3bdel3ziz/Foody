package com.peter.foody.data.remote.model.classes.receipts;

import com.peter.foody.data.remote.model.classes.RootReturns;

import java.util.ArrayList;

public class ReceiptsReturns {

    public ArrayList<RootReturns> receipts;

    public ArrayList<RootReturns> getReceipts() {
        return receipts;
    }

    public void setReceipts(ArrayList<RootReturns> receipts) {
        this.receipts = receipts;
    }
}
