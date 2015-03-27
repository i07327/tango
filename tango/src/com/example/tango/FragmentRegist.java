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
		
		// �N���b�N�C�x���g��ݒ�
        rootView.findViewById(R.id.btnRegist).setOnClickListener(onClickRegist);
        rootView.findViewById(R.id.btnBack).setOnClickListener(onClickBack);

		return rootView;
	}

    // �o�^�{�^�������C�x���g
    OnClickListener onClickRegist = new OnClickListener() {

        @Override
        public void onClick(View v) {
            
            // ===========================
            // �`�F�b�N����
            // ===========================
            // TODO ������
            
            // ===========================
            // �o�^����
            // ===========================
            EditText etWord = (EditText) rootView.findViewById(R.id.etWord);
            EditText etContent = (EditText) rootView.findViewById(R.id.etContent);

            Word word = new Word();
            word.setWord(etWord.getText().toString());
            word.setContent(etContent.getText().toString());
            
            WordDAO wordDao = new WordDAO(getActivity());
            wordDao.saveItem(word);
            
            // ===========================
            // �N���A����
            // ===========================
            etWord.setText("");
            etContent.setText("");

            // ���b�Z�[�W�\��
            Toast.makeText(getActivity(), "�P���o�^���܂����B", Toast.LENGTH_SHORT).show();
        }
    };

    // �߂�{�^�������C�x���g
    OnClickListener onClickBack = new OnClickListener() {
        
        @Override
        public void onClick(View arg0) {
            // ===========================
            // TOP��ʂɖ߂�
            // ===========================
            Fragment newFragment;
            newFragment = new FragmentTop();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, newFragment).commit();
        }
    }; 

}
