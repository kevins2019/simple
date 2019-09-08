package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.simple.model.CpshuangSeQiu;

import java.util.List;

public interface CpshuangSeQiuMapper {


    @Results(
            id = "cpshuangSeQiuMap",value = {
                    @Result(property = "id",column = "id",id = true),
            @Result(property = "pahseNum",column = "pahseNum"),
            @Result(property = "redLottery",column = "red_lottery"),
            @Result(property = "buleLottery",column = "bule_lottery")
    }
    )
    @Select("select * from cp_shuangseqiu")
    List<CpshuangSeQiu> selectAll();
}
