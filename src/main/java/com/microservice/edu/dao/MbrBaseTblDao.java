package com.microservice.edu.dao;

import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.MbrBaseTblPojo;
import com.microservice.edu.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.List;

@Repository
public class MbrBaseTblDao {

    /**
     * @throws ParseException
     * @查找信息
     */

    /** DAO */
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<MbrBaseTblPojo> findByVcd(String email) throws ParseException{

        List<MbrBaseTblPojo> list = null;

        String sql = "SELECT ";
        sql = sql + "   email ";
        sql = sql + "   , passwd ";
        sql = sql + "   , mb_name ";
        sql = sql + "   , mb_sex ";
        sql = sql + "   , mb_phone ";
        sql = sql + "   , mb_wechat ";
        sql = sql + "   , res_list ";
        sql = sql + "   , role ";
        sql = sql + "   , create_name ";
        sql = sql + "   , create_date ";
        sql = sql + "   , update_name ";
        sql = sql + "   , update_date ";
        sql = sql + " FROM ";
        sql = sql + "   mbr_base_tbl  ";
        sql = sql + " WHERE ";
        sql = sql + "   email = :email  ";
        sql = sql + " ORDER BY ";
        sql = sql + "   email ";


        list = jdbcTemplate.query(sql, new Object[] {email}, new BeanPropertyRowMapper(MbrBaseTblPojo.class));

        return list;
    }
}
