package com.min.edu.vo;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class UserVo {
	private String id;
	private String name;
	private String password;
	private String email;
	private String auth;
	private String enable;
	private String joinindate;
}
