package com.hfrontier.teamb.mapper;

import com.hfrontier.teamb.repository.Users;

public interface UsersMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbg.generated  Thu Feb 28 20:42:23 JST 2019
	 */
	int insert(Users record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table users
	 * @mbg.generated  Thu Feb 28 20:42:23 JST 2019
	 */
	int insertSelective(Users record);

	int selectByUserId(String userId);


}