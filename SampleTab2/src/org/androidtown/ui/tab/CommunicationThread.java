/*
 * @DbServer.java 0.10 12/08/28
 *
 * Copyright (c) 2012 takemehome.
 * ��⵵ ���� ���籸 �ѱ��װ���.
 * All Rights Reserved.
 *
 * �� ����Ʈ����� ??? ���ø����̼��� �����ϱ� ���� ������ �����մϴ�.
 */



package org.androidtown.ui.tab;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


/**
 * ??? ���ø����̼ǿ��� ������ ����ϱ� ���ؼ� ����ϴ� ������
 * 
 * @version 0.10 28 Aug 2012
 * @author �� ����
 */
public class CommunicationThread extends Thread{
	/* ��Ű� ���ø����̼� ��ü�� ������ �������� �����ϱ� ���ؼ� Thread
	 * ������ � Ŭ���̾�Ʈ�� �����ߴ��� �ʱ�ȭ�Ѵ�.
	 * run()�Լ��� ���ؼ� Ŭ���̾�Ʈ�� ��û�� ó���Ѵ�. 
	 * ��û�� ���� ó���� �����ٸ� disconnect�Լ��� ����� �����Ѵ�.*/

	/**	�ڻ�񱳿�û�� ����ϴ� ����� */
	final static public String REQUEST_COMPARING_ASSETS = "requestComparingAssets";
	//���� ����
	/** ����� ������ ���� Intent Extra Ű */
	final static public String CTRESULT = "ctresult";
	
	//���� Intent Extra ��
	final static public String SUCCESS_RESULT = "success_result";
	
	final static public String RESULT = "result";
	
	private int PORT;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;	
	private Socket server;
	private String request;
	private Context context;
	private Handler resultHandler;
	private float startPoint;
	private float endPoint;
	private JSONObject condition;
	
	/**
	 * ?? �ȵ���̵� ���ø����̼��� ������ ����ϴµ� ����ϴ� ��� �������� ������
	 * �ڻ�񱳸� ���� ����ϸ�, ���� �ۼ�Ʈ�� �� �ۼ�Ʈ�� �̿��Ͽ� �ش� ���� ���� �ڻ� ������ ���� ������ �˷��ش�.
	 * 
	 * @param request ��û�� ���
	 *  - REQUEST_COMPARING_ASSETS : �ڻ�񱳿� ���� ��û
	 * @param handler 	 : ��û�� ����� ������ �� �亯�� �޴� ��鷯
	 * @param startPoint : �񱳸� ������ ���� ��ġ�� �ۼ�Ʈ
	 * @param endPoint	 : �񱳸� ������ �� ��ġ�� �ۼ�Ʈ
	 * @param condition	 : ���� ������ ������ ���� 
	 */
	public CommunicationThread(String request, Handler handler, 
							   float startPoint, float endPoint, JSONObject condition)
	{
		PORT = 20000;
		this.request 	= request;
		resultHandler 	= handler;
		this.startPoint = startPoint;
		this.endPoint 	= endPoint;
		this.condition 	= condition;
	}

	/**
	 * ?? �ȵ���̵� ���ø����̼��� ������ ����ϴµ� ����ϴ� ��� �������� ������
	 * ��û, context, �亯�� ���� handler�� �ʱ�ȭ�Ѵ�.
	 * 
	 * @param request ��û�� ���
	 *  - REQUEST_COMPARING_ASSETS : �ڻ�񱳿� ���� ��û
	 *  - REQUEST_COMPARING_ASSETS : �ڻ�񱳿� ���� ��û
	 *  - REQUEST_COMPARING_ASSETS : �ڻ�񱳿� ���� ��û
	 * @param context
	 * @param handler ��û�� ����� ������ �� �亯�� �޴� ��鷯
	 */
	public CommunicationThread(String request, Context context, Handler handler)
	{
		PORT = 20000;
		this.request = request;
		this.context = context;
		resultHandler = handler;
	}

