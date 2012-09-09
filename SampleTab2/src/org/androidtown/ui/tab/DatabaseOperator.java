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
        return mdb.rawQuery(
        		"select * from (member join collectiveProperty on member.cp=collectiveProperty.cp_id) "
        		,null); 
    } 


     //��� ���
     public Cursor fetchMemberNotes() { 
        return mdb.rawQuery(
        		"select * from member "
        		,null); 
    } 
 

     //��� ���
     public Cursor fetchCPNotes() { 
        return mdb.rawQuery(
        		"select * from collectiveProperty "
        		,null); 
    } 

     //��� ���
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
    */
	
	
    //table ���ǿ� ���� ���
	public static final String TABLE_MEMBER_INFORMATION 	 	= "memberInformation";		//����� ���̺� 
    public static final String TABLE_COLLECTIVE_INFORMATION  	= "collectiveInformation"; 	//�ڻ�����
    public static final String TABLE_DEBT_INFORMATION		 	= "debtInformation"; 	//�ڻ�����
    public static final String TABLE_INCOME_EXPENSE_INFORMATION = "incomeExpenseInformation"; 	//�ڻ�����
    public static final String TABLE_ECT_INFORMATION 			= "ectInformation"; 	//�ڻ�����

    //entity ���ǿ� ���� ���
    //TABLE_MEMBER_INFORMATION
    public static final String ENTITY_MEMBER_INFORMATION_ID 		 = "member_id";						//����� id
    public static final String ENTITY_MEMBER_INFORMATION_AGE 		 = "member_age"; 						//����
    public static final String ENTITY_MEMBER_INFORMATION_RESIDENTIAL = "member_residentialArea"; //��� ����
    public static final String ENTITY_MEMBER_INFORMATION_JAP 		 = "member_jap"; 						//����
    public static final String ENTITY_MEMBER_INFORMATION_SEX 		 = "member_sex";						//����
	public static final String ENTITY_MEMBER_INFORMATION_MARRIAGE 	 = "member_marriage"; 			//��ȥ����
    public static final String ENTITY_MEMBER_INFORMATION_CHILD 		 = "member_child"; 					//�ڽ�����

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

    //���̺� ���� ����
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
    
    
    //������ ���̽� ���� ����
    private static final String DATABASE_NAME = "data3"; 
    private static final int DATABASE_VERSION = 2; 

    private final Context myContext; 
    private DatabaseHelper dbHelper; 
    private SQLiteDatabase myDb;

    private static class DatabaseHelper extends SQLiteOpenHelper {
   	 //db ������
       DatabaseHelper(Context context) { 
           super(context, DATABASE_NAME, null, DATABASE_VERSION); 
       } 

       //Ŭ���� �����ϸ� ������̺��� ������
       @Override 
       public void onCreate(SQLiteDatabase db) { 
	       	db.execSQL(CREATE_TABLE_MEMBER_INFORMATION); 
	        db.execSQL(CREATE_TABLE_COLLECTIVE_INFORMATION); 
	       	db.execSQL(CREATE_TABLE_DEBT_INFORMATION); 
	       	db.execSQL(CREATE_TABLE_INCOME_EXPENSE_INFORMATION); 
	        db.execSQL(CREATE_TABLE_ECT_INFORMATION); 
       } 

       //db ��ü�� ������ �� ���
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
    

    
    //�ڻ����� ���̺� �ν��Ͻ� �߰�
    /** true���̸� �ű� �Է�
     *  flase���̸� ����
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
    
    //�������� ���̺� �ν��Ͻ� �߰�
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
    	if (index.equals("�����ڻ� ����")) {
    		this.insertNoteCollectiveInformation("�����ڻ� ����", values);
    	}
    	else if (index.equals("�����ڻ� �ݵ�")) {
    		this.insertNoteCollectiveInformation("�����ڻ� �ݵ�", values);
    	}
    	else if (index.equals("�����ڻ� ����")) {
    		this.insertNoteCollectiveInformation("�����ڻ� ����", values);
    	}
    	else if (index.equals("�����ڻ� ä��")) {
    		this.insertNoteCollectiveInformation("�����ڻ� ä��", values);
    	}
    	else if (index.equals("�����ڻ� �ֽ�")) {
    		this.insertNoteCollectiveInformation("�����ڻ� �ֽ�", values);
    	}
    	else if (index.equals("��Ÿ�ڻ� ����")) {
    		this.insertNoteCollectiveInformation("��Ÿ�ڻ� ����", values);
    	}
    	else if (index.equals("��Ÿ�ڻ� ����")) {
    		this.insertNoteCollectiveInformation("��Ÿ�ڻ� ����", values);
    	}
    	else if (index.equals("��Ÿ�ڻ� �뿩��")) {
    		this.insertNoteCollectiveInformation("��Ÿ�ڻ� �뿩��", values);
    	}
    	else if (index.equals("��ä ���ô㺸����")) {
    		this.insertNoteDebtInformation("��ä ���ô㺸����", values);
    	}
    	else if (index.equals("��ä �ڵ����Һ��ܾ�")) {
    		this.insertNoteDebtInformation("��ä �ڵ����Һ��ܾ�", values);
    	}
    	else if (index.equals("��ä ���̳ʽ��ſ����")) {
    		this.insertNoteDebtInformation("��ä ���̳ʽ��ſ����", values);
    	}
    	else if (index.equals("��ä �ſ�ī������ޱ�")) {
    		this.insertNoteDebtInformation("��ä �ſ�ī������ޱ�", values);
    	}
    	else if (index.equals("������ ����")) {
    		this.insertNoteIncomeExpenseInformation("������ ����", values);
    	}
    	else if (index.equals("������ ��Ȱ��")) {
    		this.insertNoteIncomeExpenseInformation("������ ��Ȱ��", values);
    	}
    	else if (index.equals("������ �����")) {
    		this.insertNoteIncomeExpenseInformation("������ �����", values);
    	}
    	else if (index.equals("������ ������")) {
    		this.insertNoteIncomeExpenseInformation("������ ������", values);
    	}
    	else if (index.equals("������ ���δ����ȯ��")) {
    		this.insertNoteIncomeExpenseInformation("������ ���δ����ȯ��", values);
    	}
    	else if (index.equals("������ ���ô����ȯ��")) {
    		this.insertNoteIncomeExpenseInformation("������ ���ô����ȯ��", values);
    	}
    	else if (index.equals("��Ÿ ���������")) {
    		this.insertNoteEctInformation("��Ÿ ���������", values);
    	}
    	else if (index.equals("��Ÿ 72��Ģ�����ͷ�")) {
    		this.insertNoteEctInformation("��Ÿ 72��Ģ�����ͷ�", values);
    	}
    	else if (index.equals("��Ÿ �ܱ��ǥ�ʿ��ڱ�")) {
    		this.insertNoteEctInformation("��Ÿ �ܱ��ǥ�ʿ��ڱ�", values);
    	}
    	else if (index.equals("��Ÿ �ܱ��ǥ����")) {
    		this.insertNoteEctInformation("��Ÿ �ܱ��ǥ����", values);
    	}
    	else if (index.equals("��Ÿ �ܱ��ǥ�غ��ڱ�")) {
    		this.insertNoteEctInformation("��Ÿ �ܱ��ǥ�غ��ڱ�", values);
    	}
    	else if (index.equals("��Ÿ ���ְ�ġ���޿���·�")) {
    		this.insertNoteEctInformation("��Ÿ ���ְ�ġ���޿���·�", values);
    	}
    	else if (index.equals("��Ÿ ���𿹻����")) {
    		this.insertNoteEctInformation("��Ÿ ���𿹻����", values);
    	}
    	else if (index.equals("��Ÿ ����м�������ݾ�")) {
    		this.insertNoteEctInformation("��Ÿ ����м�������ݾ�", values);
    	}
    	else{}
    }

    public void insertDB(String index, String values){
    	if (index.equals("����� ��������")) {
    		this.insertNoteMemberInformation(0, values, "", "", "", 0);
    	}
    	else if (index.equals("����� ����")) {
    		this.insertNoteMemberInformation(0, "", values, "", "", 0);
    	}
    	else if (index.equals("����� ����")) {
    		this.insertNoteMemberInformation(0, "", "", values, "", 0);
    	}
    	else if (index.equals("����� ��ȥ����")) {
    		this.insertNoteMemberInformation(0, "", "", "", values, 0);
    	}
    	else {}
    }

    public void insertDB(String index, int values){
    	if (index.equals("����� ����")) {
    		this.insertNoteMemberInformation(values, "", "", "", "", 0);
    	}
    	else if (index.equals("����� �ڳ��")) {
    		this.insertNoteMemberInformation(0, "", "", "", "", values);
    	}
    	else {}
    }
    
    public Object getDB(String index){
    	Cursor cursor = null;
    	if (index.equals("�����ڻ� ����")) {
    		cursor = this.fetchNoteCollectiveInformation("�����ڻ� ����");
    	}
    	else if (index.equals("�����ڻ� �ݵ�")) {
    		cursor = this.fetchNoteCollectiveInformation("�����ڻ� �ݵ�");
    	}
    	else if (index.equals("�����ڻ� ����")) {
    		cursor = this.fetchNoteCollectiveInformation("�����ڻ� ����");
    	}
    	else if (index.equals("�����ڻ� ä��")) {
    		cursor = this.fetchNoteCollectiveInformation("�����ڻ� ä��");
    	}
    	else if (index.equals("�����ڻ� �ֽ�")) {
    		cursor = this.fetchNoteCollectiveInformation("�����ڻ� �ֽ�");
    	}
    	else if (index.equals("��Ÿ�ڻ� ����")) {
    		cursor = this.fetchNoteCollectiveInformation("��Ÿ�ڻ� ����");
    	}
    	else if (index.equals("��Ÿ�ڻ� ����")) {
    		cursor = this.fetchNoteCollectiveInformation("��Ÿ�ڻ� ����");
    	}
    	else if (index.equals("��Ÿ�ڻ� �뿩��")) {
    		cursor = this.fetchNoteCollectiveInformation("��Ÿ�ڻ� �뿩��");
    	}
    	else if (index.equals("��ä ���ô㺸����")) {
    		cursor = this.fetchNoteDebtInformation("��ä ���ô㺸����");
    	}
    	else if (index.equals("��ä �ڵ����Һ��ܾ�")) {
    		cursor = this.fetchNoteDebtInformation("��ä �ڵ����Һ��ܾ�");
    	}
    	else if (index.equals("��ä ���̳ʽ��ſ����")) {
    		cursor = this.fetchNoteDebtInformation("��ä ���̳ʽ��ſ����");
    	}
    	else if (index.equals("��ä �ſ�ī������ޱ�")) {
    		cursor = this.fetchNoteDebtInformation("��ä �ſ�ī������ޱ�");
    	}
    	else if (index.equals("������ ����")) {
    		cursor = this.fetchNoteIncomeExpenseInformation("������ ����");
    	}
    	else if (index.equals("������ ��Ȱ��")) {
    		cursor = this.fetchNoteIncomeExpenseInformation("������ ��Ȱ��");
    	}
    	else if (index.equals("������ �����")) {
    		cursor = this.fetchNoteIncomeExpenseInformation("������ �����");
    	}
    	else if (index.equals("������ ������")) {
    		cursor = this.fetchNoteIncomeExpenseInformation("������ ������");
    	}
    	else if (index.equals("������ ���δ����ȯ��")) {
    		cursor = this.fetchNoteIncomeExpenseInformation("������ ���δ����ȯ��");
    	}
    	else if (index.equals("������ ���ô����ȯ��")) {
    		cursor = this.fetchNoteIncomeExpenseInformation("������ ���ô����ȯ��");
    	}
    	else if (index.equals("��Ÿ ���������")) {
    		cursor = this.fetchNoteEctInformation("��Ÿ ���������");
    	}
    	else if (index.equals("��Ÿ 72��Ģ�����ͷ�")) {
    		cursor = this.fetchNoteEctInformation("��Ÿ 72��Ģ�����ͷ�");
    	}
    	else if (index.equals("��Ÿ �ܱ��ǥ�ʿ��ڱ�")) {
    		cursor = this.fetchNoteEctInformation("��Ÿ �ܱ��ǥ�ʿ��ڱ�");
    	}
    	else if (index.equals("��Ÿ �ܱ��ǥ����")) {
    		cursor = this.fetchNoteEctInformation("��Ÿ �ܱ��ǥ����");
    	}
    	else if (index.equals("��Ÿ �ܱ��ǥ�غ��ڱ�")) {
    		cursor = this.fetchNoteEctInformation("��Ÿ �ܱ��ǥ�غ��ڱ�");
    	}
    	else if (index.equals("��Ÿ ���ְ�ġ���޿���·�")) {
    		cursor = this.fetchNoteEctInformation("��Ÿ ���ְ�ġ���޿���·�");
    	}
    	else if (index.equals("��Ÿ ���𿹻����")) {
    		cursor = this.fetchNoteEctInformation("��Ÿ ���𿹻����");
    	}
    	else if (index.equals("��Ÿ ����м�������ݾ�")) {
    		cursor = this.fetchNoteEctInformation("��Ÿ ����м�������ݾ�");
    	}
    	else if (index.equals("����� ��������")) {
    		cursor = this.fetchNoteMemberInformation();
        	if(cursor.getCount() != 0){
        		return cursor.getString(2);
        	}
        	else {
        		return "";
        	}
    	}
    	else if (index.equals("����� ����")) {
    		cursor = this.fetchNoteMemberInformation();
        	if(cursor.getCount() != 0){
        		return cursor.getString(3);
        	}
        	else {
        		return "";
        	}
    	}
    	else if (index.equals("����� ����")) {
    		cursor = this.fetchNoteMemberInformation();
        	if(cursor.getCount() != 0){
        		return cursor.getString(4);
        	}
        	else {
        		return "";
        	}
    	}
    	else if (index.equals("����� ��ȥ����")) {
    		cursor = this.fetchNoteMemberInformation();
        	if(cursor.getCount() != 0){
        		return cursor.getString(5);
        	}
        	else {
        		return "";
        	}
    	}
    	else if (index.equals("����� ����")) {
    		cursor = this.fetchNoteMemberInformation();
        	if(cursor.getCount() != 0){
        		return cursor.getInt(1);
        	}
        	else {
        		Integer returnValue = new Integer(0);
        		return returnValue;
        	}
    	}
    	else if (index.equals("����� �ڳ��")) {
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
    		return "�ش� index �׸��� �����ϴ�.";
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