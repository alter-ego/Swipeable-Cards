package com.andtinder.view;

import com.andtinder.R;
import com.andtinder.model.CardModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public final class SimpleCardStackAdapter extends CardStackAdapter {

	public SimpleCardStackAdapter(Context mContext) {
		super(mContext);
	}

	@Override
	public View getCardView(int position, Object model, View convertView, ViewGroup parent) {

	    CardModel cardModel = (CardModel) model;

		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.std_card_inner, parent, false);
			assert convertView != null;
		}

		((ImageView) convertView.findViewById(R.id.image)).setImageDrawable(cardModel.getCardImageDrawable());
		((TextView) convertView.findViewById(R.id.title)).setText(cardModel.getTitle());
		((TextView) convertView.findViewById(R.id.description)).setText(cardModel.getDescription());

		return convertView;
	}
}
