package test.yf.cp;

import org.junit.Test;

import java.io.*;
import java.util.*;


public class TestRandom3D extends Basic3D {

    @Test
    public void   getNum3d(){
          List<Integer[]> list=getList3d();
//        List<Integer[]> list=new ArrayList<Integer[]>();
//        Integer[]  ss=new Integer[]{5,7,5};
//        list.add(ss);
        System.out.println("**********生成：************"+list.size());
        List<Integer[]> result=new ArrayList<>();
//        System.out.println("**********筛选出：************"+result.size());
//        for(Integer[] i:result){
//            System.out.println(Arrays.toString(i));
//        }
        for(int i=0;i<list.size();i++){
           // System.out.println(Arrays.toString(list.get(i)));
            boolean  sum=getSum3d(list.get(i),SUMSTART,SUMEND);
            boolean  sumend=getSumEnd3d(list.get(i),TAILSTART,TAILEND);
            boolean  ac=getAc3d(list.get(i),ACSTART,ACEND);
            boolean  kd=getKd3d(list.get(i),KDSTART,KDEND);

            boolean jdood=true;  //奇偶
            String  jd=getJudgeAndOdd(list.get(i)).get("奇偶");
            for(int a=0;a<JUDEGANDODD.length;a++){
                if(jd.equals(JUDEGANDODD[a])){
                   jdood=false;
                }
            }
            boolean  bigsm=true;//大小
            String   bs=getBigOrSmall(list.get(i)).get("大小");
            for(int b=0;b<BIGANDSMALL.length;b++){
                if(bs.equals(BIGANDSMALL[b])){
                    bigsm=false;
                }
            }

            boolean  prisum=true;//质合
            String   ps=getPrime3d(list.get(i)).get("质合");
            for(int c=0;c<PRIMESUM.length;c++){
                if(ps.equals(PRIMESUM[c])){
                    prisum=false;
                }
            }

            boolean  y3=true;//余3
            String   yu=expectDelayed3d(list.get(i)).get("余3");
            for(int d=0;d<EXPECTED3.length;d++){
                if(yu.equals(EXPECTED3[d])){
                    y3=false;
                }
            }

            boolean  avg=false; //平均数
            int   av=averValue3d(list.get(i)).get("平均");
            if(av>=AVG[0]&&av<=AVG[1]){
                avg=true;
            }

            boolean  ys=false;//余和
            int  ysum=exDelayedSum(list.get(i)).get("余和");
            if(ysum>=EXDSUM[0]&&ysum<=EXDSUM[1]){
                ys=true;
            }

            if(sum&&sumend&&ac&&kd&&jdood&&bigsm&&prisum&&y3&&avg&&ys){
                result.add(list.get(i));
            }

        }

        Set<Integer[]>  set=new HashSet<>(result);
        List<Integer[]> over=new ArrayList<>();
        for(Integer[] i:set){
            if(!over.contains(i)) {
                over.add(i);
            }
        }

        System.out.println("**********筛选出：************"+over.size());
        for(Integer[] as:over){
            System.out.println(Arrays.toString(as).replace(" ",""));
        }

    }


    public static  List<Integer[]>  getList3d(){
        List<Integer[]>  list=new ArrayList<>();
        for (int i=0;i<HUNDREDNUM.length;i++){
            for (int j=0;j<TENNUM.length;j++){
                for(int k=0;k<BITNUM.length;k++){
                    Integer[] temp=new Integer[3];
                    temp[0]=HUNDREDNUM[i];
                    temp[1]=TENNUM[j];
                    temp[2]=BITNUM[k];
                    list.add(temp);
                }
            }
        }

    return list;

    }

    public void   saveTxt(String path,String content) throws  IOException{
        BufferedWriter bufferedWriter=null;
        File  file=new File(path);
        try {
            if(file.exists()){
                file.createNewFile();
            }
            bufferedWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));
            bufferedWriter.write(content+"\r\n");
            bufferedWriter.flush();

            bufferedWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }







}
