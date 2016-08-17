package com.crabstick.myapp.cont;

import java.util.ArrayList;

import com.crabstick.myapp.venue.Venue;

public class Apriori {

	/* 추천 관광 지역 지지도 계산 */
	public ArrayList<Venue> apriori_Algorithm(ArrayList<Integer> transactions, ArrayList<String> seqeunces, ArrayList<Venue> venues,int order, String selectVenue) {



		ArrayList<ArrayList<String>> data_Mart = new ArrayList<ArrayList<String>>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> name2 = new ArrayList<String>();
		double selectVenueProbability = 0.0;

		/* 분석을 위한 데이터 마트 구성 */
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
				if (result[i]>=0.5){ // 지지도 0.5 이상일 때, 최초 추천장소로 등록 
					System.out.println(seqeunces.get(i));
					name.add(seqeunces.get(i));
				}

				if (seqeunces.get(i).equals(selectVenue)){
					selectVenueProbability = result[i];
				}
			}
		}


		/* 1차 경로 선택 후 추천 장소에 대한 업데이트  */
		if (order>=1 && selectVenueProbability!=0.0){

			for (int i=0; i<seqeunces.size(); i++){
				int count=0;
				boolean isMatched = false;
				double[] result = new double[seqeunces.size()];

				if (!selectVenue.equals(seqeunces.get(i))){
					for (int j=0; j<data_Mart.size(); j++){

						for (int k=0; k<data_Mart.get(j).size(); k++){
							if (selectVenue.equals(data_Mart.get(j).get(k))){
								isMatched = true;
								break;
							}
						}

						if (isMatched){
							for (int k=0; k<data_Mart.get(j).size(); k++){
								if (seqeunces.get(i).equals(data_Mart.get(j).get(k))){
									count++;
									break;
								}
							}
						}
					}
					result[i]=(double)count/data_Mart.size();
					System.out.println(seqeunces.get(i)+"과"+selectVenue+"의 빈발 지지도 "+ result[i]);
					if (result[i]>=0.5 && result[i]/selectVenueProbability>=0.5){ // 지지도0.5이상 & 신뢰도 0.5 이상
						System.out.println(selectVenue+","+seqeunces.get(i));
						name2.add(seqeunces.get(i));
					}

				} else {
					result[i] = 0.0;
				}
			}
		}

		if (order>=2){

		}

		ArrayList<Venue> recommend_Venues = new ArrayList<Venue>();
		
		if (order==0){
			for (int i = 0 ; i<name.size(); i++){
				for (int j = 0; j<venues.size(); j++){
					if (name.get(i).equals(venues.get(j).getVen_name())){
						recommend_Venues.add(venues.get(j));
					}
				}
			}
		} else if (order==1){
			for (int i = 0 ; i<name2.size(); i++){
				for (int j = 0; j<venues.size(); j++){
					if (name2.get(i).equals(venues.get(j).getVen_name())){
						recommend_Venues.add(venues.get(j));
					}
				}
			}
		}
		return recommend_Venues;


	}

}
