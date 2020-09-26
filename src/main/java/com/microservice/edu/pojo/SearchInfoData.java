package com.microservice.edu.pojo;

import java.util.List;

import lombok.Data;

@Data
public class SearchInfoData {
	private List<HotKeyPojo> hotKeyWords;
	private List<CoursePojo> allCourseList;
}
