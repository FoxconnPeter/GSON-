package com.liqipeter.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

//https://api.douban.com/v2/book/1220562

public class MainActivity extends AppCompatActivity {
    private BookListAdapter adapter;


    @Bind(R.id.listview)
    ListView lv;
    private String url = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/50/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData();

    }

    private void getData() {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String arg0) {
                Log.i("info",arg0);
                try {
                    dealData(arg0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        Volley.newRequestQueue(getApplicationContext()).add(request);
    }

    private void dealData(String arg0) throws JSONException{
        Gson gson = new Gson();
        Type listType=new TypeToken<ArrayList<Book>>(){

        }.getType();
           JSONObject object = new JSONObject(arg0);

        ArrayList<Book> books = gson.fromJson(object.getString("results"),listType);

        adapter = new BookListAdapter(this,books);
        lv.setAdapter(adapter);

    }


}
