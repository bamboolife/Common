package com.bamboo.sample.bean;

import io.reactivex.internal.operators.single.SingleDoAfterTerminate;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/4/10 6:54 AM
 * 描述：
 */
public class Data {
    private int mValue=0;
    private String mUnit1=" 元";
    private String mUnit2="美金";

    public Data(String unit1, String unit2) {
        mUnit1 = unit1;
        mUnit2 = unit2;
    }

    public Data(int value) {
        mValue = value;
    }
    public Data(){

    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }

    public String getUnit1() {
        return mUnit1;
    }

    public void setUnit1(String unit1) {
        mUnit1 = unit1;
    }

    public String getUnit2() {
        return mUnit2;
    }

    public void setUnit2(String unit2) {
        mUnit2 = unit2;
    }
}
