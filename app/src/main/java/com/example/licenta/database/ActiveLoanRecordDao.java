package com.example.licenta.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.licenta.dto.ActiveLoanRecord;

import java.util.List;

@Dao
public interface ActiveLoanRecordDao {
    @Insert
    long insert(ActiveLoanRecord activeLoanRecord);

    @Query("select * from active_loan_record")
    List<ActiveLoanRecord> getAll();

    @Query("SELECT * FROM active_loan_record WHERE loan_id = :loanId")
    List<ActiveLoanRecord> getListOfRecordsByLoanId(long loanId);
}
