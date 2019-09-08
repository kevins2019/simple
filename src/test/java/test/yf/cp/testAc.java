package test.yf.cp;

import org.apache.ibatis.session.SqlSession;
import tk.mybatis.simple.mapper.BaseMapperTest;
import tk.mybatis.simple.mapper.CpshuangSeQiuMapper;
import tk.mybatis.simple.model.CpshuangSeQiu;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class testAc extends BaseMapperTest {

    public static void main(String[] args) {
        int[] a={4,12,14,21,27,29};
        Set<Integer>  tc=new TreeSet<>();
       for(int i=0;i<a.length-1;i++){
           for(int j=i;j<a.length-1;j++){
               System.out.println(a[j+1]+"-"+a[i]);
               tc.add(a[j+1]-a[i]);
           }
       }
        System.out.println(tc.toString());
        System.out.println(tc.size());

        String   td="8:5";
        String[]  sb={"0:5","5:0"};
        boolean   flag=false;
        for(int i=0;i<sb.length;i++){
            if( td.equals(sb[i])){
                flag=true;
            }

        }

        System.out.println(flag);




        for(int i=1;i<100;i++){
            boolean  bc=true;
           for(int j=2;j<i;j++){
               if(i%j==0){
                  bc=false;
                  break;
               }
           }
            if(bc){
               // System.out.println("质数"+i);
            }
        }

        int[]  su={1,2,3,6,7,8,10,11,12,14,16,17};
        for (int i=0;i<su.length;i++){
            if(isPrime(su[i])){
               // System.out.println("++"+su[i]);
            }
        }

       // int[]  exd={1,4,12,13,30,32};
        //int[]  exd={1,4,9,21,31,33};
       // int[]  exd={3,8,12,16,20,32};
        //int[]  exd={4,12,14,21,27,29};
        int[]  exd={1,5,16,17,18,24};
        int[]  p1={16,17,26,29,35};
        int[]  p2={1,12,14,26,27};
        int[]  p3={3,12,17,19,31};
        int[]  p4={2,12,22,23,27};
        int[]  p5={10,17,20,30,35};
        int[]  p6={13,15,17,19,22};
        int[]  p7={4,6,18,27,33};
        int[]  p8={5,8,12,19,21};
        int[]  p9={5,11,18,19,27};
        int[]  p10={5,10,15,19,31};

        System.out.println("*************和值********************"+getSum(exd));
        System.out.println("*************和尾********************"+endSum(exd));
        System.out.println("*************AC********************"+calcuAc(exd));
        System.out.println("*************跨度********************"+kuaDu(exd));
        System.out.println("*************平均值********************"+averValue(exd));
        System.out.println("*************余和********************"+expectDelayedSum(exd));
        System.out.println("*************奇偶比********************"+getJudgeAndOdd(exd));
        System.out.println("*************大小比********************"+getBigOrSmall(exd));
        System.out.println("*************质合比********************"+primeSum(exd));
        System.out.println("*************除3后0,1,2,比********************"+expectDelayed(exd));
        System.out.println("*************区域比********************"+getArea(exd));
        ArrayList<int[]>  pj=new ArrayList<int[]>();
       pj.add(p1);pj.add(p2);pj.add(p3);pj.add(p4);pj.add(p5);
        pj.add(p6);pj.add(p7);pj.add(p8);pj.add(p9);pj.add(p10);
        for(int i=0;i<pj.size();i++){

            //System.out.println(averValue(pj.get(i)));
        }


//        for(int i=0;i<pj.size();i++){
//
//            System.out.println(expectDelayedSum(pj.get(i)));
//        }

            int[]  test={2,4,14,16,20,22};
//        boolean  result=getCompareQiu(test);
//        System.out.println(result);

        int[]   a1={1,};
        int[]   a2={0,2,};
        int[]   a3={3,5,};

        List<String> list=new ArrayList<>();
        for(int i=0;i<a1.length;i++){
            for(int j=0;j<a2.length;j++){
                for(int k=0;k<a3.length;k++){
                    int[]  temp=new int[3];
                    temp[0]=a1[i];
                    temp[1]=a2[j];
                    temp[2]=a3[k];
                list.add(Arrays.toString(temp));
                }

            }

        }

        System.out.println(list.toString());









    }











    //和值
    public  static  int getSum(int[] a ){
        int sum=0;
        for(int i=0;i<a.length;i++){
           sum+=a[i];

        }
        return sum;
    }
    //计算AC
    public static  int  calcuAc(int[] a){
        int ac=0;
        Set<Integer>  tc=new TreeSet<>();
        for(int i=0;i<a.length-1;i++){
            for(int j=i;j<a.length-1;j++){
                //System.out.println(a[j+1]+"-"+a[i]);
                tc.add(a[j+1]-a[i]);
            }
        }
        ac=tc.size()-(a.length-1);

        return ac;
    }

    //计算尾和
    public  static  int endSum(int[] a){
       int sum=0;
       for (int i=0;i<a.length;i++) {
           if (a[i] > 0 && a[i] < 10) {
               sum += a[i];

           }
           if (a[i] >= 10) {
               sum += a[i]%10;
           }
       }
       return  sum;
    }

    //跨度
    public  static  int kuaDu(int[] a){
        int  kd=0;
        kd=a[a.length-1]-a[0];
        return kd;
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


//平均值
public static  Map<String,Integer> averValue(int[] a){
    Map<String,Integer>  result=new HashMap<>();
    int value5=0;
    int sum=0;
    for(int i=0;i<a.length;i++){
     sum+=a[i];
    }
   BigDecimal  bigDecimal=new BigDecimal(sum+"");
    BigDecimal  bigDecimal12=new BigDecimal(6+"");
    String   sb=bigDecimal.divide(bigDecimal12,0, RoundingMode.HALF_UP).toPlainString();
    value5=Integer.parseInt(sb);
    result.put("平均",value5);
    return result;
}


    //除3余数和
    public static Map<String,Integer> expectDelayedSum(int [] a){
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

    //区比
    public  static Map<String,String>  getArea(int[] a){
        Map<String,String>  resultCn=new HashMap<>();
        int  firstArea=0;   //1~5
        int  twoArea=0;     //二区6~10
        int  threeArea=0;   //三区11~15
        int  fourArea=0;    //四区16~20
        int  fiveArea=0;    //五分区21~25
        int  sixArea=0;     //六分区26~30
        int   sevenArea=0;  //七分区31~33
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
            if(a[i]>30&&a[i]<=33){
                sevenArea++;
            }
        }
        resultCn.put("区比",firstArea+":"+twoArea+":"+threeArea+":"+fourArea+":"+fiveArea+":"+sixArea+":"+sevenArea);
        return resultCn;
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


    public static  Boolean getCompareQiu(int[] a){
        boolean  flag=true;
        init();
        SqlSession sqlSession=getSqlSession();
        CpshuangSeQiuMapper cpshuangSeQiuMapper=sqlSession.getMapper(CpshuangSeQiuMapper.class);
        List<CpshuangSeQiu>  lists=cpshuangSeQiuMapper.selectAll();
        List<String>    cpRed=new ArrayList<>();
        for (int i =0;i<lists.size();i++){
            CpshuangSeQiu cpshuangSeQiu=lists.get(i);
            cpRed.add(cpshuangSeQiu.getRedLottery());
        }

        String sb=Arrays.toString(a).replace(" ","");

        for(int i=0;i<cpRed.size();i++){
            String  source="["+cpRed.get(i)+"]";
             if(sb.equals(source)){
              flag=false;
             }

        }


        return  flag;
    }




}
