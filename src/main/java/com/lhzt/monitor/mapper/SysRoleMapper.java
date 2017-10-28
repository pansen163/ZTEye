package com.lhzt.monitor.mapper;

import com.lhzt.monitor.model.SysRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysRoleMapper {
    @Delete({
        "delete from sys_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_role (id, role, ",
        "description, resource_ids, ",
        "available)",
        "values (#{id,jdbcType=BIGINT}, #{role,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR}, #{resourceIds,jdbcType=VARCHAR}, ",
        "#{available,jdbcType=BIT})"
    })
    int insert(SysRole record);

    int insertSelective(SysRole record);

    @Select({
        "select",
        "id, role, description, resource_ids, available",
        "from sys_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.lhzt.monitor.mapper.SysRoleMapper.BaseResultMap")
    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    @Update({
        "update sys_role",
        "set role = #{role,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "resource_ids = #{resourceIds,jdbcType=VARCHAR},",
          "available = #{available,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysRole record);
}