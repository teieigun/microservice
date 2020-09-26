package com.microservice.edu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microservice.edu.pojo.SwiperPojo;

@Repository
public class SwiperDao {

	/** DAO */
	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<SwiperPojo> getAllSwiper() {
		String sql = "select ";
		sql = sql + "    t1.swiper_id  as swiperId, ";
		sql = sql + "    t1.img_url  as imgUrl, ";
		sql = sql + "    t1.title  as title, ";
		sql = sql + "    t1.description  as description ";
		sql = sql + "    from swiper_tbl t1 ";
		sql = sql + "   order by t1.swiper_id ";

		List<SwiperPojo> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(SwiperPojo.class));
		return list;
	}

}
