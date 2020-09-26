package com.microservice.edu.pojo;

import java.util.List;

import lombok.Data;

@Data
public class TopInfoData {
	private String introduceTitle;
	private String baseCourseTitle;
	private List<SwiperPojo> swiperList;
	private List<CoursePojo> baseCourseList;
}
