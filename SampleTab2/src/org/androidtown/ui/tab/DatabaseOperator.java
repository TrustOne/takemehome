package org.androidtown.ui.tab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseOperator {
 /*
	
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

   
 
     public DatabaseOperator(Context ctx) { 
        this.mCtx = ctx; 
    } 

    public DatabaseOperator open() throws SQLException { 
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
        return mdb.rawQuery(
        		"select * from (member join collectiveProperty on member.cp=collectiveProperty.cp_id) "
        		,null); 
    } 


     //멤버 출력
     public Cursor fetchMemberNotes() { 
        return mdb.rawQuery(
        		"select * from member "
        		,null); 
    } 
 

     //멤버 출력
     public Cursor fetchCPNotes() { 
        return mdb.rawQuery(
        		"select * from collectiveProperty "
        		,null); 
    } 

     //멤버 출력
     public Cursor fetchCFNotes() { 
        return mdb.rawQuery(
        		"select * from cashFlow "
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
    */
	
	
    //table 정의에 사용될 상수
	public static final String TABLE_MEMBER_INFORMATION 	 	= "memberInformation";		//사용자 테이블 
    public static final String TABLE_COLLECTIVE_INFORMATION  	= "collectiveInformation"; 	//자산형태
    public static final String TABLE_DEBT_INFORMATION		 	= "debtInformation"; 	//자산형태
    public static final String TABLE_INCOME_EXPENSE_INFORMATION = "incomeExpenseInformation"; 	//자산형태
    public static final String TABLE_ECT_INFORMATION 			= "ectInformation"; 	//자산형태

    //entity 정의에 사용될 상수
    //TABLE_MEMBER_INFORMATION
    public static final String ENTITY_MEMBER_INFORMATION_ID 		 = "member_id";						//사용자 id
    public static final String ENTITY_MEMBER_INFORMATION_AGE 		 = "member_age"; 						//나이
    public static final String ENTITY_MEMBER_INFORMATION_RESIDENTIAL = "member_residentialArea"; //사는 지역
    public static final String ENTITY_MEMBER_INFORMATION_JAP 		 = "member_jap"; 						//직업
    public static final String ENTITY_MEMBER_INFORMATION_SEX 		 = "member_sex";						//성별
	public static final String ENTITY_MEMBER_INFORMATION_MARRIAGE 	 = "member_marriage"; 			//결혼유무
    public static final String ENTITY_MEMBER_INFORMATION_CHILD 		 = "member_child"; 					//자식유무

    //TABLE_COLLECTIVE_INFORMATION  
    public static final String ENTITY_COLLECTIVE_INFORMATION_INDEX  = "cllectiveInformationIndex";
    public static final String ENTITY_COLLECTIVE_INFORMATION_VALUES = "cllectiveInformationValues";

    //TABLE_DEBT_INFORMATION
    public static final String ENTITY_DEBT_INFORMATION_INDEX  = "debtInformationIndex";
    public static final String ENTITY_DEBT_INFORMATION_VALUES = "debtInformationValues";

    //TABLE_INCOME_EXPENSE_INFORMATION
    public static final String ENTITY_INCOME_EXPENSE_INFORMATION_INDEX  = "incomeExpenseInformationIndex";
    public static final String ENTITY_INCOME_EXPENSE_INFORMATION_VALUES = "incomeExpenseInformationValues";

    //TABLE_ECT_INFORMATION
    public static final String ENTITY_ECT_INFORMATION_INDEX  = "ectInformationIndex";
    public static final String ENTITY_ECT_INFORMATION_VALUES = "ectInformationValues";

    //테이블 생성 쿼리
    //
    private static final String CREATE_TABLE_MEMBER_INFORMATION = "create table " + TABLE_MEMBER_INFORMATION + " ("
			+ ENTITY_MEMBER_INFORMATION_ID 			+ " integer primary key autoincrement, "
			+ ENTITY_MEMBER_INFORMATION_AGE 		+ " integer, "
			+ ENTITY_MEMBER_INFORMATION_RESIDENTIAL + " text, "
			+ ENTITY_MEMBER_INFORMATION_JAP 		+ " text, "
			+ ENTITY_MEMBER_INFORMATION_SEX 		+ " text, " //CHECK(" + ENTITY_SEX +" IN(\"female\", \"male\")) not null,"
			+ ENTITY_MEMBER_INFORMATION_MARRIAGE 	+ " text, " // CHECK(" + ENTITY_MARRIAGE +" IN(\"yes\", \"no\")) not null,"
			+ ENTITY_MEMBER_INFORMATION_CHILD 		+ " integer);";
    private static final String CREATE_TABLE_COLLECTIVE_INFORMATION = "create table " + TABLE_COLLECTIVE_INFORMATION + " ("
			+ ENTITY_COLLECTIVE_INFORMATION_INDEX + " String primary key, "
			+ ENTITY_COLLECTIVE_INFORMATION_VALUES + " double );";
    private static final String CREATE_TABLE_DEBT_INFORMATION = "create table " + TABLE_DEBT_INFORMATION + " ("
			+ ENTITY_DEBT_INFORMATION_INDEX + " String primary key, "
			+ ENTITY_DEBT_INFORMATION_VALUES + " double );";
    private static final String CREATE_TABLE_INCOME_EXPENSE_INFORMATION = "create table " + TABLE_INCOME_EXPENSE_INFORMATION + " ("
			+ ENTITY_INCOME_EXPENSE_INFORMATION_INDEX + " String primary key, "
			+ ENTITY_INCOME_EXPENSE_INFORMATION_VALUES + " double );";
    private static final String CREATE_TABLE_ECT_INFORMATION = "create table " + TABLE_ECT_INFORMATION + " ("
			+ ENTITY_ECT_INFORMATION_INDEX + " String primary key, "
			+ ENTITY_ECT_INFORMATION_VALUES + " double );";
    
    
    //데이터 베이스 네임 설정
    private static final String DATABASE_NAME = "data3"; 
    private static final int DATABASE_VERSION = 2; 

    private final Context myContext; 
    private DatabaseHelper dbHelper; 
    private SQLiteDatabase myDb;

    private static class DatabaseHelper extends SQLiteOpenHelper {
   	 //db 생성됨
       DatabaseHelper(Context context) { 
           super(context, DATABASE_NAME, null, DATABASE_VERSION); 
       } 

       //클래스 생성하면 디비테이블을 생성함
       @Override 
       public void onCreate(SQLiteDatabase db) { 
	       	db.execSQL(CREATE_TABLE_MEMBER_INFORMATION); 
	        db.execSQL(CREATE_TABLE_COLLECTIVE_INFORMATION); 
	       	db.execSQL(CREATE_TABLE_DEBT_INFORMATION); 
	       	db.execSQL(CREATE_TABLE_INCOME_EXPENSE_INFORMATION); 
	        db.execSQL(CREATE_TABLE_ECT_INFORMATION); 
       } 

       //db 전체를 갱신할 때 사용
       @Override 
       public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
           db.execSQL("DROP TABLE IF EXISTS" + TABLE_MEMBER_INFORMATION); 
           db.execSQL("DROP TABLE IF EXISTS" + TABLE_COLLECTIVE_INFORMATION); 
           db.execSQL("DROP TABLE IF EXISTS" + TABLE_DEBT_INFORMATION); 
           db.execSQL("DROP TABLE IF EXISTS" + TABLE_INCOME_EXPENSE_INFORMATION); 
           db.execSQL("DROP TABLE IF EXISTS" + TABLE_ECT_INFORMATION); 
           onCreate(db); 
       } 
   } 

    public DatabaseOperator(Context ctx) { 
        this.myContext = ctx; 
    } 

    public DatabaseOperator open() throws SQLException { 
        dbHelper = new DatabaseHelper(myContext); 
        myDb = dbHelper.getWritableDatabase();
        return this; 
    } 

    public void close() { 
        dbHelper.close(); 
    } 

    public boolean insertNoteMemberInformation(int age, String residentialArea, String jap,
    		String sex, String marriage, int child) { 
        ContentValues initialValues = new ContentValues();
        
        int i = fetchNoteMemberInformation().getCount();
        if(i == 0) {
            initialValues.put(ENTITY_MEMBER_INFORMATION_AGE,age);
            initialValues.put(ENTITY_MEMBER_INFORMATION_RESIDENTIAL,residentialArea);
            initialValues.put(ENTITY_MEMBER_INFORMATION_JAP,jap);
            initialValues.put(ENTITY_MEMBER_INFORMATION_SEX,sex);
            initialValues.put(ENTITY_MEMBER_INFORMATION_MARRIAGE,marriage);
            initialValues.put(ENTITY_MEMBER_INFORMATION_CHILD,child);
            myDb.insert(TABLE_MEMBER_INFORMATION, null, initialValues); 
            return true; 
        }
        else if (i == 1){
        	if (age != 0) {
        		initialValues.put(ENTITY_MEMBER_INFORMATION_AGE,age);
        	}
        	if (!(residentialArea.equals(""))) {
        		initialValues.put(ENTITY_MEMBER_INFORMATION_RESIDENTIAL,residentialArea);
        	}
        	if (!(jap.equals(""))) {
        		initialValues.put(ENTITY_MEMBER_INFORMATION_JAP,jap);
        	}
        	if (!(sex.equals(""))) {
        		initialValues.put(ENTITY_MEMBER_INFORMATION_SEX,sex);
        	}
        	if (!(marriage.equals(""))) {
                initialValues.put(ENTITY_MEMBER_INFORMATION_MARRIAGE,marriage);
        	}
        	if (child != 0) {
                initialValues.put(ENTITY_MEMBER_INFORMATION_CHILD,child);
        	}
        	myDb.update(TABLE_MEMBER_INFORMATION, initialValues, ENTITY_MEMBER_INFORMATION_ID + "= 1", null);
            return false;
        }
        else{
            return false;
        }
    }
    

    
    //자산형태 테이블에 인스턴스 추가
    /** true값이면 신규 입력
     *  flase값이면 갱신
     */
    public boolean insertNoteCollectiveInformation(String index ,double values) { 
        ContentValues initialValues = new ContentValues(); 
        initialValues.put(ENTITY_COLLECTIVE_INFORMATION_INDEX, index);
        initialValues.put(ENTITY_COLLECTIVE_INFORMATION_VALUES, values);

        int i = fetchNoteCollectiveInformation(index).getCount();
        if(i == 0) {
        	myDb.insert(TABLE_COLLECTIVE_INFORMATION, null, initialValues);
            return true; 
        }
        else if (i == 1){
        	myDb.update(TABLE_COLLECTIVE_INFORMATION, initialValues, ENTITY_COLLECTIVE_INFORMATION_INDEX + "= \"" + index+"\"", null);
            return false;
        }
        else{
            return false;
        }
	}
    
    //수입지출 테이블에 인스턴스 추가
    public boolean insertNoteDebtInformation(String index ,double values) { 
        ContentValues initialValues = new ContentValues(); 
        initialValues.put(ENTITY_DEBT_INFORMATION_INDEX, index);
        initialValues.put(ENTITY_DEBT_INFORMATION_VALUES, values);

        
        int i = fetchNoteDebtInformation(index).getCount();
        if(i == 0) {
        	myDb.insert(TABLE_DEBT_INFORMATION, null, initialValues);
            return true; 
        }
        else if (i == 1){
        	myDb.update(TABLE_DEBT_INFORMATION, initialValues, ENTITY_DEBT_INFORMATION_INDEX + "= \"" + index+"\"", null);
            return false;
        }
        else{
            return false;
        }
    } 

    public boolean insertNoteIncomeExpenseInformation(String index ,double values) { 
        ContentValues initialValues = new ContentValues(); 
        initialValues.put(ENTITY_INCOME_EXPENSE_INFORMATION_INDEX, index);
        initialValues.put(ENTITY_INCOME_EXPENSE_INFORMATION_VALUES, values);

        int i = fetchNoteIncomeExpenseInformation(index).getCount();
        if(i == 0) {
        	myDb.insert(TABLE_INCOME_EXPENSE_INFORMATION, null, initialValues);
            return true; 
        }
        else if (i == 1){
        	myDb.update(TABLE_INCOME_EXPENSE_INFORMATION, initialValues, ENTITY_INCOME_EXPENSE_INFORMATION_INDEX + "= \"" + index+"\"", null);
            return false;
        }
        else{
            return false;
        } 
    } 

    public boolean insertNoteEctInformation(String index ,double values) { 
        ContentValues initialValues = new ContentValues(); 
        initialValues.put(ENTITY_ECT_INFORMATION_INDEX, index);
        initialValues.put(ENTITY_ECT_INFORMATION_VALUES, values);

        int i = fetchNoteEctInformation(index).getCount();
        if(i == 0) {
        	myDb.insert(TABLE_ECT_INFORMATION, null, initialValues);
            return true; 
        }
        else if (i == 1){
        	myDb.update(TABLE_ECT_INFORMATION, initialValues, ENTITY_ECT_INFORMATION_INDEX + "= \"" + index+"\"", null);
            return false;
        }
        else{
            return false;
        }
    }
    
    public Cursor fetchNoteMemberInformation() throws SQLException { 

        Cursor mCursor =  myDb.query(true, TABLE_MEMBER_INFORMATION, 
        							 new String[] {ENTITY_MEMBER_INFORMATION_ID, ENTITY_MEMBER_INFORMATION_AGE, 
        											ENTITY_MEMBER_INFORMATION_RESIDENTIAL, ENTITY_MEMBER_INFORMATION_JAP, 
        											ENTITY_MEMBER_INFORMATION_SEX, ENTITY_MEMBER_INFORMATION_MARRIAGE, 
        											ENTITY_MEMBER_INFORMATION_CHILD},
        											ENTITY_MEMBER_INFORMATION_ID + "= 1", null, null, null, null, null);
        if (mCursor != null) { 
            mCursor.moveToFirst(); 
        } 
        return mCursor; 
    } 
    
    public Cursor fetchNoteCollectiveInformation(String index) throws SQLException { 

        Cursor mCursor =  myDb.query(true, TABLE_COLLECTIVE_INFORMATION, 
        							 new String[] {ENTITY_COLLECTIVE_INFORMATION_INDEX, ENTITY_COLLECTIVE_INFORMATION_VALUES},
        							 ENTITY_COLLECTIVE_INFORMATION_INDEX + "= \"" + index + "\"", null, null, null, null, null);
        if (mCursor != null) { 
            mCursor.moveToFirst(); 
        } 
        return mCursor; 
    } 
    
    public Cursor fetchNoteDebtInformation(String index) throws SQLException { 

        Cursor mCursor =  myDb.query(true, TABLE_DEBT_INFORMATION, 
        							 new String[] {ENTITY_DEBT_INFORMATION_INDEX, ENTITY_DEBT_INFORMATION_VALUES},
        							 ENTITY_DEBT_INFORMATION_INDEX + "= \"" + index + "\"", null, null, null, null, null);
        if (mCursor != null) { 
            mCursor.moveToFirst(); 
        } 
        return mCursor; 
    } 

    public Cursor fetchNoteIncomeExpenseInformation(String index) throws SQLException { 

        Cursor mCursor =  myDb.query(true, TABLE_INCOME_EXPENSE_INFORMATION, 
        							 new String[] {ENTITY_INCOME_EXPENSE_INFORMATION_INDEX, ENTITY_INCOME_EXPENSE_INFORMATION_VALUES},
        							 ENTITY_INCOME_EXPENSE_INFORMATION_INDEX + "= \"" + index + "\"", null, null, null, null, null);
        if (mCursor != null) { 
            mCursor.moveToFirst(); 
        } 
        return mCursor; 
    } 
    
    public Cursor fetchNoteEctInformation(String index) throws SQLException { 

        Cursor mCursor =  myDb.query(true, TABLE_ECT_INFORMATION, 
        							 new String[] {ENTITY_ECT_INFORMATION_INDEX, ENTITY_ECT_INFORMATION_VALUES},
        							 ENTITY_ECT_INFORMATION_INDEX + "= \"" + index + "\"", null, null, null, null, null);
        if (mCursor != null) { 
            mCursor.moveToFirst(); 
        } 
        return mCursor; 
    } 
    
    
    public void insertDB(String index, double values){
    	if (index.equals("금융자산 예금")) {
    		this.insertNoteCollectiveInformation("금융자산 예금", values);
    	}
    	else if (index.equals("금융자산 펀드")) {
    		this.insertNoteCollectiveInformation("금융자산 펀드", values);
    	}
    	else if (index.equals("금융자산 보험")) {
    		this.insertNoteCollectiveInformation("금융자산 보험", values);
    	}
    	else if (index.equals("금융자산 채권")) {
    		this.insertNoteCollectiveInformation("금융자산 채권", values);
    	}
    	else if (index.equals("금융자산 주식")) {
    		this.insertNoteCollectiveInformation("금융자산 주식", values);
    	}
    	else if (index.equals("기타자산 주택")) {
    		this.insertNoteCollectiveInformation("기타자산 주택", values);
    	}
    	else if (index.equals("기타자산 차량")) {
    		this.insertNoteCollectiveInformation("기타자산 차량", values);
    	}
    	else if (index.equals("기타자산 대여금")) {
    		this.insertNoteCollectiveInformation("기타자산 대여금", values);
    	}
    	else if (index.equals("부채 주택담보대출")) {
    		this.insertNoteDebtInformation("부채 주택담보대출", values);
    	}
    	else if (index.equals("부채 자동차할부잔액")) {
    		this.insertNoteDebtInformation("부채 자동차할부잔액", values);
    	}
    	else if (index.equals("부채 마이너스신용대출")) {
    		this.insertNoteDebtInformation("부채 마이너스신용대출", values);
    	}
    	else if (index.equals("부채 신용카드미지급금")) {
    		this.insertNoteDebtInformation("부채 신용카드미지급금", values);
    	}
    	else if (index.equals("지출입 연봉")) {
    		this.insertNoteIncomeExpenseInformation("지출입 연봉", values);
    	}
    	else if (index.equals("지출입 생활비")) {
    		this.insertNoteIncomeExpenseInformation("지출입 생활비", values);
    	}
    	else if (index.equals("지출입 저축액")) {
    		this.insertNoteIncomeExpenseInformation("지출입 저축액", values);
    	}
    	else if (index.equals("지출입 교육비")) {
    		this.insertNoteIncomeExpenseInformation("지출입 교육비", values);
    	}
    	else if (index.equals("지출입 개인대출상환액")) {
    		this.insertNoteIncomeExpenseInformation("지출입 개인대출상환액", values);
    	}
    	else if (index.equals("지출입 주택대출상환액")) {
    		this.insertNoteIncomeExpenseInformation("지출입 주택대출상환액", values);
    	}
    	else if (index.equals("기타 모기지이율")) {
    		this.insertNoteEctInformation("기타 모기지이율", values);
    	}
    	else if (index.equals("기타 72법칙기대수익률")) {
    		this.insertNoteEctInformation("기타 72법칙기대수익률", values);
    	}
    	else if (index.equals("기타 단기목표필요자금")) {
    		this.insertNoteEctInformation("기타 단기목표필요자금", values);
    	}
    	else if (index.equals("기타 단기목표연도")) {
    		this.insertNoteEctInformation("기타 단기목표연도", values);
    	}
    	else if (index.equals("기타 단기목표준비자금")) {
    		this.insertNoteEctInformation("기타 단기목표준비자금", values);
    	}
    	else if (index.equals("기타 생애가치법급여상승률")) {
    		this.insertNoteEctInformation("기타 생애가치법급여상승률", values);
    	}
    	else if (index.equals("기타 은퇴예상시점")) {
    		this.insertNoteEctInformation("기타 은퇴예상시점", values);
    	}
    	else if (index.equals("기타 니즈분석법니즈금액")) {
    		this.insertNoteEctInformation("기타 니즈분석법니즈금액", values);
    	}
    	else{}
    }

    public void insertDB(String index, String values){
    	if (index.equals("사용자 거주지역")) {
    		this.insertNoteMemberInformation(0, values, "", "", "", 0);
    	}
    	else if (index.equals("사용자 직업")) {
    		this.insertNoteMemberInformation(0, "", values, "", "", 0);
    	}
    	else if (index.equals("사용자 성별")) {
    		this.insertNoteMemberInformation(0, "", "", values, "", 0);
    	}
    	else if (index.equals("사용자 결혼유무")) {
    		this.insertNoteMemberInformation(0, "", "", "", values, 0);
    	}
    	else {}
    }

    public void insertDB(String index, int values){
    	if (index.equals("사용자 나이")) {
    		this.insertNoteMemberInformation(values, "", "", "", "", 0);
    	}
    	else if (index.equals("사용자 자녀수")) {
    		this.insertNoteMemberInformation(0, "", "", "", "", values);
    	}
    	else {}
    }
    
    public Object getDB(String index){
    	Cursor cursor = null;
    	if (index.equals("금융자산 예금")) {
    		cursor = this.fetchNoteCollectiveInformation("금융자산 예금");
    	}
    	else if (index.equals("금융자산 펀드")) {
    		cursor = this.fetchNoteCollectiveInformation("금융자산 펀드");
    	}
    	else if (index.equals("금융자산 보험")) {
    		cursor = this.fetchNoteCollectiveInformation("금융자산 보험");
    	}
    	else if (index.equals("금융자산 채권")) {
    		cursor = this.fetchNoteCollectiveInformation("금융자산 채권");
    	}
    	else if (index.equals("금융자산 주식")) {
    		cursor = this.fetchNoteCollectiveInformation("금융자산 주식");
    	}
    	else if (index.equals("기타자산 주택")) {
    		cursor = this.fetchNoteCollectiveInformation("기타자산 주택");
    	}
    	else if (index.equals("기타자산 차량")) {
    		cursor = this.fetchNoteCollectiveInformation("기타자산 차량");
    	}
    	else if (index.equals("기타자산 대여금")) {
    		cursor = this.fetchNoteCollectiveInformation("기타자산 대여금");
    	}
    	else if (index.equals("부채 주택담보대출")) {
    		cursor = this.fetchNoteDebtInformation("부채 주택담보대출");
    	}
    	else if (index.equals("부채 자동차할부잔액")) {
    		cursor = this.fetchNoteDebtInformation("부채 자동차할부잔액");
    	}
    	else if (index.equals("부채 마이너스신용대출")) {
    		cursor = this.fetchNoteDebtInformation("부채 마이너스신용대출");
    	}
    	else if (index.equals("부채 신용카드미지급금")) {
    		cursor = this.fetchNoteDebtInformation("부채 신용카드미지급금");
    	}
    	else if (index.equals("지출입 연봉")) {
    		cursor = this.fetchNoteIncomeExpenseInformation("지출입 연봉");
    	}
    	else if (index.equals("지출입 생활비")) {
    		cursor = this.fetchNoteIncomeExpenseInformation("지출입 생활비");
    	}
    	else if (index.equals("지출입 저축액")) {
    		cursor = this.fetchNoteIncomeExpenseInformation("지출입 저축액");
    	}
    	else if (index.equals("지출입 교육비")) {
    		cursor = this.fetchNoteIncomeExpenseInformation("지출입 교육비");
    	}
    	else if (index.equals("지출입 개인대출상환액")) {
    		cursor = this.fetchNoteIncomeExpenseInformation("지출입 개인대출상환액");
    	}
    	else if (index.equals("지출입 주택대출상환액")) {
    		cursor = this.fetchNoteIncomeExpenseInformation("지출입 주택대출상환액");
    	}
    	else if (index.equals("기타 모기지이율")) {
    		cursor = this.fetchNoteEctInformation("기타 모기지이율");
    	}
    	else if (index.equals("기타 72법칙기대수익률")) {
    		cursor = this.fetchNoteEctInformation("기타 72법칙기대수익률");
    	}
    	else if (index.equals("기타 단기목표필요자금")) {
    		cursor = this.fetchNoteEctInformation("기타 단기목표필요자금");
    	}
    	else if (index.equals("기타 단기목표연도")) {
    		cursor = this.fetchNoteEctInformation("기타 단기목표연도");
    	}
    	else if (index.equals("기타 단기목표준비자금")) {
    		cursor = this.fetchNoteEctInformation("기타 단기목표준비자금");
    	}
    	else if (index.equals("기타 생애가치법급여상승률")) {
    		cursor = this.fetchNoteEctInformation("기타 생애가치법급여상승률");
    	}
    	else if (index.equals("기타 은퇴예상시점")) {
    		cursor = this.fetchNoteEctInformation("기타 은퇴예상시점");
    	}
    	else if (index.equals("기타 니즈분석법니즈금액")) {
    		cursor = this.fetchNoteEctInformation("기타 니즈분석법니즈금액");
    	}
    	else if (index.equals("사용자 거주지역")) {
    		cursor = this.fetchNoteMemberInformation();
        	if(cursor.getCount() != 0){
        		return cursor.getString(2);
        	}
        	else {
        		return "";
        	}
    	}
    	else if (index.equals("사용자 직업")) {
    		cursor = this.fetchNoteMemberInformation();
        	if(cursor.getCount() != 0){
        		return cursor.getString(3);
        	}
        	else {
        		return "";
        	}
    	}
    	else if (index.equals("사용자 성별")) {
    		cursor = this.fetchNoteMemberInformation();
        	if(cursor.getCount() != 0){
        		return cursor.getString(4);
        	}
        	else {
        		return "";
        	}
    	}
    	else if (index.equals("사용자 결혼유무")) {
    		cursor = this.fetchNoteMemberInformation();
        	if(cursor.getCount() != 0){
        		return cursor.getString(5);
        	}
        	else {
        		return "";
        	}
    	}
    	else if (index.equals("사용자 나이")) {
    		cursor = this.fetchNoteMemberInformation();
        	if(cursor.getCount() != 0){
        		return cursor.getInt(1);
        	}
        	else {
        		Integer returnValue = new Integer(0);
        		return returnValue;
        	}
    	}
    	else if (index.equals("사용자 자녀수")) {
    		cursor = this.fetchNoteMemberInformation();
        	if(cursor.getCount() != 0){
        		return cursor.getInt(6);
        	}
        	else {
        		Integer returnValue = new Integer(0);
        		return returnValue;
        	}
    	}
    	else {
    		return "해당 index 항목이 없습니다.";
    	}

    	Log.i("dffdsadfas","dfpfdjpdafsjpdpfjspfjdsapj");
    	Log.i("DSdsafdaffdsafdsdfs",""+cursor.getCount());
    	if (cursor.getCount() == 0) {
    		Double returnValue = new Double(0);
    		return returnValue;
    	} else {
       		return cursor.getDouble(1);
    	}
    }
}