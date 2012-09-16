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
				returnValues = "��" + returnValues;
			} else if (cipher == 3) {
				returnValues = "��" + returnValues;
			} else if (cipher == 4) {
				returnValues = "��" + returnValues;
			} else if (cipher == 5) {
				returnValues = "��" + returnValues;
			}

			if (mod%10 != 0){
				returnValues = returnValues + mod%10;			//0
			}
			mod = mod/10;
			if (mod%10 != 0){
				returnValues = mod%10 + "��" + returnValues;			//0
			}
			mod = mod/10;
			if (mod%10 != 0){
				mod = mod/10;
			}
			mod = mod/10;
			if (mod%10 != 0){
				returnValues = mod%10 + "��" + returnValues;			//0
			}
			mod = mod/10;
			if (mod%10 != 0){
				returnValues = mod%10 + "õ" + returnValues;			//1
			}
		} while((share > 0));
		Log.i("��� : ", returnValues);
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
				returnValues = "��" + returnValues;
			} else if (cipher == 3) {
				returnValues = "��" + returnValues;
			} else if (cipher == 4) {
				returnValues = "��" + returnValues;
			} else if (cipher == 5) {
				returnValues = "��" + returnValues;
			}

			if (mod%10 != 0){
				returnValues = returnValues + mod%10;			//0
			}
			mod = mod/10;
			if (mod%10 != 0){
				returnValues = mod%10 + "��" + returnValues;			//0
			}
			mod = mod/10;
			if (mod%10 != 0){
				mod = mod/10;
			}
			mod = mod/10;
			if (mod%10 != 0){
				returnValues = mod%10 + "��" + returnValues;			//0
			}
			mod = mod/10;
			if (mod%10 != 0){
				returnValues = mod%10 + "õ" + returnValues;			//1
			}
		} while((share > 0));
		Log.i("��� : ", returnValues);
		return returnValues;
	}
}
