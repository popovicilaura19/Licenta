package com.example.licenta.services;

import android.content.Context;

import com.example.licenta.async.AsyncTaskRunner;
import com.example.licenta.async.Callback;
import com.example.licenta.database.ActiveLoanRecordDao;
import com.example.licenta.database.DatabaseManager;
import com.example.licenta.dto.ActiveLoanRecord;

import java.util.List;
import java.util.concurrent.Callable;

public class ActiveLoanRecordService {
    private final ActiveLoanRecordDao activeLoanRecordDao;
    private final AsyncTaskRunner asyncTaskRunner;

    public ActiveLoanRecordService(Context context) {
        this.activeLoanRecordDao = DatabaseManager.getInstance(context).getActiveLoanRecordDao();
        asyncTaskRunner = new AsyncTaskRunner();
    }

    public void insert(ActiveLoanRecord activeLoanRecord, Callback<ActiveLoanRecord> activityThread) {
        Callable<ActiveLoanRecord> insertOperation = new Callable<ActiveLoanRecord>() {
            @Override
            public ActiveLoanRecord call() throws Exception {
                if (activeLoanRecord == null || activeLoanRecord.getLoanId() > 0) {
                    return null;
                }
                long id = activeLoanRecordDao.insert(activeLoanRecord);
                if (id < 0) {
                    return null;
                }
                activeLoanRecord.setLoanId(id);
                return activeLoanRecord;
            }
        };
        asyncTaskRunner.executeAsync(insertOperation, activityThread);
    }


    public void getAll(Callback<List<ActiveLoanRecord>> activityThread) {
        Callable<List<ActiveLoanRecord>> getAllOperation = new Callable<List<ActiveLoanRecord>>() {
            @Override
            public List<ActiveLoanRecord> call() throws Exception {
                return activeLoanRecordDao.getAll();
            }
        };
        asyncTaskRunner.executeAsync(getAllOperation, activityThread);
    }

    public void getListOfRecordsByLoanId(long loanId, Callback<List<ActiveLoanRecord>> activityThread) {
        Callable<List<ActiveLoanRecord>> getRecordListByIdOperation = new Callable<List<ActiveLoanRecord>>() {
            @Override
            public List<ActiveLoanRecord> call() throws Exception {
                if (loanId <= 0) {
                    return null;
                }
                return activeLoanRecordDao.getListOfRecordsByLoanId(loanId);
            }
        };
        asyncTaskRunner.executeAsync(getRecordListByIdOperation, activityThread);
    }
}
