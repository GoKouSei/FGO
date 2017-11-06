package com.gokousei.fgo_tools;

import android.content.Context;
import android.content.res.Resources;

/**
 * The type Fire calc.
 */
public class FireCalc {
    /**
     * 当前等级
     */
    private int mCurLevel;
    private int mAimsLevel; //目标等级
    private int mWithFireExp; //同职阶种火经验
    private int mUnWithFireExp; //不同职阶种火经验
    private Context mContext; //Context对象
    private Resources mRes; //Resources对象
    private boolean mIsWithRank; //是否符合职阶

    //构造方法1
    public FireCalc(Resources res) {
        mRes = res;
    }

    //构造方法2
    public FireCalc(Resources res, Context context, int curLevel, int aimsLevel, int isWithRank) {
//        if (curLevel == 0) {
//            curLevel = 0;
//        }
//        if (aimsLevel == 0) {
//            aimsLevel = 0;
//        }
        mCurLevel = curLevel; //获得传递的当前等级
        mAimsLevel = aimsLevel; //获得传递的目标等级
        mWithFireExp = 32400; //初始化同职阶种火EXP值
        mUnWithFireExp = 27000; //初始化不同职阶种火EXP值
        mRes = res; //获得传递的Resources
        mIsWithRank = isWithRank == 0 ? true : false; //获得传递的职阶参数
    }

    /**
     * 所需的种火数量计算
     *
     * @return 所需的种火数量
     */
    public int fireQuantity() {
        int mFireQuantity; //所需数量计算的结果
        int mNeedExp; //需要的EXP
        int mExp[] = mRes.getIntArray(R.array.servant_exp); //读取EXP值阶梯
        mNeedExp = mExp[mAimsLevel] - mExp[mCurLevel]; //计算所需的EXP
        if (mIsWithRank) { //判断是否符合职阶
            mFireQuantity = mNeedExp / mWithFireExp; //计算符合职阶需要的种火数量
        } else {
            mFireQuantity = mNeedExp / mUnWithFireExp; //计算不符合职阶需要的种火数量
        }
        if (mNeedExp % mWithFireExp != 0) { //处理余数情况
            mFireQuantity++;
        }
        return mFireQuantity; //返回需要的种火数量
    }
//                //根据输入等级计算经验值
//                EditText editText = findViewById(R.id.editText);
//                TextView textView = findViewById(R.id.textView5);
//                String inputLevel = editText.getText().toString(); 获得输入等级
//                int level;   需要计算的等级
//                int expSum = 0;   初始化总经验
//                level = Integer.parseInt(inputLevel); 获得待计算等级
//                for (int i = 0; i < level; i++) {
//                    expSum += i * (i + 1) / 2;
//                }
//                expSum = expSum * 100; 得到总经验值
//                textView.setText(expSum + "");
}
