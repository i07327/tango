package com.example.tango;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {
	private LayoutInflater layoutInflater_;

	public WordAdapter(Context context, int textViewResourceId,
			List<Word> objects) {
		super(context, textViewResourceId, objects);
		layoutInflater_ = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    Word item = (Word) getItem(position);

		convertView = layoutInflater_.inflate(R.layout.word_list_row,
				null);

        TextView tvWord;
        tvWord = (TextView) convertView.findViewById(R.id.tvWord);
        tvWord.setText(item.getWord());

        TextView tvContent;
        tvContent = (TextView) convertView.findViewById(R.id.tvContent);
        tvContent.setText(item.getContent());

		convertView.setTag(item.getId());

		return convertView;
	}
}
