package com.marketpulse;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
        Pattern pattern = Pattern.compile("\\$\\d");
        Matcher matcher = pattern.matcher(criteriaText);
        int delta = 0;

        while (matcher.find()) {
            final int start = matcher.start() + delta;
            Double replacedValue = 0.0;
            String match = matcher.group();

            final MarketResponseModel.Variable variable = variableHashMap.get(match);
            if (variable.getType().equalsIgnoreCase("indicator")) {
                replacedValue = Double.valueOf(variable.getDefaultValue());
            } else if (variable.getType().equalsIgnoreCase("value")) {
                replacedValue = variable.getValues().get(0);
            }

            String replacedValueFormatted;
            if (replacedValue % 1 == 0)
                replacedValueFormatted = String.format(Locale.getDefault(), "(%d)", replacedValue.intValue());
            else
                replacedValueFormatted = String.format(Locale.getDefault(), "(%.1f)", replacedValue);

            criteriaText = criteriaText.replace(start, start + match.length(), replacedValueFormatted);
            final int end = start + replacedValueFormatted.length();

            criteriaText.setSpan(new ClickableSpan() {

                @Override
                public void onClick(@NonNull View widget) {
                    if (variable.getType().equalsIgnoreCase("indicator")) {
                        showIndicatorVariableDialog(variable.getDefaultValue(), variable.getMinValue(), variable.getMaxValue());
                    } else if (variable.getType().equalsIgnoreCase("value")) {
                        showVariableValueList(variable.getValues());
                    }
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

        if (holder.getAdapterPosition() == getItemCount() - 1)
            holder.andDividerText.setVisibility(View.GONE);
        else
            holder.andDividerText.setVisibility(View.VISIBLE);
    }

    private void showIndicatorVariableDialog(double defaultValue, double minValue, double maxValue) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.indicator_dialog_layout, null);
        dialogBuilder.setView(dialogView);

        final EditText editText = dialogView.findViewById(R.id.indicator_value);
        editText.setText(String.valueOf(defaultValue));
        // TODO: Restrict the minimum and maximum value

        dialogBuilder.setTitle("Set Value");
        dialogBuilder.setMessage(String.format(activity.getString(R.string.indicator_dialog_message), minValue, maxValue));
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // TODO: Set the value
                dialog.dismiss();
            }
        });
        dialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    private void showVariableValueList(final List<Double> values) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Please select");
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(
                activity,
                android.R.layout.simple_list_item_1,
                values
        );

        builder.setSingleChoiceItems(arrayAdapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(activity, values.get(i).toString(), Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return criteriaList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView criteriaText, andDividerText;

        ItemViewHolder(View itemView) {
            super(itemView);
            criteriaText = itemView.findViewById(R.id.criteria_text);
            andDividerText = itemView.findViewById(R.id.and_divider_text);
        }
    }
}
