package com.example.tacademy.testbabycharts;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2015-10-19.
 */
public class BabyAdapter extends BaseAdapter {

    List<BabyItem> items = new ArrayList<BabyItem>();

    String keyword;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public void add(BabyItem item) {
        items.add(item);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BabyItemView view;
        if (convertView == null) {
            view = new BabyItemView(parent.getContext());
        } else {
            view = (BabyItemView)convertView;
        }
        view.setMovieItem(items.get(position));
        return view;
    }
}