	/**
	 * ?? �ȵ���̵� ���ø����̼��� ������ ����ϴµ� ����ϴ� ��� �������� �����
	 * ��û(request)�� �����Ͽ� ������ ����� �����Ѵ�.
	 * 
	 */
	public void run()
	{
		try {
			server = new Socket("192.168.206.225",PORT);
			
			inputStream = new DataInputStream(new BufferedInputStream(server.getInputStream()));
			outputStream = new DataOutputStream(new BufferedOutputStream(server.getOutputStream()));

			Log.i("CommunicationTread",request);
			if (request.equals(REQUEST_COMPARING_ASSETS)){
				requestComparingAssets();
			} else if (request.equals("act1"))
			{
				Log.i("CommunicationTread","act1 ���� ��");
				act1();
				Log.i("CommunicationTread","act1 ���� ��");

			} else if (request.equals("act2"))
			{
//				act2();
			} else if (request.equals("act3"))
			{
//				act3();
			} else if (request.equals("use1"))
			{
				use1();
			} else{
				
			}

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//connect�Լ� ����
	
	public void disconnect()
	{
		System.out.println("���� ����");
		
		try
		{
			if(inputStream != null)
			{
				inputStream.close();
				outputStream.close();
				inputStream = null;
				outputStream = null;
			}
			if(server != null)
			{
				server.close();
				server=null;
			}
		}
		catch(IOException e)
		{
		}
	}
	//disconnect �Լ� ����
	
	/**
	 * requestComparingAssets��
	 * ???�ȵ���̵� ���ø����̼� �������� �ڻ�񱳿� ���� ��û�� �ϴ� �޼ҵ�
	 * ����� handler�� ��ȯ�Ѵ�.
	 */
	private void requestComparingAssets(){
		Log.i("CommunicationTread","requestComparingAssets ������ ��û");
		try {
			outputStream.writeUTF(REQUEST_COMPARING_ASSETS);
			outputStream.flush();
			
			JSONObject request = new JSONObject();
			request.put("startPoint", startPoint);
			request.put("endPoint"	, endPoint);
			request.put("condition" , condition);
			Log.i("CommunicationTread",request.toString());
			
			outputStream.writeUTF(request.toString());
			outputStream.flush();

			JSONObject answer = new JSONObject(inputStream.readUTF());

			Log.i("CommunicationTread","�ֽ�    : " + answer.getDouble("regularSavings"));
			Log.i("CommunicationTread","�ε��� : " + answer.getDouble("inheritance"));
			Log.i("CommunicationTread","��Ÿ    : " + answer.getDouble("useEct"));
			Log.i("CommunicationTread","ü��    : " + answer.getDouble("debt"));
			Log.i("CommunicationTread","��Ÿ2 : " + answer.getDouble("investmentEct"));
			Log.i("CommunicationTread","��Ÿ2 : " + answer.getDouble("investmentEct"));

			double answerList[] = new double[5];
			answerList[0] = answer.getDouble("regularSavings");
			answerList[1] = answer.getDouble("inheritance");
			answerList[2] = answer.getDouble("useEct");
			answerList[3] = answer.getDouble("debt");
			answerList[4] = answer.getDouble("investmentEct");
			
			Message message = resultHandler.obtainMessage();
			Bundle bundle = new Bundle();
			bundle.putString(RESULT, SUCCESS_RESULT);
			bundle.putDoubleArray("answer", answerList);
			message.setData(bundle);
			resultHandler.sendMessage(message);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void act1(){
/*		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			Log.i("CommunicationTread","act1 ���� ���� ����");
			outputStream.writeUTF("act1");
			outputStream.flush();
			
			//db ����
			Log.i("CommunicationTread","db ����");

			Log.i("CommunicationTread","db ����������");
			Cursor cursor = dbc.fetchMemberNotes();
			
//			while (cursor.moveToNext()) {
			cursor.moveToNext();
			int age = cursor.getInt(cursor.getColumnIndex(DatabaseOperator.ENTITY_AGE));
			Log.i("CommunicationTread",""+age);
			String res = cursor.getString(cursor.getColumnIndex(DatabaseOperator.ENTITY_RESIDENTIAL));
			Log.i("CommunicationTread",""+res);
			String jap = cursor.getString(cursor.getColumnIndex(DatabaseOperator.ENTITY_JAP));
			Log.i("CommunicationTread",""+cursor);
			String mar = cursor.getString(cursor.getColumnIndex(DatabaseOperator.ENTITY_MARRIAGE));
			Log.i("CommunicationTread",""+mar);
			String child = cursor.getString(cursor.getColumnIndex(DatabaseOperator.ENTITY_CHILD));
			Log.i("CommunicationTread",""+child);
			String sex = cursor.getString(cursor.getColumnIndex(DatabaseOperator.ENTITY_SEX));
			Log.i("CommunicationTread",""+sex);
//			}
			Log.i("CommunicationTread","db �� ���װ�");

			outputStream.writeUTF(age+","+res+","+jap+","+mar+","+child+","+sex);
			outputStream.flush();
			
			
//			String result = inputStream.readUTF();
//			arrayList=analysis(result);
//			for(int i=0;i<arrayList.size();i++)
//			{
//				Log.i("community",arrayList.get(i));
//			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}
	
	private void use1()
	{
		Log.i("CommunicationTread","use1 ���� ���� ����");
		try {
			outputStream.writeUTF("use1");
			outputStream.flush();
			Log.i("CommunicationTread","use1 ���� ���� ���� ��");

			String str = inputStream.readUTF();
			ArrayList<String> arrayList = analysis(str);
			Log.i("CommunicationTread",str);
			Message message = resultHandler.obtainMessage();
			Bundle bundle = new Bundle();
			bundle.putString(RESULT, SUCCESS_RESULT);
			bundle.putStringArrayList("arrayList", arrayList);
			message.setData(bundle);
			resultHandler.sendMessage(message);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private ArrayList<String> analysis(String str){
		ArrayList<String> arrayList = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(str, ",");
		int i = 0;
		while(st.hasMoreElements()){
			arrayList.add(st.nextToken());
		}	
		
		return arrayList;
	}
}
