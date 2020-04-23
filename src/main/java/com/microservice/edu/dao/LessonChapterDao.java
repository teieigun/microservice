package com.microservice.edu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.edu.pojo.LessonChapterPojo;

@Repository
public class LessonChapterDao {

    /** DAO */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 可以免费观看的视频
     * */
    public List<LessonChapterPojo> getChapterList(String LessonId) {

        String sql = "select ";
        sql = sql + "     lesson_id as LessonId ";
        sql = sql + "   , chapter_no as chapterNo ";
        sql = sql + "   , chapter_name as chapterName ";
        sql = sql + "   , url as url ";
        sql = sql + " from ";
        sql = sql + "   lesson_chapter  ";
        sql = sql + " where ";
        sql = sql + "   lesson_id = ? ";
        sql = sql + " order by ";
        sql = sql + "     lesson_id ";
        sql = sql + "   , chapter_no ";

        List<LessonChapterPojo> list = jdbcTemplate.query(sql,  new Object[] { LessonId },new BeanPropertyRowMapper(LessonChapterPojo.class));
        return list;
    }


    /**
     * 可以免费观看的视频
     * */
    public List<LessonChapterPojo> getOneChapter(String LessonId,String chapterNo) {

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

        List<LessonChapterPojo> list = jdbcTemplate.query(sql,  new Object[] { LessonId, chapterNo},new BeanPropertyRowMapper(LessonChapterPojo.class));
        return list;
    }

}
