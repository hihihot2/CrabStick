package com.crabstick.myapp.recommendation;


public class Weight_Loc {

	public static City updateCity(City resultCity, String accompany, String purpose, String favor_city){

		String[] purpose_Name = {"지역 문화 탐방","지역 음식 체험","쇼핑","휴식"};
		int purpose_Weight[] = {resultCity.getLoc_p_cult(),
				resultCity.getLoc_p_food(),
				resultCity.getLoc_p_shop(),
				resultCity.getLoc_p_rest()};
		
		for (int i=0; i<purpose_Name.length;i++){
			if (purpose.equals(purpose_Name[i])){
				purpose_Weight[i]++;
				if (i == 0 ){
					resultCity.setLoc_p_cult(purpose_Weight[i]);
				} else if (i == 1) {
					resultCity.setLoc_p_food(purpose_Weight[i]);
				} else if (i == 2) {
					resultCity.setLoc_p_rest(purpose_Weight[i]);
				} else {
					resultCity.setLoc_p_shop(purpose_Weight[i]);
				}
			}
		}

		String[] accompany_Name = {"연인","가족","친구","혼자"};
		int accompany_Weight[] = {resultCity.getLoc_a_coup(),
				resultCity.getLoc_a_fam(),
				resultCity.getLoc_a_frnd(),
				resultCity.getLoc_a_solo()};
		
		for (int i=0; i<accompany_Name.length;i++){
			if (accompany.equals(accompany_Name[i])){
				accompany_Weight[i]++;
				if (i == 0 ){
					resultCity.setLoc_a_coup(accompany_Weight[i]);
				} else if (i == 1) {
					resultCity.setLoc_a_fam(accompany_Weight[i]);
				} else if (i == 2) {
					resultCity.setLoc_a_frnd(purpose_Weight[i]);
				} else {
					resultCity.setLoc_a_solo(purpose_Weight[i]);
				}
			}
		}

		String[] city_Name = {"대도시","유적지","자연경관"};
		int city_Weight[] ={
				resultCity.getLoc_c_capt(),
				resultCity.getLoc_c_his(),
				resultCity.getLoc_c_natu()};
		
		for (int i=0; i<city_Name.length;i++){
			if (favor_city.equals(city_Name[i])){
				city_Weight[i]++;
				if (i == 0 ){
					resultCity.setLoc_c_capt(city_Weight[i]);
				} else if (i == 1) {
					resultCity.setLoc_c_his(city_Weight[i]);
				} else if (i == 2) {
					resultCity.setLoc_c_natu(city_Weight[i]);
				}
			}
		}

		return resultCity;
	}

}
