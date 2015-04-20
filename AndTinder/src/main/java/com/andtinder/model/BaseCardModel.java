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

package com.andtinder.model;

import com.andtinder.interfaces.OnCardClickListener;
import com.andtinder.interfaces.OnCardDismissedListener;

public class BaseCardModel {

    private OnCardDismissedListener mOnCardDismissedListener = null;

    private OnCardClickListener mOnCardClickListener = null;

    public void setOnCardDismissedListener(OnCardDismissedListener listener) {
        mOnCardDismissedListener = listener;
    }

    public OnCardDismissedListener getOnCardDismissedListener() {
        return mOnCardDismissedListener;
    }


    public void setOnCardClickListener(OnCardClickListener listener) {
        mOnCardClickListener = listener;
    }

    public OnCardClickListener getOnCardClickListener() {
        return mOnCardClickListener;
    }
}