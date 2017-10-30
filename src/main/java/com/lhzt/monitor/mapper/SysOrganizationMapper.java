package com.lhzt.monitor.mapper;

import com.lhzt.monitor.model.SysOrganization;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysOrganizationMapper {
    @Delete({
        "delete from sys_organization",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into sys_organization (id, name, ",
        "parent_id, parent_ids, ",
        "available)",
        "values (#{id,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{parentId,jdbcType=BIGINT}, #{parentIds,jdbcType=VARCHAR}, ",
        "#{available,jdbcType=BIT})"
    })
    int insert(SysOrganization record);

    int insertSelective(SysOrganization record);

    @Select({
        "select",
        "id, name, parent_id, parent_ids, available",
        "from sys_organization",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("com.lhzt.monitor.mapper.SysOrganizationMapper.BaseResultMap")
    SysOrganization selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysOrganization record);

    @Update({
        "update sys_organization",
        "set name = #{name,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=BIGINT},",
          "parent_ids = #{parentIds,jdbcType=VARCHAR},",
          "available = #{available,jdbcType=BIT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(SysOrganization record);
}