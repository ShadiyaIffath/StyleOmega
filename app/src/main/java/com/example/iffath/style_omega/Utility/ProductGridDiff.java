package com.example.iffath.style_omega.Utility;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.iffath.style_omega.Model.Product;

import java.util.List;

public class ProductGridDiff extends DiffUtil.Callback {
    List<Product> initalList;
    List<Product> newList;

    public ProductGridDiff(List<Product> initalList, List<Product> newList) {
        this.initalList = initalList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return initalList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return initalList.get(oldItemPosition).getId() == (newList.get(newItemPosition).getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return initalList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition,newItemPosition);
    }
}


