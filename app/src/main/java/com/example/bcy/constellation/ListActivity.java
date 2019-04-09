package com.example.bcy.constellation;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
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
    TextView title, content;

    String myJSON ;
    Integer id;
    int index =MainListActivity.index;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_CONTENT = "content";

    JSONArray stars = null;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_page);

        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);

        getData("http://10.96.123.15/PHP_connection.php");
    }

    protected void showList() {
        try {
                if(myJSON != null) {
                    JSONObject jsonObj = new JSONObject(myJSON);
                    stars = jsonObj.getJSONArray(TAG_RESULTS);

                    for (int i = 0; i < stars.length(); i++) {
                        JSONObject c = stars.getJSONObject(i);
                        id = Integer.parseInt(c.getString(TAG_ID));
                        if ((id - 1) == index) {
                            title.setText(c.getString(TAG_TITLE));
                            content.setText(c.getString(TAG_CONTENT));
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
