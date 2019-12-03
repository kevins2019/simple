package test.yf.cp;

import org.apache.commons.lang3.ArrayUtils;
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


    public  static final  int[]   MULTIPLENUM={4,5,6,7,12,15,18,20,22,29};  //多个待选数范围
    public  static final  int  GETNUM=5;    //取数个数

    public  static final  int  SUMSTART=60;   //和值开始

    public static final int SUMEND=109;       //和值结束

    public static final int TAILSTART=19;       //和尾开始

    public static final int TAILEND=31;       //和尾结束

    public static final int ACSTART=4;       //AC开始

    public static final int ACEND=6;       //AC结束

    public static final int SKIPSTART=15;       //跨度开始

    public static final int SKIPEND=31;       //跨度结束

    public static final  int[]  FIRSTKD={2,13};  //1~2kd,为空不参与筛选

    public  static final int[]  SECONDKD={1,8}; //2~3kd,为空不参与筛选

    public static final  int[]  THIRDKD={1,13};  //3~4kd,为空不参与筛选

    public static final  int[]  FIVEKD={1,10};   //4~5kd,为空不参与筛选

    public  static final  int ARVESTART=12;    //平均值开始

    public  static final  int ARVEEND=27;    //平均值结束

    public  static final  int EXDSTART=3;    //余三和开始

    public  static final  int EXDEND=7;    //余三和结束

    public  static final  String APPOINT="";   //每组必包含

    public  static  final  String  OKAPPOINT3="";

    public  static  final  String  OKAPPOINT="";  //最后一位表示指定第几位  eg:第五位   "29,32,5"



    //奇偶比
    public static  final String[]   JUDGEANDODD={"0:5","5:0"};   //奇偶比

    //大小比
    public  static  final String[]  BIGANDSMALL={"0:5","5:0"};

    //质合比
    public  static  final String[]  PRIMESUM={"0:5","5:0"};

    //除3余0、1、2比
    public static final  String[]  EXPECTDELAY={"5:0:0","0:5:0","0:0:5","4:0:1","4:1:0","0:4:1","1:4:0"};  //,"1:0:4","0:1:4"


    //五区比
    public  static  final  String[] AREA={"5:0:0:0:0:0:0","0:5:0:0:0:0:0","0:0:5:0:0:0:0","0:0:0:5:0:0:0",
            "0:0:0:0:5:0:0","0:0:0:0:0:5:0","0:0:0:0:0:0:5","0:0:0:0:0:5:0","0:0:0:0:0:0:5",
            "4:1:0:0:0:0:0","4:0:1:0:0:0:0","4:0:0:1:0:0:0","4:0:0:0:1:0:0","4:0:0:0:0:1:0","4:0:0:0:0:0:1",
            "1:4:0:0:0:0:0","0:4:1:0:0:0:0","0:4:0:1:0:0:0","0:4:0:0:1:0:0","0:4:0:0:0:1:0","0:4:0:0:0:0:1",
            "1:0:4:0:0:0:0","0:1:4:0:0:0:0","0:0:4:1:0:0:0","0:0:4:0:1:0:0","0:0:4:0:0:1:0","0:0:4:0:0:0:1",
            "1:0:0:4:0:0:0","0:1:0:4:0:0:0","0:0:1:4:0:0:0","0:0:0:4:1:0:0","0:0:0:4:0:1:0","0:0:0:4:0:0:1",
            "1:0:0:0:4:0:0","0:1:0:0:4:0:0","0:0:1:0:4:0:0","0:0:0:1:4:0:0","0:0:0:0:4:1:0","0:0:0:0:4:0:1",
            "1:0:0:0:0:4:0","0:1:0:0:0:4:0","0:0:1:0:0:4:0","0:0:0:1:0:4:0","0:0:0:0:1:4:0","0:0:0:0:0:4:1",
            "1:0:0:0:0:0:4","0:1:0:0:0:0:4","0:0:1:0:0:0:4","0:0:0:1:0:0:4","0:0:0:0:1:0:4","0:0:0:0:0:1:4",
            "3:2:0:0:0:0:0","3:0:2:0:0:0:0","3:0:0:2:0:0:0","3:0:0:0:2:0:0","3:0:0:0:0:2:0","3:0:0:0:0:0:2",
            "2:3:0:0:0:0:0","0:3:2:0:0:0:0","0:3:0:2:0:0:0","0:3:0:0:2:0:0","0:3:0:0:0:2:0","0:3:0:0:0:0:2",
            "2:0:3:0:0:0:0","0:2:3:0:0:0:0","0:0:3:2:0:0:0","0:0:3:0:2:0:0","0:0:3:0:0:2:0","0:0:3:0:0:0:2",
            "2:0:0:3:0:0:0","0:2:0:3:0:0:0","0:0:2:3:0:0:0","0:0:0:3:2:0:0","0:0:0:3:0:2:0","0:0:0:3:0:0:2",
            "2:0:0:0:3:0:0","0:2:0:0:3:0:0","0:0:2:0:3:0:0","0:0:0:2:3:0:0","0:0:0:0:3:2:0","0:0:0:0:3:0:2",
            "2:0:0:0:0:3:0","0:2:0:0:0:3:0","0:0:2:0:0:3:0","0:0:0:2:0:3:0","0:0:0:0:2:3:0","0:0:0:0:0:3:2",
            "2:0:0:0:0:0:3","0:2:0:0:0:0:3","0:0:2:0:0:0:3","0:0:0:2:0:0:3","0:0:0:0:2:0:3","0:0:0:0:0:2:3",
            "2:2:1:0:0:0:0","2:2:0:1:0:0:0","2:2:0:0:1:0:0","2:2:0:0:0:1:0","2:2:0:0:0:0:1","1:0:0:0:0:2:2",
            "0:1:0:0:0:2:2","0:0:1:0:0:2:2","0:0:0:1:0:2:2","0:0:0:0:1:2:2"};


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
        result.put("大小",big+":"+small);
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
        int  firstArea=0;   //1~5
        int  twoArea=0;     //二区6~10
        int  threeArea=0;   //三区11~15
        int  fourArea=0;    //四区16~20
        int  fiveArea=0;    // 五区21~25
        int  sixArea=0;     //六区26~30
        int  sevenArea=0;   //七区31~35

        for(int i=0;i<a.length;i++){
            if(a[i]>0&&a[i]<=5){
                firstArea++;
            }
            if(a[i]>5&&a[i]<=10){
                twoArea++;
            }
            if(a[i]>10&&a[i]<=15){
                threeArea++;
            }
            if(a[i]>15&&a[i]<=20){
                fourArea++;
            }
            if(a[i]>20&&a[i]<=25){
                fiveArea++;
            }
            if(a[i]>25&&a[i]<=30){
                sixArea++;
            }
            if(a[i]>30&&a[i]<=35){
                sevenArea++;
            }
        }
        resultCn.put("区比",firstArea+":"+twoArea+":"+threeArea+":"+fourArea+":"+fiveArea+":"+sixArea+":"+sevenArea);
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
    public   boolean  getOkNum(int[] temp,String str){
        boolean flag=false;
        if(!"".equals(str)){
            int[]  tempNum=strArrayToInteger(str);
            switch (tempNum.length){
                case 1:
                    if(ArrayUtils.contains(temp,tempNum[0])){
                        flag=true;
                    }
                    break;
                case 2:
                    if(ArrayUtils.contains(temp,tempNum[0])&&ArrayUtils.contains(temp,tempNum[1])){
                        flag=true;
                    }
                    break;
                case 3:
                    if(ArrayUtils.contains(temp,tempNum[0])&&ArrayUtils.contains(temp,tempNum[1])&&ArrayUtils.contains(temp,tempNum[2])){
                        flag=true;
                    }
                    break;
                default:
                    break;

            }

        }else {
            flag=true;
        }





        return   flag;
    }

    public  boolean  getLimitNum(int[] temp ,String str){
        boolean flag=false;
        if(!"".equals(str)){
            int[]  limit=strArrayToInteger1(str);
            int  bitNum=limit[limit.length-1]-1;
            switch (limit.length){
                case 2:
                    if(temp[bitNum]==limit[0]){
                       flag=true;
                    }
                    break;
                case 3:
                    if(temp[bitNum]==limit[0]||temp[bitNum]==limit[1]){
                        flag=true;
                    }
                    break;
                case 4:
                    if(temp[bitNum]==limit[0]||temp[bitNum]==limit[1]||temp[bitNum]==limit[2]){
                        flag=true;
                    }
                    break;
                 default:
                     break;
            }

        }else{
            flag=true;
        }
        return  flag;
    }

