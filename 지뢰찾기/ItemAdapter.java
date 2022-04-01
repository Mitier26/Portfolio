package com.malkang.mitier.a105_minesweeper02;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import java.util.ArrayList;

class ItemAdapter extends BaseAdapter
{

    ArrayList<ListItem> items = new ArrayList<ListItem>();
    Context con;
    ItemAdapter(Context context)
    {
        con = context;
    }
    @Override
    public int getCount() {
        Log.d("@@@@@@@@@@@@" , ""+items.size());
        return items.size();
    }

    public void addItem(ListItem item)
    {
        items.add(item);
    }


//    void setView(Context context)
//    {
//        con = context;
//    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemListView itemListView = null;
        if(view == null)
        {
            Log.d("@@@@@@@@@@@@@@@@@@@@@@", ""+con);
            itemListView = new ItemListView(con);
        }
        else
        {
            Log.d("@@@@@@@@@@@@@@@@@@@@@@", "이곳이냐?");
            itemListView = (ItemListView)view;
        }
        ListItem item = items.get(i);
        Log.d("@@@@@@@@@@@@" , ""+item.getTime());
        itemListView.setRank(""+item.getRank());
        itemListView.setName(item.getName());
        itemListView.setScore(""+item.getTime());


        return itemListView;
    }
}