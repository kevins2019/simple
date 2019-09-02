package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tk.mybatis.simple.model.Country;
import java.util.List;
import static org.junit.Assert.assertTrue;
public class CountryMapperTest  extends BaseMapperTest{



    @Test
    public  void testSelectAll(){
        SqlSession  sqlSession=getSqlSession();
        try {
            //userMapper中也有selectAll方法，故这里要带上命名空间
          List<Country>  countryList=sqlSession.selectList("tk.mybatis.simple.mapper.CountryMapper.selectAll");
          printCountryList(countryList);

          if(countryList.size()>0){
              assertTrue(true);
          }



        }finally {
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countryList){
        for (Country  country:countryList){
            System.out.printf("%-4d%4s%4s\n",country.getId(),country.getCountryname(),country.getCountrycode());
        }


    }
}
