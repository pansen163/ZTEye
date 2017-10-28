package com.lhzt.monitor.mapper;

import com.lhzt.monitor.model.SysResource;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysResourceMapper {
    @Delete({
        "delete from sys_resource",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_resource (id, name, ",
        "type, url, parent_id, ",
        "parent_ids, permission, ",
        "available)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, #{parentId,jdbcType=BIGINT}, ",
        "#{parentIds,jdbcType=VARCHAR}, #{permission,jdbcType=VARCHAR}, ",
        "#{available,jdbcType=BIT})"
    })
    int insert(SysResource record);

    int insertSelective(SysResource record);

    @Select({
        "select",
        "id, name, type, url, parent_id, parent_ids, permission, available",
        "from sys_resource",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.lhzt.monitor.mapper.SysResourceMapper.BaseResultMap")
    SysResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysResource record);

    @Update({
        "update sys_resource",
        "set name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=BIGINT},",
          "parent_ids = #{parentIds,jdbcType=VARCHAR},",
          "permission = #{permission,jdbcType=VARCHAR},",
          "available = #{available,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysResource record);
}