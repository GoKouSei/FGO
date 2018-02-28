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
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainActivity extends BaseActivity {

    private TextView mTevFireQuantity; //狗粮计算界面 计算结果输出
    private EditText mEdtCurLevel; //狗粮计算界面 当前等级的输入框
    private EditText mEdtAimsLevel; //狗粮计算界面 目标等级的输入框
    private Button mBtnConfirm; //狗粮计算界面 计算按钮
    private Resources mRes;
    private Spinner mSpinIsWithRank; //狗粮计算界面 符合职阶与否下拉选择
    private WebView mWebView; //WebView
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init(); //初始化方法
        mListener(); //事件监听方法
    }

    @Override
    protected void onResume() {
        if (mWebView != null) {
            mWebView.getSettings().setJavaScriptEnabled(true); //启用JS支持
        }
        super.onResume();
    }

    @Override
    protected void onStop() {
        if (mWebView != null) {
            mWebView.getSettings().setJavaScriptEnabled(false); //关闭JS支持
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null) { //释放WebView资源
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory(); //清除历史
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    //初始化操作
    private void init() {
        mRes = getResources();
        mEdtCurLevel = findViewById(R.id.fireCalc_edt_curLevel); //当前等级输入框
        mEdtAimsLevel = findViewById(R.id.fireCalc_edt_aimsLevel); //目标等级输入框
        mBtnConfirm = findViewById(R.id.fireCalc_btn_confirm); //计算按钮
        mTevFireQuantity = findViewById(R.id.fireCalc_tev_quantity); //显示计算结果
        mSpinIsWithRank = findViewById(R.id.fireCalc_spin_isWithRank); //选择是否符合职阶下拉框
        mSpinIsWithRank.setSelection(0); //设置下拉框默认选择项
        mWebView = findViewById(R.id.web_view); //初始化WebView
        mRecyclerView = findViewById(R.id.servant);//初始化RecyclerView
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,OrientationHelper.VERTICAL));//线性宫格显示 类似瀑布流
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));//线性宫格显示 类似Grid View
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//线性显示 类似ListView
        mRecyclerView.setAdapter(new ServantAdapter(this));//设置adapter
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));//横向分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));//垂直分割线
    }

    private InputFilter mFilter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if (Integer.parseInt(source.toString()) > 100) {
                return "";
            }
            return null;
        }
    };

    //侧滑界面项点击事件
    public void leftSlipSelect(View view) {
        DrawerLayout mDrawerLayout = findViewById(R.id.main_drawerLayout);
        LinearLayout mLinearLayout = findViewById(R.id.main_group);
        View viewFireCalc = findViewById(R.id.main_include_loadFireCalc);
        View viewWeb = findViewById(R.id.main_include_loadWeb);
        View viewServant = findViewById(R.id.servant);
        String url;
        viewServant.setVisibility(View.GONE);
        viewFireCalc.setVisibility(View.GONE);
        viewWeb.setVisibility(View.GONE);
        mLinearLayout.setVisibility(View.GONE);
        switch (view.getId()) { //根据点击的选项显示隐藏界面
            case R.id.leftSlip_tev_rxjw:
                viewWeb.setVisibility(View.VISIBLE);
                url = "http://kazemai.github.io/fgo-vz/";
                mWebView(url);
                break;
            case R.id.leftSlip_tev_fgowiki:
                viewWeb.setVisibility(View.VISIBLE);
                url = "http://fgowiki.com/";
                mWebView(url);
                break;
            case R.id.leftSlip_tev_fireCalc:
                viewFireCalc.setVisibility(View.VISIBLE);
                break;
            case R.id.leftSlip_tev_servant:
                break;
        }
        if ((view.getId() != R.id.leftSlip_tev_rxjw) && (view.getId() != R.id.leftSlip_tev_fgowiki)) {
            if (mWebView != null) {
                mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            }
        }
        mDrawerLayout.closeDrawers(); //关闭侧滑界面
    }

    //事件监听
    private void mListener() {
        //狗粮计算界面计算按钮点击事件
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
                if (curLevel > 99) {
                    makeToast("目前等级不能大于100");
                    return;
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
                if (aimsLevel > 100) {
                    makeToast("目标等级不能大于100");
                    return;
                }
                if (aimsLevel == 0) {
                    aimsLevel++;
                }
                int fireQuantity;
                fireCalc = new FireCalc(mRes, MainActivity.this, curLevel, aimsLevel, isWith);
                fireQuantity = fireCalc.fireQuantity();
                mTevFireQuantity.setText(fireQuantity + "");
            }
        });
    }

    /**
     * WebView的初始化和设置方法
     *
     * @param url 载入的地址
     */
    public void mWebView(String url) {
//        final TextView textView=findViewById(R.id.web_tev_title);
        //用来显示当前页面载入进度
        final Snackbar sk = Snackbar.make(mWebView, "载入中..", Snackbar.LENGTH_INDEFINITE);
        mWebView.clearHistory();
        WebSettings mWebSettings = mWebView.getSettings();
        //设置使访问的页面中支持与Javascript交互
        // 如果加载的页面里有JS在执行动画等操作 会造成资源浪费（CPU 电量）
        // 需要在onStop和onResume分别把值置成false和true
        mWebSettings.setJavaScriptEnabled(true);

        //设置自适应屏幕，两者合用
        mWebSettings.setUseWideViewPort(true); //将图片调整到适合WebView的大小
        mWebSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        mWebSettings.setSupportZoom(true); //支持缩放 默认为true 是下面那个的前提。
        mWebSettings.setBuiltInZoomControls(true); //设置内置的缩放控件若为false 则不可缩放
        mWebSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT); //设置WebView中缓存方式为默认
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
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress < 100) {
//                    String progress = newProgress + "%";
//                    sk.setText("载入中" + progress);
//                    sk.show();
//                }
//                super.onProgressChanged(view, newProgress);
//            }

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
            }//打开网页是不调用系统浏览器而是在此WebView打开

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
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
        if (mWebView != null) {
            if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
                mWebView.goBack(); //按返回键后退
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void loadData(View view) {
        new Thread(getData).start();
    } //启动线程

    Runnable getData = new Runnable() {
        @Override
        public void run() { //使用Jsoup获取网页指定数据
            try {
                Document doc = Jsoup.connect("http://fgowiki.com/").get();
                Elements game = doc.select("div.header-container");
//               str = game.get(0).select("ul").select("li").select("a").get(0).text();
                String str = BASE_URL + game.select("ul").select("li").select("a").get(0).attr("href");
                setMessage.sendEmptyMessage(0); //使用handle更改UI
            } catch (Exception e) {
                Log.d(LOG_TAG, e.toString());
            }
        }
    };

    Handler setMessage = new Handler() {
        @Override
        public void handleMessage(Message msg) { //设置Text
            super.handleMessage(msg);
//            tevGame.setText(str);
        }
    };
}
