package com.example.tecknet.view.inventory_maintenance_man;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InventoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public InventoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is inventory fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}