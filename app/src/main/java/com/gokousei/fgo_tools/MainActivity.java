package com.gokousei.fgo_tools;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    Spinner mSpinIsWithRank;
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mClick();
    }

    @Override
    protected void onResume() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        super.onResume();
    }

    @Override
    protected void onStop() {
        mWebView.getSettings().setJavaScriptEnabled(false);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    //初始化操作
    public void init() {
        mRes = getResources();
        mTevFireCalc = findViewById(R.id.leftSlip_tev_fireCalc);
        mEdtCurLevel = findViewById(R.id.fireCalc_edt_curLevel);
        mEdtAimsLevel = findViewById(R.id.fireCalc_edt_aimsLevel);
        mBtnConfirm = findViewById(R.id.fireCalc_btn_confirm);
        mTevFireQuantity = findViewById(R.id.fireCalc_tev_quantity);
        mSpinIsWithRank = findViewById(R.id.fireCalc_spin_isWithRank);
        mSpinIsWithRank.setSelection(0);
        mWebView = findViewById(R.id.web_view);
    }

    //侧滑界面项点击事件
    public void leftSlipSelect(View view) {
        DrawerLayout mDrawerLayout = findViewById(R.id.main_drawerLayout);
        LinearLayout mLinearLayout = findViewById(R.id.main_group);
        View viewFireCalc = findViewById(R.id.main_include_loadFireCalc);
        View viewWeb = findViewById(R.id.main_include_loadWeb);
        String url;
        viewFireCalc.setVisibility(View.GONE);
        viewWeb.setVisibility(View.GONE);
        mLinearLayout.setVisibility(View.GONE);
        switch (view.getId()) {
            case R.id.leftSlip_tev_rxjw:
                viewWeb.setVisibility(View.VISIBLE);
                url = "http://kazemai.github.io/fgo-vz/";
                mVebView(url);
                break;
            case R.id.leftSlip_tev_fgowiki:
                viewWeb.setVisibility(View.VISIBLE);
                url = "http://fgowiki.com/";
                mVebView(url);
                break;
            case R.id.leftSlip_tev_fireCalc:
                viewFireCalc.setVisibility(View.VISIBLE);
        }
        mDrawerLayout.closeDrawers();
    }

    public void mClick() {
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

    public void mVebView(String url) {
//        final TextView textView=findViewById(R.id.web_tev_title);
        final Snackbar sk = Snackbar.make(mWebView, "载入中..", Snackbar.LENGTH_INDEFINITE);
        mWebView.clearHistory();
        WebSettings mWebSettings = mWebView.getSettings();
        //设置使访问的页面中支持与Javascript交互
        // 如果加载的页面里有JS在执行动画等操作 会造成资源浪费（CPU 电量）
        // 需要在onStop和onResume分别把值置成false和true
        mWebSettings.setJavaScriptEnabled(true);

        //设置自适应屏幕，两者合用
        mWebSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        mWebSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        mWebSettings.setSupportZoom(true); //支持缩放 默认为true 是下面那个的前提。
        mWebSettings.setBuiltInZoomControls(true); //设置内置的缩放控件若为false 则不可缩放
        mWebSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT); //设置webview中缓存方式为默认
        //缓存模式如下:
        //LOAD_CACHE_ONLY:不使用网络只读取本地缓存数据
        //LOAD_DEFAULT:根据cache-control决定是否从网络上取数据
        //LOAD_NO_CACHE:不使用缓存 只从网络获取数据
        //LOAD_CACHE_ELSE_NETWORK:只要本地有 无论是否过期 或者no-cache 都使用缓存的数据

        mWebSettings.setAllowFileAccess(true); //设置可以访问文件
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        mWebSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        mWebSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    sk.setText("载入中" + progress);
                    sk.show();
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
//                textView.setText(title);
                super.onReceivedTitle(view, title);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("JsAlert")
                        .setMessage(message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        })
                        .setCancelable(false)
                        .show();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("JsConfirm")
                        .setMessage(message)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.cancel();
                            }
                        })
                        .setCancelable(false)
                        .show();
                // 返回布尔值：判断点击时确认还是取消
                // true表示点击了确认；false表示点击了取消；
                return true;
            }
        });

        mWebView.loadUrl(url);//载入地址
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }//打开网页是不调用系统浏览器而是在此webview打开

            @Override
            public void  onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view,url,favicon);
            }//页面开始加载时调用

            @Override
            public void onPageFinished(WebView view, String url) {
                if (sk.isShown()) {
                    sk.dismiss();
                }
                super.onPageFinished(view, url);
            }//页面加载完成是调用
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
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
