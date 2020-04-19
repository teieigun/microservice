package com.microservice.edu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.microservice.edu.pojo.BigCategoryTblPojo;

@Repository
public class BigCategoryTblDao {


	/** DAO */
	 @Autowired
	 JdbcTemplate jdbcTemplate;

	@GetMapping("getAllBigCtg")
	  public List<BigCategoryTblPojo> getAllBigCtg(){

		String sql = "SELECT ";
		sql =sql + "    T1.BIG_CTG_CODE as ctgCode, ";
		sql =sql + "    T1.BIG_CTG_NAME  as ctgName";
		sql =sql + "    FROM big_category_tbl T1 ";
		sql =sql + "   WHERE T1.DEL = 0 ";
		sql =sql + "   ORDER BY T1.BIG_CTG_CODE ";


		List<BigCategoryTblPojo> list= jdbcTemplate.query(sql, new BeanPropertyRowMapper(BigCategoryTblPojo.class));
	    return list;
	  }
}
