package com.andtinder.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

public final class BindableCardStackAdapter extends CardStackAdapter {

    private List<AdapterDataBinder> mAdapterDataBinders;

    public interface AdapterDataBinder {

        public boolean canBind(int position, Object model);

        public View doBind(int position, Object model, View convertView, ViewGroup parent);

    }

    public BindableCardStackAdapter(Context mContext) {
        super(mContext);
        mAdapterDataBinders = new LinkedList<>();
    }

    public void registerAdapterDataBinder(AdapterDataBinder adapterDataBinder) {
        if (!mAdapterDataBinders.contains(adapterDataBinder)) {
            mAdapterDataBinders.add(adapterDataBinder);
        }
    }

    public void unregisterAdapterDataBinder(AdapterDataBinder adapterDataBinder) {
        if (mAdapterDataBinders.contains(adapterDataBinder)) {
            mAdapterDataBinders.remove(adapterDataBinder);
        }
    }

    @Override
    public View getCardView(int position, Object model, View convertView, ViewGroup parent) {

        for (AdapterDataBinder binder : mAdapterDataBinders) {
            if (binder.canBind(position, model)) {
                return binder.doBind(position, model, convertView, parent);
            }
        }

        return convertView;
    }
}
