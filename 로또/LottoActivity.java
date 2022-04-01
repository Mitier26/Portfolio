package kr.or.bukedu.mitier.a16_lotto;

import static java.lang.Thread.sleep;

import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;

public class LottoActivity extends AppCompatActivity {

    // 숫자를 추첨하기 위한 버튼
    ImageButton btn;

    // 뽑은 숫자를 저장하는 배열
    int selectLotto[] = new int[5];

    // 뽑은 숫자를 보여주는 것
    TextView[] lottoText = new TextView[5];

    // 1 ~ 45 까지 숫자를 저장하기 위한 배열, 겹치지 않는 랜덤숫자를 위한 것
    int lotto[]  = new int[45];

    // 뽑았던 숫자를 저장하는 2중 배열
    int saveLotto[][] = new int[6][5];

    // 2중 배열의 라인
    int count = 0;

    // 2중 배열의 전체 인덱스
    int row = 0;

    int saveTextId[] = {
            R.id.textView8,R.id.textView9,R.id.textView10,R.id.textView11,R.id.textView12,
            R.id.textView13,R.id.textView14,R.id.textView15,R.id.textView16,R.id.textView17,
            R.id.textView18,R.id.textView19,R.id.textView20,R.id.textView21,R.id.textView22,
            R.id.textView23,R.id.textView24,R.id.textView25,R.id.textView26,R.id.textView27,
            R.id.textView28,R.id.textView29,R.id.textView30,R.id.textView31,R.id.textView32,
            R.id.textView33,R.id.textView34,R.id.textView35,R.id.textView36,R.id.textView37
    };
    TextView saveTextView[] = new TextView[saveTextId.length];

    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lotto);

        // 리소스에서 각 아이템의 번호를 찾아오는 것
        findResources();

        // 1 ~ 45 까지 숫자를 저장하기 위한 것
        setLottoNumber();

        // 이미지 버튼을 클릭 했을 때 발생하는 이벤트
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 글자 뒤 배경 색을 초기화 하기 위한것
                // 초기화 하지 않으면 기존의 색에서 변경되지 않는다.
                setTextBackgroundColor();

                // 핵심 기능
                // 서로 겹치지 않는 숫자를 얻기 위한 것
                shuffleNumber();

                selectNumber();

                showNumber();

                setSaveLotto();

                showSaveList();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBackBtn();
            }
        });
    }


    void findResources()
    {
        btn = findViewById(R.id.imageButton);
        lottoText[0] = findViewById(R.id.textView1);
        lottoText[1] = findViewById(R.id.textView2);
        lottoText[2] = findViewById(R.id.textView3);
        lottoText[3] = findViewById(R.id.textView4);
        lottoText[4] = findViewById(R.id.textView5);

        for(int i = 0; i < saveTextView.length; i++)
        {
            saveTextView[i] = findViewById(saveTextId[i]);
        }

        backBtn = findViewById(R.id.button48);
    }
    void setLottoNumber()
    {
        for(int i  = 0 ; i < 45; i++)
        {
            lotto[i] = i+1;
        }
    }
    void setTextBackgroundColor()
    {
        for(int i =0; i< 5; i++)
        {
            lottoText[i].setBackgroundColor(Color.WHITE);
            lottoText[i].setTextColor(Color.BLACK);
        }
    }
    void shuffleNumber()
    {
        // 1000 번 반복 하라는 뜻
        for(int i = 0 ; i < 1000; i++)
        {
            // 랜덤한 값을 저장한다.
            int temp = (int)(Math.random() * 45);
            int dec = (int)(Math.random() * 45);

            // 배열을 섞는 기능
            int num = lotto[temp];
            lotto[temp] = lotto[dec];
            lotto[dec] = num;
        }
    }
    void selectNumber()
    {
        for(int i = 0; i< 5; i++)
        {
            // 섞인 숫자의 배열의 앞에서 5 번째 까지의 수를 다른 배열에 저장한다.
            selectLotto[i] = lotto[i];
        }
        // 다른 배열에 저장한 이유
        // 정렬을 하기 위해서
        Arrays.sort(selectLotto);
    }
    void showNumber()
    {
        for(int i = 0; i < 5; i++)
        {
            lottoText[i].setText(""+selectLotto[i]);

            if(selectLotto[i] > 0 && selectLotto[i] <= 10)
            {
                lottoText[i].setBackgroundColor(Color.YELLOW);
                lottoText[i].setTextColor(Color.BLACK);
            }
            if(selectLotto[i] > 10 && selectLotto[i] <= 20)
            {
                lottoText[i].setBackgroundColor(Color.BLUE);
                lottoText[i].setTextColor(Color.WHITE);
            }
            if(selectLotto[i] > 20 && selectLotto[i] <= 30)
            {
                lottoText[i].setBackgroundColor(Color.RED);
                lottoText[i].setTextColor(Color.WHITE);
            }
            if(selectLotto[i] > 30 && selectLotto[i] <= 40)
            {
                lottoText[i].setBackgroundColor(Color.BLACK);
                lottoText[i].setTextColor(Color.WHITE);
            }
            if(selectLotto[i] >40 && selectLotto[i] <= 45)
            {
                lottoText[i].setBackgroundColor(Color.GREEN);
                lottoText[i].setTextColor(Color.WHITE);
            }
        }
    }
    void setSaveLotto()
    {
        // 데이터 저장

        if(count < 6)
        {
            for(int i = 0 ;i < 5; i++)
            {
                saveLotto[count][i] = selectLotto[i];
            }
        }
        else
        {
            for(int i = 0; i < 5; i++)
            {
                for(int k = 0; k < 5; k++)
                {
                    saveLotto[i][k] = saveLotto[i+1][k];
                }
                //====================================================
                // 주의!!!!!!!!!!!!!!!!!!!!
                // saveLotto[i] = saveLotto[i+1];
                // 이것은 값 복사가 아니 주소 복사된다.
                // 그래서 값은 가지게 된다.
                // 깊은 복사

            }
            for(int i = 0; i < 5; i++)
            {
                saveLotto[5][i] = selectLotto[i];
            }

        }
        count++;
    }
    void showSaveList()
    {
        // 저장된것 보여주기
        row = 0;

        for(int i= 0; i < saveLotto.length; i++)
        {
            for(int j = 0; j < saveLotto[i].length; j++)
            {
                // 30 번 반복
                saveTextView[row].setText(""+saveLotto[i][j]);

                if(saveLotto[i][j] > 0 && saveLotto[i][j] <= 10)
                {
                    saveTextView[row].setBackgroundColor(Color.YELLOW);
                    saveTextView[row].setTextColor(Color.BLACK);
                }
                if(saveLotto[i][j] > 10 && saveLotto[i][j] <= 20)
                {
                    saveTextView[row].setBackgroundColor(Color.BLUE);
                    saveTextView[row].setTextColor(Color.WHITE);
                }
                if(saveLotto[i][j] > 20 && saveLotto[i][j] <= 30)
                {
                    saveTextView[row].setBackgroundColor(Color.RED);
                    saveTextView[row].setTextColor(Color.WHITE);
                }
                if(saveLotto[i][j] > 30 && saveLotto[i][j] <= 40)
                {
                    saveTextView[row].setBackgroundColor(Color.BLACK);
                    saveTextView[row].setTextColor(Color.WHITE);
                }
                if(saveLotto[i][j] >40 &&saveLotto[i][j] <= 45)
                {
                    saveTextView[row].setBackgroundColor(Color.GREEN);
                    saveTextView[row].setTextColor(Color.WHITE);
                }

                row++;
            }
        }
    }

    void setBackBtn()
    {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        startActivity(intent);
        finish();
    }
}