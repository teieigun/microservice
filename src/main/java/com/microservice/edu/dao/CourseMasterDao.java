package com.microservice.edu.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.edu.pojo.CourseMasterPojo;

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

    public Integer isBuyCourse(String email,String lessonId){

        String sql = "SELECT COUNT(1) FROM user_course_mapping T1 WHERE T1.email=? AND T1.course_id =?";

        Integer count = jdbcTemplate.queryForObject(sql, new Object[] {email,lessonId},Integer.class);

        if(count==null){
            count=0;
        }
        return count;
    }

}
