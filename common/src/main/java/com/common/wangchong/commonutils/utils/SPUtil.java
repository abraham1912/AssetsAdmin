package com.common.wangchong.commonutils.utils;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * sp存储操作的工具类
 * @author ChongWang
 *
 */
public final class SPUtil {
	private static SPUtil instance ;
	private static SharedPreferences sp ;
	private SPUtil(){}
	public static final String  USER_NAME = "user_name";

	public static SPUtil getInstance(Context context){
		if(instance==null){
			instance = new SPUtil();
			sp = context.getSharedPreferences("AssetsAdmin", Context.MODE_PRIVATE);
		}
		return instance;
		
	}
	//sp保存数据
	public  void save(String name , Object value){
		if(value instanceof String){
			sp.edit().putString(name, (String)value).commit();
		}else if(value instanceof Integer){
			sp.edit().putInt(name, (Integer)value).commit();
		}else if(value instanceof Boolean){
			sp.edit().putBoolean(name, (Boolean)value).commit();
		}
	}
	
	public  String read(String name,String defValue){
		return sp.getString(name, defValue);
		
	}
	public  int read(String name,int defValue){
		return sp.getInt(name, defValue);
	}
	public  boolean read(String name,boolean defValue){
		return sp.getBoolean(name, defValue);
	}
	
	//sp移除数据
	public  void remove(String name){
		sp.edit().remove(name).commit();
	}
}
