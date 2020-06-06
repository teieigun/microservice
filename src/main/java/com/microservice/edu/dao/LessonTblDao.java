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
	 * 现实所以视频，同时显示购买情况
	 * */
	public List<LessonTblPojo> getAllEnableVideo(String email, String bigCtgCode, String smallCtgCode) {

		List<String> paralist = new ArrayList<String>();

		paralist.add(email);

		String sql = "SELECT ";
		sql = sql + "   t3.lessonId ";
		sql = sql + "   ,(case when email IS NULL then '0' ELSE '1' END) AS buyFlg ";
		sql = sql + "   ,CONCAT(t3.lessonName,case  ";
		sql = sql + "                             when email IS NULL then (case when (SELECT sum(S.TEST_FLG) AS cnt FROM lesson_chapter S WHERE t3.lessonId = S.LESSON_ID)>0  ";
		sql = sql + "           then '<span style=\"color:#2C7CFF;font-weight:bold;\">　<br/>未购买（有试听）</span>' ";
		sql = sql + "           ELSE '<span style=\"color:#FFAD90;font-weight:bold;\">　<br/>未购买（无试听）</span>' ";
		sql = sql + "   END) ELSE '<span style=\"color:#008000;font-weight:bold;\">　<br/>已购买</span>' END) AS lessonName ";
		sql = sql + " ,case  when email IS NULL then (case when (SELECT sum(S.TEST_FLG) AS cnt FROM lesson_chapter S WHERE t3.lessonId = S.LESSON_ID)>0 ";
		sql = sql + " then 1 ELSE 0 END) ELSE 2 END AS statusFlg  ";
		sql = sql + "   ,t3.bitCtgName ";
		sql = sql + "   ,t3.bigCtgCode ";
		sql = sql + "   ,t3.smallCtgCode ";
		sql = sql + "   ,t3.lessonImg ";
		sql = sql + "   ,t3.lessonType ";
		sql = sql + "    ";
		sql = sql + "   ,case when email IS NULL then 'N' ELSE 'Y' END AS videoFlg ";
		sql = sql + "  FROM (SELECT ";
		sql = sql + "   t1.lesson_id as lessonId ";
		sql = sql + "   , t1.lesson_name as lessonName ";
		sql = sql + "   , t2.big_ctg_name as bitCtgName ";
		sql = sql + "   , t1.big_ctg_code as bigCtgCode ";
		sql = sql + "   , t1.small_ctg_code as smallCtgCode ";
		sql = sql + "   , t1.lesson_img as lessonImg ";
		sql = sql + "   , t1.del ";
		sql = sql + "   , 'lesson' AS lessonType ";
		sql = sql + "   FROM lesson_tbl t1 left join big_category_tbl t2 ";
		sql = sql + "   on t1.big_ctg_code = t2.BIG_CTG_NAME ";
		sql = sql + "   UNION ";
		sql = sql + "   SELECT ";
		sql = sql + "   T2.course_id AS lessonId ";
		sql = sql + "   ,T2.course_name AS lessonName ";
		sql = sql + "   ,'' AS bitCtgName ";
		sql = sql + "   ,'' AS bigCtgCode ";
		sql = sql + "   ,'' AS smallCtgCode ";
		sql = sql + "   ,T2.course_img AS lessonImg ";
		sql = sql + "   ,T2.del ";
		sql = sql + "   ,'course' AS lessonType ";
		sql = sql + "   FROM course_master T2 ) t3 ";
		sql = sql + "   LEFT JOIN (SELECT email, ";
		sql = sql + "   lesson_id AS lessonId, ";
		sql = sql + "   lessonType ";
		sql = sql + "   FROM ";
		sql = sql + "  (SELECT t1.email, ";
		sql = sql + "   t2.lesson_id, ";
		sql = sql + "   'lesson' AS lessonType ";
		sql = sql + "   FROM user_course_mapping t1 LEFT JOIN course_lesson_mapping t2 ";
		sql = sql + "   ON t1.course_id = t2.course_id ";
		sql = sql + "  UNION ";
		sql = sql + "   SELECT t3.email, ";
		sql = sql + "   t3.lesson_id, ";
		sql = sql + "   'lesson' AS lessonType ";
		sql = sql + "   FROM user_lession_mapping t3 ";
		sql = sql + "  UNION ";
		sql = sql + "   SELECT t4.email, ";
		sql = sql + "   t4.course_id as lesson_id, ";
		sql = sql + "   'course' AS lessonType ";
		sql = sql + "   FROM user_course_mapping t4 ";
		sql = sql + "  ) t5 WHERE t5.email = ?) t6 ";
		sql = sql + "  ON t3.lessonId =t6.lessonId AND ";
		sql = sql + "  t3.lessonType =t6.lessonType ";
		sql = sql + "  WHERE 1=1 ";

		if (bigCtgCode != null && smallCtgCode != null) {
			paralist.add(bigCtgCode);
			paralist.add(smallCtgCode);
			sql = sql + " and  t3.bigCtgCode = ?  ";
			sql = sql + " and  t3.smallCtgCode = ?  ";
		}
		sql = sql + "  and t3.del = 0 ";

		List<LessonTblPojo> list = jdbcTemplate.query(sql, paralist.toArray(),
				new BeanPropertyRowMapper(LessonTblPojo.class));
		return list;
	}


	/**
	 * 现实所以视频，同时显示购买情况
	 * */
	public List<LessonTblPojo> getAllEnableVideoUseLike(String email, String videoNm) {

		List<String> paralist = new ArrayList<String>();

		paralist.add(email);

		String sql = "SELECT ";
		sql = sql + "  t3.lessonId";
		sql = sql + "  ,(case when email IS NULL then '0' ELSE '1' END) AS buyFlg ";
		sql = sql + "  ,CONCAT(t3.lessonName,case when email IS NULL then '　未购买' ELSE '　<span style=\"color:#ff0000;font-weight:bold;\">已购买</span>' END) AS lessonName ";
		sql = sql + "  ,t3.bitCtgName";
		sql = sql + "  ,t3.bigCtgCode";
		sql = sql + "  ,t3.smallCtgCode";
		sql = sql + "  ,t3.lessonImg";
		sql = sql + "  ,t3.lessonType";
		sql = sql + "  ,case when email IS NULL then 'N' ELSE 'Y' END AS videoFlg";
		sql = sql + " FROM (SELECT ";
		sql = sql + "  t1.lesson_id as lessonId ";
		sql = sql + "  , t1.lesson_name as lessonName ";
		sql = sql + "  , t2.big_ctg_name as bitCtgName ";
		sql = sql + "  , t1.big_ctg_code as bigCtgCode ";
		sql = sql + "  , t1.small_ctg_code as smallCtgCode ";
		sql = sql + "  , t1.lesson_img as lessonImg ";
		sql = sql + "  , t1.del ";
		sql = sql + "  , 'lesson' AS lessonType";
		sql = sql + "  FROM lesson_tbl t1 left join big_category_tbl t2 ";
		sql = sql + "  on t1.big_ctg_code = t2.BIG_CTG_NAME ";
		sql = sql + "  UNION ";
		sql = sql + "  SELECT ";
		sql = sql + "  T2.course_id AS lessonId";
		sql = sql + "  ,T2.course_name AS lessonName";
		sql = sql + "  ,'' AS bitCtgName";
		sql = sql + "  ,'' AS bigCtgCode";
		sql = sql + "  ,'' AS smallCtgCode";
		sql = sql + "  ,T2.course_img AS lessonImg";
		sql = sql + "  ,T2.del";
		sql = sql + "  ,'course' AS lessonType";
		sql = sql + "  FROM course_master T2 ) t3 ";
		sql = sql + "  LEFT JOIN (SELECT email,";
		sql = sql + "  lesson_id AS lessonId,";
		sql = sql + "  lessonType ";
		sql = sql + "  FROM ";
		sql = sql + " (SELECT t1.email,";
		sql = sql + "  t2.lesson_id,";
		sql = sql + "  'lesson' AS lessonType ";
		sql = sql + "  FROM user_course_mapping t1 LEFT JOIN course_lesson_mapping t2";
		sql = sql + "  ON t1.course_id = t2.course_id";
		sql = sql + " UNION ";
		sql = sql + "  SELECT t3.email,";
		sql = sql + "  t3.lesson_id,";
		sql = sql + "  'lesson' AS lessonType ";
		sql = sql + "  FROM user_lession_mapping t3";
		sql = sql + " UNION ";
		sql = sql + "  SELECT t4.email,";
		sql = sql + "  t4.course_id as lesson_id,";
		sql = sql + "  'course' AS lessonType ";
		sql = sql + "  FROM user_course_mapping t4";
		sql = sql + " ) t5 WHERE t5.email = ?) t6";
		sql = sql + " ON t3.lessonId =t6.lessonId AND";
		sql = sql + " t3.lessonType =t6.lessonType";
		sql = sql + " WHERE 1=1 ";

		if (videoNm != null) {
			paralist.add( "%"+videoNm + "%");
			sql = sql + " and  t3.lessonName like ?  ";
		}
		sql = sql + "  and t3.del = 0 ";

		List<LessonTblPojo> list = jdbcTemplate.query(sql, paralist.toArray(),
				new BeanPropertyRowMapper(LessonTblPojo.class));
		return list;
	}


	/**
	 * 现实所以视频，同时显示购买情况
	 * */
	public List<LessonTblPojo> getAllEnableVideoIsRcommend(String email) {

		List<String> paralist = new ArrayList<String>();

		paralist.add(email);

		String sql = "SELECT ";
		sql = sql + "  t3.lessonId";
		sql = sql + "  ,(case when email IS NULL then '0' ELSE '1' END) AS buyFlg ";
		sql = sql + "  ,CONCAT(t3.lessonName,case when email IS NULL then '　未购买' ELSE '　<span style=\"color:#ff0000;font-weight:bold;\">已购买</span>' END) AS lessonName ";
		sql = sql + "  ,t3.bitCtgName";
		sql = sql + "  ,t3.bigCtgCode";
		sql = sql + "  ,t3.smallCtgCode";
		sql = sql + "  ,t3.lessonImg";
		sql = sql + "  ,t3.lessonType";
		sql = sql + "  ,case when email IS NULL then 'N' ELSE 'Y' END AS videoFlg";
		sql = sql + " FROM (SELECT ";
		sql = sql + "  t1.lesson_id as lessonId ";
		sql = sql + "  , t1.lesson_name as lessonName ";
		sql = sql + "  , t2.big_ctg_name as bitCtgName ";
		sql = sql + "  , t1.big_ctg_code as bigCtgCode ";
		sql = sql + "  , t1.small_ctg_code as smallCtgCode ";
		sql = sql + "  , t1.lesson_img as lessonImg ";
		sql = sql + "  , t1.del ";
		sql = sql + "  , 'lesson' AS lessonType";
		sql = sql + "  FROM lesson_tbl t1 left join big_category_tbl t2 ";
		sql = sql + "  on t1.big_ctg_code = t2.BIG_CTG_NAME ";
		sql = sql + "  UNION ";
		sql = sql + "  SELECT ";
		sql = sql + "  T2.course_id AS lessonId";
		sql = sql + "  ,T2.course_name AS lessonName";
		sql = sql + "  ,'' AS bitCtgName";
		sql = sql + "  ,'' AS bigCtgCode";
		sql = sql + "  ,'' AS smallCtgCode";
		sql = sql + "  ,T2.course_img AS lessonImg";
		sql = sql + "  ,T2.del";
		sql = sql + "  ,'course' AS lessonType";
		sql = sql + "  FROM course_master T2 ) t3 ";
		sql = sql + "  LEFT JOIN (SELECT email,";
		sql = sql + "  lesson_id AS lessonId,";
		sql = sql + "  lessonType ";
		sql = sql + "  FROM ";
		sql = sql + " (SELECT t1.email,";
		sql = sql + "  t2.lesson_id,";
		sql = sql + "  'lesson' AS lessonType ";
		sql = sql + "  FROM user_course_mapping t1 LEFT JOIN course_lesson_mapping t2";
		sql = sql + "  ON t1.course_id = t2.course_id";
		sql = sql + " UNION ";
		sql = sql + "  SELECT t3.email,";
		sql = sql + "  t3.lesson_id,";
		sql = sql + "  'lesson' AS lessonType ";
		sql = sql + "  FROM user_lession_mapping t3";
		sql = sql + " UNION ";
		sql = sql + "  SELECT t4.email,";
		sql = sql + "  t4.course_id as lesson_id,";
		sql = sql + "  'course' AS lessonType ";
		sql = sql + "  FROM user_course_mapping t4";
		sql = sql + " ) t5 WHERE t5.email = ?) t6";
		sql = sql + " ON t3.lessonId =t6.lessonId AND";
		sql = sql + " t3.lessonType =t6.lessonType ";
		sql = sql + " INNER JOIN recommend_lesson A ON t3.lessonId =A.lesson_id ";
		sql = sql + " WHERE t3.del = 0 and A.del=0";

		List<LessonTblPojo> list = jdbcTemplate.query(sql, paralist.toArray(),
				new BeanPropertyRowMapper(LessonTblPojo.class));
		return list;
	}

	/**
	 * 显示已购买套餐的内容
	 * */
	public List<LessonTblPojo> getBuyCourseVideo(String email, String courseId) {

		List<String> paralist = new ArrayList<String>();

		paralist.add(email);
		paralist.add(courseId);

		String sql = " SELECT ";
		sql = sql + "   t1.LESSON_ID AS lessonId";
		sql = sql + "  ,CONCAT(t1.LESSON_NAME,'　<span style=\"color:#ff0000;font-weight:bold;\">已购买</span>') AS lessonName";
		sql = sql + "  ,t1.BIG_CTG_CODE AS bigCtgCode";
		sql = sql + "  ,t1.SMALL_CTG_CODE AS smallCtgCode";
		sql = sql + "  ,t1.LESSON_IMG AS lessonImg";
		sql = sql + "  ,'lesson' AS lessonType";
		sql = sql + "  ,t1.DEL";
		sql = sql + " FROM lesson_tbl t1";
		sql = sql + " WHERE t1.DEL =0 ";
		sql = sql + "  AND t1.LESSON_ID IN";
		sql = sql + "   (SELECT t2.lesson_id";
		sql = sql + "      FROM user_course_mapping t1 LEFT JOIN ";
		sql = sql + "           course_lesson_mapping t2 ";
		sql = sql + "        ON t1.course_id = t2.course_id";
		sql = sql + "     WHERE t1.email =? ";
		sql = sql + "       and t1.course_id = ?) ";


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


	/**
	 * 根据大分类和小分类，取得课程一览
	 * */
	public List<LessonTblPojo> getLessonListByCtg(String bigCtgCode, String smallCtgCode) {

		String sql = "select ";
		sql = sql + "     t1.lesson_name    as LessonName ";
		sql = sql + "   , t1.big_ctg_code   as bigCtgCode ";
		sql = sql + "   , t1.small_ctg_code as smallCtgCode ";
		sql = sql + "   , t1.lesson_img     as LessonImg ";
		sql = sql + "   , t1.upload_path    as uploadPath ";
		sql = sql + "   , t1.del ";
		sql = sql + "   , t1.levle  ";
		sql = sql + " from ";
		sql = sql + "   lesson_tbl t1 ";
		sql = sql + " where ";
		sql = sql + "       t1.big_ctg_code = ? ";
		sql = sql + "   and t1.small_ctg_code= ? ";
		sql = sql + "   and t1.del = '0' order by t1.big_ctg_code,t1.small_ctg_code";

		List<LessonTblPojo> list = jdbcTemplate.query(sql, new Object[] {bigCtgCode, smallCtgCode },
				new BeanPropertyRowMapper(LessonTblPojo.class));
		return list;
	}

}
