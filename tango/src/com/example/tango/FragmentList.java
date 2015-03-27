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

		// リストビューを表示
		ListView listView = setListView();
		
        // アイテムクリック時ののイベントを追加
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View view, int pos, long id) {

                // 選択アイテムを取得
                ListView listView = (ListView)parent;
                final Word word = (Word)listView.getItemAtPosition(pos);
                
                // ダイアログ表示
                // 確認ダイアログの生成
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(getActivity());
                alertDlg.setTitle("削除確認");
                alertDlg.setMessage("この単語を削除しますか？");
                alertDlg.setNegativeButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // OK ボタンクリック処理
                            WordDAO wordDao = new WordDAO(getActivity());
                            wordDao.deleteWithId(word.getId());
                            Toast.makeText(getActivity(), "単語を削除しました。", Toast.LENGTH_SHORT).show();
                            
                            // 再描画
                            setListView();
                        }
                    });
                alertDlg.setPositiveButton(
                    "Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Cancel ボタンクリック処理
                            // 何もしない
                        }
                    });

                // 表示
                alertDlg.create().show();
            }
        });


        // ======================
        // クリックイベント設定
        // ======================
        rootView.findViewById(R.id.btnBack).setOnClickListener(onClickBack);
        
		return rootView;
	}

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
    
    /**
     * リストビューを設定
     * @return
     */
    private ListView setListView() {
        // ======================
        // 単語取得
        // ======================
        WordDAO wordDao = new WordDAO(getActivity());
        wordList = wordDao.getItem();
        
        // ======================
        // リストビュー設定
        // ======================
        WordAdapter customAdapater = new WordAdapter(getActivity(),
                0, wordList);

        ListView listView = (ListView) rootView.findViewById(R.id.listWord);
        listView.setAdapter(customAdapater);
        
        return listView;
    }

}
