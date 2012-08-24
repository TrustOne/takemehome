package org.androidtown.ui.tab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class dbControler {
 
	
    //table ����
	public static final String TABLE_MEMBER = "member";					//����� ���̺� 
    public static final String TABLE_PROPERTY  = "collectiveProperty"; 	//�ڻ�����
    public static final String TABLE_CASH = "cashFlow"; 				//��������
    
    //entity ����
    //table member entity
    public static final String ENTITY_ID = "id";						//����� id
    public static final String ENTITY_AGE = "age"; 						//����
    public static final String ENTITY_RESIDENTIAL  = "residentialArea"; //��� ����
    public static final String ENTITY_JAP = "jap"; 						//����
    public static final String ENTITY_SEX = "sex";						//����
	public static final String ENTITY_MARRIAGE = "marriage"; 			//��ȥ����
    public static final String ENTITY_CHILD = "child"; 					//�ڽ�����

    //table collectiveProperty entity
    public static final String ENTITY_REGULARSAVINGS = "regularSavings";//���� �Ѿ�
    public static final String ENTITY_INHERUTANCE = "inheritance"; 		//������� �Ѿ�
    public static final String ENTITY_USEECT = "useEct"; 				//���wk�� �Ѿ�
    public static final String ENTITY_DEBT= "debt"; 					//��ä �Ѿ�
    public static final String ENTITY_INVESTMENTECT= "investmentEct"; 	//�����ڻ� �Ѿ�

    //table cashFlow entity
    public static final String ENTITY_EARNEDINCOME = "earnedIncome";	//����
    public static final String ENTITY_INVARIABLEEXPENSE = "invariableExpense";	//���� ���� 
    public static final String ENTITY_VARIABLEEXPENSE = "variableExpense";		//���� ����

    
    
    private DatabaseHelper dbHelper; 
    private SQLiteDatabase mdb;

    
    //db ���̺� ���� ����
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
    
    //�ܷ�Ű�� ����ϱ� ���� Ʈ����
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
    //������ ���̽� ���� ����
    private static final String DATABASE_NAME = "data2"; 
    private static final int DATABASE_VERSION = 2; 


    private final Context mCtx; 

   
     private static class DatabaseHelper extends SQLiteOpenHelper {
    	 //db ������
        DatabaseHelper(Context context) { 
            super(context, DATABASE_NAME, null, DATABASE_VERSION); 
        } 
 
        //Ŭ���� �����ϸ� ������̺��� ������
        @Override 
        public void onCreate(SQLiteDatabase db) { 
        	Log.i("dbControler","���� ����");
        	db.execSQL(CREATE_TABLE_MEMBER); 
        	Log.i("dbControler","��� ���̺� ����");
            db.execSQL(CREATE_TABLE_CP); 
        	Log.i("dbControler","�ڻ� �Ѿ� ���̺� ����");
            db.execSQL(CREATE_TABLE_CF); 
        	Log.i("dbControler","������ ���̺� ����");
            db.execSQL(CREATE_TRIGGER1); 
        	Log.i("dbControler","Ʈ���� 1 ����");
            db.execSQL(CREATE_TRIGGER2); 
        	Log.i("dbControler","Ʈ��Ŀ 2 ����");
        } 


        //db ��ü�� ������ �� ���
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

   
 
    //����� ���̺� �ν��Ͻ� �߰�
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

    //�ڻ����� ���̺� �ν��Ͻ� �߰�
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

    //�������� ���̺� �ν��Ͻ� �߰�
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

    //��ü ���
     public Cursor fetchAllNotes() { 
    	 Log.i("asdf","���� ������ �´��� Ʋ������..;");
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
  
    
    //����� ���̺� �ν��Ͻ� �߰�
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

    //�ڻ����� ���̺� �ν��Ͻ� �߰�
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

    //�������� ���̺� �ν��Ͻ� �߰�
    public boolean updateNoteCF(Long cf_Id, Integer earnedIncome ,Integer invariableExpense,
    		Integer variableExpense) { 
        ContentValues initialValues = new ContentValues(); 
        initialValues.put(ENTITY_EARNEDINCOME,earnedIncome);
        initialValues.put(ENTITY_INVARIABLEEXPENSE, invariableExpense);
        initialValues.put(ENTITY_VARIABLEEXPENSE, variableExpense);

        return mdb.update(TABLE_CASH, initialValues, "cf_id =" + cf_Id, null) > 0; 
    } 
} 
