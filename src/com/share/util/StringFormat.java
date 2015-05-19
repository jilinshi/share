package com.share.util;

public class StringFormat {
	
	public static String getformatting(String s){
	  String str_m = String.valueOf(s); 
	  String str ="0000000000";
	  str_m=str_m + str.substring(str_m.length(), 10);
	  return str_m;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = StringFormat.getformatting("12345");
		System.out.println(a);
	}

}
