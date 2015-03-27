package com.example.tango;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class FragmentTop extends Fragment {

	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_top, container, false);

		rootView.findViewById(R.id.btnStart).setOnClickListener(onClickStart);
        rootView.findViewById(R.id.btnRegist).setOnClickListener(onClickRegist);
        rootView.findViewById(R.id.btnList).setOnClickListener(onClickList);

		return rootView;
	}

    // 開始ボタン押下イベント
    OnClickListener onClickStart = new OnClickListener() {

        @Override
        public void onClick(View v) {
            
            Fragment newFragment;
            newFragment = new FragmentMain();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, newFragment).commit();
        }
    };

    // 登録ボタン押下イベント
    OnClickListener onClickRegist = new OnClickListener() {

        @Override
        public void onClick(View v) {
            
            Fragment newFragment;
            newFragment = new FragmentRegist();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, newFragment).commit();
        }
    };

    // 「一覧」ボタン押下イベント
    OnClickListener onClickList = new OnClickListener() {

        @Override
        public void onClick(View v) {
            
            Fragment newFragment;
            newFragment = new FragmentList();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, newFragment).commit();
        }
    };
}
