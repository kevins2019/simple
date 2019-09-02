package test.yf.cp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class testAc {

    public static void main(String[] args) {
        int[] a={8,12,16,18,19,25};
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
                System.out.println("质数"+i);
            }
        }

        int[]  su={1,2,3,6,7,8,10,11,12,14,16,17};
        for (int i=0;i<su.length;i++){
            if(isPrime(su[i])){
                System.out.println("++"+su[i]);
            }
        }


        int[]  exd={16,17,26,29,35};
        System.out.println(expectDelayed(exd));
        System.out.println(averValue(exd));

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
    BigDecimal  bigDecimal12=new BigDecimal(5+"");
    String   sb=bigDecimal.divide(bigDecimal12,0, RoundingMode.HALF_UP).toPlainString();
    value5=Integer.parseInt(sb);
    result.put("平均",value5);
    return result;
}


    //除3余数和
    public static Map<String,Integer> expectDelayed(int [] a){
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
