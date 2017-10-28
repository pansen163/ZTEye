package com.lhzt.monitor.mapper;

import com.lhzt.monitor.model.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysUserMapper {
    @Delete({
        "delete from sys_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_user (id, organization_id, ",
        "username, password, ",
        "salt, role_ids, locked)",
        "values (#{id,jdbcType=BIGINT}, #{organizationId,jdbcType=BIGINT}, ",
        "#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{salt,jdbcType=VARCHAR}, #{roleIds,jdbcType=VARCHAR}, #{locked,jdbcType=BIT})"
    })
    int insert(SysUser record);

    int insertSelective(SysUser record);

    @Select({
        "select",
        "id, organization_id, username, password, salt, role_ids, locked",
        "from sys_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.lhzt.monitor.mapper.SysUserMapper.BaseResultMap")
    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    @Update({
        "update sys_user",
        "set organization_id = #{organizationId,jdbcType=BIGINT},",
          "username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "salt = #{salt,jdbcType=VARCHAR},",
          "role_ids = #{roleIds,jdbcType=VARCHAR},",
          "locked = #{locked,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysUser record);
}