package com.gokousei.fgo_tools;

import com.google.gson.Gson;

public  class JSONAnalyze {
    private Gson gson;
    private  static JSONAnalyze instance =new JSONAnalyze();

    private JSONAnalyze(){
        gson =new Gson();
    }

    public static JSONAnalyze getInstance(){
        return instance;
    }

    private ServantDataBean analyze(String json){
        ServantDataBean servantDataBean =new ServantDataBean();
        servantDataBean = gson.fromJson(json, ServantDataBean.class);
        return servantDataBean;
    }
}
