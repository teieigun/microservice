package com.microservice.edu.dao;

import com.microservice.edu.pojo.BookPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {


    /** DAO */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     *
     * */
    public List<BookPojo> getBook() {

        String sql = "select ";
        sql = sql + " id,";
        sql = sql + " name,";
        sql = sql + " author,";
        sql = sql + " publish,";
        sql = sql + " pages,";
        sql = sql + " price,";
        sql = sql + " bookcaseid,";
        sql = sql + " abled";
        sql = sql + " from book";

        List<BookPojo> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper(BookPojo.class));
        return list;
    }

}
