package com.example.bcy.constellation;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListActivity extends Activity {

    ImageView image;
    TextView title_kr, title_en;
    Button next_btn;

    String myJSON ;
    static int id;
    static String content, location;
    String title = MainListActivity.title;

    int starImg[] = {R.drawable.lyra, R.drawable.cancer, R.drawable.cetus, R.drawable.puppis, R.drawable.antlia, R.drawable.pavo, R.drawable.sagittarius, R.drawable.reticulum,
                     R.drawable.apus, R.drawable.camelopardalis, R.drawable.corvus, R.drawable.pyxis, R.drawable.volans, R.drawable.crux, R.drawable.piscisaustrinus, R.drawable.triangulumaustrale,
                     R.drawable.coronaaustralis, R.drawable.lacerta, R.drawable.aquila, R.drawable.delphinus, R.drawable.vela, R.drawable.grus, R.drawable.auriga, R.drawable.telescopium,
                     R.drawable.comaberenices, R.drawable.bootes, R.drawable.pisces, R.drawable.hydrus, R.drawable.aquarius, R.drawable.hydra, R.drawable.scutum, R.drawable.cygnus,
                     R.drawable.serpens, R.drawable.ophiuchus, R.drawable.phoenix, R.drawable.coronaaustralis, R.drawable.columba, R.drawable.canesvenatici, R.drawable.leo, R.drawable.lynx,
                     R.drawable.triangulum, R.drawable.horologium, R.drawable.gemini, R.drawable.andromeda, R.drawable.aries, R.drawable.capricornus, R.drawable.eridanus, R.drawable.orion,
                     R.drawable.monoceros, R.drawable.draco, R.drawable.carina, R.drawable.sextans, R.drawable.lupus, R.drawable.indus, R.drawable.canisminor, R.drawable.ursaminor,
                     R.drawable.leominor, R.drawable.vulpecula, R.drawable.scorpius, R.drawable.ara, R.drawable.sculptor, R.drawable.caelum, R.drawable.equuleus, R.drawable.norma,
                     R.drawable.virgo, R.drawable.libra, R.drawable.chamaeleon, R.drawable.cassiopeia, R.drawable.circinus, R.drawable.crater, R.drawable.cepheus, R.drawable.centaurus,
                     R.drawable.canismajor, R.drawable.ursamajor, R.drawable.tucana, R.drawable.mensa, R.drawable.lepus, R.drawable.musca, R.drawable.octans, R.drawable.pegasus,
                     R.drawable.perseus, R.drawable.microscopium, R.drawable.hercules, R.drawable.pictor, R.drawable.fornax, R.drawable.sagitta, R.drawable.dorado, R.drawable.taurus};

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE_KR = "title_kr";
    private static final String TAG_TITLE_EN = "title_en";
    private static final String TAG_LOCATION = "location";
    private static final String TAG_CONTENT = "content";


    JSONArray stars = null;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_page);

        image = findViewById(R.id.image);
        title_kr = findViewById(R.id.title_kr);
        title_en = findViewById(R.id.title_en);
        next_btn = findViewById(R.id.next_btn);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListExplainActivity.class);
                startActivity(intent);
            }
        });

        getData("http://10.96.124.88/php_connection.php");
    }

    protected void showList() {
        String str = null;
        try {
                if(myJSON != null) {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    stars = jsonObj.getJSONArray(TAG_RESULTS);

                    for (int i = 0; i < stars.length(); i++) {
                        JSONObject c = stars.getJSONObject(i);
                        str = c.getString(TAG_TITLE_KR);
                        if (title.equals(str)) {
                            id = c.getInt(TAG_ID);
                            title_kr.setText(c.getString(TAG_TITLE_KR));
                            title_en.setText(c.getString(TAG_TITLE_EN));
                            content = c.getString(TAG_CONTENT);
                            location = c.getString(TAG_LOCATION);
                            image.setImageResource(starImg[(id-1)]);
                            break;
                        }
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "데이터 불러오기를 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];
                BufferedReader bufferedReader = null;

                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}
