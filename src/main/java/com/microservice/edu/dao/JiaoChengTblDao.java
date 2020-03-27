package com.microservice.edu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.SmallCategoryTblPojo;

@Repository
public class JiaoChengTblDao {

	/** DAO */
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 主页轮播 大分类菜单显示
	 * */
	@GetMapping("getAllBigCtg")
	public List<BigCategoryTblPojo> getAllBigCtg() {

		String sql = "SELECT ";
		sql = sql + "    T1.CTG_CODE as ctgCode, ";
		sql = sql + "    T1.CTG_NAME  as ctgName";
		sql = sql + "    FROM BIG_CATEGORY_TBL T1 ";
		sql = sql + "   WHERE T1.DEL IS NOT NULL ";
		sql = sql + "   ORDER BY T1.CTG_CODE ";

		List<BigCategoryTblPojo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BigCategoryTblPojo.class));
		return list;
	}

	/**
	 * 主页轮播 小分类菜单显示
	 * */
	@GetMapping("getBigCtgByPk")
	public List<SmallCategoryTblPojo> getSmallCtgByPk(String bigCtgCode) {

		String sql = "select ";
		sql = sql + "    t1.small_ctg_code as smallCtgCode, ";
		sql = sql + "    t1.big_ctg_code as bigCtgCode, ";
		sql = sql + "    t1.small_ctg_name  as smallCtgName";
		sql = sql + "    from small_category_tbl t1 ";
		sql = sql + "   where t1.del is not null ";
		sql = sql + "   and t1.big_ctg_code =? ";
		sql = sql + "   order by t1.small_ctg_code ";

		List<SmallCategoryTblPojo> list = jdbcTemplate.query(sql, new Object[] { bigCtgCode },new BeanPropertyRowMapper(SmallCategoryTblPojo.class));
		return list;
	}


}
