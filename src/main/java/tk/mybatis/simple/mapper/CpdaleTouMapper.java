package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.simple.model.CpdaleTou;

import java.util.List;

public interface CpdaleTouMapper {
    @Results(
            id = "daletouMap",value = {
             @Result(property = "id",column = "id",id=true),
            @Result(property = "pahseNum",column = "pahseNum"),
            @Result(property = "proLottery",column = "pro_lottery"),
            @Result(property = "backLettery",column = "back_lettery")
    }
    )
    @Select("select * from cp_daletou")
    List<CpdaleTou>  selectAll();
}
