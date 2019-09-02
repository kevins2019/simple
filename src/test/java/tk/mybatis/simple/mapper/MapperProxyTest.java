package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import tk.mybatis.simple.model.SysUser;

import java.lang.reflect.Proxy;
import java.util.List;

public class MapperProxyTest extends BaseMapperTest {


    @Test
    public void  proxyTest(){

        SqlSession  sqlSession=getSqlSession();

        //获取userMapper接口

        MyMapperProxy  userMapperProxy=new MyMapperProxy(UserMapper.class,sqlSession);

        UserMapper   userMapper= (UserMapper) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{UserMapper.class},userMapperProxy);

        //调用selectAll
        List<SysUser>  users=userMapper.selectAll();

        System.out.print(users.get(0).getUserName());
    }
}
