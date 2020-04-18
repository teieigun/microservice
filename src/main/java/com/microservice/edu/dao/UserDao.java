package com.microservice.edu.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    public void saveAllInfo(UserPojo user){

        String insertSql = " INSERT INTO microService.user_temp_profile " +
                "(ID,NAME,PASSWD, EMAIL, STATUS, VALIDATE_CODE, REGISTER_TIME, DEL) VALUES(?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(insertSql,new Object[] { user.getId(),user.getName(),user.getPassword(),user.getEmail(),user.getStatus(),user.getValidateCode(),user.getRegisterTime() });

    }

    public void saveSimpleInfo(UserPojo user){

        String insertSql = " INSERT INTO microService.user_temp_profile " +
                "(ID,EMAIL, STATUS, VALIDATE_CODE, REGISTER_TIME) VALUES(?,?,?,?,?)";

        jdbcTemplate.update(insertSql,new Object[] { user.getId(),user.getEmail(),user.getStatus(),user.getValidateCode(),user.getRegisterTime()});

    }

    /**
     * @更新 user
     */
    public void update(UserPojo user){
        map.put("email", user.getEmail());
        map.put("validateCode", user.getValidateCode());
        Date time=user.getRegisterTime();
        map.put("registerTime", String.valueOf(time));
        int status=user.getStatus();
        map.put("status", String.valueOf(status));
        System.out.println("=======st========="+status);
    }

    /**
     * @throws ParseException
     * @查找信息
     */
    public UserPojo find(String email) throws ParseException{
        UserPojo user=new UserPojo();
        user.setEmail(map.get("email"));
        user.setName(map.get("name"));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        Date day=sdf.parse(map.get("registerTime"));
        user.setRegisterTime(day);
        user.setStatus(Integer.valueOf(map.get("status")));
        user.setValidateCode(map.get("validateCode"));
        return user;
    }
}