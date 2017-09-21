package com.gokousei.fgo_tools;

import android.content.Context;
import android.content.res.Resources;

public class FireCalc {
    private int mCurLevel;
    private int mAimsLevel;
    private int mWithFireExp;
    private int mUnWithFireExp;
    private Context mContext;
    private Resources mRes;
    private int mIsWithRank;

    public FireCalc(Resources res){
        mRes = res;
    }

    public FireCalc(Resources res, Context context, int curLevel, int aimsLevel, int isWithRank) {
        if (curLevel == 0) {
            curLevel = 0;
        }
        if (aimsLevel == 0) {
            aimsLevel = 0;
        }
        mCurLevel = curLevel;
        mAimsLevel = aimsLevel;
        mWithFireExp = 32400;
        mUnWithFireExp = 27000;
        mRes = res;
        mIsWithRank = isWithRank;
    }

    public int fireQuantity() {
        int mFireQuantity;
        int mNeedExp;
        int mExp[] = mRes.getIntArray(R.array.servant_exp);
        mNeedExp = mExp[mAimsLevel] - mExp[mCurLevel];
        if (mIsWithRank == 0) {
            mFireQuantity = mNeedExp / mWithFireExp;
        } else {
            mFireQuantity = mNeedExp / mUnWithFireExp;
        }
        if (mNeedExp % mWithFireExp != 0) {
            mFireQuantity++;
        }
        return mFireQuantity;
    }
}
