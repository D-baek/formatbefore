package com.doosan.web.usr;

import java.util.HashMap;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
	public void insertUser(User param);
	public User selectUserById(User param);
	public int existId(String uid);
	public int countUsers();
	public void createUser(HashMap<String,String> paramMap);
	public void dropUser(HashMap<String,String> paramMap);
	public void createCatdogDb(HashMap<String,String> paramMap);
	public void truncateUser(HashMap<String,String> paramMap);

}