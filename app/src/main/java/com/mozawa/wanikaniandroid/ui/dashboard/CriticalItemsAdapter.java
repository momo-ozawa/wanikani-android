package com.mozawa.wanikaniandroid.ui.dashboard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.CriticalItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CriticalItemsAdapter extends RecyclerView.Adapter<CriticalItemsAdapter.CriticalItemsViewHolder> {

    private List<CriticalItem> criticalItemList;

    @Inject
    public CriticalItemsAdapter() {
        criticalItemList = new ArrayList<>();
    }

    public void setCriticalItemList(List<CriticalItem> criticalItemList) {
        this.criticalItemList = criticalItemList;
    }

    @Override
    public CriticalItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_critical, parent, false);
        return new CriticalItemsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CriticalItemsViewHolder holder, int position) {
        CriticalItem criticalItem = criticalItemList.get(position);
        holder.characterTextView.setText(criticalItem.getCharacter() + " " + criticalItem.meaning);
        holder.percentageTextView.setText(criticalItem.percentage);
    }

    @Override
    public int getItemCount() {
        return criticalItemList.size();
    }

    public class CriticalItemsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.characterTextView)
        TextView characterTextView;
        @BindView(R.id.percentageTextView)
        TextView percentageTextView;

        public CriticalItemsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
