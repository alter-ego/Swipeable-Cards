package com.andtinder.view;

import com.andtinder.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class CardStackAdapter extends BaseAdapter {

    public interface AdapterEmptyEvent {
	void onAdapterEmpty();
	void onAdapterNotEmpty();
    }

    private final Context mContext;

    /**
     * Lock used to modify the content of {@link #mData}. Any write operation
     * performed on the deque should be synchronized on this lock.
     */
    private final Object mLock = new Object();

    private List<Object> mData;

    private int mWrapperBackgroundResource = R.drawable.card_bg;

    private AdapterEmptyEvent mAdapterEmptyEventListener;

    public CardStackAdapter(Context context) {
	mContext = context;
	mData = new LinkedList<>();
	notifyAdapterEmptyEventListener();
    }

    public CardStackAdapter(Context context, Collection<Object> items) {
	mContext = context;
	mData = new LinkedList<Object>(items);
	notifyAdapterEmptyEventListener();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
	FrameLayout wrapper = (FrameLayout) convertView;
	FrameLayout innerWrapper;
	View cardView;
	View convertedCardView;
	if (wrapper == null) {
	    wrapper = new FrameLayout(mContext);
	    wrapper.setBackgroundResource(mWrapperBackgroundResource);
	    if (shouldFillCardBackground()) {
		innerWrapper = new FrameLayout(mContext);
		innerWrapper.setBackgroundColor(mContext.getResources().getColor(R.color.card_bg));
		wrapper.addView(innerWrapper);
	    } else {
		innerWrapper = wrapper;
	    }
	    cardView = getCardView(position, getCardModel(position), null, parent);
	    innerWrapper.addView(cardView);
	} else {
	    if (shouldFillCardBackground()) {
		innerWrapper = (FrameLayout) wrapper.getChildAt(0);
	    } else {
		innerWrapper = wrapper;
	    }
	    cardView = innerWrapper.getChildAt(0);
	    convertedCardView = getCardView(position, getCardModel(position), cardView, parent);
	    if (convertedCardView != cardView) {
		wrapper.removeView(cardView);
		wrapper.addView(convertedCardView);
	    }
	}

	return wrapper;
    }

    protected abstract View getCardView(int position, Object model, View convertView, ViewGroup parent);

    public boolean shouldFillCardBackground() {
	return true;
    }

    public void add(Object item) {
	synchronized (mLock) {
	    mData.add(item);
	}
	notifyDataSetChanged();
	notifyAdapterEmptyEventListener();
    }

    public void addAll(List<Object> items) {
	synchronized (mLock) {
	    mData.addAll(items);
	}
	notifyDataSetChanged();
	notifyAdapterEmptyEventListener();
    }

    public Object pop() {
	Object model;
	synchronized (mLock) {
	    model = mData.remove(mData.size() - 1);
	}
	notifyDataSetChanged();
	notifyAdapterEmptyEventListener();
	return model;
    }

    @Override
    public Object getItem(int position) {
	return getCardModel(position);
    }

    public Object getCardModel(int position) {
	synchronized (mLock) {
	    return mData.get(mData.size() - 1 - position);
	}
    }

    @Override
    public int getCount() {
	return mData.size();
    }

    @Override
    public long getItemId(int position) {
	return getItem(position).hashCode();
    }

    public Context getContext() {
	return mContext;
    }

    @Override
    public boolean hasStableIds() {
	return true;
    }

    public void setWrapperBackgroundResource(int resourceId) {
	mWrapperBackgroundResource = resourceId;
    }

    private void notifyAdapterEmptyEventListener() {
	if (mAdapterEmptyEventListener == null)
	    return;

	if (mData != null && mData.size() > 0)
	    mAdapterEmptyEventListener.onAdapterNotEmpty();
	else
	    mAdapterEmptyEventListener.onAdapterEmpty();
    }

    public void setAdapterEmptyEventListener(AdapterEmptyEvent listener) {
	mAdapterEmptyEventListener = listener;
    }
}
