package com.example.licenta.services;

import android.content.Context;

import com.example.licenta.async.AsyncTaskRunner;
import com.example.licenta.async.Callback;
import com.example.licenta.database.DatabaseManager;
import com.example.licenta.database.LoanRequestDao;
import com.example.licenta.dto.LoanRequest;

import java.util.List;
import java.util.concurrent.Callable;

public class LoanRequestService {
    private final LoanRequestDao loanRequestDao;
    private final AsyncTaskRunner asyncTaskRunner;

    public LoanRequestService(Context context) {
        this.loanRequestDao = DatabaseManager.getInstance(context).getLoanRequestDao();
        asyncTaskRunner = new AsyncTaskRunner();
    }

    public void insert(LoanRequest loanRequest, Callback<LoanRequest> activityThread) {
        Callable<LoanRequest> insertOperation = new Callable<LoanRequest>() {
            @Override
            public LoanRequest call() throws Exception {
                if (loanRequest == null || loanRequest.getRequestId() > 0) {
                    return null;
                }
                long id = loanRequestDao.insert(loanRequest);
                if (id < 0) {
                    return null;
                }
                loanRequest.setRequestId(id);
                return loanRequest;
            }
        };
        asyncTaskRunner.executeAsync(insertOperation, activityThread);
    }



    public void getAll(Callback<List<LoanRequest>> activityThread){
        Callable<List<LoanRequest>> getAllOperation = new Callable<List<LoanRequest>>() {
            @Override
            public List<LoanRequest> call() throws Exception {
                return loanRequestDao.getAll();
            }
        };
        asyncTaskRunner.executeAsync(getAllOperation, activityThread);
    }

    public void getListOfLoansByClientId(long clientId, Callback<List<LoanRequest>> activityThread) {
        Callable<List<LoanRequest>> getLoanListByIdOperation = new Callable<List<LoanRequest>>() {
            @Override
            public List<LoanRequest> call() throws Exception {
                if (clientId <= 0) {
                    return null;
                }
                return loanRequestDao.getListOfLoansByClientId(clientId);
            }
        };
        asyncTaskRunner.executeAsync(getLoanListByIdOperation, activityThread);
    }
}
