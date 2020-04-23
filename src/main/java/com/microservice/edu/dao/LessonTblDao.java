package com.microservice.edu.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.LessonTblPojo;
import com.microservice.edu.pojo.SmallCategoryTblPojo;

@Repository
public class LessonTblDao {

	/** DAO */
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 主页轮播 大分类菜单显示
	 * */
	public List<BigCategoryTblPojo> getAllBigCtg() {

		String sql = "select ";
		sql = sql + "    t1.ctg_code  as ctgCode, ";
		sql = sql + "    t1.ctg_name  as ctgName";
		sql = sql + "    from big_category_tbl T1 ";
		sql = sql + "   where t1.del is not null ";
		sql = sql + "   order by t1.ctg_code ";

		List<BigCategoryTblPojo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(BigCategoryTblPojo.class));
		return list;
	}

	/**
	 * 主页轮播 小分类菜单显示
	 * */
	public List<SmallCategoryTblPojo> getSmallCtgByPk(String bigCtgCode) {

		String sql = "select ";
		sql = sql + "    t1.small_ctg_code  as smallCtgCode, ";
		sql = sql + "    t1.big_ctg_code    as bigCtgCode, ";
		sql = sql + "    t1.small_ctg_name  as smallCtgName";
		sql = sql + "    from small_category_tbl t1 ";
		sql = sql + "   where t1.del =0 ";
		sql = sql + "   and t1.big_ctg_code =? ";
		sql = sql + "   order by t1.small_ctg_code ";

		List<SmallCategoryTblPojo> list = jdbcTemplate.query(sql, new Object[] { bigCtgCode },
				new BeanPropertyRowMapper(SmallCategoryTblPojo.class));
		return list;
	}

	/**
	 * 可以免费观看的视频
	 * */
	public List<LessonTblPojo> getAllEnableVideo(String level, String bigCtgCode, String smallCtgCode) {

		List<String> paralist = new ArrayList<String>();

		paralist.add(level);

		String sql = "SELECT ";
		sql = sql + "     t1.lesson_id      as lessonId ";
		sql = sql + "   , t1.lesson_name    as lessonName ";
		sql = sql + "   , t2.big_ctg_name   as bitCtgName ";
		sql = sql + "   , t1.big_ctg_code   as bigCtgCode ";
		sql = sql + "   , t1.small_ctg_code as smallCtgCode ";
		sql = sql + "   , t1.lesson_jieshao as lessonJieshao ";
		sql = sql + "   , t1.lesson_img     as lessonImg ";
		sql = sql + "   , t1.del ";
		sql = sql + "   , t1.levle  ";
		sql = sql + " FROM ";
		sql = sql + "   Lesson_tbl t1 left join big_category_tbl t2 ";
		sql = sql + "   on t1.big_ctg_code = t2.BIG_CTG_NAME ";
		sql = sql + " WHERE ";
		sql = sql + "   t1.levle < ? and ";

		if (bigCtgCode != null && smallCtgCode != null) {
			paralist.add(bigCtgCode);
			paralist.add(smallCtgCode);
			sql = sql + "   t1.big_ctg_code = ? and ";
			sql = sql + "   t1.small_ctg_code = ? and ";
		}
		sql = sql + "   t1.del = 0 ";

		List<LessonTblPojo> list = jdbcTemplate.query(sql, paralist.toArray(),
				new BeanPropertyRowMapper(LessonTblPojo.class));
		return list;
	}

	/**
	 * 可以免费观看的视频
	 * */
	public List<LessonTblPojo> getAllEnableVideoByCtg(String level, String bigCtgCode, String smallCtgCode) {

		String sql = "select ";
		sql = sql + "   , t1.lesson_name    as LessonName ";
		sql = sql + "   , t2.ctg_name       as bitCtgName ";
		sql = sql + "   , t1.big_ctg_code   as bigCtgCode ";
		sql = sql + "   , t1.small_ctg_code as smallCtgCode ";
		sql = sql + "   , t1.lesson_role    as lessonRole ";
		sql = sql + "   , t1.lesson_img     as LessonImg ";
		sql = sql + "   , t1.del ";
		sql = sql + "   , t1.levle  ";
		sql = sql + " from ";
		sql = sql + "   lesson_tbl t1 left join big_category_tbl t2 ";
		sql = sql + "   on t1.big_ctg_code = t2.ctg_code ";
		sql = sql + " where ";
		sql = sql + "   t1.levle = ? ";
		sql = sql + "   and t1.big_ctg_code = ? ";
		sql = sql + "   and t1.small_ctg_code= ? ";
		sql = sql + " and t1.del = '0' ";

		List<LessonTblPojo> list = jdbcTemplate.query(sql, new Object[] { level, bigCtgCode, smallCtgCode },
				new BeanPropertyRowMapper(LessonTblPojo.class));
		return list;
	}

}
