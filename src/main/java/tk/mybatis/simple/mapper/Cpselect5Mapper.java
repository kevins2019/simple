package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.simple.model.Cpselect5;

import java.util.List;

public interface Cpselect5Mapper {

@Results(
        id = "Cpselect3Map",value = {
        @Result(property = "id",column = "id",id=true),
        @Result(property = "period",column = "period"),
        @Result(property = "winNum",column = "win_num")
}
)
@Select({
        "select * from cp_select5"
})
   List<Cpselect5>  selectAllCount();




@Insert(
        {
                "insert into cp_select5(id,period,win_num) values(#{id},#{period},#{winNum})"
        }
)
   int  insertAllData(Cpselect5 cp5);

  @ResultMap("Cpselect3Map")
    @Delete("delete  from cp_select5 where id>0")
    int   deleteAll();

}
