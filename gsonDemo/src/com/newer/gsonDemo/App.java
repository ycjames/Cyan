package com.newer.gsonDemo;

import java.util.HashMap;

import com.google.gson.Gson;


public class App {

	public static void main(String[] args) {
		
		Book b = new Book();
		b.setId(3);
		b.setIsbn("999");
		b.setTitle("哈利波特");
		b.setAuthor("JK.罗琳");
		
		System.out.println(new Gson().toJson(b));
		
		//造轮子
		//反序列化
		//json --> obj
		//返回User类中的toString方法的返回结果
//		String input = "{\"id\":1,\"name\":\"Tom\"}";
//		User u1 = new Gson().fromJson(input, User.class);
//  		System.out.println(u1);	
//		
//		//序列化
//		//obj --> json
//		User u = new User(999,"bob");
//		String test = new Gson().toJson(u);
//		System.out.println(test);
//		
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("code", 200);
//		map.put("msg", "Ok");
//		
//		//{code:200,msg:'Ok'}
//		String json = new Gson().toJson(map);
//		
//		System.out.println(json);
//		
//		//JSON数据传输、交换格式，语言中立
//		HashMap<String, Object> m = new HashMap<String,Object>();
//		m.put("name", "alice");
//		m.put("age", 22);
//		m.put("phone", new String[] {"131","150","186"});
//		
//		//上面已经声明过json是String类型的了，再重新声明的话需要对json重命名
//		json = new Gson().toJson(m);
//		System.out.println(json);
	}
}
