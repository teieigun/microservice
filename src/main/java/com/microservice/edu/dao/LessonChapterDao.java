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
     * 可以免费观看的视频
     */
    public List<LessonChapterPojo> getChapterList(String email, String lesson_id) {

        String sql = "select ";
        sql = sql + "     lesson_id as LessonId ";
        sql = sql + "   , chapter_no as chapterNo ";
        sql = sql + "   , chapter_name as chapterName ";
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
        sql = sql + "      FROM user_lession_mapping t3)";
        sql = sql + " order by ";
        sql = sql + "     lesson_id ";
        sql = sql + "   , chapter_no ";

        List<LessonChapterPojo> list = jdbcTemplate.query(sql, new Object[]{lesson_id, email}, new BeanPropertyRowMapper(LessonChapterPojo.class));
        return list;
    }


    /**
     * 可以免费观看的视频
     */
    public List<LessonChapterPojo> getOneChapter(String LessonId, String chapterNo) {

        String sql = "select ";
        sql = sql + "     lesson_id       ";
        sql = sql + "   , chapter_no          ";
        sql = sql + "   , chapter_name  ";
        sql = sql + "   , url      ";
        sql = sql + " from";
        sql = sql + "   lesson_chapter ";
        sql = sql + " where";
        sql = sql + "   lesson_id = ? ";
        sql = sql + " and chapter_no = ? ";

        List<LessonChapterPojo> list = jdbcTemplate.query(sql, new Object[]{LessonId, chapterNo}, new BeanPropertyRowMapper(LessonChapterPojo.class));
        return list;
    }

}
