package com.microservice.edu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.microservice.edu.pojo.JiaoChengChapterPojo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JiaoChengChapterDao {

    /** DAO */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 可以免费观看的视频
     * */
    public List<JiaoChengChapterPojo> getChapterList(String jiaoChengId) {

        String sql = "select ";
        sql = sql + "     jiao_cheng_id       ";
        sql = sql + "   , chapter_no          ";
        sql = sql + "   , jiao_cheng_chapter  ";
        sql = sql + "   , jiao_cheng_url      ";
        sql = sql + " from";
        sql = sql + "   jiao_cheng_chapter ";
        sql = sql + " where";
        sql = sql + "   jiao_cheng_id = ? ";
        sql = sql + " order by";
        sql = sql + "     jiao_cheng_id";
        sql = sql + "   , chapter_no";

        List<JiaoChengChapterPojo> list = jdbcTemplate.query(sql,  new Object[] { jiaoChengId },new BeanPropertyRowMapper(JiaoChengChapterPojo.class));
        return list;
    }

}
