package com.malkang.mitier.a105_minesweeper02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InputRankActivity extends AppCompatActivity {

    TextView timeText;
    EditText inputNameText;
    Button confirmButton;
    int keyNum =0;
    int maxRank = 10;

    int time = 0;
    String userName;

    List<String> name;
    List<Integer> score;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_ranking);

        timeText = findViewById(R.id.textView8);
        inputNameText = findViewById(R.id.editTextTextPersonName);
        confirmButton = findViewById(R.id.button2);

        Intent intent = getIntent();
        time = intent.getIntExtra("time", 0);
        timeText.setText(""+time);

        pref = getSharedPreferences("pref", MODE_PRIVATE);
        editor = pref.edit();

        // 어차피 엑티비티가 실행되면 리스트는 초기화 된다.
        name = new ArrayList<String>();
        score = new ArrayList<Integer>();

        //adapter = new ItemAdapter();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 이름을 입력 안했을 경우 기본값
                if(inputNameText.length() == 0)
                {
                    userName = "이름없음";
                }
                else
                {
                    userName = inputNameText.getText().toString();
                }

                LoadData();
                finish();
            }
        });
    }

    // 1. 버튼을 누르면 작동한다.
    // 2. 이름과 점수를 저장한다.
    // 3. 배열에 저장하고 값이 비교하여 정렬한다.
    // 4. 정렬된 값을 저장한다.
    // 5. 표현되는 수는 10 줄 이지만 비교해야 하는 값은 11 줄 이다..
    // 저장된 데이터는 10개....흠...

    // 10. 데이터 저장을 빼고 배열에 랭킹을 넣는것 부터하자.

    public void LoadData()
    {
        // 저장된 랭킹이 몇개 인지 확인하기 위한것.
        int rankCount = pref.getInt("keyNum", 0);  // 기본값이 -1???
        // 저장을 할 때 +1을 하기 때문이다.
        // ?????
        Log.d("@@@@@@@@@@@@@@@@@@", userName);
        // 10개 이하면 데이터를 저장한다.
        // 10개 보다 많으면 데이터를 비교해야 한다.
        if(rankCount < maxRank)
        {
            // 10 개 이하면 그냥 저장 하는데...
            // 그냥 저장해도 순서를 정해야 한다.


            editor.putString("saveData"+rankCount, userName +"/"+time);
            // 불러온 데이터에서 +1을 해야 다음것에 숫자가 저장이 된다.
            // 1 부터 저장을 할 것 인가? 0 부터 저장을 할 것인가?
            editor.putInt("keyNum", rankCount+1);
            editor.commit();
            //rankCount++;  이것은 잘못된것이다. rankCount는 사라지는 것이다.
        }
        else
        {
            // 데이터가 가득
            // 데이터를 전부 꺼내서 비교 하고 다시 넣어야?????
            // 이것인 저장되어 있는 데이터를 꺼내오는 것이다
            // 10 개, 왜? 10 개 일때만 이것이 작동된다.
            for(int i = 0; i < maxRank; i++)
            {
                // 리스트에 저장된 데이터
                // 저장된 데이터를 가지고와 리스트 또는 배열에 넣고 비교를 해야한다.
                String str = pref.getString("saveData"+i, "0/999");
                String[] str1 = str.split("/");
                // 배열에 데이터를 넣는다.
                name.add(str1[0]);
                score.add(Integer.parseInt(str1[1]));

                // time 과 비교를 해야한다.
            }

            compareDate();
        }
    }

    public void compareDate()
    {
        int position;
        //?? 리스트에 넣고 ... 앞에 10 개 만 보여지면?
        // 처음에는 상관없겠지만 데이터가 많아 지면 리스트에 쌓인다.
        // 필요없는 10개 이후 데이터를 삭제를 해야 한다.

        for(position = 0; position < score.size(); position++)
        {
            // 리스트의 작은 값부터 검색을 한다.
            // 검색된 값보다 작다는 것는 그 더 랭킹이 높다는 뜻
            // 바로 그 위치에 값을 넣으면 된다.
            if(time < score.get(position))
            {
                score.add(position, time);
                name.add(position, userName);
                break;
            }
        }
        saveDate();
    }

    public void saveDate()
    {
        for (int i = 0; i < maxRank; i++)
        {
            //adapter.addItem(new ListItem(i, name.get(i), score.get(i)));
            editor.putString("saveData"+i, name.get(i) +"/"+score.get(i));
        }
        editor.commit();
    }
}