package com.microservice.edu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.SmallCategoryTblPojo;

@Repository
public class CategoryTblDao {


    /** DAO */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     *
     * */
    public List<BigCategoryTblPojo> getBigCtgCode() {

        String sql = "select ";
        sql = sql + "   big_ctg_code as ctgCode,";
        sql = sql + "   big_ctg_name as ctgName,";
        sql = sql + "   del";
        sql = sql + " from big_category_tbl order by big_ctg_code";

        List<BigCategoryTblPojo> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper(BigCategoryTblPojo.class));
        return list;
    }

    public List<SmallCategoryTblPojo> getSmallCtgCode(String bigCtgCode) {

        String sql = "select ";
        sql = sql + "   small_ctg_code as smallCtgCode,";
        sql = sql + "   small_ctg_name as smallCtgName,";
        sql = sql + "   big_ctg_code as bigCtgCode,";
        sql = sql + "   del";
        sql = sql + " from small_category_tbl where big_ctg_code =?  order by bigCtgCode,smallCtgCode";

        List<SmallCategoryTblPojo> list = jdbcTemplate.query(sql,new Object[] {bigCtgCode},new BeanPropertyRowMapper(SmallCategoryTblPojo.class));
        return list;
    }


    public List<BigCategoryTblPojo> getAllBigCtg(){

        String sql = "SELECT ";
        sql =sql + "    T1.BIG_CTG_CODE as ctgCode, ";
        sql =sql + "    T1.BIG_CTG_NAME  as ctgName";
        sql =sql + "    FROM big_category_tbl T1 ";
        sql =sql + "   WHERE T1.DEL = 0 ";
        sql =sql + "   ORDER BY T1.BIG_CTG_CODE ";


        List<BigCategoryTblPojo> list= jdbcTemplate.query(sql,  new BeanPropertyRowMapper(BigCategoryTblPojo.class));
        return list;
    }

}
