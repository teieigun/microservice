package com.microservice.edu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.edu.pojo.LessonChapterPojo;


@Repository
public class LessonChapterDao {

	/**
	 * DAO
	 */
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 可以观看的视频
	 */
	public List<LessonChapterPojo> getChapterList(String email, String lesson_id) {

		String sql = "select distinct";
		sql = sql + "     lesson_id as LessonId ";
		sql = sql + "   , chapter_no as chapterNo ";
		sql = sql + "   , chapter_name as chapterName ";
		sql = sql + " from ";
		sql = sql + "   lesson_chapter  ";
		sql = sql + " where lesson_id = ? ";
		sql = sql + "   and lesson_id in ";
		sql = sql + " (SELECT t2.lesson_id";
		sql = sql + "  FROM user_course_mapping t1 LEFT JOIN ";
		sql = sql + "       course_lesson_mapping t2 ";
		sql = sql + "    ON t1.course_id = t2.course_id";
		sql = sql + " WHERE t1.email =? ";
		sql = sql + " UNION ";
		sql = sql + "    SELECT t3.lesson_id ";
		sql = sql + "      FROM user_lession_mapping t3 )";
		sql = sql + " order by ";
		sql = sql + "     lesson_id ";
		sql = sql + "   , chapter_no ";

		List<LessonChapterPojo> list = jdbcTemplate.query(sql, new Object[] { lesson_id, email},
				new BeanPropertyRowMapper(LessonChapterPojo.class));
		return list;
	}

	/**
	 * 可以观看的视频
	 */
	public List<LessonChapterPojo> getChapterListNotBuy(String lesson_id) {

		String sql = "select distinct";
		sql = sql + "     lesson_id as LessonId ";
		sql = sql + "   , chapter_no as chapterNo ";
		sql = sql + "   , chapter_name as chapterName ";
		sql = sql + " from ";
		sql = sql + "   lesson_chapter  ";
		sql = sql + " where lesson_id = ? ";

		List<LessonChapterPojo> list = jdbcTemplate.query(sql, new Object[] { lesson_id},
				new BeanPropertyRowMapper(LessonChapterPojo.class));
		return list;
	}

	/**
	 * 登录用户可以观看的视频
	 */
	public List<LessonChapterPojo> getSectionList(String email, String lesson_id) {

		String sql = "select ";
		sql = sql + "     lesson_id as LessonId ";
		sql = sql + "   , chapter_no as chapterNo ";
		sql = sql + "   , chapter_name as chapterName ";
		sql = sql + "   , section_no as sectionNo ";
		sql = sql + "   , section_name as sectionName ";
		sql = sql + "   , 1 as testFlg ";
		sql = sql + "   , url as url ";
		sql = sql + " from ";
		sql = sql + "   lesson_chapter  ";
		sql = sql + " where lesson_id = ? ";
		sql = sql + "   and lesson_id in ";
		sql = sql + " (SELECT t2.lesson_id";
		sql = sql + "  FROM user_course_mapping t1 LEFT JOIN ";
		sql = sql + "       course_lesson_mapping t2 ";
		sql = sql + "    ON t1.course_id = t2.course_id";
		sql = sql + " WHERE t1.email =? ";
		sql = sql + " UNION ";
		sql = sql + "    SELECT t3.lesson_id ";
		sql = sql + "      FROM user_lession_mapping t3 where t3.email=?)";
		sql = sql + " order by ";
		sql = sql + "     lesson_id ";
		sql = sql + "   , chapter_no ";
		sql = sql + "   , section_no ";
		;

		List<LessonChapterPojo> list = jdbcTemplate.query(sql, new Object[] { lesson_id, email, email},
				new BeanPropertyRowMapper(LessonChapterPojo.class));
		return list;
	}
	/**
	 * 非购买用户可以观看的视频
	 */
	public List<LessonChapterPojo> getSectionListByNotBuy(String lesson_id) {

		String sql = "select ";
		sql = sql + "     lesson_id as LessonId ";
		sql = sql + "   , chapter_no as chapterNo ";
		sql = sql + "   , chapter_name as chapterName ";
		sql = sql + "   , section_no as sectionNo ";
		sql = sql + "   , section_name as sectionName ";
		sql = sql + "   , test_flg as test_flg ";
		sql = sql + "   , url as url ";
		sql = sql + " from ";
		sql = sql + "   lesson_chapter  ";
		sql = sql + " where lesson_id = ? ";

		List<LessonChapterPojo> list = jdbcTemplate.query(sql, new Object[] {lesson_id},
				new BeanPropertyRowMapper(LessonChapterPojo.class));
		return list;
	}

