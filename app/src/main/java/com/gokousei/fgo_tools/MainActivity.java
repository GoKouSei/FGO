package com.gokousei.fgo_tools;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainActivity extends BaseActivity {

    TextView mTevFireCalc;
    TextView mTevFireQuantity;
    EditText mEdtCurLevel;
    EditText mEdtAimsLevel;
    Button mBtnConfirm;
    Resources mRes;
    DrawerLayout mDrawerLayout;
    Spinner mSpinIsWithRank;
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mClick();
        mVebView();
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

    public void init() {
        mRes = getResources();
        mTevFireCalc = findViewById(R.id.leftSlip_tev_fireCalc);
        mEdtCurLevel = findViewById(R.id.fireCalc_edt_curLevel);
        mEdtAimsLevel = findViewById(R.id.fireCalc_edt_aimsLevel);
        mBtnConfirm = findViewById(R.id.fireCalc_btn_confirm);
        mTevFireQuantity = findViewById(R.id.fireCalc_tev_quantity);
        mDrawerLayout = findViewById(R.id.main_drawerLayout);
        mSpinIsWithRank = findViewById(R.id.fireCalc_spin_isWithRank);
        mSpinIsWithRank.setSelection(0);
        mWebView=findViewById(R.id.webView);
    }

    public void mClick() {
        mTevFireCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = findViewById(R.id.main_include_loadMainView);
                view1.setVisibility(View.VISIBLE);
                mDrawerLayout.closeDrawers();
            }
        });
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FireCalc fireCalc;
                int isWith = mSpinIsWithRank.getSelectedItemPosition();
                if (mEdtCurLevel.getText().toString().equals("")) {
                    makeToast("请输入目前等级");
                    return;
                }
                int curLevel = Integer.parseInt(mEdtCurLevel.getText().toString());
                if (curLevel == 0) {
                    curLevel++;
                }
                if (mEdtAimsLevel.getText().toString().equals("")) {
                    makeToast("请输入目标等级");
                    return;
                }
                int aimsLevel = Integer.parseInt(mEdtAimsLevel.getText().toString());
                if (aimsLevel < curLevel) {
                    makeToast("请不要输入小于目前等级的目标等级");
                    return;
                }
                int fireQuantity;
                fireCalc = new FireCalc(mRes, MainActivity.this, curLevel, aimsLevel, isWith);
                fireQuantity = fireCalc.fireQuantity();
                mTevFireQuantity.setText(fireQuantity + "");
            }
        });
        mSpinIsWithRank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                mTevFireQuantity.setText(new FireCalc(mRes).fireQuantity() + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void mVebView(){
        mWebView.setVisibility(View.VISIBLE);
        WebSettings mWebSettings=mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl("https://kazemai.github.io/fgo-vz/");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
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
