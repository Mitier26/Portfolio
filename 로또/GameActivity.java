package kr.or.bukedu.mitier.a16_lotto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    final int REQUEST_CODE = 1;
    String TAG = "@@@@@@@@@@@@@  ";
    Button testLuck, clearNum, backBtn;
    int[] numberId = {
            R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10,
            R.id.button11, R.id.button12, R.id.button13, R.id.button14, R.id.button15, R.id.button16, R.id.button17, R.id.button18, R.id.button19, R.id.button20,
            R.id.button21, R.id.button22, R.id.button23, R.id.button24, R.id.button25, R.id.button26, R.id.button27, R.id.button28, R.id.button29, R.id.button30,
            R.id.button31, R.id.button33, R.id.button33, R.id.button34, R.id.button35, R.id.button36, R.id.button37, R.id.button38, R.id.button39, R.id.button40,
            R.id.button41, R.id.button42, R.id.button43, R.id.button44, R.id.button45,  };
    TextView[] textNumber = new TextView[5];
    Button[] buttons = new Button[numberId.length];
    ImageButton[] removeBtn = new ImageButton[5];

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        testLuck = findViewById(R.id.button46);
        clearNum = findViewById(R.id.button47);

        for(int i = 0; i < numberId.length; i++)
        {
            buttons[i] = findViewById(numberId[i]);
        }

        testLuck.setBackgroundColor(Color.RED);

        textNumber[0] = findViewById(R.id.textView43);
        textNumber[1] = findViewById(R.id.textView44);
        textNumber[2] = findViewById(R.id.textView45);
        textNumber[3] = findViewById(R.id.textView46);
        textNumber[4] = findViewById(R.id.textView47);

        removeBtn[0] = findViewById(R.id.imageButton8);
        removeBtn[1] = findViewById(R.id.imageButton9);
        removeBtn[2] = findViewById(R.id.imageButton10);
        removeBtn[3] = findViewById(R.id.imageButton11);
        removeBtn[4] = findViewById(R.id.imageButton12);

        backBtn = findViewById(R.id.button49);

        for(int i = 0 ; i < 5 ; i++)
        {
            textNumber[i].setText("0");
            testLuck.setBackgroundColor(Color.RED);
            count = 0;
        }


        View.OnClickListener buttonClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 0; i < numberId.length; i++)
                {
                    if(view.getId() == buttons[i].getId())
                    {
                        for(int j = 0 ; j < textNumber.length; j++)
                        {
                            if(textNumber[j].getText().toString().equals(buttons[i].getTag().toString()))
                            {
                                break;
                            }
                            else
                            {
                                if(textNumber[j].getText().equals("0"))
                                {
                                    count++;
                                    if (count >= 5) {
                                        count = 5;
                                        testLuck.setBackgroundColor(Color.GREEN);
                                    }
                                    textNumber[j].setText(buttons[i].getTag().toString());
                                    break;

                                }
                            }

                        }
                    }
                }
            }
        };

        View.OnClickListener removeNumber = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0 ; i < 5 ; i++)
                {
                    if(!textNumber[i].getText().equals("0"))
                    {
                        if(view.getId() == removeBtn[i].getId())
                        {
                            count--;
                            if(count < 5)
                            {
                                testLuck.setBackgroundColor(Color.RED);
                            }
                            if(count < 0)
                            {
                                count  = 0;
                            }
                            textNumber[i].setText("0");

                            break;
                        }
                    }

                }
            }
        };

        for(int i = 0 ; i <  5 ;i++)
        {
            removeBtn[i].setTag(i);
            removeBtn[i].setOnClickListener(removeNumber);
        }

        for (int i = 0; i< numberId.length; i++)
        {
           buttons[i].setTag(i+1);

           buttons[i].setOnClickListener(buttonClick);
        }

        clearNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0 ; i < 5 ; i++)
                {
                    textNumber[i].setText("0");
                    testLuck.setBackgroundColor(Color.RED);
                    count = 0;
                }
            }
        });

        testLuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count >= 5)
                {
                    int myNumber[] = new int[5];
                    for(int i = 0 ; i < 5; i++)
                    {
                        myNumber[i] = Integer.parseInt(textNumber[i].getText().toString());
                    }
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("myNumber", myNumber );
                    startActivityForResult(intent, REQUEST_CODE);
                }

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                for(int i = 0 ; i < 5 ; i++)
                {
                    textNumber[i].setText("0");
                    testLuck.setBackgroundColor(Color.RED);
                    count = 0;
                }
            }
        }
    }
}