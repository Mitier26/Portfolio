package kr.or.bukedu.mitier.a16_lotto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;

public class ResultActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    int[] lottoNumber = new int[45];
    int[] selectNumber = new int[5];
    int[] myNumber = new int[5];
    int[] placeNumber = new int[5];


    TextView[] lottoText = new TextView[5];
    TextView[] myText = new TextView[5];
    TextView[]  resultText = new TextView[5];
    Button backBtn;
    ImageButton removeData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        lottoText[0] = findViewById(R.id.textView39);
        lottoText[1] = findViewById(R.id.textView40);
        lottoText[2] = findViewById(R.id.textView41);
        lottoText[3] = findViewById(R.id.textView42);
        lottoText[4] = findViewById(R.id.textView43);

        myText[0] = findViewById(R.id.textView50);
        myText[1] = findViewById(R.id.textView51);
        myText[2] = findViewById(R.id.textView52);
        myText[3] = findViewById(R.id.textView53);
        myText[4] = findViewById(R.id.textView54);

        resultText[0] = findViewById(R.id.textView61);
        resultText[1] = findViewById(R.id.textView62);
        resultText[2] = findViewById(R.id.textView63);
        resultText[3] = findViewById(R.id.textView64);
        resultText[4] = findViewById(R.id.textView65);

        backBtn = findViewById(R.id.button);
        removeData = findViewById(R.id.imageButton2);

        Intent intent = getIntent();

        myNumber = intent.getIntArrayExtra("myNumber");

        placeNumber[0] = pref.getInt("place1",0);
        placeNumber[1] = pref.getInt("place2",0);
        placeNumber[2] = pref.getInt("place3",0);
        placeNumber[3] = pref.getInt("place4",0);
        placeNumber[4] = pref.getInt("place5",0);

        for(int i = 0 ; i < 45 ; i++)
        {
            lottoNumber[i] = i + 1;
        }

        for(int i = 0 ; i < 1000; i++)
        {
            int temp = (int)(Math.random() * 45);
            int dec = (int)(Math.random() * 45);

            int num = lottoNumber[temp];
            lottoNumber[temp] = lottoNumber[dec];
            lottoNumber[dec] = num;
        }

        for(int i = 0; i< 5; i++)
        {
            selectNumber[i] = lottoNumber[i];
        }

        Arrays.sort(selectNumber);
        Arrays.sort(myNumber);

        for(int i = 0 ; i < 5 ; i++)
        {
            lottoText[i].setText(""+selectNumber[i]);
            myText[i].setText(""+myNumber[i]);
        }

        for(int i = 0; i < 5; i++)
        {
            if(selectNumber[i] > 0 && selectNumber[i] <= 10)
            {
                lottoText[i].setBackgroundColor(Color.YELLOW);
                myText[i].setBackgroundColor(Color.YELLOW);
            }
            if(selectNumber[i] > 10 && selectNumber[i] <= 20)
            {
                lottoText[i].setBackgroundColor(Color.BLUE);
                lottoText[i].setTextColor(Color.WHITE);
                myText[i].setBackgroundColor(Color.BLUE);
                myText[i].setTextColor(Color.WHITE);
            }
            if(selectNumber[i] > 20 && selectNumber[i] <= 30)
            {
                lottoText[i].setBackgroundColor(Color.RED);
                lottoText[i].setTextColor(Color.WHITE);
                myText[i].setBackgroundColor(Color.RED);
                myText[i].setTextColor(Color.WHITE);
            }
            if(selectNumber[i] > 30 && selectNumber[i] <= 40)
            {
                lottoText[i].setBackgroundColor(Color.BLACK);
                lottoText[i].setTextColor(Color.WHITE);
                myText[i].setBackgroundColor(Color.BLACK);
                myText[i].setTextColor(Color.WHITE);
            }
            if(selectNumber[i] >40 && selectNumber[i] <= 45)
            {
                lottoText[i].setBackgroundColor(Color.GREEN);
                lottoText[i].setTextColor(Color.WHITE);
                myText[i].setBackgroundColor(Color.GREEN);
                myText[i].setTextColor(Color.WHITE);
            }
        }

        int matchCount = 0;

        for(int i = 0 ; i < 5 ; i++)
        {
            for(int j = 0 ; j < 5; j++)
            {
                if(lottoNumber[i] == myNumber[j] && lottoNumber[i]!=0)
                {
                    matchCount++;
                }
            }
        }


        if(matchCount == 5)
        {
            placeNumber[0] += 1;
        }
        else if (matchCount == 4)
        {
            placeNumber[1]++;
        }
        else if(matchCount == 3)
        {
            placeNumber[2]++;
        }
        else if(matchCount == 2)
        {
            placeNumber[3]++;
        }
        else
        {
            placeNumber[4]++;
        }

        for(int i  = 0 ; i < 5 ; i++)
        {
            resultText[i].setText(""+ placeNumber[i]);
        }

        removeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.remove("place1");
                editor.remove("place2");
                editor.remove("place3");
                editor.remove("place4");
                editor.remove("place5");
                editor.clear();
                editor.apply();

                placeNumber[0] = pref.getInt("place1",0);
                placeNumber[1] = pref.getInt("place2",0);
                placeNumber[2] = pref.getInt("place3",0);
                placeNumber[3] = pref.getInt("place4",0);
                placeNumber[4] = pref.getInt("place5",0);

                for (int i = 0 ; i < 5 ; i ++)
                {
                    resultText[i].setText(""+0);
                }

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putInt("place1", placeNumber[0]);
                editor.putInt("place2", placeNumber[1]);
                editor.putInt("place3", placeNumber[2]);
                editor.putInt("place4", placeNumber[3]);
                editor.putInt("place5", placeNumber[4]);
                editor.apply();
                setResult(RESULT_OK);
                finish();
            }
        });


    }
}