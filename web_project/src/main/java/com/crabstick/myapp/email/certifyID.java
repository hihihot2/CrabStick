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
	
	
	public static String email_plus_parsing(String id){
		int first_num = 0;
		int end_num = 0;
		
		String[] id_parse = new String[id.length()];
		for(int i=0; i<id.length(); i++){
			id_parse[i] = id.substring(i, i+1);
			if(id_parse[i].equals(" ")){
				first_num = i;
			} else if(id_parse[i].equals("@")){
				end_num = i;
				if(first_num != 0){ // +기호가 있을경우
					String temp1 = id.substring(0, first_num);
					String temp2 = id.substring(end_num, id.length());
					id = temp1 + temp2;
				}
			}
		}
		
		return id;
	}
}
