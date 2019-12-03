package tk.mybatis.simple.service.serviceImpl;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.simple.dao.CpSelect5Dao;
import tk.mybatis.simple.model.Cpselect5;
import tk.mybatis.simple.service.CpSelect5Service;

import java.util.*;

@Service("Cpselect5")
public class CpSelect5ServiceImpl implements CpSelect5Service {

    @Autowired
    private CpSelect5Dao cpSelect5Dao;
    @Override
    public Map<String, String> getData() {
        Map<String,String>  result=new HashMap<>();
        String temp1="";
        String temp2="";
        List<Cpselect5> cpselect5List=cpSelect5Dao.getAll();
        String[] re=new String[50];
        String[] xre=new String[50];
        List<String>  st=new ArrayList<>();
        List<String>  xst=new ArrayList<>();
        for(int i=0;i<cpselect5List.size();i++){
            st.add(cpselect5List.get(i).getWinNum());
            xst.add(cpselect5List.get(i).getPeriod());
        }

        for(int i=0;i<st.size();i++){
            String temp=st.get(i);
            String[] tempstr=temp.split(",");
            int[]  tempNum=new int[tempstr.length];
            for(int j=0;j<tempstr.length;j++){
                tempNum[j]=Integer.parseInt(tempstr[j].replace(" ",""));
            }
            Arrays.sort(tempNum);
            Set<Integer> tac=new TreeSet<>();
            for(int m=0;m<tempNum.length-1;m++){
                for(int n=m;n<tempNum.length-1;n++){
                    tac.add(tempNum[n+1]-tempNum[m]);
                }

            }
            re[i]=(tac.size()-4)+"";
        }
        temp1= ArrayUtils.toString(re);
        for(int i=0;i<xre.length;i++) {
            xre[i] = xst.get(i);
        }
        String last="";
        for(int i=0;i<xre.length;i++){
            last+="\""+xre[i]+"\",";
        }
        temp2=last.substring(0,last.length()-1);
    result.put("xPoint",temp2);
    result.put("yPoint",temp1);


        return result;
    }
}
