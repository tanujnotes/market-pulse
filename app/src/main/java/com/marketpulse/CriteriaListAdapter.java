package com.marketpulse;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
        SpannableStringBuilder criteriaText = new SpannableStringBuilder(criteriaList.get(position).getText());
//        Today's Volume > prev $2 Vol SMA by $3 x
        Pattern pattern = Pattern.compile("\\$\\d");
        Matcher matcher = pattern.matcher(criteriaText);
        int delta = 0;

        while (matcher.find()) {
            final int start = matcher.start() + delta;
            String replacedValue = "";
            String match = matcher.group();

            MarketResponseModel.Variable variable = variableHashMap.get(match);
            if (variable.getType().equalsIgnoreCase("indicator")) {
                replacedValue = variable.getDefaultValue().toString();
            } else if (variable.getType().equalsIgnoreCase("value")) {
                replacedValue = variable.getValues().get(0).toString();
            }
//            criteriaText = criteriaText.replace(match, replacedValue);
            criteriaText = criteriaText.replace(start, start + match.length(), replacedValue);
            final int end = start + replacedValue.length();

            criteriaText.setSpan(new ClickableSpan() {

                @Override
                public void onClick(@NonNull View widget) {
                    Toast.makeText(activity, "Click", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            delta += match.length();
        }

        holder.criteriaText.setText(criteriaText);
        holder.criteriaText.setMovementMethod(LinkMovementMethod.getInstance());
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
