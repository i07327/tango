package com.example.tango;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

/**
 * データアクセスクラス
 * @author minei
 *
 */
public class WordDAO {

	private DBAdapter dbAdapter;

	/**
	 * コンストラクタ
	 */
	public WordDAO(Context context) {
		this.dbAdapter = new DBAdapter(context);
	}

	/**
	 * 仮データ追加
	 */
	public void kariData() {
//	    Word chokin = new Word();
//		// 登録
//		chokin.setMoney(100);
//		chokin.setDate("2014-11-01");
//		chokin.setFlg(0);
//		saveItem(chokin);
	}
    
    /**
     * 削除(全件)
     */
    public void deleteData() {
        dbAdapter.open();
        dbAdapter.deleteAllNotes();
        dbAdapter.close();
    }
    
    /**
     * 削除(1件)
     */
    public void deleteWithId(Integer id) {
        dbAdapter.open();
        dbAdapter.deleteNote(id);
        dbAdapter.close();
    }

	/**
	 * 登録(1件)
	 * @param chokin
	 */
	protected void saveItem(Word word) {
		dbAdapter.open();
		// 種別は初期値を設定
		if (word.getType() == null) {
		    word.setType(0);
		}
		
		dbAdapter.saveWord(word);
		dbAdapter.close();
	}
	
	/**
	 * 更新(1件)
	 * @param chokin
	 */
	protected void updateItem(Word word) {
		dbAdapter.open();
        // 種別は初期値を設定
        if (word.getType() == null) {
            word.setType(0);
        }
		dbAdapter.updateNote(word);
		dbAdapter.close();
	}

	/**
	 * 検索(全件)
	 * @return
	 */
	protected List<Word> getItem() {
		dbAdapter.open();

		List<Word> wordList = new ArrayList<Word>();

		// 全件検索
		Cursor c = dbAdapter.getAllWord();

		// 検索結果取得
		if (c.moveToFirst()) {
			do {
			    Word note = new Word(
						c.getInt(c.getColumnIndex(DBAdapter.COL_ID)), 
						c.getString(c.getColumnIndex(DBAdapter.COL_WORD)), 
						c.getString(c.getColumnIndex(DBAdapter.COL_CONTENT)), 
						c.getInt(c.getColumnIndex(DBAdapter.COL_TYPE)));
				wordList.add(note);
			} while (c.moveToNext());
		}

		c.close();

		dbAdapter.close();
		
		return wordList;
	}
}