public   boolean   getFirstKD(int[] kd,int[] temp){
        boolean flag=false;
        Arrays.sort(temp);
    int kv=temp[1]-temp[0];
        if(kd.length==0){
            flag=true;
        }else{
            if(kv>=kd[0]&&kv<=kd[1]){
               flag=true;
            }else{
                flag=false;
            }
        }

        return flag;
}
    public   boolean   getSecondKD(int[] kd,int[] temp){
        boolean flag=false;
        Arrays.sort(temp);
        int kv=temp[2]-temp[1];
        if(kd.length==0){
            flag=true;
        }else{
            if(kv>=kd[0]&&kv<=kd[1]){
                flag=true;
            }else{
                flag=false;
            }
        }


        return flag;
    }
    public   boolean   getThirdKD(int[] kd,int[] temp){
        boolean flag=false;
        Arrays.sort(temp);
        int kv=temp[3]-temp[2];
        if(kd.length==0){
            flag=true;
        }else{
            if(kv>=kd[0]&&kv<=kd[1]){
                flag=true;
            }else{
                flag=false;
            }
        }


        return flag;
    }
    public   boolean   getFiveKD(int[] kd,int[] temp){
        boolean flag=false;
        Arrays.sort(temp);
        int kv=temp[4]-temp[3];
        if(kd.length==0){
            flag=true;
        }else{
            if(kv>=kd[0]&&kv<=kd[1]){
                flag=true;
            }else{
                flag=false;
            }
        }


        return flag;
    }

    public   int[]   strArrayToInteger(String s){
        String[] a=s.replace(" ","").split(",");
        int[]  sb=new int[a.length];
        for (int i=0;i<sb.length;i++){
            sb[i]=Integer.parseInt(a[i]);
        }
        Arrays.sort(sb);

        return  sb;
    }


    public   int[]   strArrayToInteger1(String s){
        String[] a=s.replace(" ","").split(",");
        int[]  sb=new int[a.length];
        for (int i=0;i<sb.length;i++){
            sb[i]=Integer.parseInt(a[i]);
        }

        return  sb;
    }




}
