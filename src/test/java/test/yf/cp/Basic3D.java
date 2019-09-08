package test.yf.cp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Basic3D {


    public  static final  int[]   HUNDREDNUM={ 1,2,3,4,5};  //百位待选数范围

    public static final   int[]  TENNUM={3,4,5,6,7,8,9};  //十位待选数范围

    public static  final  int[] BITNUM={2,3,4,5,6,7};   //个位待选数范围

    //public  static final  int  GETNUM=3;    //取数个数

    public  static final  int  SUMSTART=10;   //和值开始

    public static final int SUMEND=23;       //和值结束

    public static final int TAILSTART=4;       //和尾开始

    public static final int TAILEND=8;       //和尾结束

    public static final int ACSTART=0;       //AC开始

    public static final int ACEND=1;       //AC结束

    public static final int KDSTART=2;       //跨度开始

    public static final int KDEND=8;       //跨度结束

    //奇偶比
    public static final  String[] JUDEGANDODD={"0:3"};

    //大小比
    public static final String[]  BIGANDSMALL={"1:2","0:3"};

    //质合
    public static final String[] PRIMESUM={"1:2","0:3"};

    //余3比
    public static final  String[] EXPECTED3={"0:0:0","1:1:1","2:2:2"};

    //平均值
    public static  final  int[]  AVG={3,6};

    //余和
    public static final int[]  EXDSUM={3,5};


//和值

    public static boolean getSum3d(Integer[] a,int start,int end){
        boolean  flag=false;
      int sum=0;
      for (int i=0;i<a.length;i++){
          sum+=a[i];
      }
      if(sum>=start&&sum<=end){
         flag=true;
      }

    return flag;
    }

    //和尾
    public  static  boolean getSumEnd3d(Integer[] a,int start,int end){
        boolean flag=false;
        int sum=0;
        int sa=0;
        for(int i=0;i<a.length;i++){
            sa+=a[i];
        }
         if(sa>=0&&sa<=9){
             sum=sa;
         }
         if(sa>9&&sa<=27){
             sum=sa%10;
         }

         if(sum>=start&&sum<=end){
            flag=true;
         }

        return flag;
    }


    //跨度
    public static  boolean  getKd3d(Integer[] a,int start,int end){
        boolean flag=false;
        Arrays.sort(a);
         int kd=a[2]-a[0];
         if(kd>=start&&kd<=end){
             flag=true;
         }
        return  flag;
    }

    public static boolean  getAc3d(Integer[] a,int start,int end){
        boolean flag=false;
        Set<Integer> tc=new TreeSet<>();
        for(int i=0;i<a.length-1;i++){
            for(int j=i;j<a.length-1;j++){
               // System.out.println(a[j+1]+"-"+a[i]);
                tc.add(a[j+1]-a[i]);
            }
        }

        int  ac=tc.size()-2;
        if(ac>=start&&ac<=end){
          flag=true;
        }

        return flag;
    }




    //获取奇偶比
    public static Map<String,String> getJudgeAndOdd(Integer [] a){
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
    public static  Map<String,String>  getBigOrSmall(Integer [] a){
        Map<String,String>  result=new HashMap<>();
        int  small=0;
        int  big=0;
        for(int i=0;i<a.length;i++){
            if(a[i]>=0&a[i]<5){
                small++;
            }
            if(a[i]>=5&&a[i]<=9){

                big++;
            }
        }
        if(big>=2){
            result.put("大小",big+":"+small);
        }else{
            result.put("大小",small+":"+big);
        }

        return result;
    }


    //质合
    public  static Map<String,String>  getPrime3d(Integer[] a) {
        Map<String,String>  result=new HashMap<>();
        int  prime=0;
        int  sum=0;
        for(int i=0;i<a.length;i++){
            if(isPrime(a[i])){
                prime++;
            }else{
                sum++;
            }
        }
        result.put("质合",prime+":"+sum);
        return result;

    }

    public  static  boolean  isPrime(int a) {
        boolean flag = true;
        if (a>0&&a < 2) {
            return flag;

        } else {
            for (int i = 2; i < Math.sqrt(a); i++) {
                if (a % i == 0) {
                    flag = false;
                    break;
                }

            }
        }
        return flag;
    }


    //除3余数
    public static  Map<String,String>  expectDelayed3d(Integer [] a){
        Map<String,String>  result=new HashMap<>();
        int  delay0=0;
        int  delay1=0;
        int  delay2=0;
        for(int i=0;i<a.length;i++){
            if(a[i]%3==0){
                delay0++;
            }
            if(a[i]==1||a[i]%3==1){
                delay1++;
            }
            if(a[i]==2||a[i]%3==2){
                delay2++;
            }
        }
        result.put("余3",delay0+":"+delay1+":"+delay2);
        return result;
    }


    //平均值
    public static  Map<String,Integer> averValue3d(Integer[] a){
        Map<String,Integer>  result=new HashMap<>();
        int value5=0;
        int sum=0;
        for(int i=0;i<a.length;i++){
            sum+=a[i];
        }
        BigDecimal bigDecimal=new BigDecimal(sum+"");
        BigDecimal  bigDecimal12=new BigDecimal(3+"");
        String   sb=bigDecimal.divide(bigDecimal12,0, RoundingMode.HALF_UP).toPlainString();
        value5=Integer.parseInt(sb);
        result.put("平均",value5);
        return result;
    }


    //除3余数和
    public static Map<String,Integer> exDelayedSum(Integer [] a){
        Map<String,Integer>  result=new HashMap<>();
        int  sum=0;
        for(int i=0;i<a.length;i++){
            if(a[i]%3==0){
                sum+=0;
            }
            if(a[i]==1||a[i]%3==1){
                sum+=1;
            }
            if(a[i]==2||a[i]%3==2){
                sum+=2;
            }
        }
        result.put("余和",sum);
        return result;
    }


}
