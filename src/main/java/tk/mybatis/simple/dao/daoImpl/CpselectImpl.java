package tk.mybatis.simple.dao.daoImpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import tk.mybatis.simple.dao.CpSelect5Dao;
import tk.mybatis.simple.mapper.Cpselect5Mapper;
import tk.mybatis.simple.model.Cpselect5;

import java.io.Serializable;
import java.util.List;

@Repository("CpSelect5Dao")
public class CpselectImpl implements CpSelect5Dao {

    protected SqlSession   sqlSession;
    @Override
    public  List<Cpselect5> getAll() {
        sqlSession=new BaseGetSqlSession().getSqlSession();
        Cpselect5Mapper cpselect5Mapper=sqlSession.getMapper(Cpselect5Mapper.class);
        return cpselect5Mapper.selectAllCount();
    }
}
