package com.example.bankingapp.databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "bank.db";
    private static final int DATABASE_VERSION = 17;

    private static final String TABLE_NAME = "Users";
    public static final String COLUMN_ID = "_id";
    public final static String COLUMN_USER_NAME ="user_name";
    public final static String COLUMN_USER_ACCOUNT_NUMBER ="accountNo";
    public final static String COLUMN_USER_EMAIL ="email";
    public final static String COLUMN_USER_IFSC_CODE ="ifscCode";
    public final static String COLUMN_USER_PHONE_NO ="phoneNo";
    public final static String COLUMN_USER_ACCOUNT_BALANCE ="balance";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + TABLE_NAME + " ("
                +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USER_ACCOUNT_NUMBER + " INTEGER, "
                + COLUMN_USER_NAME + " VARCHAR, "
                + COLUMN_USER_EMAIL + " VARCHAR, "
                + COLUMN_USER_IFSC_CODE + " VARCHAR, "
                + COLUMN_USER_PHONE_NO + " VARCHAR, "
                + COLUMN_USER_ACCOUNT_BALANCE + " INTEGER NOT NULL);";
        db.execSQL(query);
        db.execSQL("insert into " + TABLE_NAME + " values(1,78591254,'Shivam Patwal', 'patwalshivam21@gmail.com','7584','8494845715', 4000)");
        db.execSQL("insert into " + TABLE_NAME + " values(2,68652859,'Rahul Rawat', 'rahulrawat0@gmail.com','1258','9856641238', 6000)");
        db.execSQL("insert into " + TABLE_NAME + " values(3,88597954,'Harsh Choudhary', 'harshcc@gmail.com','8896','8565645896', 11000)");
        db.execSQL("insert into " + TABLE_NAME + " values(4,25621583,'KK Kumar', 'kkk32@gmail.com','7752','6775640038', 5000)");
        db.execSQL("insert into " + TABLE_NAME + " values(5,74185909,'Shivam kumar', 'shivamkm666@gmail.com','3669','8085648962', 5500)");
        db.execSQL("insert into " + TABLE_NAME + " values(6,85236294,'Rahul Gandhi', 'gandhirahul10@gmail.com','9985','8745640238', 70000)");
        db.execSQL("insert into " + TABLE_NAME + " values(7,36859985,'Yuvraj Singh', 'yuvi98@gmail.com','1207','8245640215', 14500)");
        db.execSQL("insert into " + TABLE_NAME + " values(8,78538456,'Hasan Ali', 'alihasan@gmail.com','4522','9665021539', 6500)");
        db.execSQL("insert into " + TABLE_NAME + " values(9,45624764,'Sushil rawat', 'mesushi@gmail.com','6582','9859565238', 10500)");
        db.execSQL("insert into " + TABLE_NAME + " values(10,23859654,'Khushi Giri', 'KhushiGiri00@gmail.com','5450','9995691201', 19900)");
        db.execSQL("insert into " + TABLE_NAME + " values(11,78547348,'Jaspreet Singh', 'jassisingh1@gmail.com','2656','8015641200', 8800)");
        db.execSQL("insert into " + TABLE_NAME + " values(12,36217391,'Raj Verma', 'rajvarma@gmail.com','1203','9525641999', 9000)");
        db.execSQL("insert into " + TABLE_NAME + " values(13,15628237,'Naman Mathur', 'naman0mathur@gmail.com','5566','9229541001', 15800)");
        db.execSQL("insert into " + TABLE_NAME + " values(14,78362921,'Sourabh Raj', 'sourabhraj87@gmail.com','2236','6114642205', 13500)");
        db.execSQL("insert into " + TABLE_NAME + " values(15,35562704,'Rohit Sharma', 'rohit266@gmail.com','6334','9874541266', 11500)");
        db.execSQL("insert into " + TABLE_NAME + " values(16,35562704,'Anil kapoor', 'kapoorfamily@gmail.com','62492','9874541266', 12500)");
        db.execSQL("insert into " + TABLE_NAME + " values(17,35562704,'Rossy de souza', 'desouzarossy24@gmail.com','1629','9874541266', 2500)");
        db.execSQL("insert into " + TABLE_NAME + " values(18,35562704,'Akash Bhatia', 'bhadakash@gmail.com','4659','9874541266', 1150)");
        db.execSQL("insert into " + TABLE_NAME + " values(19,35562704,'Ritik Bhandari', 'bhadritik@gmail.com','7892','9874541266', 5600)");
        db.execSQL("insert into " + TABLE_NAME + " values(20,35562704,'Tanmay Singhania', 'singhaniatan@gmail.com','6256','9874541266',2350)");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
        if(oldversion!=newversion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);}
    }


    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

/*
    void addBook(String title, String author, int pages){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
*/


    public  void updateData(String row_id, Double balance){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_ACCOUNT_BALANCE, balance);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}