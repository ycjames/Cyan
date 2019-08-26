package com.newer.gsonDemo;

import csuft.yc.ujson.Ujson;

public class MyApp {
	public static void main(String[] args) {
		
		//反序列化   将json转换为object
		String text1 = "{\"id\":1,\"name\":\"Tom\"}";
		String text2 = "{\"id\":3,\"isbn\":\"9999\","
				+ "\"title\":\"哈利波特\",\"author\":\"JK.罗琳\",\"price\":59}";
		
		User u1 = (User)new Ujson().fromJson(text1,User.class);
		Book b1 = (Book)new Ujson().fromJson(text2,Book.class);
		System.out.println(u1);
		System.out.println(b1);
		
		//序列化
//		Ujson ujson = new Ujson();
//				
//		User u1 = new User(1, "alice");
//		System.out.println(Ujson.toJson(u1));

		
		Book b = new Book();
		b.setId(3);
		b.setIsbn("999");
		b.setTitle("哈利波特");
		b.setAuthor("JK.罗琳");
//		System.out.println(Ujson.toJson(b));
		
	}

}
