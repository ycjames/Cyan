package csuft.yc.ujson;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.newer.gsonDemo.User;


/**
 * 自定义的解析器
 * @author yc
 *
 */
public class Ujson {
	
	public Ujson() {
		
	}

	/**
	 * 对象序列化成 JSON 格式的字符串
	 * 
	 * @param o 对象
	 * @return String JSON 格式
	 */
	public static String toJson(Object o) {
	
		//TODO ArrayList
		//TODO HashMap
		//TODO o 包含其他嵌套类型
		
		Class clazz = o.getClass();
		//获得声明的字段、方法
		Field[] fs = clazz.getDeclaredFields();
		Method[] ms = clazz.getDeclaredMethods();
		
		//是一个可变的字符串类，可看成是一个容器
		StringBuilder builder = new StringBuilder("{");
		
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			//获得字段名
			String name = f.getName();
			System.out.println(name);
			System.out.println(f.getType().getName());
			
			//获得首字母为大写，1后面的正常输出
			String name2 = name.substring(0,1).toUpperCase() + name.substring(1);
			try {
				//获得getXXX方法
				Method method = clazz.getDeclaredMethod("get" + name2);
				//动态调用（invoke）方法获得属性值
				Object value = method.invoke(o);
				//字符串拼接
				builder.append(String.format("\"%s\":\"%s\",", name,value.toString()));
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}		
	
		}
		//将倒数第二个元素去掉    即去掉}前面的",".
		builder.deleteCharAt(builder.length() - 1);
		
		builder.append("}");
		
//		通过此方法能将StringBuilder转换为String
		return builder.toString();
	}

	/**
	 * Json数据到Object的反序列化
	 * 
	 * @param json	  JSON格式字符串
	 * @param clazz   返回数据的类型
	 * @return
	 */
	public Object fromJson(String json, Class clazz) {
		//创建一个对象
		Object o = null;
		try {
			
			//获得类(假设是User)的声明的构造方法的实例 即一个对象
			o = clazz.getDeclaredConstructor().newInstance();
			//根据json填充对象o的属性
			//获得字段名
			Field[] fs = clazz.getDeclaredFields();
			//json{},[]
			//取从1开始       length()-1结束    这样就去掉了 { 和 } 
			json = json.substring(1,json.length() - 1);
			String[] kv = json.split(",");
			System.out.println(kv.length);
			for (String s : kv) {
				System.out.println(s);
				
				
				//键  从1开始 到引号前面一位结束
				String k = s.substring(1, s.indexOf(':') - 1);
				System.out.println("#############" + k);
				//值  从引号前面的第一位开始直到全读完
				String v = s.substring(s.indexOf(':') + 1);
				System.out.println("=============" + v);
				
				//获得set方法名
				String name = "set" + k.substring(0,1).toUpperCase() + k.substring(1);
				
//				for (int i = 0; i < fs.length; i++) {
//					String s3 = fs[i].getType().getName();
//				}
				
				//获得类的方法列表
				Method[] m = clazz.getDeclaredMethods();
				
				for (int i = 0; i < m.length; i++) {
					//获得类的方法名
					String s1 = m[i].getName();
					
					//获得类的参数类型
					String s2 = Arrays.toString(m[i].getParameterTypes());
									
					//当类方法名和参数类型均与set方法一致时才调用
					if (s1.equals(name) && !s2.substring(1, s2.length()-1).equals("int")) {
						m[i].invoke(o,v);
					} else if(s1.equals(name)){
						int t = Integer.parseInt(v);
						m[i].invoke(o, t);
					}
				}

				
			}
			
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		
		return o;
		
	}

}
