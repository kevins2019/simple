package tk.mybatis.simple.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SysUserMapperTest  extends  BaseMapperTest {


    @Test
    public  void testSelectById(){

        SqlSession sqlSession=getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectById(1001l);

            Assert.assertNotNull(user);
            Assert.assertEquals("test", user.getUserName());
        }finally {
            sqlSession.close();
        }

    }

   @Test
    public void  testSelectAll(){

       SqlSession sqlSession=getSqlSession();
       try {
           UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
           List<SysUser> userList = userMapper.selectAll();

           Assert.assertNotNull(userList);

           Assert.assertTrue(userList.size()>0);

       }finally {
           sqlSession.close();
       }
    }


    @Test
    public  void  selectRoleByUserIdTest(){
        SqlSession  sqlSession=getSqlSession();
        try{

            UserMapper  userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRole>  sysUserList=userMapper.selectRoleByUserId(1l);

            Assert.assertNotNull(sysUserList);


        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void  selectRoleByUserId2Test(){
     SqlSession  sqlSession=getSqlSession();
     try {
         UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
         List<SysRole> sysRoleList=userMapper.selectRoleByUserId2(1l);
         Assert.assertNotNull(sysRoleList);
         printfValue(sysRoleList);

     }finally {
         sqlSession.close();
     }

    }

@Test
    public void  insertTest(){
        SqlSession  sqlSession=getSqlSession();
        try{
            UserMapper  userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser  sysUser=new SysUser();
            sysUser.setUserName("杨小龙");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("457960644@qq.com");
            sysUser.setUserInfo("学习一下");
            sysUser.setHeadImg(new byte[]{1,2,3});
            sysUser.setCreateTime(new Date());
            int result=userMapper.insert(sysUser);
            sqlSession.commit();
            Assert.assertEquals(1,result);
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void insert2(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper  userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser  sysUser=new SysUser();
            sysUser.setUserName("王平");
            sysUser.setUserPassword("123456");
            sysUser.setUserEmail("457960644@qq.com");
            sysUser.setUserInfo("无所谓");
            sysUser.setHeadImg(new byte[]{3,4,5});
            sysUser.setCreateTime(new Date());
            int  result=userMapper.insert2(sysUser);
            Assert.assertEquals(1,result);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void  insert3(){
       SqlSession  sqlSession=getSqlSession();
       try{
           UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
           SysUser  sysUser=new SysUser();
           sysUser.setUserName("刘莉");
           sysUser.setUserPassword("123456");
           sysUser.setUserEmail("457960644@qq.com");
           sysUser.setUserInfo("无所谓");
           sysUser.setHeadImg(new byte[]{3,4,5});
           sysUser.setCreateTime(new Date());
           int result=userMapper.insert3(sysUser);
           sqlSession.commit();
           Assert.assertEquals(1,result);
       }finally {
           sqlSession.close();
       }
    }

    @Test
   public void  updateByIdTest(){
        SqlSession  sqlSession=getSqlSession();
        try{
            UserMapper  userMapper=sqlSession.getMapper(UserMapper.class);
            //从数据库中查询一个对象
            SysUser sysUser=userMapper.selectById(1003l);
            sysUser.setUserName("张三");
            sysUser.setUserEmail("dadad@163.com");
            int  result=userMapper.updateById(sysUser);
            sqlSession.commit();
            Assert.assertEquals(1,result);
        }finally {

            sqlSession.close();
        }
   }

  @Test
   public  void deleteByIdTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper  userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser  sysUser=userMapper.selectById(1006l);
            Assert.assertNotNull(sysUser);
            int result=userMapper.deleteById(sysUser.getId());
            Assert.assertEquals(1,result);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
   }

    @Test
    public  void selectRoleByIdAndRoldEnabledTest(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper  userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRole>  sysUser=userMapper.selectRoleByIdAndRoldEnabled(1l,1l);
            Assert.assertNotNull(sysUser);
            Assert.assertTrue(sysUser.size()>0);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public  void selectRoleByIdAndRoldEnabled2Test(){
        SqlSession sqlSession=getSqlSession();
        try{
            UserMapper  userMapper=sqlSession.getMapper(UserMapper.class);
            List<SysRole>  sysUser=userMapper.selectRoleByIdAndRoldEnabled2(userMapper.selectById(1l),userMapper.selectRoleById(1l));
            Assert.assertNotNull(sysUser);
            Assert.assertTrue(sysUser.size()>0);
        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }


    @Test
    public void selectByUserTest(){
        SqlSession  sqlSession=getSqlSession();
        try{
        UserMapper  userMapper=sqlSession.getMapper(UserMapper.class);
        SysUser   user1=userMapper.selectByUser("杨小龙",null);
        Assert.assertEquals("学习一下",user1.getUserInfo());

        SysUser   user2=userMapper.selectByUser("","testAc@qq.com");
        Assert.assertEquals("test",user2.getUserName());

        SysUser    user3=userMapper.selectByUser("王平","457960644@qq.com");
        Assert.assertEquals("王平",user3.getUserName());



        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void updateByIdSelectiveTest(){
        SqlSession  sqlSession=getSqlSession();
        try{
            UserMapper  userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser   user1=new SysUser();
            user1.setId(1l);
            user1.setUserEmail("778899@qq.com");
            int result=userMapper.updateByIdSelective(user1);
            Assert.assertEquals(1,result);

            user1=userMapper.selectById(1l);
            Assert.assertEquals("admin",user1.getUserName());
            Assert.assertEquals("778899@qq.com",user1.getUserEmail());


        }finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void inset5Test(){
        SqlSession  sqlSession=getSqlSession();
        try {
            UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
            SysUser  user=new SysUser();
            user.setUserName("abcd");
            user.setUserPassword("123456");
            user.setUserInfo("testAc info");
            user.setCreateTime(new Date());
            userMapper.insert5(user);


            user=userMapper.selectById(userMapper.selectByUser("abcd","").getId());
            Assert.assertEquals("1405202804@qq.com",user.getUserEmail());

        }finally {
           sqlSession.rollback();
            sqlSession.close();
        }
        }

        @Test
        public void selectByIdOrUserNameTest(){
        SqlSession  sqlSession=getSqlSession();
        try {
            UserMapper  userMapper=sqlSession.getMapper(UserMapper.class);

            SysUser  query=new SysUser();
            query.setId(1l);
            query.setUserName("admin");

            SysUser user=userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);

            //id为空
            query.setId(null);
            user=userMapper.selectByIdOrUserName(query);
            Assert.assertNotNull(user);

            //id 和name都为空
            query.setUserName(null);
            user=userMapper.selectByIdOrUserName(query);
            Assert.assertNull(user);
        }finally {
            sqlSession.close();
        }
        }



    public  void printfValue(List<SysRole>  value){
        for(SysRole c:value){
            System.out.printf("%-4d%4s%4d%8s%12s%10s%25s\n",c.getId(),c.getRoleName(),c.getEnabled(),
                    c.getCreateBy(),String.format(Locale.ENGLISH,"%tY-%tm-%td",c.getCreateTime(),c.getCreateTime(),c.getCreateTime()),
                    c.getUser().getUserName(),c.getUser().getUserEmail());
        }
    }




}
