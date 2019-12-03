package tk.mybatis.simple.dao.daoImpl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import tk.mybatis.simple.dao.DaletouDao;
import tk.mybatis.simple.mapper.CpdaleTouMapper;
import tk.mybatis.simple.model.CpdaleTou;

import java.io.Serializable;
import java.util.List;

@Repository("DaletouDao")
public class DaletouImpl implements DaletouDao {

    protected SqlSession  sqlSession;
    @Override
    public <T extends Serializable> List<CpdaleTou> getAll(Class<T> clazz) {
        sqlSession=new BaseGetSqlSession().getSqlSession();
        CpdaleTouMapper  cpdaleTouMapper=sqlSession.getMapper(CpdaleTouMapper.class);
        return  cpdaleTouMapper.selectAll();
    }
}
