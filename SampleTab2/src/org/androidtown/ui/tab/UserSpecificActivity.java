package org.androidtown.ui.tab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UserSpecificActivity extends Activity {
Intent intent = null;
Bundle bundle;
int whichimg = 1;

Double user_asset_deposit=0d;
Double user_asset_fund=0d;
Double user_asset_insurance=0d;
Double user_asset_bond=0d;
Double user_asset_stock=0d;

Double user_etc_house=0d;
Double user_etc_loan=0d;
Double user_etc_car=0d;

Double user_loan_mortgageloan=0d;
Double user_loan_carloan=0d;
Double user_loan_minusloan=0d;
Double user_loan_creditdebit=0d;

Double user_sandu_salary=0d;
Double user_sandu_livingcost=0d;
Double user_sandu_saving=0d;
Double user_sandu_schoolcost=0d;
Double user_sandu_personalfinancing=0d;
Double user_sandu_mortgage=0d;

int user_infor_age;
String user_infor_area;
String user_infor_job;
String user_infor_gender;
String user_infor_marrage;
int user_infor_child;
NumberToString nts;

TextView tv_deposit,tv_fund,tv_insurance,tv_bond,tv_stock;
TextView tv_house,tv_loan,tv_car;
TextView tv_carloan,tv_creditdebit,tv_minusloan,tv_mortgageloan;
TextView tv_salary,tv_livingcost,tv_saving,tv_schoolcost,tv_personalfinancing,tv_mortgage;
TextView tv_age,tv_area,tv_job,tv_gender,tv_marrage,tv_child;

EditText et_deposit,et_fund,et_insurance,et_bond,et_stock;
EditText et_house,et_loan,et_car;
EditText et_carloan,et_creditdebit,et_minusloan,et_mortgageloan;
EditText et_salary,et_livingcost,et_saving,et_schoolcost,et_personalfinancing,et_mortgage;
EditText et_age,et_area,et_job,et_gender,et_marrage,et_child;

DatabaseOperator databaseOperator;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 //       setContentView(R.layout.activity_user_specific);
        intent = getIntent();
        bundle = intent.getExtras();
        whichimg = bundle.getInt("getimg");
        databaseOperator = new DatabaseOperator(this);
        databaseOperator.open();
        nts = new NumberToString();

        user_asset_deposit = (Double) databaseOperator.getDB("�����ڻ� ����");
    	user_asset_fund = (Double) databaseOperator.getDB("�����ڻ� �ݵ�");
    	user_asset_insurance = (Double) databaseOperator.getDB("�����ڻ� ����");
    	user_asset_bond = (Double) databaseOperator.getDB("�����ڻ� ä��");
    	user_asset_stock = (Double) databaseOperator.getDB("�����ڻ� �ֽ�");
    	//
    	user_etc_house = (Double) databaseOperator.getDB("��Ÿ�ڻ� ����");
    	user_etc_loan = (Double) databaseOperator.getDB("��Ÿ�ڻ� ����");
    	user_etc_car = (Double) databaseOperator.getDB("��Ÿ�ڻ� �뿩��");
    	//
    	user_loan_carloan = (Double) databaseOperator.getDB("��ä �ڵ����Һ��ܾ�");
    	user_loan_creditdebit = (Double) databaseOperator.getDB("��ä �ſ�ī������ޱ�");
    	user_loan_minusloan = (Double) databaseOperator.getDB("��ä ���̳ʽ��ſ����");
    	user_loan_mortgageloan = (Double) databaseOperator.getDB("��ä ���ô㺸����");
    	//
    	user_sandu_salary = (Double) databaseOperator.getDB("������ ����");
    	user_sandu_livingcost = (Double) databaseOperator.getDB("������ ��Ȱ��");
    	user_sandu_saving = (Double) databaseOperator.getDB("������ �����");
    	user_sandu_schoolcost = (Double) databaseOperator.getDB("������ ������");
    	user_sandu_personalfinancing = (Double) databaseOperator.getDB("������ ���δ����ȯ��");
    	user_sandu_mortgage = (Double) databaseOperator.getDB("������ ���ô����ȯ��");
    	//
        Log.d("test �ǳ�?", "��?");
    	user_infor_age = (Integer) databaseOperator.getDB("����� ����");
        
    	user_infor_area = (String) databaseOperator.getDB("����� ��������");
    	user_infor_job = (String) databaseOperator.getDB("����� ����");
    	user_infor_gender = (String) databaseOperator.getDB("����� ����");
    	user_infor_marrage = (String) databaseOperator.getDB("����� ��ȥ����");
    	user_infor_child = (Integer) databaseOperator.getDB("����� �ڳ��");
    	Log.d("test �ǳ�?", "��?");
       checknullvalues();
        if(whichimg == 1)
        {
        	setContentView(R.layout.activity_user_specific);
        	
        	tv_deposit = (TextView)findViewById(R.id.tvsaving);
        	tv_fund = (TextView)findViewById(R.id.tvfund);
        	tv_insurance = (TextView)findViewById(R.id.tvinsurance);
        	tv_bond = (TextView)findViewById(R.id.tvbond);
        	tv_stock = (TextView)findViewById(R.id.tvstock);
        	//
        	et_deposit = (EditText)findViewById(R.id.editdeposit);
        	et_fund = (EditText)findViewById(R.id.editfund);
        	et_insurance = (EditText)findViewById(R.id.editinsurance);
        	et_bond = (EditText)findViewById(R.id.editbond);
        	et_stock = (EditText)findViewById(R.id.editstock);
        	//
        	tv_deposit.setText(nts.returnToString(user_asset_deposit));
        	tv_fund.setText(nts.returnToString(user_asset_fund));
        	tv_insurance.setText(nts.returnToString(user_asset_insurance));
        	tv_bond.setText(nts.returnToString(user_asset_bond));
        	tv_stock.setText(nts.returnToString(user_asset_stock));
        	Log.d("�̰ǹ���", "��"+tv_deposit.getText().toString());
        	
        	et_deposit.setText(tv_deposit.getText().toString());
        	et_fund.setText(tv_fund.getText().toString());
        	et_insurance.setText(tv_insurance.getText().toString());
        	et_bond.setText(tv_bond.getText().toString());
        	et_stock.setText(tv_stock.getText().toString());
        	
        	Log.d("test first", "");
        	
        	
        }
        else if(whichimg == 2)
        {
        	setContentView(R.layout.activity_user_specific2);
        	tv_house = (TextView)findViewById(R.id.tvhouse);
        	tv_car = (TextView)findViewById(R.id.tvcar);
        	tv_loan = (TextView)findViewById(R.id.tvloan);
        	//
        	et_house = (EditText)findViewById(R.id.ethouse);
        	et_car = (EditText)findViewById(R.id.etcar);
        	et_loan = (EditText)findViewById(R.id.etloan);
        	//
        	tv_house.setText(nts.returnToString(user_etc_house));
        	tv_car.setText(nts.returnToString(user_etc_car));
        	tv_loan.setText(nts.returnToString(user_etc_loan));
        	//
        	et_house.setText(nts.returnToString(user_etc_house));
        	et_car.setText(nts.returnToString(user_etc_car));
        	et_loan.setText(nts.returnToString(user_etc_loan));
			
        }

        
        else if(whichimg == 3)
        {
        	setContentView(R.layout.activity_user_specific3);
        	tv_carloan = (TextView)findViewById(R.id.tvcarloan);
        	tv_creditdebit = (TextView)findViewById(R.id.tvcreditdebit);
        	tv_minusloan = (TextView)findViewById(R.id.tvminusloan);
        	tv_mortgageloan = (TextView)findViewById(R.id.tvmortgageloan);
        	//
        	et_carloan = (EditText)findViewById(R.id.etcarloan);
        	et_creditdebit = (EditText)findViewById(R.id.etcreditdebit);
        	et_minusloan = (EditText)findViewById(R.id.etminusloan);
        	et_mortgageloan = (EditText)findViewById(R.id.etmortgageloan);
        	//
        	tv_carloan.setText(nts.returnToString(user_loan_carloan));
        	tv_creditdebit.setText(nts.returnToString(user_loan_creditdebit));
        	tv_minusloan.setText(nts.returnToString(user_loan_minusloan));
        	tv_mortgageloan.setText(nts.returnToString(user_loan_mortgageloan));
        	//
        	et_carloan.setText(nts.returnToString(user_loan_carloan));
        	et_creditdebit.setText(nts.returnToString(user_loan_creditdebit));
        	et_minusloan.setText(nts.returnToString(user_loan_minusloan));
        	et_mortgageloan.setText(nts.returnToString(user_loan_mortgageloan));

        }
        
        
        else if(whichimg == 4)
        {
        	setContentView(R.layout.activity_user_specific4);
        	tv_salary = (TextView)findViewById(R.id.tvsalary);
        	tv_livingcost = (TextView)findViewById(R.id.tvlivingcost);
        	tv_saving = (TextView)findViewById(R.id.tvsaving);
        	tv_schoolcost = (TextView)findViewById(R.id.tvschoolcost);
        	tv_personalfinancing = (TextView)findViewById(R.id.tvpersonalfinancing);
        	tv_mortgage = (TextView)findViewById(R.id.tvmortgage);
        	//
        	et_salary = (EditText)findViewById(R.id.etsalary);
        	et_livingcost = (EditText)findViewById(R.id.etlivingcost);
        	et_saving = (EditText)findViewById(R.id.etsaving);
        	et_schoolcost = (EditText)findViewById(R.id.etschoolcost);
        	et_personalfinancing = (EditText)findViewById(R.id.etpersonalfinancing);
        	et_mortgage = (EditText)findViewById(R.id.etmortgage);
        	//
        	tv_salary.setText(nts.returnToString(user_sandu_salary));
        	tv_livingcost.setText(nts.returnToString(user_sandu_livingcost));
        	tv_saving.setText(nts.returnToString(user_sandu_saving));
        	tv_schoolcost.setText(nts.returnToString(user_sandu_schoolcost));
        	tv_personalfinancing.setText(nts.returnToString(user_sandu_personalfinancing));
        	tv_mortgage.setText(nts.returnToString(user_sandu_mortgage));
        	
        	et_salary.setText(nts.returnToString(user_sandu_salary));
        	et_livingcost.setText(nts.returnToString(user_sandu_livingcost));
        	et_saving.setText(nts.returnToString(user_sandu_saving));
        	et_schoolcost.setText(nts.returnToString(user_sandu_schoolcost));
        	et_personalfinancing.setText(nts.returnToString(user_sandu_personalfinancing));
        	et_mortgage.setText(nts.returnToString(user_sandu_mortgage));
        	
        }
        else if(whichimg == 5)
        {
        	setContentView(R.layout.activity_user_specific5);
        	
        } 
    }


	private void checknullvalues() {
		// TODO Auto-generated method stub
		if(user_asset_deposit == null)
			user_asset_deposit = 0d;
		if(user_asset_fund == null)
			user_asset_fund = 0d;
		if(user_asset_insurance == null)
			user_asset_insurance = 0d;
		if(user_asset_bond == null)
			user_asset_bond = 0d;
		if(user_asset_stock == null)
			user_asset_stock = 0d;
    	//
		if(user_etc_house == null)
			user_etc_house = 0d;
		if(user_etc_loan == null)
			user_etc_loan = 0d;
		if(user_etc_car == null)
			user_etc_car = 0d;
    	//
		if(user_loan_carloan == null)
			user_loan_carloan = 0d;
		if(user_loan_creditdebit == null)
			user_loan_creditdebit = 0d;
		if(user_loan_minusloan == null)
			user_loan_minusloan = 0d;
		if(user_loan_mortgageloan == null)
			user_loan_mortgageloan = 0d;
    	//
		if(user_sandu_salary == null)
			user_sandu_salary = 0d;
		if(user_sandu_livingcost == null)
			user_sandu_livingcost = 0d;
		if(user_sandu_saving == null)
			user_sandu_saving = 0d;
		if(user_sandu_schoolcost == null)
			user_sandu_schoolcost = 0d;
		if(user_sandu_personalfinancing == null)
			user_sandu_personalfinancing = 0d;
		if(user_sandu_mortgage == null)
			user_sandu_mortgage = 0d;
		//
		if(user_infor_age == 0)
			user_infor_age = 0;
		if(user_infor_area == null)
			user_infor_area = "";
		if(user_infor_job == null)
			user_infor_job = "";
		if(user_infor_gender == null)
			user_infor_gender = "";
		if(user_infor_marrage == null)
			user_infor_marrage = "";
		if(user_infor_child == 0)
			user_infor_child = 0;
	}


	public void Onclick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btndeposit:
			databaseOperator.insertDB("�����ڻ� ����", et_deposit.getText().toString());
			tv_deposit.setText(et_deposit.getText().toString());Log.d("����?", "���ı�");
			break;
		case R.id.btnfund:
			databaseOperator.insertDB("�����ڻ� �ݵ�", et_fund.getText().toString());
			tv_fund.setText(et_fund.getText().toString());
			break;
		case R.id.btninsurance:
			databaseOperator.insertDB("�����ڻ� ����", et_insurance.getText().toString());
			tv_insurance.setText(et_insurance.getText().toString());
			break;
		case R.id.btnbond:
			databaseOperator.insertDB("�����ڻ� ä��", et_bond.getText().toString());
			tv_bond.setText(et_bond.getText().toString());
			break;
		case R.id.btnstock:
			databaseOperator.insertDB("�����ڻ� �ֽ�", et_stock.getText().toString());
			tv_stock.setText(et_stock.getText().toString());
			break;
			////

		case R.id.btnhouse:
			databaseOperator.insertDB("��Ÿ�ڻ� ����", et_house.getText().toString());
			tv_house.setText(et_house.getText().toString());
			break;
		case R.id.btncar:
			databaseOperator.insertDB("��Ÿ�ڻ� ����", et_car.getText().toString());
			tv_car.setText(et_car.getText().toString());
			break;
		case R.id.btnloan:
			databaseOperator.insertDB("��Ÿ�ڻ� �뿩��", et_loan.getText().toString());
			tv_loan.setText(et_loan.getText().toString());
			break;
			//////

		case R.id.btnmortgageloan:
			databaseOperator.insertDB("��ä ���ô㺸����", et_mortgageloan.getText().toString());
			tv_mortgageloan.setText(et_mortgageloan.getText().toString());
			break;
		case R.id.btncarloan:
			databaseOperator.insertDB("��ä �ڵ����Һ��ܾ�", et_carloan.getText().toString());
			tv_carloan.setText(et_carloan.getText().toString());
			break;
		case R.id.btnminusloan:
			databaseOperator.insertDB("��ä ���̳ʽ��ſ����", et_minusloan.getText().toString());
			tv_minusloan.setText(et_minusloan.getText().toString());
			break;
		case R.id.btncreditdebit:
			databaseOperator.insertDB("��ä �ſ�ī������ޱ�", et_creditdebit.getText().toString());
			tv_creditdebit.setText(et_creditdebit.getText().toString());
			break;
			
			////////


		case R.id.btnsalary:
			databaseOperator.insertDB("������ ����", et_mortgageloan.getText().toString());
			tv_mortgageloan.setText(et_mortgageloan.getText().toString());
			break;
		case R.id.btnlivingcost:
			databaseOperator.insertDB("������ ��Ȱ��", et_carloan.getText().toString());
			tv_carloan.setText(et_carloan.getText().toString());
			break;
		case R.id.btnsaving:
			databaseOperator.insertDB("������ �����", et_minusloan.getText().toString());
			tv_minusloan.setText(et_minusloan.getText().toString());
			break;
		case R.id.btnschoolcost:
			databaseOperator.insertDB("������ ������", et_creditdebit.getText().toString());
			tv_creditdebit.setText(et_creditdebit.getText().toString());
			break;
		case R.id.btnpersonalfinancing:
			databaseOperator.insertDB("������ ���δ����ȯ��", et_minusloan.getText().toString());
			tv_minusloan.setText(et_minusloan.getText().toString());
			break;
		case R.id.btnmortgage:
			databaseOperator.insertDB("������ ���ô����ȯ��", et_creditdebit.getText().toString());
			tv_creditdebit.setText(et_creditdebit.getText().toString());
			break;
			
			
			
			
			
		default:
			break;
		}
	}
    
}
