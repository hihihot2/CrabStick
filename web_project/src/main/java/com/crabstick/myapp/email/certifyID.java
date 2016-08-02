package com.crabstick.myapp.email;

import java.util.Random;

public class certifyID {

	public static int generateNum(){
		Random ran = new Random();
		int num =0;
		do{
			num = ran.nextInt(10000)+1000;
		}while(num > 10000);
		System.out.println(num);
		return num;
	}
}
