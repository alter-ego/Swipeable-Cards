/**
 * AndTinder v0.1 for Android
 *
 * @Author: Enrique López Mañas <eenriquelopez@gmail.com>
 * http://www.lopez-manas.com
 *
 * TAndTinder is a native library for Android that provide a
 * Tinder card like effect. A card can be constructed using an
 * image and displayed with animation effects, dismiss-to-like
 * and dismiss-to-unlike, and use different sorting mechanisms.
 *
 * AndTinder is compatible with API Level 13 and upwards
 * @copyright: Enrique López Mañas
 * @license: Apache License 2.0
 */

package com.andtinder.demo;

import com.andtinder.model.Orientations;
import com.andtinder.view.BindableCardStackAdapter;
import com.andtinder.view.CardContainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import java.util.ArrayList;

public class DifferentCardActivity extends Activity {

    /**
     * This variable is the container that will host our cards
     */
    private CardContainer mCardContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mainlayout);

        mCardContainer = (CardContainer) findViewById(R.id.layoutview);

        BindableCardStackAdapter adapter = new BindableCardStackAdapter(this);
        adapter.registerAdapterDataBinder(new DifferentCardBinder(this));
        adapter.setWrapperBackgroundResource(android.R.color.white);

        ArrayList<Object> cards = new ArrayList<Object>();
        cards.add(new DifferentCard("Title1", "Description goes here"));
        cards.add(new DifferentCard("Title2", "Description goes here"));
        cards.add(new DifferentCard("Title3", "Description goes here"));
        cards.add(new DifferentCard("Title4", "Description goes here"));

        adapter.addAll(cards);

        mCardContainer.setMaxVisible(3);
        mCardContainer.setAdapter(adapter);
        mCardContainer.setOrientation(Orientations.Orientation.Ordered);
    }
}
