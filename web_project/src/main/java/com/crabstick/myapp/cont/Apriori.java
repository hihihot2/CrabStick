package com.crabstick.myapp.cont;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.crabstick.myapp.venue.Venue;

public class Apriori {
	static Map<String,String> Map_V = new HashMap<String, String>();
	static Map<String,String> Map_Path = new HashMap<String, String>();
	/* 추천 관광 지역 지지도 계산 */
	
	public ArrayList<String> apriori_Algorithm(ArrayList<Integer> transactions, ArrayList<String> seqeunces, ArrayList<Venue> venues, int order, String selectVenue, int mem_no) {
		System.out.println("오더 순서"+order);
		if (order>0){
			Map_Path.put(mem_no+"PP"+order, selectVenue);
			if (order==1){
				Map_V.put(mem_no+"DD"+order, selectVenue);
			} else if (order==2){
				Map_V.put(mem_no+"DD"+order, selectVenue);
			} else if (order>=3){
				Map_V.replace(mem_no+"DD1", Map_V.get(mem_no+"DD2"));
				Map_V.replace(mem_no+"DD2", selectVenue);
			}
		}
		
		/* 필드 */
		ArrayList<ArrayList<String>> data_Mart = new ArrayList<ArrayList<String>>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> name2 = new ArrayList<String>();
		ArrayList<String> name3 = new ArrayList<String>();
		double selectVenueProbability = 0.0;

		/* 분석을 위한 데이터 마트 구성 */
		for (int i=0; i<transactions.size(); i++){
			ArrayList<String> inner_Data = new ArrayList<String>();
			for (int j=0; j<venues.size(); j++){
				if (transactions.get(i)==venues.get(j).getPath_no()){
					inner_Data.add(venues.get(j).getVen_lati()+":"+venues.get(j).getVen_long());
				}
			}
			data_Mart.add(inner_Data);
		}
		System.out.println("데이터 마트 사이즈는? "+data_Mart.size());
		/* 분석을 위한 데이터 마트 구성 */


		/* 최초 추천~ */
		if (order>=0){
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
				if (result[i]>=0.25){ // 지지도 0.1 이상일 때, 최초 추천장소로 등록 
					name.add(seqeunces.get(i));
				}


				if (seqeunces.get(i).equals(Map_V.get(mem_no+"DD1"))){
					selectVenueProbability = result[i];
				}

			}
		}


		/* 1차 경로 선택 후 다음 추천 장소에 대한 업데이트  */
		if (order>=1 && selectVenueProbability!=0.0){
			for (int i=0; i<seqeunces.size(); i++){
				int count=0;
				int index=0;
				boolean isMatched = false;
				double[] result = new double[seqeunces.size()];


				for (int j=0; j<data_Mart.size(); j++){

					for (int k=0; k<data_Mart.get(j).size(); k++){
						if (Map_V.get(mem_no+"DD1").equals(data_Mart.get(j).get(k))){
							isMatched = true;
							index=k;
							break;
						}
					}

					if (isMatched){
						for (int k=index+1; k<data_Mart.get(j).size(); k++){
							if (seqeunces.get(i).equals(data_Mart.get(j).get(k))){
								count++;
								break;
							}
						}
					}
				}
				result[i]=(double)count/data_Mart.size();
				if (result[i]>=0.2 && ((result[i]/selectVenueProbability)>=0.5)){ // 지지도 0.2이상 & 신뢰도 0.5 이상
					name2.add(seqeunces.get(i));
				}


				if (seqeunces.get(i).equals(Map_V.get(mem_no+"DD2"))){
					selectVenueProbability = result[i];
				}

			}
		}

		if (order>=2){
			for (int i=0; i<seqeunces.size(); i++){
				int count=0;
				int index=0;
				int index_2=0;
				boolean isMatched = false;
				boolean isMatched_2 = false;
				double[] result = new double[seqeunces.size()];


				for (int j=0; j<data_Mart.size(); j++){

					for (int k=0; k<data_Mart.get(j).size(); k++){
						if (Map_V.get(mem_no+"DD1").equals(data_Mart.get(j).get(k))){
							isMatched = true;
							index=k;
							break;
						}
					}

					if (isMatched){
						for (int k=index+1; k<data_Mart.get(j).size(); k++){
							if (Map_V.get(mem_no+"DD2").equals(data_Mart.get(j).get(k))){
								isMatched_2 = true;
								index_2=k;
								break;
							}
						}
					}

					if (isMatched_2){
						for (int k=index_2+1; k<data_Mart.get(j).size(); k++){
							if (seqeunces.get(i).equals(data_Mart.get(j).get(k))){
								count++;
								break;
							}
						}
					}
				}
				result[i]=(double)count/data_Mart.size();
				if (result[i]>=0.1 && ((result[i]/selectVenueProbability)>=0.5)){ // 지지도 0.25이상 & 신뢰도 50% 이상
					name3.add(seqeunces.get(i));
				}

			}
		}


		ArrayList<String> recommend_Venues = new ArrayList<String>();

		if (order==0){
			for (int i = 0 ; i<name.size(); i++){
				for (int j = 0; j<seqeunces.size(); j++){
					if (name.get(i).equals(seqeunces.get(j))){
						recommend_Venues.add(seqeunces.get(j));
					}
				}
			}
		} else if (order==1){
			for (int i = 0 ; i<name2.size(); i++){
				for (int j = 0; j<seqeunces.size(); j++){
					if (name2.get(i).equals(seqeunces.get(j))){
						recommend_Venues.add(seqeunces.get(j));
					}
				}
			}
		} else {
			for (int i = 0 ; i<name3.size(); i++){
				for (int j = 0; j<seqeunces.size(); j++){
					if (name3.get(i).equals(seqeunces.get(j))){
						recommend_Venues.add(seqeunces.get(j));
					}
				}
			}
		}
		System.out.println("반환 갯수"+recommend_Venues.size());
		return recommend_Venues;
	}
	
	public boolean check_Path(String location){
		
		return Map_Path.containsValue(location);
		
	}

}
