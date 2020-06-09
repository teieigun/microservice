package com.microservice.edu.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.edu.constants.MicroServiceConstants;
import com.microservice.edu.pojo.UserBaseInfo;
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
    public void saveProfile(UserPojo user){

        String insertSql = " INSERT INTO user_base_profile " +
                "(EMAIL, STATUS, VALIDATE_CODE, REGISTER_TIME) VALUES(?,?,?,?)";

        jdbcTemplate.update(insertSql,new Object[] {user.getEmail(),user.getStatus(),user.getValidateCode(),user.getRegisterTime() });

    }

    public void saveBaseInfo(String email){

        String insertSql = " INSERT INTO user_base_info " +
                "(EMAIL) VALUES(?)";

        jdbcTemplate.update(insertSql,new Object[] {email });

    }

    public void saveRoleInfo(String email){

        String insertSql = " INSERT INTO user_role_mst " +
                "(EMAIL,role_id,del) VALUES(?,1,0)";

        jdbcTemplate.update(insertSql,new Object[] {email});

    }


    /**
     * @更新 user
     */
    public void setNewPass(String passwd,String validateCode){

        String updateSql = " update user_base_profile set PASSWORD=?, STATUS=? where VALIDATE_CODE=? ";

        jdbcTemplate.update(updateSql,new Object[] { passwd, MicroServiceConstants.USER_STATUS_PASSED,validateCode});
    }

    public void resetUser(String email){

        String updateSql = " update user_base_profile set STATUS=?, REGISTER_TIME=? where EMAIL=? ";

        jdbcTemplate.update(updateSql,new Object[] {MicroServiceConstants.USER_STATUS_NOPASS,new Date(),email});
    }

    public void updateUserStatus(String email,int status){

        String updateSql = " update user_base_profile set STATUS=? where EMAIL=? ";

        jdbcTemplate.update(updateSql,new Object[] {status,email});
    }

    public void updateUserProfileImage(String email,String imagePath){

        String updateSql = " update user_base_info set profile_image=? where email=? ";

        jdbcTemplate.update(updateSql,new Object[] {imagePath,email});
    }


    /**
     * @throws ParseException
     * @查找信息
     */
    public UserPojo findbyPk(String email) throws ParseException{

        List<UserPojo> list = null;

        String sql = "SELECT ";
        sql = sql + "     PASSWORD  AS passwd, ";
        sql = sql + "     EMAIL   AS email, ";
        sql = sql + "     VALIDATE_CODE AS validateCode, ";
        sql = sql + "     REGISTER_TIME AS registerTime, ";
        sql = sql + "     ENABLE AS del ";
        sql = sql + " FROM   ";
        sql = sql + "   user_base_profile WHERE EMAIL = ?";

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
        sql = sql + "     PASSWORD  AS passwd, ";
        sql = sql + "     EMAIL   AS email, ";
        sql = sql + "     VALIDATE_CODE AS validateCode, ";
        sql = sql + "     REGISTER_TIME AS registerTime, ";
        sql = sql + "     ENABLE AS del ";
        sql = sql + " FROM   ";
        sql = sql + "   user_base_profile WHERE VALIDATE_CODE = ? and STATUS = ?";

        list = jdbcTemplate.query(sql, new Object[] {validateCd,status}, new BeanPropertyRowMapper(UserPojo.class));

        if(list !=null && list.size() > 0) {
            return  list.get(0);
        }
        return new UserPojo();
    }

    /**
     * @throws ParseException
     * @查找信息
     */
    public UserBaseInfo findUserInofByPk(String email) throws ParseException{

        List<UserBaseInfo> list = null;

		String sql = "select ";
		sql = sql + "    email, ";
		sql = sql + "    mb_name, ";
		sql = sql + "    mb_sex, ";
		sql = sql + "    mb_phone, ";
		sql = sql + "    mb_wechat, ";
		sql = sql + "    res_list, ";
		sql = sql + "    profile_image, ";
		sql = sql + "    create_name, ";
		sql = sql + "    create_date, ";
		sql = sql + "    update_name, ";
		sql = sql + "    update_date, ";
		sql = sql + "    del ";
		sql = sql + "from user_base_info where email = ?";

        list = jdbcTemplate.query(sql, new Object[] {email}, new BeanPropertyRowMapper(UserBaseInfo.class));

        if(list !=null && list.size() > 0) {
            return  list.get(0);
        }
        return new UserBaseInfo();
    }
}