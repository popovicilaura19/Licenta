package com.example.licenta.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.licenta.utils.CreditType;
import com.example.licenta.utils.FamilySituation;
import com.example.licenta.utils.Occupation;
import com.example.licenta.utils.RequestStatus;

import java.io.Serializable;

@Entity(tableName = "loan_request")
public class LoanRequest implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "request_id")
    private long requestId;
    @ColumnInfo(name = "client_id")
    private long clientId;
    @ColumnInfo(name = "status")
    private RequestStatus status;
    @ColumnInfo(name = "creditType")
    private CreditType creditType;
    @ColumnInfo(name = "totalAmount")
    private long totalAmount;
    @ColumnInfo(name = "period")
    private int period;
    @ColumnInfo(name = "interestRate")
    private float interestRate;
    @ColumnInfo(name = "IBAN")
    private String IBAN;
    @ColumnInfo(name = "nrKids")
    private int nrKids;
    @ColumnInfo(name = "familySituation")
    private FamilySituation familySituation;
    @ColumnInfo(name = "occupation")
    private Occupation occupation;
    @ColumnInfo(name = "dateOfEmployment")
    private String dateOfEmployment;
    @ColumnInfo(name = "monthlyIncome")
    private long monthlyIncome;

    public LoanRequest(long clientId, CreditType creditType, long totalAmount, int period, float interestRate, String IBAN, int nrKids, FamilySituation familySituation, Occupation occupation, String dateOfEmployment, long monthlyIncome) {
        this.clientId = clientId;
        this.creditType = creditType;
        this.totalAmount = totalAmount;
        this.period = period;
        this.interestRate = interestRate;
        this.IBAN = IBAN;
        this.nrKids = nrKids;
        this.familySituation = familySituation;
        this.occupation = occupation;
        this.dateOfEmployment = dateOfEmployment;
        this.monthlyIncome = monthlyIncome;
    }

    @Ignore
    public LoanRequest(long requestId, long clientId, RequestStatus status, CreditType creditType, long totalAmount, int period, float interestRate, String IBAN, int nrKids, FamilySituation familySituation, Occupation occupation, String dateOfEmployment, long monthlyIncome) {
        this.requestId = requestId;
        this.clientId = clientId;
        this.status = status;
        this.creditType = creditType;
        this.totalAmount = totalAmount;
        this.period = period;
        this.interestRate = interestRate;
        this.IBAN = IBAN;
        this.nrKids = nrKids;
        this.familySituation = familySituation;
        this.occupation = occupation;
        this.dateOfEmployment = dateOfEmployment;
        this.monthlyIncome = monthlyIncome;
    }

    @Ignore
    public LoanRequest(long clientId){
        this.clientId=clientId;
    }

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public CreditType getCreditType() {
        return creditType;
    }

    public void setCreditType(CreditType creditType) {
        this.creditType = creditType;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public int getNrKids() {
        return nrKids;
    }

    public void setNrKids(int nrKids) {
        this.nrKids = nrKids;
    }

    public FamilySituation getFamilySituation() {
        return familySituation;
    }

    public void setFamilySituation(FamilySituation familySituation) {
        this.familySituation = familySituation;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public String getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(String dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public long getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(long monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "requestId=" + requestId +
                ", clientId=" + clientId +
                ", status=" + status +
                ", creditType=" + creditType +
                ", totalAmount=" + totalAmount +
                ", period=" + period +
                ", interestRate=" + interestRate +
                ", IBAN='" + IBAN + '\'' +
                ", nrKids=" + nrKids +
                ", familySituation=" + familySituation +
                ", occupation=" + occupation +
                ", dateOfEmployment='" + dateOfEmployment + '\'' +
                ", monthlyIncome=" + monthlyIncome +
                '}';
    }
}
