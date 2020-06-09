package com.microservice.edu.dao;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.edu.form.ChangePwdForm;
import com.microservice.edu.pojo.UserBaseProfilePojo;

@Repository
public class UserBaseProfileDao {

    /**
     * @throws ParseException
     * @查找信息
     */

    /**
     * DAO
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserBaseProfilePojo findByVcd(String email) throws ParseException {

        List<UserBaseProfilePojo> list = null;

        String sql = "SELECT ";
        sql = sql + " PASSWORD as passwd,";
        sql = sql + " EMAIL as email,";
        sql = sql + " STATUS as status,";
        sql = sql + " VALIDATE_CODE as validateCode,";
        sql = sql + " REGISTER_TIME as registerTime,";
        sql = sql + " ENABLE as enable";
        sql = sql + " FROM ";
        sql = sql + "   user_base_profile  ";
        sql = sql + " WHERE ";
        sql = sql + "   email = ?  ";
        sql = sql + " ORDER BY ";
        sql = sql + "   email ";


        list = jdbcTemplate.query(sql, new Object[]{email}, new BeanPropertyRowMapper(UserBaseProfilePojo.class));

        if (list != null && list.size() > 0) {
            return list.get(0);
        }

        return new UserBaseProfilePojo();
    }

    public int updatePasswd(ChangePwdForm cpwdForm) {

        String updateSql = "update user_base_profile t1 SET t1.PASSWORD =? WHERE t1.EMAIL=? and t1.PASSWORD =?";
        int rows = jdbcTemplate.update(updateSql, new Object[]{cpwdForm.new_pwd, cpwdForm.email, cpwdForm.old_pwd});
        return rows;
    }
}
