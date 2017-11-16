package com.allcheer.bpos.util;

import java.util.Random;

public class RandomString {
	public static String getRandomString(int length) {
	    String base = "0123456789ABCDEF0123456789ABCDEF";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }     
	
	
	public static void main(String[] args) {
		RandomString li = new RandomString();
		
		System.out.println(li.getRandomString(32));
	}
}
