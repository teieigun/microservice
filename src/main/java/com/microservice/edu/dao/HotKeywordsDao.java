package com.microservice.edu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.edu.pojo.HotKeyPojo;

@Repository
public class HotKeywordsDao {

	/** DAO */
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<HotKeyPojo> getAllHotKey() {
		String sql = "select ";
		sql = sql + "    t1.keyword  as keyword, ";
		sql = sql + "    t1.sort  as sort ";
		sql = sql + "    from hot_keywords_tbl t1 ";
		sql = sql + "   order by t1.sort ";

		List<HotKeyPojo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(HotKeyPojo.class));
		return list;
	}

}
