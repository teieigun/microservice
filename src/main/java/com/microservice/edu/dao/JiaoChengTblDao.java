package com.microservice.edu.dao;

import java.util.List;

import com.microservice.edu.pojo.JiaoChengTblExt1Pojo;
import com.microservice.edu.pojo.JiaoChengTblPojo;
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

	/**
	 * 可以免费观看的视频
	 * */
	public List<JiaoChengTblExt1Pojo> getAllEnableVideo(String level) {

		String sql = "SELECT ";
		sql = sql + "     t1.JIAO_CHENG_ID as jiaoChengId ";
		sql = sql + "   , t1.JIAO_CHENG_NAME as jiaoChengName ";
		sql = sql + "   , t2.BIG_CTG_NAME as bitCtgName ";
		sql = sql + "   , t1.BIG_CTG_CODE as bigCtgCode ";
		sql = sql + "   , t1.SMALL_CTG_CODE as smallCtgCode ";
		sql = sql + "   , t1.JIAO_CHENG_JIESHAO as jiaoChengJieshao ";
		sql = sql + "   , t1.JIAO_CHENG_IMG as jiaoChengImg ";
		sql = sql + "   , t1.DEL ";
		sql = sql + "   , t1.LEVLE  ";
		sql = sql + " FROM ";
		sql = sql + "   jiao_cheng_tbl t1 left join big_category_tbl t2 ";
		sql = sql + "   on t1.BIG_CTG_CODE = t2.BIG_CTG_NAME ";
		sql = sql + " WHERE ";
		sql = sql + "   t1.LEVLE < ? ";
		sql = sql + " and t1.DEL = '0' ";

		List<JiaoChengTblExt1Pojo> list = jdbcTemplate.query(sql, new Object[] { level },new BeanPropertyRowMapper(JiaoChengTblExt1Pojo.class));
		return list;
	}

	/**
	 * 可以免费观看的视频
	 * */
	public List<JiaoChengTblExt1Pojo> getAllEnableVideoByCtg(String level,String bigCtgCode,String smallCtgCode) {

		String sql = "SELECT ";
		sql = sql + "     t1.JIAO_CHENG_ID as jiaoChengId ";
		sql = sql + "   , t1.JIAO_CHENG_NAME as jiaoChengName ";
		sql = sql + "   , t2.CTG_NAME as bitCtgName ";
		sql = sql + "   , t1.BIG_CTG_CODE as bigCtgCode ";
		sql = sql + "   , t1.SMALL_CTG_CODE as smallCtgCode ";
		sql = sql + "   , t1.JIAO_CHENG_JIESHAO as jiaoChengJieshao ";
		sql = sql + "   , t1.JIAO_CHENG_IMG as jiaoChengImg ";
		sql = sql + "   , t1.DEL ";
		sql = sql + "   , t1.LEVLE  ";
		sql = sql + " FROM ";
		sql = sql + "   jiao_cheng_tbl t1 left join big_category_tbl t2 ";
		sql = sql + "   on t1.BIG_CTG_CODE = t2.CTG_CODE ";
		sql = sql + " WHERE ";
		sql = sql + "   t1.LEVLE = ? ";
		sql = sql + "   and t1.BIG_CTG_CODE = ? ";
		sql = sql + "   and t1.SMALL_CTG_CODE= ? ";
		sql = sql + " and t1.DEL = '0' ";

		List<JiaoChengTblExt1Pojo> list = jdbcTemplate.query(sql, new Object[] { level ,bigCtgCode,smallCtgCode},new BeanPropertyRowMapper(JiaoChengTblExt1Pojo.class));
		return list;
	}

}
