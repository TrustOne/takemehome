package org.androidtown.ui.tab;

import android.util.Log;

public class NumberToString {
	public NumberToString(){
		
	}

	public String returnToString(double values){
		long share =(long) values;
		long mod;
		int cipher = 0;
		String returnValues = "";
		
		do {
			mod = share%10000;
			share = share/10000;
			cipher++;
			if(mod == 0){
				continue;
			}
			if(cipher == 2){
				returnValues = "만" + returnValues;
			} else if (cipher == 3) {
				returnValues = "억" + returnValues;
			} else if (cipher == 4) {
				returnValues = "조" + returnValues;
			} else if (cipher == 5) {
				returnValues = "경" + returnValues;
			}

			if (mod%10 != 0){
				returnValues = returnValues + mod%10;			//0
			}
			mod = mod/10;
			if (mod%10 != 0){
				returnValues = mod%10 + "십" + returnValues;			//0
			}
			mod = mod/10;
			if (mod%10 != 0){
				mod = mod/10;
			}
			mod = mod/10;
			if (mod%10 != 0){
				returnValues = mod%10 + "백" + returnValues;			//0
			}
			mod = mod/10;
			if (mod%10 != 0){
				returnValues = mod%10 + "천" + returnValues;			//1
			}
		} while((share > 0));
		Log.i("출력 : ", returnValues);
		return returnValues;
	}

	public String returnToString(int values){
		int share = values;
		int mod;
		int cipher = 0;
		String returnValues = "";
		
		do {
			mod = share%10000;
			share = share/10000;
			cipher++;
			if(mod == 0){
				continue;
			}
			if(cipher == 2){
				returnValues = "만" + returnValues;
			} else if (cipher == 3) {
				returnValues = "억" + returnValues;
			} else if (cipher == 4) {
				returnValues = "조" + returnValues;
			} else if (cipher == 5) {
				returnValues = "경" + returnValues;
			}

			if (mod%10 != 0){
				returnValues = returnValues + mod%10;			//0
			}
			mod = mod/10;
			if (mod%10 != 0){
				returnValues = mod%10 + "십" + returnValues;			//0
			}
			mod = mod/10;
			if (mod%10 != 0){
				mod = mod/10;
			}
			mod = mod/10;
			if (mod%10 != 0){
				returnValues = mod%10 + "백" + returnValues;			//0
			}
			mod = mod/10;
			if (mod%10 != 0){
				returnValues = mod%10 + "천" + returnValues;			//1
			}
		} while((share > 0));
		Log.i("출력 : ", returnValues);
		return returnValues;
	}
}
