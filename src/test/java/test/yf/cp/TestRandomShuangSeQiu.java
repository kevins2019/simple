package test.yf.cp;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class TestRandomShuangSeQiu  extends BasicShuangSeQiu {



    @Test
    public  void  test() throws IOException{


        long  startTime=System.currentTimeMillis();
        //int m = 6; // 待取出组合的个数
        TestRandomShuangSeQiu c = new TestRandomShuangSeQiu();
        List<int[]> list = c.testRandom(MULTIPLENUM, GETNUM);
      //  List<int[]> list =new ArrayList<>();
      //  list.add(new int[]{7,18,20,22,24,33});  测试中奖号是否筛选出
       int num= c.print(list);
       long   endTime=System.currentTimeMillis();
        DecimalFormat  df=new DecimalFormat("0.00");
       BigDecimal  b1=new BigDecimal((endTime-startTime)+"");
        BigDecimal  b2=new BigDecimal(1000*60+"");
        df.format(b1);
        df.format(b2);

        BigDecimal  costTime=b1.divide(b2,0, RoundingMode.HALF_UP);

        System.out.println("共花费时间:"+costTime);
        System.out.println("一共" + list.size() + "组!");
        System.out.println("一共筛选出" + num + "组!");




    }

    private List<int[]> testRandom(int[] a, int m) {
        TestRandomShuangSeQiu c = new TestRandomShuangSeQiu();
        List<int[]> list = new ArrayList<int[]>();
        int n = a.length;
        boolean end = false; // 是否是最后一种组合的标记
        // 生成辅助数组。首先初始化，将数组前n个元素置1，表示第一个组合为前n个数。
        int[] tempNum = new int[n];
        for (int i = 0; i < n; i++) {
            if (i < m) {
                tempNum[i] = 1;

            } else {
                tempNum[i] = 0;
            }
        }
        printVir(tempNum);// 打印首个辅助数组
        list.add(c.createResult(a, tempNum, m));// 打印第一种默认组合
        int k = 0;//标记位
        while (!end) {
            boolean findFirst = false;
            boolean swap = false;
            // 然后从左到右扫描数组元素值的"10"组合，找到第一个"10"组合后将其变为"01"
            for (int i = 0; i < n; i++) {
                int l = 0;
                if (!findFirst && tempNum[i] == 1) {
                    k = i;
                    findFirst = true;
                }
                if (tempNum[i] == 1 && tempNum[i + 1] == 0) {
                    tempNum[i] = 0;
                    tempNum[i + 1] = 1;
                    swap = true;
                    for (l = 0; l < i - k; l++) { // 同时将其左边的所有"1"全部移动到数组的最左端。
                        tempNum[l] = tempNum[k + l];
                    }
                    for (l = i - k; l < i; l++) {
                        tempNum[l] = 0;
                    }
                    if (k == i && i + 1 == n - m) {//假如第一个"1"刚刚移动到第n-m+1个位置,则终止整个寻找
                        end = true;
                    }
                }
                if (swap) {
                    break;
                }
            }
            printVir(tempNum);// 打印辅助数组
            list.add(c.createResult(a, tempNum, m));// 添加下一种默认组合
        }
        return list;
    }

    // 根据辅助数组和原始数组生成结果数组
    public int[] createResult(int[] a, int[] temp, int m) {
        int[] result = new int[m];
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (temp[i] == 1) {
                result[j] = a[i];
                System.out.println("result[" + j + "]:" + result[j]);
                j++;
            }
        }
        return result;
    }

    // 打印整组数组
    public Integer print(List<int[]> list)   throws IOException {
        int  num=0;
        System.out.println("具体组合结果为:");
        for (int i = 0; i < list.size(); i++) {
            // if(list.get(i).toString().equals("1,5,16,17,18,24")) {
           int[] temp = (int[]) list.get(i);
            //int[]  temp ={7,18,20,22,24,33};
            //去除往期已开号码

            //各参数说明  -------和值范围(n,m)--和尾范围(x,y)--AC范围(z,w) --跨度(e,f) 排奇偶比、大小比、区比见方法里

            if (getSumFlag(temp, SUMSTART, SUMEND, TAILSTART, TAILEND, ACSTART, ACEND, SKIPSTART, SKIPEND)) {
                boolean bf = getCompareQiu(temp);
                if (bf) {
                    if (getLimitNum(temp,BasicShuangSeQiu.OKAPPINT)&&getFirstKD(BasicShuangSeQiu.FIRSTKD,temp)&&getSecondKD(BasicShuangSeQiu.SECONDKD,temp)&&getThirdKD(BasicShuangSeQiu.THIRDKD,temp)&&getFiveKD(BasicShuangSeQiu.FIVEKD,temp)&&getSixKD(BasicShuangSeQiu.SIXKD,temp)) {
                        for (int j = 0; j < temp.length; j++) {
                            java.text.DecimalFormat df = new java.text.DecimalFormat("00");//将输出格式化
                            System.out.print(df.format(temp[j]) + " ");
                        }
                        System.out.println();
                        num++;
                        String st1 = Arrays.toString(temp).replace("[", "");
                        String parseTemp = st1.replace("]", "");
                        String bule = "+7";
                        if (num % 5 == 0) {
                            saveTxt("D:\\cp.txt", parseTemp + bule + "\r\n---------------");
                        } else {
                            saveTxt("D:\\cp.txt", parseTemp + bule);
                        }

                    }
                }
            }
        }

        return  num;
    }

    public static  boolean  getSumFlag(int[] a,int n,int m,int x,int y,int z,int w,int e,int f){
        boolean  flag=false;
        int sum=0;
        for(int i=0;i<a.length;i++){
           sum+=a[i];
        }

        //取尾数和
        int sum2=0;
        for(int j=0;j<a.length;j++){
            if(a[j]>0&&a[j]<=9){
             sum2+=a[j];
            }
            if(a[j]>=10){
                sum2+=a[j]%10;
            }
        }
        //计算AC值
        int sumAC=0;
        Set<Integer> tc=new TreeSet<>();
        for(int k=0;k<a.length-1;k++){
            for(int b=k;b<a.length-1;b++){
                tc.add(a[b+1]-a[k]);
            }
        }
         sumAC=tc.size()-(GETNUM-1);

        //计算跨度
        int kuadu=a[a.length-1]-a[0];

        //排除奇偶比被否定的数据
          Map<String,String>  result=getJudgeAndOdd(a);
          String sb=result.get("奇偶");
          boolean   jo=true;
        for(int i=0;i<JUDGEANDODD.length;i++){
            if(sb.equals(JUDGEANDODD[i])){
                jo=false;
            }
        }

        //排除大小比不合理的组合
        Map<String,String>  bigSmall=getBigOrSmall(a);
          String  bsm=bigSmall.get("大小");
          boolean  bm=true;
         for (int i=0;i<BIGANDSMALL.length;i++){
            if(bsm.equals(BIGANDSMALL[i])){
                bm=false;
            }
        }

        //排除质合不合理的组合
        Map<String,String>  prsu=primeSum(a);
        String  su=prsu.get("质合");
        boolean  ps=true;
        for (int i=0;i<PRIMESUM.length;i++){
            if(su.equals(PRIMESUM[i])){
                ps=false;
            }
        }
        //余3
        Map<String,String>  ex3=expectDelayed(a);
        String  ed=ex3.get("余3");
        boolean  yd=true;
        for (int i=0;i<EXPECTDELAY.length;i++){
            if(ed.equals(EXPECTDELAY[i])){
                yd=false;
            }
        }

        //平均值
        Map<String,Integer>  av5=averValue(a);
        Integer  v5=av5.get("平均");
        boolean   y5=false;
        if(v5>=ARVESTART&&v5<=ARVEEND){
            y5=true;
        }

        //余三和
        Map<String,Integer>  y3sum=exDelayedSum(a);
        int   y3s=y3sum.get("余和");
        boolean  y3=false;
        if(y3s>=EXDSTART&&y3s<=EXDEND){
            y3=true;
        }




          //排除七区出号比例不合理的组合
        boolean   cn=true;
        Map<String,String>  area=getArea(a);
        String   am=area.get("区比");
        for (int i=0;i<AREA.length;i++){
            if(am.equals(AREA[i])){
                cn=false;

            }
        }

          //

        if(sum>=n&&sum<=m&&sum2>=x&&sum2<=y&&sumAC>=z&&sumAC<=w&&kuadu>=e&&kuadu<=f&&jo&&bm&&ps&&yd&&y3&&y5&&cn){
            flag=true;
        }
        return  flag;
    }


    // 打印辅助数组的方法
    public void printVir(int[] a) {
        System.out.println("生成的辅助数组为：");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
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
