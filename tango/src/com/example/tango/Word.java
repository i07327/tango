package com.example.tango;

/**
 * �G���e�B�e�B�N���X
 * @author minei
 *
 */
public class Word {
	private int id;
	private String word;       // �P��
	private String content;    // ���e
	private Integer type;      // ���

	/**
	 * �R���X�g���N�^
	 */
	public Word() {
	}
	
	/**
	 * �R���X�g���N�^
	 * @param id
	 * @param money
	 * @param date
	 * @param flg
	 */
	public Word(Integer id, String word, String content, Integer type) {
		this.id = id;
		this.word = word;
		this.content = content;
		this.type = type;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
