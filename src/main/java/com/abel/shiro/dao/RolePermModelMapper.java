package com.abel.shiro.dao;

import com.abel.shiro.model.RolePermModel;
import com.abel.shiro.model.RolePermModelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermModelMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_perm
     *
     * @mbggenerated
     */
    int countByExample(RolePermModelCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_perm
     *
     * @mbggenerated
     */
    int deleteByExample(RolePermModelCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_perm
     *
     * @mbggenerated
     */
    int insert(RolePermModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_perm
     *
     * @mbggenerated
     */
    int insertSelective(RolePermModel record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_perm
     *
     * @mbggenerated
     */
    List<RolePermModel> selectByExample(RolePermModelCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_perm
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RolePermModel record, @Param("example") RolePermModelCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_perm
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RolePermModel record, @Param("example") RolePermModelCriteria example);
}