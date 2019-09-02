package test.yf.cp;

import java.util.HashMap;
import java.util.Map;

public class Basic3D {


    public  static final  int[]   MULTIPLENUM={ 3,5,6,8};  //多个待选数范围

    public  static final  int  GETNUM=3;    //取数个数

    public  static final  int  SUMSTART=88;   //和值开始

    public static final int SUMEND=123;       //和值结束

    public static final int TAILSTART=12;       //和尾开始

    public static final int TAILEND=19;       //和尾结束

    public static final int ACSTART=9;       //AC开始

    public static final int ACEND=10;       //AC结束

    public static final int SKIPSTART=23;       //跨度开始

    public static final int SKIPEND=29;       //跨度结束




    //获取奇偶比
    public static Map<String,String> getJudgeAndOdd(int [] a){
        Map<String,String>  result=new HashMap<>();
        int  odd=0;
        int  judge=0;
        for(int i=0;i<a.length;i++){
            if((a[i]&1)!=1){
                odd++;
            }
            if((a[i]&1)!=0){

                judge++;
            }
        }
        result.put("奇偶",judge+":"+odd);
        return result;
    }


   //获取大小比
    public static  Map<String,String>  getBigOrSmall(int [] a){
        Map<String,String>  result=new HashMap<>();
        int  small=0;
        int  big=0;
        for(int i=0;i<a.length;i++){
            if(a[i]>=1&a[i]<17){
                small++;
            }
            if(a[i]>=17&&a[i]<=33){

                big++;
            }
        }
        result.put("大小",small+":"+big);
        return result;
    }


    //区比
    public  static Map<String,String>  getArea(int[] a){
        Map<String,String>  resultCn=new HashMap<>();
        int  firstArea=0;   //1~8
        int  twoArea=0;     //二区8~16
        int  threeArea=0;   //三区17~25
        int  fourArea=0;    //四区26~33
        for(int i=0;i<a.length;i++){
            if(a[i]>0&&a[i]<=8){
                firstArea++;
            }
            if(a[i]>8&&a[i]<=16){
                twoArea++;
            }
            if(a[i]>16&&a[i]<=25){
                threeArea++;
            }
            if(a[i]>25&&a[i]<=33){
                fourArea++;
            }
        }
        resultCn.put("区比",firstArea+":"+twoArea+":"+threeArea+":"+fourArea);
        return resultCn;
    }





}
