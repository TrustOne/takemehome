/*
 * @DbServer.java 0.10 12/08/28
 *
 * Copyright (c) 2012 takemehome.
 * 경기도 고양시 덕양구 한국항공대.
 * All Rights Reserved.
 *
 * 이 소프트웨어는 ??? 어플리케이션이 동작하기 위한 서버를 제작합니다.
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
 * ??? 어플리케이션에서 서버와 통신하기 위해서 사용하는 쓰레드
 * 
 * @version 0.10 28 Aug 2012
 * @author 김 형규
 */
public class CommunicationThread extends Thread{
	/* 통신과 어플리케이션 자체의 동작의 개별성을 보장하기 위해서 Thread
	 * 생성시 어떤 클라이언트와 접속했는지 초기화한다.
	 * run()함수를 통해서 클라이언트의 요청을 처리한다. 
	 * 요청에 대한 처리가 끝났다면 disconnect함수를 통신을 종료한다.*/

	/**	자산비교요청시 사용하는 상수값 */
	final static public String REQUEST_COMPARING_ASSETS = "requestComparingAssets";
	//차후 정리
	/** 결과값 전송을 위한 Intent Extra 키 */
	final static public String CTRESULT = "ctresult";
	
	//성공 Intent Extra 값
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
	 * ?? 안드로이드 어플리케이션이 서버와 통신하는데 사용하는 통신 쓰레드의 생성자
	 * 자산비교를 위해 사용하며, 시작 퍼센트와 끝 퍼센트를 이용하여 해당 범위 내의 자산 분포에 대한 비율을 알려준다.
	 * 
	 * @param request 요청할 기능
	 *  - REQUEST_COMPARING_ASSETS : 자산비교에 대한 요청
	 * @param handler 	 : 요청한 기능을 수행한 후 답변을 받는 헨들러
	 * @param startPoint : 비교를 시작할 시작 위치의 퍼센트
	 * @param endPoint	 : 비교를 시작할 끝 위치의 퍼센트
	 * @param condition	 : 비교할 범위를 설정할 조건 
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
	 * ?? 안드로이드 어플리케이션이 서버와 통신하는데 사용하는 통신 쓰레드의 생성자
	 * 요청, context, 답변을 위한 handler를 초기화한다.
	 * 
	 * @param request 요청할 기능
	 *  - REQUEST_COMPARING_ASSETS : 자산비교에 대한 요청
	 *  - REQUEST_COMPARING_ASSETS : 자산비교에 대한 요청
	 *  - REQUEST_COMPARING_ASSETS : 자산비교에 대한 요청
	 * @param context
	 * @param handler 요청한 기능을 수행한 후 답변을 받는 헨들러
	 */
	public CommunicationThread(String request, Context context, Handler handler)
	{
		PORT = 20000;
		this.request = request;
		this.context = context;
		resultHandler = handler;
	}

	/**
	 * ?? 안드로이드 어플리케이션이 서버와 통신하는데 사용하는 통신 쓰레드의 실행부
	 * 요청(request)를 구분하여 각각의 기능을 실행한다.
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
				Log.i("CommunicationTread","act1 동작 전");
				act1();
				Log.i("CommunicationTread","act1 동작 후");

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
	//connect함수 종료
	
	public void disconnect()
	{
		System.out.println("접속 끓김");
		
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
	//disconnect 함수 종료
	
	/**
	 * requestComparingAssets는
	 * ???안드로이드 어플리케이션 서버에서 자산비교에 대한 요청을 하는 메소드
	 * 결과를 handler로 반환한다.
	 */
	private void requestComparingAssets(){
		Log.i("CommunicationTread","requestComparingAssets 서버에 요청");
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

			Log.i("CommunicationTread","주식    : " + answer.getDouble("regularSavings"));
			Log.i("CommunicationTread","부동산 : " + answer.getDouble("inheritance"));
			Log.i("CommunicationTread","기타    : " + answer.getDouble("useEct"));
			Log.i("CommunicationTread","체권    : " + answer.getDouble("debt"));
			Log.i("CommunicationTread","기타2 : " + answer.getDouble("investmentEct"));
			Log.i("CommunicationTread","기타2 : " + answer.getDouble("investmentEct"));

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
			Log.i("CommunicationTread","act1 동작 서버 전송");
			outputStream.writeUTF("act1");
			outputStream.flush();
			
			//db 열고
			Log.i("CommunicationTread","db 열고");

			Log.i("CommunicationTread","db 값가져오고");
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
			Log.i("CommunicationTread","db 값 보네고");

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
		Log.i("CommunicationTread","use1 동작 서버 전송");
		try {
			outputStream.writeUTF("use1");
			outputStream.flush();
			Log.i("CommunicationTread","use1 동작 서버 전송 후");

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
