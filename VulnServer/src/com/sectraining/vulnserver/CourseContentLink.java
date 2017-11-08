package com.sectraining.vulnserver;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface CourseContentLink {
	String title();
	int courseId();
	String section();
}
