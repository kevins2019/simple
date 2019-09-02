package tk.mybatis.simple.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import tk.mybatis.simple.model.SysRole;

import java.util.Date;
import java.util.List;

public class RoleMapperTest extends BaseMapperTest {

    @Test
    public void selectById() {

        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);

            Assert.assertNotNull(roleMapper.selectById(1l));

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectById2() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            Assert.assertNotNull(roleMapper.selectById2(1l));

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void selectAllTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            List<SysRole> sysRoleList = roleMapper.selectAll();

            Assert.assertTrue(sysRoleList.size() > 0);

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void insertTest() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("eee");
            sysRole.setEnabled(1l);
            sysRole.setCreateBy(1l);
            sysRole.setCreateTime(new Date(2019, 8, 28));
            int result = roleMapper.insert(sysRole);

            Assert.assertEquals(1, result);
        } finally {
            sqlSession.close();
        }

    }


    @Test
    public void insert2Test() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("fff");
            sysRole.setEnabled(1l);
            sysRole.setCreateBy(3l);
            sysRole.setCreateTime(new Date(2019, 8, 28));
            int result = roleMapper.insert2(sysRole);

            Assert.assertEquals(1, result);
        } finally {
            sqlSession.close();
        }

    }


    @Test
    public void insert3Test() {
        SqlSession sqlSession = getSqlSession();
        try {
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            SysRole sysRole = new SysRole();
            sysRole.setRoleName("GGG");
            sysRole.setEnabled(1l);
            sysRole.setCreateBy(4l);
            sysRole.setCreateTime(new Date(2019, 8, 28));
            int result = roleMapper.insert3(sysRole);

            Assert.assertEquals(1, result);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void   updateTest(){
        SqlSession  sqlSession=getSqlSession();
        try{
            RoleMapper  roleMapper=sqlSession.getMapper(RoleMapper.class);
            SysRole  sysRole=roleMapper.selectById(1L);
            sysRole.setCreateBy(6l);
            int  result=roleMapper.updateById(sysRole);
            Assert.assertEquals(1,result);

        }finally {
            sqlSession.close();
        }

    }

    @Test
    public void   deleteTest(){
        SqlSession sqlSession=getSqlSession();

        try{
            RoleMapper  roleMapper=sqlSession.getMapper(RoleMapper.class);
            int result=roleMapper.deleteById(2l);

            Assert.assertEquals(1,result);

        }finally {
            sqlSession.close();
        }
    }



}