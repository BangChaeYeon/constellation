package com.example.bcy.constellation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Quiz_page extends AppCompatActivity {
    // timer
    CountDownTimer timer = null;

    // 임시 변수
    int timeChk = 0; // 시간 확인
    TextView testTimer; // 임시 타이머 체크 (view)

    ImageView quizImg; // 이미지
    int imgRanId; // 이미지 랜덤으로 돌릴 값
    Button quizBtn; // 확인 버튼

    EditText userTxt; // 사용자가 입력한 값
    TextView score; // <0/10>
    int userSc = 0; // score에 들어가 점수
    int answerChk = 0; // 정답, 오류 flag

    int allQuizCnt = 12; // 총 퀴즈 갯수
    int quizCnt = 0; // 퀴즈를 몇 개를 했는지?
    int btnClick = 0; // 버튼을 눌렀는지에 대한 flag
    ArrayList<Integer> quizNum = new ArrayList(); // 퀴즈 중복 문제 x.....

    // DB 관련된 변수
    String myJSON;
    Integer id;
    JSONArray stars = null;
    HashMap<String, String> DataList;
    public static int quizNumArr[] = {};
    int quizRanNum=0;

    TextView time;

    public static int quizResult = 0;

    private static final String TAG_ID = "id";
    private static final String TAG_RESULTS = "result";
    private static final String TAG_TITLE_KR = "title_en";


    String rand_title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        testTimer = (TextView) findViewById(R.id.testTimer);
        quizBtn = (Button) findViewById(R.id.quizBtn);
        score = (TextView) findViewById(R.id.score);
        quizImg = (ImageView) findViewById(R.id.quizImg);
        userTxt = (EditText) findViewById(R.id.userTxt);
        score.setText("O : " + userSc + " / X: " + (quizCnt-userSc));
        time = (TextView) findViewById(R.id.time) ;
        quizNumArr = new int[12];
        Random r = new Random();

        for(int i=0; i<12; i++) {
            quizNumArr[i] = r.nextInt(12)+1;
            for(int j=0; j<i; j++) {
                if(quizNumArr[i] == quizNumArr[j]) {
                    i--;
                }
            }
        }

        if(quizCnt >= allQuizCnt) {
            testTimer.setText("finish2");
            timer.cancel();
        }
        timeChk = 0;

        imgRanId = quizNumArr[quizRanNum];
        titleNameChk(imgRanId);
        timer =  new CountDownTimer(11000, 1000) { // 5초동안 1초씩 재실행

            public void onTick(long millisUntilFinished) {  // 타이머가 종료될 때까지 동작하는 코드
                // 시간 체크
                timeChk+=1;
                testTimer.setText("문제 | " + (quizCnt+1));
                time.setText("남은 시간 : " + (11-timeChk));

                quizBtn.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(rand_title.equals(userTxt.getText().toString())) { userSc += 1;   }
                        userTxt.setText(null);

                        onFinish();
                    }
                }); // quizBtn
            } // onTick()

            public void onFinish() { // 타이머가 종료될 때 실행되는 코드
                quizRanNum++;
                ++quizCnt;
                timer.cancel();
                score.setText("O : " + userSc + " / X: " + (quizCnt-userSc));
                quizTimer();
                if(quizCnt >= allQuizCnt){
                    testTimer.setText("finish");
                    timer.cancel();
                }
            } // onFinsh()
        }.start();
    } // onCreate

    public void quizTimer(){
        if(quizCnt >= allQuizCnt) {
            testTimer.setText("finish2");
            timer.cancel();
        }
        timeChk=0;

        imgRanId = quizNumArr[quizRanNum];
        titleNameChk(imgRanId);

        timer =  new CountDownTimer(11000, 1000) {
            public void onTick(long millisUntilFinished) {  // 타이머가 종료될 때까지 동작하는 코드
                // 시간 체크
                timeChk+=1;
                testTimer.setText("문제 | " + (quizCnt+1));
                time.setText("남은 시간 : " + (11-timeChk));

                // 이미지 랜덤 (= 아이디 값)

                // 확인 버튼 -> 사용자 입력한 값(view 가져오기)과 이미지 이름과 동일한지(DB)
                quizBtn.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btnClick = 1;
                        ++quizCnt;

                        if(rand_title.equals(userTxt.getText().toString())) { userSc += 1;   }
                        userTxt.setText(null);

                        onFinish();
                    }
                }); // quizBtn
            } // onTick()

            public void onFinish() { // 타이머가 종료될 때 실행되는 코드
                quizRanNum++;
                if(btnClick==0) { ++quizCnt;}
                else{  btnClick=0; }
                score.setText("O : " + userSc + " / X: " + (quizCnt-userSc));
                timer.cancel();
                if(quizCnt >= allQuizCnt) {
                    testTimer.setText("finish2" + " " + quizCnt);
                    if(quizCnt == 12){
                        quizResult = userSc;
                        Intent intent = new Intent(getApplicationContext(), QuizResultActivity.class);
                        startActivity(intent);
                    }
                }else{
                    quizTimer();
                }
            } // onFinsh()
        }.start();
    } // quizTimer class

    protected  void titleNameChk(int imgRanId){
        if(imgRanId == 1) { rand_title = "염소자리";   quizImg.setImageResource(R.drawable.capricornus);}
        else if(imgRanId == 2) { rand_title = "물병자리";   quizImg.setImageResource(R.drawable.aquarius);}
        else if(imgRanId == 3) { rand_title = "물고기자리"; quizImg.setImageResource(R.drawable.pisces);}
        else if(imgRanId == 4) { rand_title = "양자리";     quizImg.setImageResource(R.drawable.aries);}
        else if(imgRanId == 5) { rand_title = "황소자리";   quizImg.setImageResource(R.drawable.taurus);}
        else if(imgRanId == 6) { rand_title = "쌍둥이자리";   quizImg.setImageResource(R.drawable.gemini);}
        else if(imgRanId == 7) { rand_title = "게자리";   quizImg.setImageResource(R.drawable.cancer);}
        else if(imgRanId == 8) { rand_title = "사자자리";   quizImg.setImageResource(R.drawable.leo);}
        else if(imgRanId == 9) { rand_title = "처녀자리";   quizImg.setImageResource(R.drawable.virgo);}
        else if(imgRanId == 10) { rand_title = "천칭자리";   quizImg.setImageResource(R.drawable.libra);}
        else if(imgRanId == 11) { rand_title = "전갈자리";   quizImg.setImageResource(R.drawable.scorpius);}
        else if(imgRanId == 12) { rand_title = "궁수자리";   quizImg.setImageResource(R.drawable.sagittarius);}
//        Toast.makeText(getApplicationContext(), "imgRandId : " + imgRanId +" , rand_title : " + rand_title + ", cnt: "+quizCnt, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        timer.cancel();
    }
}
