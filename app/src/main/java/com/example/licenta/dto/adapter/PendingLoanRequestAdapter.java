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

public class PendingLoanRequestAdapter extends ArrayAdapter<LoanRequest> {

    private Context context;
    private int resource;
    private List<LoanRequest> loanRequestList;
    private LayoutInflater inflater;

    public PendingLoanRequestAdapter(@NonNull Context context, int resource, @NonNull List<LoanRequest> objects, LayoutInflater inflater) {
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
        addCreditType(loanRequest.getCreditType().toString(), view);
        addAmount(loanRequest.getTotalAmount(), view);
        addPeriod(loanRequest.getPeriod(), view);
        addInterestRate(loanRequest.getInterestRate(), view);

        return view;
    }

    private void addLoanId(long loanId, View view) {
        TextView textView = view.findViewById(R.id.id_tv_idPendingLoan);
        textView.append(": " + loanId);
    }

    private void addCreditType(String creditType, View view) {
        TextView textView = view.findViewById(R.id.id_tv_creditTypePending);
        textView.append(": " + creditType);
    }

    private void addAmount(long amount, View view) {
        TextView textView = view.findViewById(R.id.id_tv_pendingAmount);
        textView.append(": " + calculateTextViewValue(String.valueOf(amount)));
    }

    private void addPeriod(int period, View view) {
        TextView textView = view.findViewById(R.id.id_tv_pendingPeriod);
        textView.append(" " + calculateTextViewValue(String.valueOf(period)));
    }

    private void addInterestRate(float interestRate, View view) {
        TextView textView = view.findViewById(R.id.id_tv_pendingInterestRate);
        textView.append(": " + calculateTextViewValue(String.valueOf(interestRate)));
    }

    private String calculateTextViewValue(String value) {
        if (value == null || value.isEmpty()) {
            return context.getString(R.string.lv_row_item_default_value);
        }

        return value;
    }

}

