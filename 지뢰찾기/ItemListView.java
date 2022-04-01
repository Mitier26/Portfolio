package com.malkang.mitier.a105_minesweeper02;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ItemListView extends LinearLayout {

    TextView rankText, nameText, scoreText;

    public ItemListView(Context context) {
        super(context);
    }

    public ItemListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item, this,true);
        rankText = findViewById(R.id.textView13);
        nameText = findViewById(R.id.textView22);
        scoreText = findViewById(R.id.textView23);

    }

    public void setRank(String rank)
    {
        rankText.setText(rank);
    }
    public void setName(String name)
    {
        nameText.setText(name);
    }
    public void setScore(String score)
    {
        scoreText.setText(score);
    }
}
