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
//                根据输入等级计算经验值
//                EditText editText = findViewById(R.id.editText);
//                TextView textView = findViewById(R.id.textView5);
//                String inputLevel = editText.getText().toString(); 输入等级
//                int level;   需要计算的等级
//                int expSum = 0;   初始化总经验
//                level = Integer.parseInt(inputLevel); 获得待计算等级
//                for (int i = 0; i < level; i++) {
//                    expSum += i * (i + 1) / 2;
//                }
//                expSum = expSum * 100; 得到总经验值
//                textView.setText(expSum + "");
}
