package com.marketpulse;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CriteriaListAdapter extends RecyclerView.Adapter<CriteriaListAdapter.ItemViewHolder> {

    final String TAG = "CriteriaListAdapter";
    private List<MarketResponseModel.Criteria> criteriaList;
    private Activity activity;

    public CriteriaListAdapter(Activity activity, List<MarketResponseModel.Criteria> items) {
        this.activity = activity;
        this.criteriaList = items;
    }

    @NonNull
    @Override
    public CriteriaListAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_criteria_list, null);
        return new CriteriaListAdapter.ItemViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull final CriteriaListAdapter.ItemViewHolder holder, int position) {
        holder.criteriaText.setText(criteriaList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return criteriaList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView criteriaText;

        ItemViewHolder(View itemView) {
            super(itemView);
            criteriaText = itemView.findViewById(R.id.criteria_text);
        }
    }
}
