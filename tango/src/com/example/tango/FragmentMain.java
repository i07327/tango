package com.example.tango;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FragmentMain extends Fragment {

	private View rootView;
	private List<Word> wordList;
	private Integer dispIndex;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_main, container, false);

		// =======================
		// 単語読み込み
        // =======================
		WordDAO wordDao = new WordDAO(getActivity());
		wordList = wordDao.getItem();
		
        // =======================
		// ボタンイベント設定
        // =======================
		rootView.findViewById(R.id.btnFirst).setOnClickListener(onClickFirst);
        rootView.findViewById(R.id.btnPrev).setOnClickListener(onClickPrev);
        rootView.findViewById(R.id.btnNext).setOnClickListener(onClickNext);
        rootView.findViewById(R.id.btnEnd).setOnClickListener(onClickEnd);
        rootView.findViewById(R.id.btnBack).setOnClickListener(onClickBack);
        rootView.findViewById(R.id.llWord).setOnClickListener(onClickContent);;

        // =======================
		// 最初の単語を表示
        // =======================
		dispIndex = 0;
        TextView tvWord = (TextView) rootView.findViewById(R.id.tvWord);
        tvWord.setText(wordList.get(dispIndex).getWord());
        TextView tvContent = (TextView) rootView.findViewById(R.id.tvContent);
        tvContent.setText("");

		return rootView;
	}

    // 「<<」ボタン押下イベント
    OnClickListener onClickFirst = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // =======================
            // 最初の単語を表示
            // =======================
            if (dispIndex > 0) {
                dispIndex = 0;
            }
            TextView tvWord = (TextView) rootView.findViewById(R.id.tvWord);
            tvWord.setText(wordList.get(dispIndex).getWord());
            TextView tvContent = (TextView) rootView.findViewById(R.id.tvContent);
            tvContent.setText("");
        }
    };

    // 「<」ボタン押下イベント
    OnClickListener onClickPrev = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // =======================
            // 最初の単語を表示
            // =======================
            if (dispIndex > 0) {
                dispIndex--;
            }
            TextView tvWord = (TextView) rootView.findViewById(R.id.tvWord);
            tvWord.setText(wordList.get(dispIndex).getWord());
            TextView tvContent = (TextView) rootView.findViewById(R.id.tvContent);
            tvContent.setText("");
        }
    };

    // 「>」ボタン押下イベント
    OnClickListener onClickNext = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // =======================
            // 最初の単語を表示
            // =======================
            if (dispIndex < wordList.size() -1) {
                dispIndex++;
            }
            TextView tvWord = (TextView) rootView.findViewById(R.id.tvWord);
            tvWord.setText(wordList.get(dispIndex).getWord());
            TextView tvContent = (TextView) rootView.findViewById(R.id.tvContent);
            tvContent.setText("");
        }
    };

    // 「>>」ボタン押下イベント
    OnClickListener onClickEnd = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // =======================
            // 最初の単語を表示
            // =======================
            if (dispIndex < wordList.size() -1) {
                dispIndex = wordList.size() - 1;
            }
            TextView tvWord = (TextView) rootView.findViewById(R.id.tvWord);
            tvWord.setText(wordList.get(dispIndex).getWord());
            TextView tvContent = (TextView) rootView.findViewById(R.id.tvContent);
            tvContent.setText("");
        }
    };

    // 「戻る」ボタン押下イベント
    OnClickListener onClickBack = new OnClickListener() {

        @Override
        public void onClick(View v) {
            
            Fragment newFragment;
            newFragment = new FragmentTop();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, newFragment).commit();
        }
    };

    // 「画面」押下イベント
    OnClickListener onClickContent = new OnClickListener() {

        @Override
        public void onClick(View v) {
            TextView tvContent = (TextView) rootView.findViewById(R.id.tvContent);
            tvContent.setText(wordList.get(dispIndex).getContent());
        }
    };
}
