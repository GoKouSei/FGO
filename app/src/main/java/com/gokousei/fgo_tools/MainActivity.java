package com.gokousei.fgo_tools;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainActivity extends BaseActivity {

    TextView mFireCalc;
    TextView mFireQuantity;
    EditText mCurLevel;
    EditText mAimsLevel;
    EditText mServantRare;
    Button mConfirm;
    Resources mRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mClick();
//        Button button = findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText editText = findViewById(R.id.editText);
//                TextView textView = findViewById(R.id.textView5);
//                String inputLevel = editText.getText().toString();
//                int level;
//                int expSum = 0;
//                level = Integer.parseInt(inputLevel);
//                for (int i = 0; i < level; i++) {
//                    expSum += i * (i + 1) / 2;
//                }
//                expSum = expSum * 100;
//                textView.setText(expSum + "");
//            }
//        });
    }

    public void init(){
        mRes =getResources();
        mFireCalc = findViewById(R.id.leftSlip_Tev_DogFoodCalculate);
        mCurLevel =findViewById(R.id.fireCalc_edt_curLevel);
        mAimsLevel =findViewById(R.id.fireCalc_edt_aimsLevel);
//        mServantRare=findViewById(R.id.fireCalc_edt_servantRare);
        mConfirm=findViewById(R.id.fireCalc_btn_confirm);
        mFireQuantity=findViewById(R.id.fireCalc_tev_quantity);
    }
    public void mClick(){
        mFireCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = findViewById(R.id.loadMainView);
                view1.setVisibility(View.VISIBLE);
                View view2 = findViewById(R.id.tevTest);
                view2.setVisibility(View.GONE);
            }
        });
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FireCalc fireCalc;
                int curLevel=Integer.parseInt(mCurLevel.getText().toString());
                int aimsLevel=Integer.parseInt(mAimsLevel.getText().toString());
                int fireQuantity;
                fireCalc=new FireCalc(mRes,MainActivity.this,curLevel,aimsLevel,4);
                fireQuantity=fireCalc.fireQuantity();
                mFireQuantity.setText(fireQuantity+"");
            }
        });
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
                String str = BASE_URL + game.select("ul").select("li").select("a").get(0).attr("href");
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
