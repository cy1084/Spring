package com.min.edu.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.min.edu.model.service.IBoardService;
import com.min.edu.model.service.IUserService;
import com.min.edu.vo.UserVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor

//ajax만 처리하는, rest ful만 처리하는 컨트롤러
public class UserRestController {

	private final IUserService service;
	private final IBoardService boardService;

	@PostMapping(value = "/duplicationAjax.do")
	public String duplicationAjax(String checkId) {
		log.info("UserRestController duplicationAjax.do 아이디 중복검사");
		int check = service.isDuplicateCheck(checkId);
		Map<String, String> map = new HashMap<String, String>();
		String chk = (check == 0 ? "true" : "false");
		map.put("isc", chk);
		
		Gson gson = new GsonBuilder().create();
		String chkJson = gson.toJson(map);
		return chkJson;
	}
	
	@PostMapping(value = "/duplicationFetch.do")
	public String duplicationFetch(String checkId) {
		log.info("UserRestController duplicationFetch.do 아이디 중복검사");
		log.info("Fetch에서 전달받은 요청값 : {}", checkId);
		
		int check = service.isDuplicateCheck(checkId);
		return check == 0 ? "true" : "false";
	}
	
	@PostMapping(value="/findId.do")
	public String findId(@RequestParam Map<String,Object> map) {
		log.info("UserRestController findId.do 아이디찾기");
		log.info("전달받은 요청값 : {}", map);
		
		String id=service.findId(map);
		
		return StringUtils.defaultIfEmpty(id, ""); //없으면 없는 값으로 대체
	}
	
//	@PostMapping(value="/convertForm.do")
//	public String convertForm(@RequestBody List<Map<String, String>> data) {
//		for(Map<String,String> map:data) {
//			String fieldName=map.get("name");
//			String fieldValue=map.get("value");
//			System.out.printf("%s / %s\n",fieldName,fieldValue); 
//			/*
//			 * chkid/A005
//			 * chkid/A001
//			 * 등 으로 출력
//			 */
//		}
//		
//		return "check";
//	}
	
	//위와 같은 방법 0703 점심시간 전 영상
	@PostMapping(value={"/{toAuth}.do","/{toUser}.do"})
	public String convertForm(@RequestParam List<String> chkid, 
								@PathVariable(value="toAuth",required=false) String toAuth,
								@PathVariable(value="toUser",required=false) String toUser) 
	/*두 경로의 요청 중 한 개로만 요청할 수 있기 때문에, 바인딩할 때 null 값이 찍히게 하지 않기 위해 required=false을 줘서 필수값이 아니게 함 */
	{
		System.out.println(chkid);
		System.out.println(toAuth);
		System.out.println(toUser);
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("checkid", chkid);
		map.put("authValue", toAuth.equals("toAuth")?"A":"U");
		
		int n=service.setChangeAuth(map);
		
		return (n>0)?"true":"false";
	}
	
	/*
	 * managementUser.jsp는
	 		if(checkCnt.length==0){
  			alert("한 개 이상의 수정 데이터를 선택해주세요");
  			}else{
  			
  			var data=$("form").serialize();
  			console.log(data);
  			
  			$.ajax({
  				url:"./convertForm.do",
  				type:"post",
  				data: data,
  				success:function(msg){
  					console.log(msg);
  				},
  				error:function(){
  					alert("잘못된 요청 처리");	
  				}
  			});
  		}
  		이렇게 바껴야 함
	 *
	 */
	
	@PostMapping(value="/getSearchUser.do")
	public String getSearchUser(@RequestParam Map<String, Object> map) {
		log.info("UserRestController getSearchUser.do 회원검색:{}",map);
		
		List<UserVo> searchList=service.getSearchUser(map);
		
		/*
		 * {"연필":"모나미"} - JSON Object
		 * {"문방사우":[{"연필":"모나미"},{"지우개":"모닝글로리"}]} - JSON Array
		 */
		
		//1)JSON Object 작성방법
		JSONObject obj=new JSONObject();
		obj.put("연필","모나미");
		System.out.println(obj.toJSONString());
		
		//return obj.toJSONString();
		
		
		//2)JSON Array 작성방법
		JSONArray jArray=new JSONArray();
		JSONObject jObj1=new JSONObject();
		jObj1.put("연필","모나미");
		
		JSONObject jObj2=new JSONObject();		
		jObj2.put("지우개","모닝글로리");
		
		jArray.add(jObj1);
		jArray.add(jObj2);
		
		JSONObject totalObj=new JSONObject();
		totalObj.put("문방사우", jArray);
		
		System.out.println(totalObj.toJSONString());
		
		
		//GSON
		Gson gson =new GsonBuilder().create();
		
		return gson.toJson(searchList);	
	}
	
	@GetMapping(value="/restore.do")
	public String restore(@RequestParam List<String> seqs) {
		log.info("UserRestController restore.do 삭제된 글 복구:{}",seqs);
		
		int n= boardService.restoreDelflag(seqs);
		return (n>0)?"true":"false";
	}
}
