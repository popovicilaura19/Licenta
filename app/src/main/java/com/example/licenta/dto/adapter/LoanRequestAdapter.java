package com.example.licenta.dto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.licenta.R;
import com.example.licenta.dto.LoanRequest;

import java.util.List;

public class LoanRequestAdapter extends ArrayAdapter<LoanRequest> {

    private Context context;
    private int resource;
    private List<LoanRequest> loanRequestList;
    private LayoutInflater inflater;

    public LoanRequestAdapter(@NonNull Context context, int resource, @NonNull List<LoanRequest> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.loanRequestList = objects;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        LoanRequest loanRequest = loanRequestList.get(position);

        addLoanId(loanRequest.getRequestId(), view);
        addTotalAmount(loanRequest.getTotalAmount(), view);
        addInterestDue((long) (loanRequest.getTotalAmount() * loanRequest.getInterestRate() / 100), view);
        addDueBy(loanRequest.getPeriod(), view);

        return view;
    }

    private void addLoanId(long loanId, View view) {
        TextView textView = view.findViewById(R.id.id_tv_idLoan);
        textView.append(" " + loanId);
    }

    private void addTotalAmount(long totalAmount, View view) {
        TextView textView = view.findViewById(R.id.id_tv_amountDue);
        textView.append(" " + calculateTextViewValue(String.valueOf(totalAmount)));
    }

    private void addInterestDue(long interestDue, View view) {
        TextView textView = view.findViewById(R.id.id_tv_interestDue);
        textView.append(" " + calculateTextViewValue(String.valueOf(interestDue)));
    }

    private void addDueBy(int period, View view) {
        TextView textView = view.findViewById(R.id.id_tv_dueBy);
        int nrYears = period / 12;
        int expirationMonth = 6;
        int expirationYear = 2023 + nrYears;
        String value = context.getString(R.string.active_loan_due_by_template, expirationMonth, expirationYear);
        textView.append(" " + value);
    }

    private String calculateTextViewValue(String value) {
        if (value == null || value.isEmpty()) {
            return context.getString(R.string.lv_row_item_default_value);
        }

        return value;
    }

}
