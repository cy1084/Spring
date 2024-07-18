package com.min.edu.service;

import java.util.Map;

import com.min.edu.dto.UserInfoDto;

public interface IUserInfoService {
	public UserInfoDto login(Map<String,Object> map);
}
