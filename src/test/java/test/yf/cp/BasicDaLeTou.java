package test.yf.cp;

import org.apache.ibatis.session.SqlSession;
import tk.mybatis.simple.mapper.BaseMapperTest;
import tk.mybatis.simple.mapper.CpdaleTouMapper;
import tk.mybatis.simple.mapper.CpshuangSeQiuMapper;
import tk.mybatis.simple.model.CpdaleTou;
import tk.mybatis.simple.model.CpshuangSeQiu;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class BasicDaLeTou extends BaseMapperTest {


    public  static final  int[]   MULTIPLENUM={ 3,4,6,7,9,10,12,18,23,26,27};  //多个待选数范围

    public  static final  int  GETNUM=5;    //取数个数

    public  static final  int  SUMSTART=56;   //和值开始

    public static final int SUMEND=112;       //和值结束

    public static final int TAILSTART=22;       //和尾开始

    public static final int TAILEND=30;       //和尾结束

    public static final int ACSTART=4;       //AC开始

    public static final int ACEND=6;       //AC结束

    public static final int SKIPSTART=9;       //跨度开始

    public static final int SKIPEND=24;       //跨度结束

    public  static final  int ARVESTART=10;    //平均值开始

    public  static final  int ARVEEND=22;    //平均值结束

    public  static final  int EXDSTART=1;    //余三和开始

    public  static final  int EXDEND=7;    //余三和结束



    //奇偶比
    public static  final String[]   JUDGEANDODD={"0:5","1:4","4:1","5:0"};   //奇偶比

    //大小比
    public  static  final String[]  BIGANDSMALL={"0:5","5:0"};

    //质合比
    public  static  final String[]  PRIMESUM={"0:5","4:1","5:0"};

    //除3余0、1、2比
    public static final  String[]  EXPECTDELAY={"5:0:0","0:5:0","0:0:5","0:4:1","1:0:4","0:1:4"};


    //五区比
    public  static  final  String[] AREA={"5:0:0:0:0","0:5:0:0:0","0:0:5:0:0","0:0:0:5:0","0:0:0:0:5",
            "4:1:0:0:0","4:0:1:0:0","4:0:0:1:0","4:0:0:0:1",
            "1:4:0:0:0","0:4:1:0:0","0:4:0:1:0","0:4:0:0:1",
            "1:0:4:0:0","0:1:4:0:0","0:0:4:1:0","0:0:4:0:1",
            "1:0:0:4:0","0:1:0:4:0","0:0:1:4:0","0:0:0:4:1",
            "1:0:0:0:4","0:1:0:0:4","0:0:1:0:4","0:0:0:1:4"};


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
            if(a[i]>=1&a[i]<18){
                small++;
            }
            if(a[i]>=18&&a[i]<=35){

                big++;
            }
        }
        result.put("大小",small+":"+big);
        return result;
    }

    //质合比
    public static  Map<String,String>  primeSum(int [] a){
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

    public  static  boolean  isPrime(int a){
        boolean flag=true;
        if(a<2){
            return  flag;

        }else{
            for(int i=2;i<Math.sqrt(a);i++){
                if(a%i==0){
                    flag=false;
                    break;
                }

            }
        }
        return  flag;

    }

    //除3余数
    public static  Map<String,String>  expectDelayed(int [] a){
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
    public static  Map<String,Integer> averValue(int[] a){
        Map<String,Integer>  result=new HashMap<>();
        int value5=0;
        int sum=0;
        for(int i=0;i<a.length;i++){
            sum+=a[i];
        }
        BigDecimal bigDecimal=new BigDecimal(sum+"");
        BigDecimal  bigDecimal12=new BigDecimal(5+"");
        String   sb=bigDecimal.divide(bigDecimal12,0, RoundingMode.HALF_UP).toPlainString();
        value5=Integer.parseInt(sb);
        result.put("平均",value5);
        return result;
    }


    //除3余数和
    public static Map<String,Integer> exDelayedSum(int [] a){
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



    //区比
    public  static Map<String,String>  getArea(int[] a){
        Map<String,String>  resultCn=new HashMap<>();
        int  firstArea=0;   //1~7
        int  twoArea=0;     //二区8~14
        int  threeArea=0;   //三区15~21
        int  fourArea=0;    //四区22~28
        int  fiveArea=0;    // 五区29~35
        for(int i=0;i<a.length;i++){
            if(a[i]>0&&a[i]<=7){
                firstArea++;
            }
            if(a[i]>7&&a[i]<=14){
                twoArea++;
            }
            if(a[i]>14&&a[i]<=21){
                threeArea++;
            }
            if(a[i]>21&&a[i]<=28){
                fourArea++;
            }
            if(a[i]>28&&a[i]<=35){
                fiveArea++;
            }
        }
        resultCn.put("区比",firstArea+":"+twoArea+":"+threeArea+":"+fourArea+":"+fiveArea);
        return resultCn;
    }


    public static  Boolean getDaleTou(int[] a){
        boolean  flag=true;
        init();
        SqlSession sqlSession=getSqlSession();
        CpdaleTouMapper cpdaleTouMapper=sqlSession.getMapper(CpdaleTouMapper.class);
        List<CpdaleTou> lists=cpdaleTouMapper.selectAll();
        List<String>    pro=new ArrayList<>();
        for (int i =0;i<lists.size();i++){
            CpdaleTou cpdaleTou=lists.get(i);
            pro.add(cpdaleTou.getProLottery());
        }
        String sb= Arrays.toString(a).replace(" ","");

        for(int i=0;i<pro.size();i++){
            String  source="["+pro.get(i)+"]";
            if(sb.equals(source)){
                flag=false;
            }

        }

        return  flag;
    }





}
