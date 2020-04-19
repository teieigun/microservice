package com.microservice.edu.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.microservice.edu.constants.MicroServiceConstants;
import com.microservice.edu.pojo.CodePojo;
import com.microservice.edu.pojo.JiaoChengTblExt1Pojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.microservice.edu.pojo.UserPojo;
/**
 *
 * @author Qixuan.Chen
 */
@Repository
public class UserDao {

    /** DAO */
    @Autowired
    JdbcTemplate jdbcTemplate;

    public HashMap<String, String> map=new HashMap<String, String>();
    /**
     * @保存注册信息
     *  private Long id;
    private String name;
    private String password;
    private String email;//注册账号
    private int status;//激活状态
    private String validateCode;//激活码
    private Date  registerTime;//注册时间
     */
    public void saveUserInfo(UserPojo user){

        String insertSql = " INSERT INTO user_temp_profile " +
                "(EMAIL, STATUS, VALIDATE_CODE, REGISTER_TIME) VALUES(?,?,?,?,?)";

        jdbcTemplate.update(insertSql,new Object[] {user.getEmail(),user.getStatus(),user.getValidateCode(),user.getRegisterTime() });

    }

    /**
     * @更新 user
     */
    public void setNewPass(String passwd,String validateCode){

        String updateSql = " update user_temp_profile set PASSWD=?, STATUS=? where VALIDATE_CODE=? ";

        jdbcTemplate.update(updateSql,new Object[] { passwd, MicroServiceConstants.USER_STATUS_PASSED,validateCode});
    }

    public void resetUser(String email){

        String updateSql = " update user_temp_profile set STATUS=?, REGISTER_TIME=? where EMAIL=? ";

        jdbcTemplate.update(updateSql,new Object[] {MicroServiceConstants.USER_STATUS_NOPASS,new Date(),email});
    }

    public void updateUserStatus(String email,int status){

        String updateSql = " update user_temp_profile set STATUS=? where EMAIL=? ";

        jdbcTemplate.update(updateSql,new Object[] {status,email});
    }



    /**
     * @throws ParseException
     * @查找信息
     */
    public UserPojo findbyPk(String email) throws ParseException{

        List<UserPojo> list = null;

        String sql = "SELECT ";
        sql = sql + "     PASSWD  AS passwd, ";
        sql = sql + "     EMAIL   AS email, ";
        sql = sql + "     VALIDATE_CODE AS validateCode, ";
        sql = sql + "     REGISTER_TIME AS registerTime, ";
        sql = sql + "     DEL AS del ";
        sql = sql + " FROM   ";
        sql = sql + "   user_temp_profile WHERE EMAIL = ?";

        list = jdbcTemplate.query(sql, new Object[] {email}, new BeanPropertyRowMapper(UserPojo.class));

        if(list !=null && list.size() > 0) {
            return  list.get(0);
        }
        return new UserPojo();
    }

    /**
     * @throws ParseException
     * @查找信息
     */
    public UserPojo findByVcd(String validateCd,int status) throws ParseException{

        List<UserPojo> list = null;

        String sql = "SELECT ";
        sql = sql + "     PASSWD  AS passwd, ";
        sql = sql + "     EMAIL   AS email, ";
        sql = sql + "     VALIDATE_CODE AS validateCode, ";
        sql = sql + "     REGISTER_TIME AS registerTime, ";
        sql = sql + "     DEL AS del ";
        sql = sql + " FROM   ";
        sql = sql + "   user_temp_profile WHERE VALIDATE_CODE = ? and STATUS = ?";

        list = jdbcTemplate.query(sql, new Object[] {validateCd,status}, new BeanPropertyRowMapper(UserPojo.class));

        if(list !=null && list.size() > 0) {
            return  list.get(0);
        }
        return new UserPojo();
    }
}