	/**
	 * 观看特定视频
	 */
	public List<LessonChapterPojo> getOneSection(String LessonId, String chapterNo, String sectionNo) {

		String sql = "select ";
		sql = sql + "     lesson_id       ";
		sql = sql + "   , chapter_no          ";
		sql = sql + "   , chapter_name  ";
		sql = sql + "   , section_no          ";
		sql = sql + "   , section_name  ";
		sql = sql + "   , url      ";
		sql = sql + " from";
		sql = sql + "   lesson_chapter ";
		sql = sql + " where";
		sql = sql + "   lesson_id = ? ";
		sql = sql + " and chapter_no = ? ";
		sql = sql + " and section_no = ? ";

		List<LessonChapterPojo> list = jdbcTemplate.query(sql, new Object[] { LessonId, chapterNo, sectionNo },
				new BeanPropertyRowMapper(LessonChapterPojo.class));
		return list;
	}

	/**
	 * 观看特定视频
	 */
	public List<LessonChapterPojo> getOneSection(String LessonId, String chapterNo, String sectionNo,String email) {

		String sql = "SELECT ";
		sql = sql + " B.lesson_id, ";
		sql = sql + " B.chapter_no, ";
		sql = sql + " B.chapter_name, ";
		sql = sql + " B.section_no, ";
		sql = sql + " B.section_name, ";
		sql = sql + " CASE WHEN T6.SECTION_NO is NULL THEN '/callme' ELSE url END AS url";
		sql = sql + " FROM lesson_chapter B";
		sql = sql + " LEFT JOIN (";
		sql = sql + " SELECT T5.LESSON_ID,T5.CHAPTER_NO,T5.SECTION_NO";
		sql = sql + " FROM (";
		sql = sql + " SELECT T4.LESSON_ID,T4.CHAPTER_NO,T4.SECTION_NO";
		sql = sql + " FROM lesson_chapter T4";
		sql = sql + " WHERE T4.TEST_FLG=1 AND T4.LESSON_ID=? UNION";
		sql = sql + " SELECT B.lesson_id, B.CHAPTER_NO, B.SECTION_NO";
		sql = sql + " FROM user_lession_mapping A";
		sql = sql + " INNER JOIN lesson_chapter B ON A.lesson_id = B.LESSON_ID";
		sql = sql + " WHERE A.email=? AND A.lesson_id=? UNION";
		sql = sql + " SELECT T3.lesson_id, T3.CHAPTER_NO, T3.SECTION_NO";
		sql = sql + " FROM user_course_mapping T1";
		sql = sql + " INNER JOIN course_lesson_mapping T2 ON T1.course_id = T2.course_id";
		sql = sql + " INNER JOIN lesson_chapter T3 ON T2.lesson_id = T3.LESSON_ID";
		sql = sql + " WHERE T1.email =? AND T3.LESSON_ID=?) T5";
		sql = sql + " ) T6 ON B.lesson_id = T6.LESSON_ID AND B.chapter_no = T6.CHAPTER_NO AND B.section_no = T6.SECTION_NO";

		sql = sql + " WHERE";
		sql = sql + "   B.lesson_id = ? ";
		sql = sql + " AND B.chapter_no = ? ";
		sql = sql + " AND B.section_no = ? ";

		List<LessonChapterPojo> list = jdbcTemplate.query(sql, new Object[] { LessonId,email,LessonId,email,LessonId,LessonId, chapterNo, sectionNo },
				new BeanPropertyRowMapper(LessonChapterPojo.class));
		return list;
	}
	/**
	 * 观看特定视频
	 */
	public int getTestChapter(String lessonId) {

		String sql = "SELECT SUM(T1.TEST_FLG) as cnt FROM lesson_chapter T1 WHERE T1.LESSON_ID=? ";

		Integer count = jdbcTemplate.queryForObject(sql, new Object[] {lessonId},Integer.class);

		if(count==null){
			 count=0;
		}
		return count;

	}




}
