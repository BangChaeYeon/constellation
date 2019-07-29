package com.example.bcy.constellation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class QuizResultActivity extends AppCompatActivity {

    TextView resultValue;
    TextView quizResult;
    TextView imgLeft;
    TextView imgRight;
    TextView count;

    Button restart, home;

    ImageView quizImg; // 이미지
    int imgRanId = Quiz_page.quizNumArr[0]; // quiz_page에서 가져온 랜덤 값
    int imgCnt = 0; // 이미지 index 카운트
    String quiz_result_title = ""; // 정답 (txt)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_result_page);

        resultValue = (TextView) findViewById(R.id.resultValue);
        restart = findViewById(R.id.restart);
        home = findViewById(R.id.home);
        quizImg = (ImageView) findViewById(R.id.quizImg); // 퀴즈 이미지
        quizResult = (TextView) findViewById(R.id.quizResult); // 퀴즈 정답 (text)
        imgLeft = (TextView) findViewById(R.id.imgLeft);  // 다음 문제 결과로 움직일 text  "<"
        imgRight = (TextView) findViewById(R.id.imgRight); // 다음 문제 결과로 움직일 text  ">"
        count = (TextView) findViewById(R.id.count); // 몇 개가 지나갔는지

        imgLeft.setText("<");
        imgRight.setText(">");

        titleNameChk(imgRanId);

        resultValue.setText("맞춘 개수: " + Quiz_page.quizResult + "개");
        quizResult.setText("정답 : " +  quiz_result_title);
        count.setText("" + (imgCnt+1) + " / 12");

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quiz_page.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // 이미지 사진........
        titleNameChk(imgRanId);

        // 이벤트
        imgLeft.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                --imgCnt;
                if(imgCnt < 0){
                    imgCnt = 11;
                }
                imgRanId = Quiz_page.quizNumArr[imgCnt];
                titleNameChk(imgRanId);
                quizResult.setText("정답 :" +  quiz_result_title);
                count.setText("" + (imgCnt+1) + " / 12");

            }
        }); // imgLeft

        imgRight.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++imgCnt;
                if(imgCnt > 11){
                    imgCnt = 0;
                }
                imgRanId = Quiz_page.quizNumArr[imgCnt];
                titleNameChk(imgRanId);
                quizResult.setText("정답 :" +  quiz_result_title);
                count.setText("" + (imgCnt+1) + " / 12");

            }
        }); // imgRight

    } // onCreate

    protected  void titleNameChk(int imgRanId){
        if(imgRanId == 1) { quiz_result_title = "염소자리";   quizImg.setImageResource(R.drawable.capricornus);}
        else if(imgRanId == 2) { quiz_result_title = "물병자리";   quizImg.setImageResource(R.drawable.aquarius);}
        else if(imgRanId == 3) { quiz_result_title = "물고기자리"; quizImg.setImageResource(R.drawable.pisces);}
        else if(imgRanId == 4) { quiz_result_title = "양자리";     quizImg.setImageResource(R.drawable.aries);}
        else if(imgRanId == 5) { quiz_result_title = "황소자리";   quizImg.setImageResource(R.drawable.taurus);}
        else if(imgRanId == 6) { quiz_result_title = "쌍둥이자리";   quizImg.setImageResource(R.drawable.gemini);}
        else if(imgRanId == 7) { quiz_result_title = "게자리";   quizImg.setImageResource(R.drawable.cancer);}
        else if(imgRanId == 8) { quiz_result_title = "사자자리";   quizImg.setImageResource(R.drawable.leo);}
        else if(imgRanId == 9) { quiz_result_title = "처녀자리";   quizImg.setImageResource(R.drawable.virgo);}
        else if(imgRanId == 10) { quiz_result_title = "천칭자리";   quizImg.setImageResource(R.drawable.libra);}
        else if(imgRanId == 11) { quiz_result_title = "전갈자리";   quizImg.setImageResource(R.drawable.scorpius);}
        else if(imgRanId == 12) { quiz_result_title = "궁수자리";   quizImg.setImageResource(R.drawable.sagittarius);}
    } // titleNameChk

} // class
