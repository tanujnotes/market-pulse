package com.marketpulse;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        HashMap<String, MarketResponseModel.Variable> variableHashMap = criteriaList.get(position).getVariable();
        String criteriaText = criteriaList.get(position).getText();

        Pattern pattern = Pattern.compile("\\$\\d");
        Matcher matcher = pattern.matcher(criteriaText);
        while (matcher.find()) {
            String replacedValue = "";
            String match = matcher.group();
            MarketResponseModel.Variable variable = variableHashMap.get(match);
            if (variable.getType().equalsIgnoreCase("indicator")) {
                replacedValue = variable.getDefaultValue().toString();
            } else if (variable.getType().equalsIgnoreCase("value")) {
                replacedValue = variable.getValues().get(0).toString();
            }
            criteriaText = criteriaText.replace(match, replacedValue);
        }

        holder.criteriaText.setText(criteriaText);
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
