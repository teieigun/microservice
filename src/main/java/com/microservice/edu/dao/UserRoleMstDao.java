package com.microservice.edu.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.edu.pojo.UserRoleMstPojo;

@Repository
public class UserRoleMstDao {

    /**
     * @throws ParseException
     * @查找信息
     */

    /** DAO */
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<UserRoleMstPojo> findByVcd(String email) throws ParseException{

        List<UserRoleMstPojo> list = null;

        String sql = "SELECT ";
        sql = sql + "   email ";
        sql = sql + "   , role_id as role ";
        sql = sql + " FROM ";
        sql = sql + "   user_role_mst  ";
        sql = sql + " WHERE ";
        sql = sql + "   email = ? ";
        sql = sql + " ORDER BY ";
        sql = sql + "   email ";


        list = jdbcTemplate.query(sql, new Object[] {email}, new BeanPropertyRowMapper(UserRoleMstPojo.class));

        return list;
    }
}
