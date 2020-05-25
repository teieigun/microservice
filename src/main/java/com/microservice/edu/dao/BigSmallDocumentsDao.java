package com.microservice.edu.dao;

import com.microservice.edu.pojo.BigCategoryTblPojo;
import com.microservice.edu.pojo.BigSmallDocumentsPojo;
import com.microservice.edu.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class BigSmallDocumentsDao {

    /** DAO */
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int saveDocumentInfo(BigSmallDocumentsPojo pojo){


        String insertSql = " INSERT INTO big_small_documents " +
                " (big_ctg_code,small_ctg_code,document_name,document_url)VALUES(?,?,?,?);";

        int rs =jdbcTemplate.update(insertSql,new Object[] {pojo.bigCtgCode,pojo.smallCtgCode,pojo.documentName,pojo.documentUrl});

        return rs;
    }

    public int deleteDocumentInfo(String bigCtgCode,String smallCtgCode,String document_name){


        String deleteSql = " delete from big_small_documents where  big_ctg_code=? and small_ctg_code=? and document_name in (?) ";

        int count = jdbcTemplate.update(deleteSql, new Object[] {bigCtgCode,smallCtgCode,document_name});

        return count;
    }


    public int getDownLoadFileCount(String bigCtgCode,String smallCtgCode,String documentNm) {

        String sql = "SELECT ";
        sql = sql + "     COUNT(1) AS cnt ";
        sql = sql + "   FROM big_small_documents t1 ";
        sql = sql + "  WHERE t1.big_ctg_code=? ";
        sql = sql + "    AND t1.small_ctg_code=? ";
        sql = sql + "    AND t1.document_name=? ";

        int count = jdbcTemplate.queryForObject(sql, new Object[] {bigCtgCode,smallCtgCode, documentNm},Integer.class);
        return count;
    }

    public List<BigSmallDocumentsPojo> getDownloadFile(String lessonId) {


        String sql = "select ";
        sql = sql + "   REPLACE((@rowid:=@rowid+1),'.0','') as id,";
        sql = sql + "    t3.BIG_CTG_CODE as bigCtgCode,";
        sql = sql + "    t3.BIG_CTG_NAME as bigCtgName,";
        sql = sql + "    t4.SMALL_CTG_CODE as smallCtgCode,";
        sql = sql + "    t4.SMALL_CTG_NAME as smallCtgName,";
        sql = sql + "    t2.document_name as documentName,";
        sql = sql + "    t2.document_url as documentUrl";
        sql = sql + " FROM (SELECT @rowid:=0) as init, lesson_tbl t1";
        sql = sql + " LEFT JOIN big_small_documents t2 ";
        sql = sql + " ON t1.BIG_CTG_CODE =t2.big_ctg_code ";
        sql = sql + " AND t1.SMALL_CTG_CODE = t2.small_ctg_code";
        sql = sql + " LEFT JOIN big_category_tbl t3 ON ";
        sql = sql + " t1.BIG_CTG_CODE = t3.BIG_CTG_CODE";
        sql = sql + " LEFT JOIN small_category_tbl t4";
        sql = sql + " ON t1.SMALL_CTG_CODE = t4.SMALL_CTG_CODE";
        sql = sql + " AND t1.BIG_CTG_CODE = t4.BIG_CTG_CODE";
        sql = sql + " WHERE t1.LESSON_ID =?";

        List<BigSmallDocumentsPojo> list = jdbcTemplate.query(sql,new Object[] { lessonId }, new BeanPropertyRowMapper(BigSmallDocumentsPojo.class));
        return list;
    }
}
