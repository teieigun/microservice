package com.microservice.edu.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.microservice.edu.form.RegistForm;
import com.microservice.edu.pojo.ListPojo;
import com.microservice.edu.pojo.StInfoPojo;

@Repository
public class StTableDao {

	/** DAO */
	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("getLoginInfo")
	public List<ListPojo> getLoginInfo(String studentId, String studentNm, String sex, String grade, String classNo) {

		List<String> listPara = new ArrayList<String>();

		String sql = "SELECT ";
		sql = sql + " t1.ST_ID as stId,  ";
		sql = sql + " t1.ST_NM as stNm,  ";
		sql = sql + " t1.ST_AGE as stAge,  ";
		sql = sql + " case  ";
		sql = sql + "   when t1.ST_SEX=1  ";
		sql = sql + "   then '男'  ";
		sql = sql + "   else '女'  ";
		sql = sql + " end as stSex,  ";
		sql = sql + " t1.ST_ADD as stAdd, ";
		sql = sql + " t1.ST_GRADE as stGrade, ";
		sql = sql + " t1.ST_CLASS as stClass, ";
		sql = sql + " t2.ST_TEACHER as stTeacher ";
		sql = sql + " from ST_TBL t1 left join ST_TEACH_MST t2 ";
		sql = sql + " on t1.ST_GRADE = t2.ST_GRADE ";
		sql = sql + " and t1.ST_CLASS = t2.ST_CLASS ";
		sql = sql + " WHERE 1=1 ";

		if (!isNULL(studentId)) {
			sql = sql + " AND  t1.ST_ID = ?";
			listPara.add(studentId);

		}
		if (!isNULL(studentNm)) {
			sql = sql + " AND  t1.ST_NM = ?";
			listPara.add(studentNm);
		}
		if (!isNULL(sex)) {
			sql = sql + " AND  t1.ST_SEX = ?";
			listPara.add(sex);
		}
		if (!isNULL(grade)) {
			sql = sql + " AND  t1.ST_GRADE = ?";
			listPara.add(grade);
		}

		if (!isNULL(classNo)) {
			sql = sql + " AND  t1.ST_CLASS = ?";
			listPara.add(classNo);
		}

		System.out.println(sql);

		List<ListPojo> list = jdbcTemplate.query(sql, listPara.toArray(), new BeanPropertyRowMapper(ListPojo.class));
		return list;
	}

	public String getMaxStudentId() {
		String sql = "SELECT ";
		sql = sql + "     MAX(ST_ID) as stId ";
		sql = sql + " FROM   ";
		sql = sql + "   ST_TBL    ";

		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);

		String stId = "1";
		if (result != null && result.size() > 0) {
			stId = result.get(0).get("stId").toString();
		}
		return stId;
	}

	private boolean isNULL(String val) {
		boolean rs = false;
		if ("".equals(val) || val == null) {
			rs = true;
		}
		return rs;
	}

	public void insertStInfo(RegistForm registForm) {
		String insertSql = " INSERT INTO ST_TBL (" + " ST_ID, " + " ST_NM, " + " ST_AGE, " + " ST_SEX, " + " ST_ADD, "
				+ " ST_GRADE, " + " ST_CLASS, " + " ST_BIRTHDAY, " + " ST_ZUJI, " + " ST_DEL) values ("
				+ registForm.getUserId() + ",'" + registForm.getUserNm() + "'," + registForm.getAge() + ",'"
				+ registForm.getSex() + "','" + registForm.getAddress() + "'," + registForm.getGrade() + ","
				+ registForm.getClassNo() + ",'" + registForm.getStBirthday() + "','" + registForm.getZuji() + "','0')";

		System.out.println(insertSql);

		jdbcTemplate.update(insertSql);

	}

	public void updateStInfo(RegistForm registForm) {
		String updateSql = " update ST_TBL "+
				           "  set ST_NM ='" + registForm.getUserNm() + "'"+
				           " ,ST_AGE =" + registForm.getAge() +
				           " ,ST_SEX ='" + registForm.getSex() + "'"+
				           " ,ST_ADD ='" + registForm.getAddress() + "'"+
				           " ,ST_GRADE =" + registForm.getGrade() +
				           " ,ST_CLASS =" + registForm.getClassNo() +
				           " ,ST_BIRTHDAY ='" + registForm.getStBirthday() + "'" +
				           " ,ST_ZUJI = '" + registForm.getZuji() + "'"+
				           " where ST_ID="+registForm.getUserId();
				

		System.out.println(updateSql);

		jdbcTemplate.update(updateSql);

	}
	
	public void deleteStInfo(RegistForm registForm) {
		String deleteSql = " delete from ST_TBL "+
				           " where ST_ID="+registForm.getUserId();
				

		System.out.println(deleteSql);

		jdbcTemplate.update(deleteSql);

	}
	
	public List<StInfoPojo> getStAllInfoById(String studentId) {

		String sql = " SELECT";
		sql = sql + "   t1.ST_ID as stId";
		sql = sql + "   , t1.ST_NM as stNm";
		sql = sql + "   , t1.ST_AGE as stAge";
		sql = sql + "   , t1.ST_SEX as stSex ";
		sql = sql + "   , CASE WHEN ";
		sql = sql + "            t1.ST_SEX ='1' ";
		sql = sql + "        THEN '男' ";
		sql = sql + "        ELSE '女' ";
		sql = sql + "     END as ST_SEX_TEXT ";
		sql = sql + "   , t1.ST_ADD as stAdd";
		sql = sql + "   , t1.ST_GRADE as stGrade";
		sql = sql + "   , t1.ST_CLASS as stClass";
		sql = sql + "   , t1.ST_BIRTHDAY as stBirthday";
		sql = sql + "   , t1.ST_ZUJI as stZuji";
		sql = sql + "   , t3.ST_JIANHU as stJianhu";
		sql = sql + "   , t3.ST_JIANHU_PHONE as stJhPhone";
		sql = sql + "   , t2.CODE_NM as ST_ZUJI_TEXT ";
		sql = sql + " FROM";
		sql = sql + "   ST_TBL t1 ";
		sql = sql + "   left join ST_CODE_MST t2 ";
		sql = sql + "     on t1.ST_ZUJI = t2.CODE_ID ";
		sql = sql + "     and t2.ID = '01'";
		sql = sql + "   left join ST_JIANHU_TBL t3 ";
		sql = sql + "     on t1.ST_ID = t3.ST_ID ";
		sql = sql + " WHERE t1.ST_ID = ?";

		System.out.println(sql);

		List<StInfoPojo> list = jdbcTemplate.query(sql, new Object[] { studentId },
				new BeanPropertyRowMapper(StInfoPojo.class));
		return list;

	}
}