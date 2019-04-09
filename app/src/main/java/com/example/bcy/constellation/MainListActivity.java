package com.example.bcy.constellation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainListActivity extends AppCompatActivity {

    static final String[] LIST_MENU = {
            "거문고자리", "게자리", "고니자리", "고래자리", "고물자리", "공기펌프자리", "공작자리", "궁수자리",
            "그물자리", "극락조자리", "기린자리", "까마귀자리", "나침반자리", "날치자리", "남십자자리", "남쪽물고기자리",
            "남쪽삼각형자리", "남쪽왕관자리", "도마뱀자리", "독수리자리", "돌고래자리", "돛자리", "두루미자리", "마차부자리",
            "망원경자리", "머리털자리", "목동자리", "물고기자리", "물뱀자리", "물병자리", "바다뱀자리", "방패자리",
            "뱀자리", "뱀주인자리", "봉황자리", "북쪽왕관자리", "비둘기자리", "사냥개자리", "사자자리", "살쾡이자리",
            "삼각형자리", "세페우스자리", "센타우루스자리", "시계자리", "쌍둥이자리", "안드로메다자리", "양자리", "염소자리",
            "에리다누스자리", "오리온자리", "외뿔소자리", "용자리", "용골자리", "육분의자리", "이리자리", "인디언자리",
            "작은개자리", "작은곰자리", "작은사자자리", "작은여우자리", "전갈자리", "제단자리", "조각가자리", "조각칼자리",
            "조랑말자리", "직각자자리", "처녀자리", "천칭자리", "카멜레온자리", "카시오페이아자리", "컴퍼스자리", "컵자리",
            "큰개자리", "큰곰자리", "큰부리새자리", "테이블산자리", "토끼자리", "파리자리", "팔분의자리", "페가수스자리",
            "페르세우스자리", "현미경자리", "헤라클레스자리", "화가자리", "화로자리", "화살자리", "황새치자리", "황소자리",
            };

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText search_text;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;

    public static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list_page);

        listView = findViewById(R.id.mainList);
        search_text = findViewById(R.id.search_text);
        list = new ArrayList<String>();

        settingList();

        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        adapter = new SearchAdapter(list, this);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = search_text.getText().toString();
                search(text);
            }
        });

    }

    public void search(String charText) {
        list.clear();

        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        else {
            for(int i = 0;i < arraylist.size(); i++) {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText)) {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 어답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    private void settingList(){
        int i = 0;
        while(i != LIST_MENU.length){
            list.add(LIST_MENU[i]);
            i++;
        }
    }

}