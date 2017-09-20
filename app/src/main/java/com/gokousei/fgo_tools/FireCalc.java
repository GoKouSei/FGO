package com.gokousei.fgo_tools;

import android.content.Context;
import android.content.res.Resources;

public class FireCalc {
    private int mCurLevel;
    private int mAimsLevel;
    private int mServantRare;
    private int mSameFireExp;
    private int mUnSameFireExp;
    private Context mContext;
    private Resources mRes;

    public FireCalc(Resources res, Context context, int curLevel, int aimsLevel, int servantRare) {
        if (curLevel == 0) {
            curLevel = 0;
        }
        if (aimsLevel == 0) {
            aimsLevel = 0;
        }
        if (servantRare == 0) {
            servantRare = 0;
        }
        mCurLevel = curLevel;
        mAimsLevel = aimsLevel;
        mServantRare = servantRare;
        mSameFireExp = 32400;
        mUnSameFireExp = 27000;
        mRes = res;
    }

    public int fireQuantity() {
        int mFireQuantity;
        int mNeedExp;
        int mExp[] = mRes.getIntArray(R.array.servant_exp);
        mNeedExp = mExp[mAimsLevel] - mExp[mCurLevel];
        mFireQuantity = mNeedExp / mSameFireExp;
        if (mNeedExp%mSameFireExp!=0){
            mFireQuantity++;
        }
        return mFireQuantity;
    }
}
