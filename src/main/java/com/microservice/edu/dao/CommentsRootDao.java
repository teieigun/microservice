package com.microservice.edu.dao;

import com.microservice.edu.pojo.BookPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentsRootDao {

    /** DAO */
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int getCommentsRootCount(int lessonId,int chapterNo) {

        String sql = "select ";
        sql = sql + "     count(id) as cnt";
        sql = sql + "  from comments_root ";
        sql = sql + " where lesson_id=?";
        sql = sql + "   and chapter_no=?";

        int count = jdbcTemplate.queryForObject(sql, new Object[] {lessonId,chapterNo},Integer.class);

        return count;
    }
}
