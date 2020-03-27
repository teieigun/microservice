package com.microservice.edu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.edu.pojo.CodePojo;

@Repository
public class StCodeMstDao {

	/** DAO */
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<CodePojo> getCodeList(String id) {
		String sql = "SELECT ";
		sql = sql + "     CODE_ID AS codeId, ";
		sql = sql + "     CODE_NM AS codeNm ";
		sql = sql + " FROM   ";
		sql = sql + "   ST_CODE_MST WHERE ID = ? ORDER BY SORT_ID ";

		List<CodePojo> list = jdbcTemplate.query(sql, new Object[] { id },
				new BeanPropertyRowMapper(CodePojo.class));

		return list;
	}
	
	public String getCodeNm(String id,String codeId) {
		
		String codeNmStr = "";
		
		String sql = "SELECT ";
		sql = sql + "     CODE_NM AS codeNm ";
		sql = sql + " FROM   ";
		sql = sql + "   ST_CODE_MST WHERE ID = ? AND CODE_ID = ? ORDER BY SORT_ID ";

		List<CodePojo> list = jdbcTemplate.query(sql, new Object[] { id,codeId},
				new BeanPropertyRowMapper(CodePojo.class));

		if(list !=null && list.size() > 0) {
			codeNmStr = list.get(0).codeNm;
		}
		
		return codeNmStr;
	}
}