package com.microservice.edu.dao;

import com.microservice.edu.pojo.BookPojo;
import com.microservice.edu.pojo.CourseMasterPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseMasterDao {

    /** DAO */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     *
     * */
    public List<CourseMasterPojo> getCourseInfo(String courseId) {

        String sql = "select ";
        sql = sql + "   t1.course_name as courseName,";
        sql = sql + "   t1.course_img as courseImg";
        sql = sql + "  FROM course_master t1 ";
        sql = sql + " WHERE t1.course_id=? ";

        List<CourseMasterPojo> list = jdbcTemplate.query(sql,new Object[] {courseId},new BeanPropertyRowMapper(CourseMasterPojo.class));
        return list;
    }

}
