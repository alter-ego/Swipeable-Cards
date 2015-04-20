package com.andtinder.demo;

import com.andtinder.interfaces.OnCardClickListener;
import com.andtinder.interfaces.OnCardDismissedListener;
import com.andtinder.view.BindableCardStackAdapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DifferentCardBinder implements BindableCardStackAdapter.AdapterDataBinder {

    private final Activity mActivity;

    public DifferentCardBinder(Activity activity) {
        mActivity = activity;
    }

    @Override
    public boolean canBind(int position, Object model) {
        return model instanceof DifferentCard;
    }

    @Override
    public View doBind(int position, Object model, View convertView, ViewGroup parent) {

        DifferentCard differentCard = (DifferentCard) model;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mActivity);
            convertView = inflater.inflate(com.andtinder.R.layout.std_card_inner, parent, false);
            assert convertView != null;
        }

        ((ImageView) convertView.findViewById(com.andtinder.R.id.image)).setImageDrawable(mActivity.getResources().getDrawable(R.drawable.nyc_1));
        ((TextView) convertView.findViewById(com.andtinder.R.id.title)).setText(differentCard.getTitle());
        ((TextView) convertView.findViewById(com.andtinder.R.id.description)).setText(differentCard.getText());

        differentCard.setOnCardClickListener(new OnCardClickListener() {
            @Override
            public void OnClickListener() {
                Log.i("Swipeable Cards", "I am pressing the card");
            }
        });

        differentCard.setOnCardDismissedListener(new OnCardDismissedListener() {
            @Override
            public void onLike() {
                Log.i("Swipeable Cards", "I like the card");
            }

            @Override
            public void onDislike() {
                Log.i("Swipeable Cards", "I dislike the card");
            }
        });

        return convertView;
    }
}
