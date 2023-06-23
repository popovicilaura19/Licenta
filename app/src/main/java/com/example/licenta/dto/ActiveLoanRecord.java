package com.example.licenta.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "active_loan_record")
public class ActiveLoanRecord implements Serializable {
    @PrimaryKey
    @ColumnInfo(name = "record_id")
    private long recordId;
    @ColumnInfo(name = "loan_id")
    private long loanId;
    @ColumnInfo(name = "dateOfRecord")
    private String dateOfRecord;
    @ColumnInfo(name = "amountPaid")
    private double amountPaid;
    @ColumnInfo(name = "monthlyRate")
    private double monthlyRate;
    @ColumnInfo(name = "amountDue")
    private double amountDue;

    public ActiveLoanRecord(long loanId, long recordId, String dateOfRecord, double amountPaid, double monthlyRate, double amountDue) {
        this.loanId = loanId;
        this.recordId = recordId;
        this.dateOfRecord = dateOfRecord;
        this.amountPaid = amountPaid;
        this.monthlyRate = monthlyRate;
        this.amountDue = amountDue;
    }

    @Ignore
    public ActiveLoanRecord() {
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public String getDateOfRecord() {
        return dateOfRecord;
    }

    public void setDateOfRecord(String dateOfRecord) {
        this.dateOfRecord = dateOfRecord;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    @Override
    public String toString() {
        return "ActiveLoanRecord{" +
                "loanId=" + loanId +
                ", recordId=" + recordId +
                ", dateOfRecord='" + dateOfRecord + '\'' +
                ", amountPaid=" + amountPaid +
                ", monthlyRate=" + monthlyRate +
                ", amountDue=" + amountDue +
                '}';
    }
}
