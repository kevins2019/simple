package tk.mybatis.simple.dao.daoImpl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;

@Repository
public class BaseGetSqlSession {
  protected SqlSessionFactory  sqlSessionFactory;

    public <T> SqlSession getSqlSession() {
        try {
            Reader  reader= Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sqlSessionFactory.openSession();
    }
}
