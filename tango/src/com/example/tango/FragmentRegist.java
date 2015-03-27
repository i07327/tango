package com.example.tango;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentRegist extends Fragment {

	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_regist, container,
				false);
		
		// クリックイベントを設定
        rootView.findViewById(R.id.btnRegist).setOnClickListener(onClickRegist);
        rootView.findViewById(R.id.btnBack).setOnClickListener(onClickBack);

		return rootView;
	}

    // 登録ボタン押下イベント
    OnClickListener onClickRegist = new OnClickListener() {

        @Override
        public void onClick(View v) {
            
            // ===========================
            // チェック処理
            // ===========================
            // TODO 未実装
            
            // ===========================
            // 登録処理
            // ===========================
            EditText etWord = (EditText) rootView.findViewById(R.id.etWord);
            EditText etContent = (EditText) rootView.findViewById(R.id.etContent);

            Word word = new Word();
            word.setWord(etWord.getText().toString());
            word.setContent(etContent.getText().toString());
            
            WordDAO wordDao = new WordDAO(getActivity());
            wordDao.saveItem(word);
            
            // ===========================
            // クリア処理
            // ===========================
            etWord.setText("");
            etContent.setText("");

            // メッセージ表示
            Toast.makeText(getActivity(), "単語を登録しました。", Toast.LENGTH_SHORT).show();
        }
    };

    // 戻るボタン押下イベント
    OnClickListener onClickBack = new OnClickListener() {
        
        @Override
        public void onClick(View arg0) {
            // ===========================
            // TOP画面に戻る
            // ===========================
            Fragment newFragment;
            newFragment = new FragmentTop();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, newFragment).commit();
        }
    }; 

}
