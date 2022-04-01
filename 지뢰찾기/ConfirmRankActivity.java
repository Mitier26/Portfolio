package com.malkang.mitier.a105_minesweeper02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ConfirmRankActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter<String> adapter;
    Button exitButton;

    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_rank);

        listView = findViewById(R.id.listview);
        exitButton = findViewById(R.id.button7);

        //ItemAdapter adapter1 = new ItemAdapter(getApplicationContext());
        //adapter1.setView(getApplicationContext());
        adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_item2);
        pref = getSharedPreferences("pref",MODE_PRIVATE);
        listView.setAdapter(adapter);
        for(int i = 0; i < 10; i++)
        {
            String str = pref.getString("saveData"+i, "이름없음/999");
            String[] str1 = str.split("/");
            // 배열에 데이터를 넣는다.
            //adapter1.addItem(new ListItem(i,str1[0],Integer.parseInt(str1[1])));
            adapter.add("" + (i+1) + "\t\t\t" + str1[0] + "\t\t\t" + Integer.parseInt(str1[1]));
        }





        // 저장되어 있는 값을 가지고 와서 대입 시켜야 한다.

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}