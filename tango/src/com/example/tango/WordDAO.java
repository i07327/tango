package com.example.tango;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

/**
 * �f�[�^�A�N�Z�X�N���X
 * @author minei
 *
 */
public class WordDAO {

	private DBAdapter dbAdapter;

	/**
	 * �R���X�g���N�^
	 */
	public WordDAO(Context context) {
		this.dbAdapter = new DBAdapter(context);
	}

	/**
	 * ���f�[�^�ǉ�
	 */
	public void kariData() {
//	    Word chokin = new Word();
//		// �o�^
//		chokin.setMoney(100);
//		chokin.setDate("2014-11-01");
//		chokin.setFlg(0);
//		saveItem(chokin);
	}
    
    /**
     * �폜(�S��)
     */
    public void deleteData() {
        dbAdapter.open();
        dbAdapter.deleteAllNotes();
        dbAdapter.close();
    }
    
    /**
     * �폜(1��)
     */
    public void deleteWithId(Integer id) {
        dbAdapter.open();
        dbAdapter.deleteNote(id);
        dbAdapter.close();
    }

	/**
	 * �o�^(1��)
	 * @param chokin
	 */
	protected void saveItem(Word word) {
		dbAdapter.open();
		// ��ʂ͏����l��ݒ�
		if (word.getType() == null) {
		    word.setType(0);
		}
		
		dbAdapter.saveWord(word);
		dbAdapter.close();
	}
	
	/**
	 * �X�V(1��)
	 * @param chokin
	 */
	protected void updateItem(Word word) {
		dbAdapter.open();
        // ��ʂ͏����l��ݒ�
        if (word.getType() == null) {
            word.setType(0);
        }
		dbAdapter.updateNote(word);
		dbAdapter.close();
	}

	/**
	 * ����(�S��)
	 * @return
	 */
	protected List<Word> getItem() {
		dbAdapter.open();

		List<Word> wordList = new ArrayList<Word>();

		// �S������
		Cursor c = dbAdapter.getAllWord();

		// �������ʎ擾
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
