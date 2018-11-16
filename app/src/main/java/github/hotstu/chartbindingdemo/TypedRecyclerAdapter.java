package github.hotstu.chartbindingdemo;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


public class TypedRecyclerAdapter extends RecyclerView.Adapter {
    final List mList;

    public TypedRecyclerAdapter() {
        mList = new ArrayList();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_chart, parent, false);
        return new BindingViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BindingViewHolder) holder).getBinding().setVariable(BR.data, getItem(position));
        ((BindingViewHolder) holder).getBinding().executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public final void setDataSet(List list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public final void clearDataSet() {
        notifyItemRangeRemoved(0, mList.size());
        mList.clear();
    }

    public final Object getItem(int position) {
        return mList.get(position);
    }

    public final void addItem(Object object) {
        mList.add(object);
        notifyItemInserted(mList.size() - 1);
    }

    public final void addItem(Object object, int position) {
        mList.add(position, object);
        notifyItemInserted(position);
    }

    public final void addItems(List list) {
        int start = mList.size();
        mList.addAll(list);
        notifyItemRangeInserted(start, list.size());
    }

    public final void removeItem(int position) {
        if (position == RecyclerView.NO_POSITION) {
            return;
        }
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public final int findItem(Object item) {
        int position = RecyclerView.NO_POSITION;
        for (int i = 0; i < mList.size(); i++) {
            if (item.equals(mList.get(i))) {
                position = i;
                break;
            }
        }
        return position;
    }

    public final void removeItem(Object item) {
        int position = findItem(item);
        if (position != RecyclerView.NO_POSITION) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }


    public final void moveItem(int fromPostion, int toPositon) {
        //Log.d(TAG, "moveItem() called with: " + "fromPostion = [" + fromPostion + "], toPositon = [" + toPositon + "]");
        Collections.swap(mList, fromPostion, toPositon);
        notifyItemMoved(fromPostion, toPositon);
    }
}