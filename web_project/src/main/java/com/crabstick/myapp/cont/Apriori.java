package com.crabstick.myapp.cont;

import java.util.ArrayList;

import com.crabstick.myapp.venue.Venue;

public class Apriori {

	/* 최초 추천 관광 지역 지지도 계산 */
	public ArrayList<Venue> apriori_Algorithm(ArrayList<Integer> transactions, ArrayList<String> seqeunces, ArrayList<Venue> venues) {

		int order_Num=0;
		ArrayList<ArrayList<String>> data_Mart = new ArrayList<ArrayList<String>>();
		ArrayList<String> name = new ArrayList<String>();
		
		for (int i=0; i<transactions.size(); i++){
			ArrayList<String> inner_Data = new ArrayList<String>();
			for (int j=0; j<venues.size(); j++){
				if (transactions.get(i)==venues.get(j).getPath_no()){
					inner_Data.add(venues.get(j).getVen_name());
				}
			}
			data_Mart.add(inner_Data);
		}

		System.out.println("데이터 마트 사이즈는? "+data_Mart.size());
		if (order_Num>=0){

			double[] result = new double[seqeunces.size()];
			for (int i=0; i<seqeunces.size(); i++){
				int count=0;

				for (int j=0; j<data_Mart.size(); j++){

					for (int k=0; k<data_Mart.get(j).size(); k++){
						if (seqeunces.get(i).equals(data_Mart.get(j).get(k))){
							count++;
							break;
						}
					}

				}
				result[i]=(double) count/data_Mart.size();
				System.out.println(i+"번"+seqeunces.get(i) +"의 빈발 지지도 "+ result[i]);
				if (result[i]>0.1){
					name.add(seqeunces.get(i));
				}
			}
		}

		if (order_Num>=1){

		}

		if (order_Num>=2){

		}
		
		ArrayList<Venue> recommend_Venues = new ArrayList<Venue>();
		for (int i = 0 ; i<name.size(); i++){
			for (int j = 0; j<venues.size(); j++){
				if (name.get(i).equals(venues.get(j).getVen_name())){
					recommend_Venues.add(venues.get(j));
				}
			}
		}
		return recommend_Venues;

		
	}

}
