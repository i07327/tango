package com.example.tango;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DBアクセスクラス
 * @author minei
 *
 */
public class DBAdapter {

	static final String DATABASE_NAME = "tango.db";
	static final int DATABASE_VERSION = 4;

	public static final String TABLE_NAME = "tbl_word";
	public static final String COL_ID = "_id";
	public static final String COL_WORD = "word";
	public static final String COL_CONTENT = "content";
	public static final String COL_TYPE = "type";

	protected final Context context;
	protected DatabaseHelper dbHelper;
	protected SQLiteDatabase db;

	public DBAdapter(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(this.context);
	}

	//
	// SQLiteOpenHelper
	//

	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		/**
		 * DB作成時の処理
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {

			// テーブル作成
			db.execSQL("CREATE TABLE " + TABLE_NAME + " (" 
					+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
					+ COL_WORD + " TEXT NOT NULL," 
					+ COL_CONTENT + " TEXT NOT NULL," 
					+ COL_TYPE + " INTEGER NOT NULL);");
		}

		/**
		 * DBバージョンアップ時の処理
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// テーブル削除
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}

	//
	// Adapter Methods
	//

	public DBAdapter open() {
		db = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	//
	// App Methods
	//

	/**
	 * 削除(全件)
	 * @return
	 */
	public boolean deleteAllNotes() {
		return db.delete(TABLE_NAME, null, null) > 0;
	}

	/**
	 * 検索(ID指定)
	 * @param id
	 * @return
	 */
	public boolean deleteNote(int id) {
		return db.delete(TABLE_NAME, COL_ID + "=" + id, null) > 0;
	}

	/**
	 * 検索(全件)
	 * @return
	 */
	public Cursor getAllWord() {
		return db.query(TABLE_NAME, null, null, null, null, null, null);
	}

	/**
	 * データ登録
	 * @param word
	 * @param content
	 * @param type
	 */
	public void saveWord(Word word) {
		ContentValues values = new ContentValues();
        values.put(COL_WORD, word.getWord());
        values.put(COL_CONTENT, word.getContent());
        values.put(COL_TYPE, word.getType());
		db.insertOrThrow(TABLE_NAME, null, values);
	}

	/**
	 * データ更新
	 * @param word
	 */
	public void updateNote(Word word) {
		ContentValues values = new ContentValues();
		values.put(COL_WORD, word.getWord());
		values.put(COL_CONTENT, word.getContent());
		values.put(COL_TYPE, word.getType());
		String whereClause = COL_ID + " = '" + word.getId() + "'";
		db.update(TABLE_NAME, values, whereClause, null);
	}
}
