package com.sectraining.vulnserver;

public class CourseContentMeta {
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public int getCourseOrderId() {
		return courseOrderId;
	}
	public void setCourseOrderId(int courseOrderId) {
		this.courseOrderId = courseOrderId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	private String section;
	private String uri;
	private int courseOrderId;
	private String title;
}
