package com.hfrontier.teamb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hfrontier.teamb.repository.Members;
@Mapper
public interface MembersMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table members
	 * @mbg.generated  Thu Apr 04 21:12:17 JST 2019
	 */
	int insert(Members record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table members
	 * @mbg.generated  Thu Apr 04 21:12:17 JST 2019
	 */
	int insertSelective(Members record);

	Members selectByPrimaryKey(@Param("userId")String userId);
}