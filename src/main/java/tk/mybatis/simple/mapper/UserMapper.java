package tk.mybatis.simple.mapper;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.simple.model.SysRole;
import tk.mybatis.simple.model.SysUser;

import java.util.List;

public interface UserMapper {

    SysUser  selectById(Long id);

    /**
     * 查询全部用户
     * @return
     */

    List<SysUser>   selectAll();

    /**
     * 根据用户ID获取角色信息
     * @param
     * @return
     */
    List<SysRole>  selectRoleByUserId(Long userId);

    /**
     * 根据用户ID获取角色信息还有其他表的字段：二种方法（1、SysRole中直接添加字段 2、xml中sql中增加u.user_name  as "user.userName"）
     * @param
     * @return
     */
    List<SysRole>  selectRoleByUserId2(Long userId);

    /**
     * 新增用户
     * @return
     */
    int   insert(SysUser sysUser);

    /**
     * 新增用户
     * @param
     * @return
     */
    int   insert2(SysUser sysUser);

    /**
     * 新增用户
     * @param  
     * @return
     */
    int   insert3(SysUser sysUser);


    /**
     * 更新操作
     * @param
     * @return
     */
    int  updateById(SysUser sysUser);

    /**
     * 删除数据操作
     * @param
     * @return
     */
    int  deleteById(Long id);

    /**
     * 根据用户id和用户enable状态获取用户数据
     * @param  userId
     * @param  enable
     * @return
     */
    List<SysRole>  selectRoleByIdAndRoldEnabled(@Param("userId") Long userId, @Param("enabled") Long enable);

    /**
     * 根据用户id和用户enable状态获取用户数据
     * @param  user
     * @param  role
     * @return
     */
    List<SysRole>  selectRoleByIdAndRoldEnabled2(@Param("user") SysUser user, @Param("role") SysRole role);


    /**
     * 查询角色表根据id
     * @param  id
     * @return
     *
     */
     SysRole   selectRoleById(Long id);

    /**
     * 可根据用户名或邮箱查询
     */
    SysUser  selectByUser(@Param("userName") String username,@Param("userEmail") String email);

    /**
     * 更新数据
     */

    int  updateByIdSelective(SysUser sysUser);


    /**
     * 插入
     */
    int  insert5(SysUser user);

    /**
     * 根据用户id或用户名查询
     */
    SysUser   selectByIdOrUserName(SysUser user);
}


