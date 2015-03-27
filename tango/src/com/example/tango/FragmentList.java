package com.example.tango;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class FragmentList extends Fragment {

	private View rootView;
	private List<Word> wordList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.fragment_list, container,
				false);

		// ���X�g�r���[��\��
		ListView listView = setListView();
		
        // �A�C�e���N���b�N���̂̃C�x���g��ǉ�
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View view, int pos, long id) {

                // �I���A�C�e�����擾
                ListView listView = (ListView)parent;
                final Word word = (Word)listView.getItemAtPosition(pos);
                
                // �_�C�A���O�\��
                // �m�F�_�C�A���O�̐���
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(getActivity());
                alertDlg.setTitle("�폜�m�F");
                alertDlg.setMessage("���̒P����폜���܂����H");
                alertDlg.setNegativeButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // OK �{�^���N���b�N����
                            WordDAO wordDao = new WordDAO(getActivity());
                            wordDao.deleteWithId(word.getId());
                            Toast.makeText(getActivity(), "�P����폜���܂����B", Toast.LENGTH_SHORT).show();
                            
                            // �ĕ`��
                            setListView();
                        }
                    });
                alertDlg.setPositiveButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Cancel �{�^���N���b�N����
                            // �������Ȃ�
                        }
                    });

                // �\��
                alertDlg.create().show();
            }
        });


        // ======================
        // �N���b�N�C�x���g�ݒ�
        // ======================
        rootView.findViewById(R.id.btnBack).setOnClickListener(onClickBack);
        
		return rootView;
	}

    // �u�߂�v�{�^�������C�x���g
    OnClickListener onClickBack = new OnClickListener() {

        @Override
        public void onClick(View v) {
            
            Fragment newFragment;
            newFragment = new FragmentTop();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, newFragment).commit();
        }
    };
    
    /**
     * ���X�g�r���[��ݒ�
     * @return
     */
    private ListView setListView() {
        // ======================
        // �P��擾
        // ======================
        WordDAO wordDao = new WordDAO(getActivity());
        wordList = wordDao.getItem();
        
        // ======================
        // ���X�g�r���[�ݒ�
        // ======================
        WordAdapter customAdapater = new WordAdapter(getActivity(),
                0, wordList);

        ListView listView = (ListView) rootView.findViewById(R.id.listWord);
        listView.setAdapter(customAdapater);
        
        return listView;
    }

}
