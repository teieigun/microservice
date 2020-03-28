package com.microservice.edu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.microservice.edu.pojo.LoginPojo;

@Repository
public class LoginDao {

	/** DAO */
	 @Autowired
	 JdbcTemplate jdbcTemplate;

	  public List<LoginPojo> getLoginInfo(String userId,String passwd){

		String sql = "SELECT ";
		sql =sql + "     T1.USERID ";
		sql =sql + "   , T1.PASSWD  ";
		sql =sql + " FROM ";
		sql =sql + "   LOGIN_TABLE T1  ";
		sql =sql + " WHERE ";
		sql =sql + "   T1.USERID = ?  ";
		sql =sql + "   AND T1.PASSWD = ? ";


	    List<LoginPojo> list= jdbcTemplate.query(sql,new Object[]{userId,passwd}, new BeanPropertyRowMapper(LoginPojo.class));
	    return list;
	  }


}