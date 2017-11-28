package com.gokousei.fgo_tools;

import android.content.res.Resources;

public class ServantData {
    private String[] servant;

    public ServantData(Resources res) {
        servant = res.getStringArray(R.array.servant);
    }

    public String[] getServantData(){
        return servant;
    }

}
