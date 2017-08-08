package com.gokousei.fgo_tools;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainActivity extends BaseActivity {

    TextView tevGame;
    String str="test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        tevGame = findViewById(R.id.Main_tev_Game);
    }

    public void loadData(View view) {
        new Thread(runnable).start();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Document doc = Jsoup.connect("http://fgowiki.com/").get();
                Elements game = doc.select("div.header-container");
//               str = game.get(0).select("ul").select("li").select("a").get(0).text();
               str =BASE_URL+game.select("ul").select("li").select("a").get(0).attr("href");
                handler.sendEmptyMessage(0);
            } catch (Exception e) {
                Log.d(LOG_TAG, e.toString());
            }
        }
    };

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            tevGame.setText(str);
        }
    };
}
