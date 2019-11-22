package com.doosan.web.usr;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class UserCtrl {
	private static final Logger logger = LoggerFactory.getLogger(UserCtrl.class);
	 //스프링컨테이너에 담아놓는다 (컨테이너와 연결)
	@Autowired User user;
//	@Autowired Printer printer;
	@Autowired UserMapper userMapper;
	@Autowired Map<String, Object> map;
//	@GetMapping("/count")//자바스크립트와 먼저 xml이 먼저 송신하고 자바로 보낸다.
//search와 관련된것을 매핑함
//	public String customer(Model model) {//model녀석이 request를 감싸고 있다. 들어있음 
//		model.addAttribute("count",customerService.countCustomer());
//여기서 모델이 mapper가 된다.
//		return "index";
//	}
	@GetMapping("/{uid}/exist")
	public Map<?,?> existId(@PathVariable String uid){
		logger.info("exist들어옴");
		Function<String,Integer> f = t->userMapper.existId(uid);
		map.clear();
		System.out.println("값은 :" +f.apply(uid));
		map.put("msg",(f.apply(uid)==0) ? "success" :"fail");
		return map;
	}
	
	
	@PostMapping("/")//customers데 아무것도 없으면 가져온값을 집어넣으라는 뜻 파라미터가 있으면 get없으면 post 아이디 유/무로 판단 
	public Map<?,?> join(@RequestBody User param) {//<?,?> ?는 와일드 카드 
//		request.getparameter("cid")를 안해도 가져올수 있다.
//		logger.info("ajax가 보낸 아이디와 비번입니다 {} ",param.getCid()+","+param.getPwd()+","+param.getPname());
//		리턴타입이 void, 이름이 번쩍하고 메퍼에다가 던져주고 없어진다.c가 있으면 이름이 있으므로 람다는 익명함수
		System.out.println("join 들어옴");
		 System.out.println("회원가입 컨트롤러 들어옴");
		 System.out.println("넘어온 값 :"+param.toString());
		 Consumer<User> c = t->userMapper.insertUser(t);
		 c.accept(param);
		 map.clear();
		 map.put("msg","success");
		 return map;
	} 
	@PostMapping("/{uid}")// " " -> 상수 
	public User login(@PathVariable String uid,@RequestBody User param){//이제 무조건 객체로 던져야한다.rest방식 서비스임플이 람다로 다오임플이 마이바티스
		Function<User,User> f = t-> userMapper.selectUserById(t);
		System.out.println("넘어온값 :"+f.apply(param));
		return f.apply(param);
	}
	@GetMapping("/{uid}")
	public User searchCustomerById(@PathVariable String cid,@RequestBody User param) {
		Function<User,User> f = t-> userMapper.selectUserById(param);
		return f.apply(param);
	}
	@PutMapping("/{uid}")
	public String UpdateCustomer(@PathVariable String cid,@RequestBody User param) {
		 Consumer<User> c = t->userMapper.insertUser(param);
		 c.accept(param);
		 return "success";
	}
	@DeleteMapping("/{uid}")
	public String removeCustomer(@PathVariable String User,@RequestBody User param) {
		 Consumer<User> c = t->userMapper.insertUser(param);
		 c.accept(param);
		return "fail";
	}
	
	@GetMapping("/create/table")
	public Map<?,?> createUser(){
		HashMap<String,String> paramMap = new HashMap<>();
		paramMap.put("CREATE_USER",SQL.CREATE_USER.toString());
		System.out.println("테이블생성 들어옴"+paramMap.get("CREATE_USER"));
		Consumer<HashMap<String,String>> c = t->userMapper.createUser(t);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg", "success");
		System.out.println("테이블 생성 결과 값 : "+paramMap);
		return paramMap;
	}
	@GetMapping("/drop/table")
	public Map<?,?> dripUser(){
		HashMap<String,String> paramMap = new HashMap<>();
		System.out.println("테이블 삭제 들어옴");
		paramMap.put("DROP_USER",SQL.DROP_USER.toString());
		Consumer<HashMap<String,String>> c = t->userMapper.dropUser(t);
		c.accept(paramMap);
		paramMap.clear();
		paramMap.put("msg","success");
		return paramMap;
	}

}