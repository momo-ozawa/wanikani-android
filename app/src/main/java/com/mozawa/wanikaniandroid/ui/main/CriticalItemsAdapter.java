package com.mozawa.wanikaniandroid.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mozawa.wanikaniandroid.R;
import com.mozawa.wanikaniandroid.data.model.CriticalItems;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CriticalItemsAdapter extends RecyclerView.Adapter<CriticalItemsAdapter.CriticalItemsViewHolder> {

    private List<CriticalItems.CriticalItemInformation> criticalItemInformationList;

    public CriticalItemsAdapter() {
        criticalItemInformationList = new ArrayList<>();
    }

    public void setCriticalItemInformationList(List<CriticalItems.CriticalItemInformation> criticalItemInformationList) {
        this.criticalItemInformationList = criticalItemInformationList;
    }

    @Override
    public CriticalItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_critical, parent, false);
        return new CriticalItemsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CriticalItemsViewHolder holder, int position) {
        CriticalItems.CriticalItemInformation criticalItemInformation = criticalItemInformationList.get(position);
        holder.characterTextView.setText(criticalItemInformation.getCharacter());
        holder.percentageTextView.setText(criticalItemInformation.percentage);
    }

    @Override
    public int getItemCount() {
        return criticalItemInformationList.size();
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
