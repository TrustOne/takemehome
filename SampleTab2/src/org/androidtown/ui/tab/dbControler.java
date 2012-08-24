package org.androidtown.ui.tab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class dbControler {
 
	
    //table 정의
	public static final String TABLE_MEMBER = "member";					//사용자 테이블 
    public static final String TABLE_PROPERTY  = "collectiveProperty"; 	//자산형태
    public static final String TABLE_CASH = "cashFlow"; 				//수입지출
    
    //entity 정의
    //table member entity
    public static final String ENTITY_ID = "id";						//사용자 id
    public static final String ENTITY_AGE = "age"; 						//나이
    public static final String ENTITY_RESIDENTIAL  = "residentialArea"; //사는 지역
    public static final String ENTITY_JAP = "jap"; 						//직업
    public static final String ENTITY_SEX = "sex";						//성별
	public static final String ENTITY_MARRIAGE = "marriage"; 			//결혼유무
    public static final String ENTITY_CHILD = "child"; 					//자식유무

    //table collectiveProperty entity
    public static final String ENTITY_REGULARSAVINGS = "regularSavings";//적금 총액
    public static final String ENTITY_INHERUTANCE = "inheritance"; 		//상속유산 총액
    public static final String ENTITY_USEECT = "useEct"; 				//사용wk산 총액
    public static final String ENTITY_DEBT= "debt"; 					//부채 총액
    public static final String ENTITY_INVESTMENTECT= "investmentEct"; 	//투자자산 총액

    //table cashFlow entity
    public static final String ENTITY_EARNEDINCOME = "earnedIncome";	//연봉
    public static final String ENTITY_INVARIABLEEXPENSE = "invariableExpense";	//고정 지출 
    public static final String ENTITY_VARIABLEEXPENSE = "variableExpense";		//변동 지출

    
    
    private DatabaseHelper dbHelper; 
    private SQLiteDatabase mdb;

    
    //db 테이블 생성 쿼리
    private static final String CREATE_TABLE_MEMBER = "create table " + TABLE_MEMBER + " ("
    													+ ENTITY_ID + " integer primary key autoincrement, "
    													+ ENTITY_AGE + " integer not null, "
														+ ENTITY_RESIDENTIAL + " text not null, "
														+ ENTITY_JAP + " text not null, "
														+ ENTITY_SEX +" text not null, " //CHECK(" + ENTITY_SEX +" IN(\"female\", \"male\")) not null,"
														+ ENTITY_MARRIAGE + " text not null, " // CHECK(" + ENTITY_MARRIAGE +" IN(\"yes\", \"no\")) not null,"
														+ ENTITY_CHILD + " text not null default 0, "
														+ " cp integer , "
														+ " cf integer "
														+");"; 
    private static final String CREATE_TABLE_CP = "create table " + TABLE_PROPERTY + "("
    		+ "cp_id integer primary key autoincrement, "
		    + ENTITY_REGULARSAVINGS + " integer not null default 0, "
		    + ENTITY_INHERUTANCE + " integer not null default 0, "
		    + ENTITY_USEECT  + " integer not null default 0, "
		    + ENTITY_DEBT + " integer not null default 0, "
		    + ENTITY_INVESTMENTECT + " integer not null default 0 "
			+");"; 
    private static final String CREATE_TABLE_CF = "create table " + TABLE_CASH + "("
    		+ "cf_id integer primary key autoincrement, "
		    + ENTITY_EARNEDINCOME + " integer not null default 0, "
		    + ENTITY_INVARIABLEEXPENSE + " integer not null default 0, "
		    + ENTITY_VARIABLEEXPENSE + " integer not null default 0 "
			+");"; 
    
    //외래키를 사용하기 위한 트리거
    private static final String CREATE_TRIGGER1 = "create trigger fk_delete_cp"
    		+" before delete on "
    		+ TABLE_PROPERTY
    		+ " for each row begin delete from "
    		+ TABLE_MEMBER
    		+" where "
    		+ TABLE_PROPERTY
    		+".cp_id = "
    		+ TABLE_MEMBER
    		+".cp; end;";
    
    private static final String CREATE_TRIGGER2 = "create trigger fk_delete_cf"
    		+" before delete on "
    		+ TABLE_CASH
    		+ " for each row begin delete from "
    		+ TABLE_MEMBER
    		+" where "
    		+ TABLE_CASH
    		+".cf_id = "
    		+ TABLE_MEMBER
    		+".cf; end;";
    //데이터 베이스 네임 설정
    private static final String DATABASE_NAME = "data2"; 
    private static final int DATABASE_VERSION = 2; 


    private final Context mCtx; 

   
     private static class DatabaseHelper extends SQLiteOpenHelper {
    	 //db 생성됨
        DatabaseHelper(Context context) { 
            super(context, DATABASE_NAME, null, DATABASE_VERSION); 
        } 
 
        //클래스 생성하면 디비테이블을 생성함
        @Override 
        public void onCreate(SQLiteDatabase db) { 
        	Log.i("dbControler","헬퍼 생성");
        	db.execSQL(CREATE_TABLE_MEMBER); 
        	Log.i("dbControler","멤버 테이블 생성");
            db.execSQL(CREATE_TABLE_CP); 
        	Log.i("dbControler","자산 총액 테이블 생성");
            db.execSQL(CREATE_TABLE_CF); 
        	Log.i("dbControler","지출입 테이블 생성");
            db.execSQL(CREATE_TRIGGER1); 
        	Log.i("dbControler","트리거 1 생성");
            db.execSQL(CREATE_TRIGGER2); 
        	Log.i("dbControler","트리커 2 생성");
        } 


        //db 전체를 갱신할 때 사용
        @Override 
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
            db.execSQL("DROP TABLE IF EXISTS" + TABLE_MEMBER); 
            db.execSQL("DROP TABLE IF EXISTS" + TABLE_CASH); 
            db.execSQL("DROP TABLE IF EXISTS" + TABLE_PROPERTY); 
            db.execSQL("DROP TRIGGER IF EXISTS trigger fk_delete_cf"); 
            db.execSQL("DROP TRIGGER IF EXISTS trigger fk_delete_cp"); 
            onCreate(db); 
        } 
    } 

   
 
     public dbControler(Context ctx) { 
        this.mCtx = ctx; 
    } 

    public dbControler open() throws SQLException { 
        dbHelper = new DatabaseHelper(mCtx); 
        mdb = dbHelper.getWritableDatabase();
        return this; 
    } 

   
    public void close() { 
        dbHelper.close(); 
    } 

   
 
    //사용자 테이블에 인스턴스 추가
    public long createNoteMember(int age, String residentialArea, String jap,
    		String sex, String marriage, String child,
    		int cp, int cf) { 
        ContentValues initialValues = new ContentValues();
        
        initialValues.put(ENTITY_AGE,age);
        initialValues.put(ENTITY_RESIDENTIAL,residentialArea);
        initialValues.put(ENTITY_JAP,jap);
        initialValues.put(ENTITY_SEX,sex);
        initialValues.put(ENTITY_MARRIAGE,marriage);
        initialValues.put(ENTITY_CHILD,child);
        initialValues.put("cp",cp);
        initialValues.put("cf",cf);
        
        return mdb.insert(TABLE_MEMBER, null, initialValues); 
    } 

    //자산형태 테이블에 인스턴스 추가
    public long createNoteCP(int regularSavings ,int inheritance,
    		int useEct ,int debt, int investmentEct) { 
        ContentValues initialValues = new ContentValues(); 
        initialValues.put(ENTITY_REGULARSAVINGS,regularSavings);
        initialValues.put(ENTITY_INHERUTANCE, inheritance);
        initialValues.put(ENTITY_USEECT,useEct);
        initialValues.put(ENTITY_DEBT,debt);
        initialValues.put(ENTITY_INVESTMENTECT,investmentEct);

        return mdb.insert(TABLE_PROPERTY, null, initialValues); 
    } 

    //수입지출 테이블에 인스턴스 추가
    public long createNoteCF(int earnedIncome ,int invariableExpense,
    		int variableExpense) { 
        ContentValues initialValues = new ContentValues(); 
        initialValues.put(ENTITY_EARNEDINCOME,earnedIncome);
        initialValues.put(ENTITY_INVARIABLEEXPENSE, invariableExpense);
        initialValues.put(ENTITY_VARIABLEEXPENSE, variableExpense);

        return mdb.insert(TABLE_CASH, null, initialValues); 
    } 

    
    
   
    public boolean deleteNoteMember(Long Id) { 
        return mdb.delete(TABLE_MEMBER, ENTITY_ID + "=" + Id, null) > 0; 
    } 

    public boolean deleteNoteCP(Long Id) { 
        return mdb.delete(TABLE_PROPERTY, "cp_id =" + Id, null) > 0; 
    } 

    public boolean deleteNoteCF(Long Id) { 
        return mdb.delete(TABLE_CASH, "cf_id =" + Id, null) > 0; 
    } 

    //전체 출력
     public Cursor fetchAllNotes() { 
    	 Log.i("asdf","쿼리 구문이 맞는지 틀리는지..;");
        return mdb.rawQuery(
        		"select * from (member join collectiveProperty on member.cp=collectiveProperty.cp_id) "
        		,null); 
    } 

   /*
    public Cursor fetchNote(Long Id) throws SQLException { 
        Cursor mCursor =  mdb.query(true, TABLE_MEMBER, new String[] {
        		ENTITY_ID
                ,ENTITY_AGE
                ,ENTITY_RESIDENTIAL
                ,ENTITY_JAP
                ,ENTITY_SEX
                ,ENTITY_MARRIAGE
                ,ENTITY_CHILD
                ,ENTITY_REGULARSAVINGS
                ,ENTITY_INHERUTANCE
                ,ENTITY_USEECT
                ,ENTITY_DEBT
                ,ENTITY_INVESTMENTECT
                ,ENTITY_EARNEDINCOME
                ,ENTITY_INVARIABLEEXPENSE
                ,ENTITY_VARIABLEEXPENSE}, ENTITY_ID + "=" + Id, null, null, null, null, null); 
        if (mCursor != null) { 
            mCursor.moveToFirst(); 
        } 
        return mCursor; 
    } 
*/
  
    
    //사용자 테이블에 인스턴스 추가
    public boolean updateNoteMember(Long Id, Integer age, String residentialArea, String jap,
    		String sex, String marriage, String child,
    		Integer cp, Integer cf) { 
        ContentValues initialValues = new ContentValues(); 
        initialValues.put(ENTITY_AGE,age);
        initialValues.put(ENTITY_RESIDENTIAL,residentialArea);
        initialValues.put(ENTITY_JAP,jap);
        initialValues.put(ENTITY_SEX,sex);
        initialValues.put(ENTITY_MARRIAGE,marriage);
        initialValues.put(ENTITY_CHILD,child);
        initialValues.put("cp",cp);
        initialValues.put("cf",cf);
        
        return mdb.update(TABLE_MEMBER, initialValues, ENTITY_ID + "=" + Id, null) > 0; 
    } 

    //자산형태 테이블에 인스턴스 추가
    public boolean updateNoteCP(Long cp_Id, Integer regularSavings ,Integer inheritance,
    		Integer useEct ,Integer debt, Integer investmentEct,
    		Integer earnedIncome ,Integer invariableExpense,
    		Integer variableExpense) { 
        ContentValues initialValues = new ContentValues(); 
        initialValues.put(ENTITY_REGULARSAVINGS,regularSavings);
        initialValues.put(ENTITY_INHERUTANCE, inheritance);
        initialValues.put(ENTITY_USEECT,useEct);
        initialValues.put(ENTITY_DEBT,debt);
        initialValues.put(ENTITY_INVESTMENTECT,investmentEct);

        return mdb.update(TABLE_PROPERTY, initialValues, "cp_id =" + cp_Id, null) > 0; 
    } 

    //수입지출 테이블에 인스턴스 추가
    public boolean updateNoteCF(Long cf_Id, Integer earnedIncome ,Integer invariableExpense,
    		Integer variableExpense) { 
        ContentValues initialValues = new ContentValues(); 
        initialValues.put(ENTITY_EARNEDINCOME,earnedIncome);
        initialValues.put(ENTITY_INVARIABLEEXPENSE, invariableExpense);
        initialValues.put(ENTITY_VARIABLEEXPENSE, variableExpense);

        return mdb.update(TABLE_CASH, initialValues, "cf_id =" + cf_Id, null) > 0; 
    } 
} 
