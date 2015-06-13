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

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
public class BaseCardModel {

    @Getter
    @Setter
    private OnCardDismissedListener mOnCardDismissedListener = null;


    @Getter
    @Setter
    private OnCardClickListener mOnCardClickListener = null;

}