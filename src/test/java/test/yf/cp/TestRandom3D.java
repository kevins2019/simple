package test.yf.cp;

import org.junit.Test;

import java.io.*;
import java.util.*;

public class TestRandom3D extends Basic3D {

    @Test
    public  void  test() throws IOException{

        //int m = 6; // 待取出组合的个数
        TestRandom3D c = new TestRandom3D();
        List<int[]> list = c.testRandom(MULTIPLENUM, GETNUM);
       int num= c.print(list);
        System.out.println("一共" + list.size() + "组!");
        System.out.println("一共筛选出" + num + "组!");




    }

    private List<int[]> testRandom(int[] a, int m) {
        TestRandom3D c = new TestRandom3D();
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
            int[] temp = (int[]) list.get(i);
            //各参数说明  -------和值范围(n,m)--和尾范围(x,y)--AC范围(z,w) --跨度(e,f) 排奇偶比、大小比、区比见方法里
            if(getSumFlag(temp,SUMSTART,SUMEND,TAILSTART,TAILEND,ACSTART,ACEND,SKIPSTART,SKIPEND)){
                for (int j = 0; j < temp.length; j++) {
                    java.text.DecimalFormat df = new java.text.DecimalFormat("00");//将输出格式化
                    System.out.print(df.format(temp[j]) + " ");
                }
                System.out.println();
                num++;
                String  st1=Arrays.toString(temp).replace("[","");
                      String   parseTemp=st1.replace("]","");
               saveTxt("D:\\cp.txt", parseTemp+"+6");
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
          if(sb.equals("0:6")||sb.equals("1:5")||sb.equals("5:1")){
               jo=false;
          }

          //排除大小比不合理的组合
        Map<String,String>  bigSmall=getBigOrSmall(a);
          String  bsm=bigSmall.get("大小");
          boolean  bm=true;
          if(bsm.equals("1:5")||bsm.equals("5:1")||bsm.equals("0:6")||bsm.equals("6:0")){
               bm=false;
          }

          //排除四区出号比例不合理的组合
        boolean   cn=true;
        Map<String,String>  area=getArea(a);
        String   am=area.get("区比");
        if(am.equals("0:1:1:4")||am.equals("1:0:1:4")||am.equals("1:0:0:4")||am.equals("4:1:1:0")||am.equals("0:1:0:4")||
        am.equals("0:3:3:0")||am.equals("3:1:2:0")||am.equals("3:2:1:0")||am.equals("3:0:1:2")||am.equals("3:0:3:0")
                ||am.equals("3:0:0:3")||am.equals("3:0:2:1")||am.equals("3:1:1:1")
                ||am.equals("0:1:2:3")||am.equals("0:2:1:3")||am.equals("1:0:2:3")
                ||am.equals("1:1:1:3")||am.equals("1:2:0:3")||am.equals("2:1:0:3")){
            cn=false;

        }

          //


        if(sum>=n&&sum<=m&&sum2>=x&&sum2<=y&&sumAC>=z&&sumAC<=w&&kuadu>=e&&kuadu<=f&&jo&&bm&&cn){
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
