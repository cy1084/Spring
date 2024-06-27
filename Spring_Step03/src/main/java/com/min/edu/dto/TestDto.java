package com.min.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//TODO 015_01 MyBatis에서 사용하게 될 DTO/VO 객체
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {
	private String job_id;
	private String job_title;
	private int min_salary;
	private int max_salary;
}
