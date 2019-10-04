package com.nested.Qi;

public class ExampleItem {
    private String mText1;
    private String mText2;
    private double mX;
    private double mY;
    private String mPhoneNumber;
    private String mImageUrl;
    private String mAllText;


    public ExampleItem(String text1 , String text2,double x,double y,String phone,String imageUrl,String alltext){
        mText1=text1;
        mText2=text2;
        mX=x;
        mY=y;
        mPhoneNumber=phone;
        mImageUrl=imageUrl;
        mAllText=alltext;

    }
    public void changeText1(String text){
        mText1 = text;
    }
    public String getmText1(){
        return mText1;
    }
    public String getmText2(){
        return mText2;
    }
    public double getmX(){
        return mX;
    }
    public double getmY(){
        return mY;
    }
    public String getmPhoneNumber(){return mPhoneNumber;}
    public String getmImageUrl(){return mImageUrl;}
    public String getmAllText(){return mAllText;}


}
