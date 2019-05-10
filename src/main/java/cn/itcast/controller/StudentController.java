package cn.itcast.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.itcast.entity.Student;

@RestController
public class StudentController {
	HashMap<String, Student> stus = null;
	{
		stus = new HashMap<>();
		stus.put("1", new Student(1, "eric", 12));
		stus.put("2", new Student(2, "jack", 12));
		stus.put("3", new Student(3, "rose", 12));
		stus.put("4", new Student(4, "tom", 12));
	}
	
	// 根据 id 获取学生对象，如果有传 id ，就按 id获取，如果没有传的话，就返回所有的学生
	@CrossOrigin(origins= {"http://127.0.0.1:8848", "http://localhost:8080"}, 
			     methods=RequestMethod.GET, 
			     allowCredentials="true")
	@GetMapping("/stu")
	public Object getStu(String sid, HttpServletResponse response) {
		//response.setHeader("Access-Control-Allow-Origin", "*");
		//System.out.println(sid);
		if(sid != null) {
			Student stu = stus.get(sid);
			if(stu != null) {
				return stu;
			}
		}
		return stus;
	}
	
	// 根据 id 获取学生对象，支持 rest 的路径变量
	@GetMapping("/stu2/{sid}")
	public Object getStu2(@PathVariable(name="sid", required=false)String sid, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println(sid);
		if(sid != null) {
			Student stu = stus.get(sid);
			if(stu != null) {
				return stu;
			}
		}
		return stus;
	}
	
	// 修改学生
	@PostMapping("/stu")
	public HashMap<String, Student> postStu(@RequestBody Student stu, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println(stu);
		if(stu == null || stu.getSid() == null) {
			throw new RuntimeException("参数异常");
		}
		if(!stus.containsKey(String.valueOf(stu.getSid()))) {
			throw new RuntimeException("要修改的学生不存在");
		}
		stus.put(String.valueOf(stu.getSid()), stu);
		return stus;
	}
	
	// 添加学生
	@CrossOrigin(origins="http://127.0.0.1:8848",
			     methods=RequestMethod.PUT,
			     maxAge=150
			    )
	@PutMapping("/stu")
	public HashMap<String, Student> putStu(@RequestBody Student stu, HttpServletResponse response) {
		System.out.println(stu);
		if(stu == null || stu.getSid() == null || stus.containsKey(String.valueOf(stu.getSid()))) {
			throw new RuntimeException("添加失败");
		}else {
			stus.put(String.valueOf(stu.getSid()), stu);
		}
		return stus;
	}
	
	// 删除学生
	@DeleteMapping("/stu")
	public HashMap<String, Student> deleteStu(Student stu, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if(stu == null || stu.getSid() == null) {
			throw new RuntimeException("参数有误");
		}
		if(!stus.containsKey(String.valueOf(stu.getSid()))) {
			throw new RuntimeException("要删除的学生不存在 ");
		}
		return stus;
	}
	
	@DeleteMapping("/stu/{sid}")
	public HashMap<String, Student> deleteStu(@PathVariable(name="sid", required=true)String sid, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if(!stus.containsKey(sid)) {
			throw new RuntimeException("要删除的学生不存在 ");
		}
		return stus;
	}
	
	@GetMapping("/jsonp")
	public String jsonpTest(String callback) {
		if(callback == null || "".equals(callback)) {
			throw new RuntimeException("参数有误");
		}
		String result = callback + "({'name': 'eric', 'age': 12})";
		return result;
	}
}
