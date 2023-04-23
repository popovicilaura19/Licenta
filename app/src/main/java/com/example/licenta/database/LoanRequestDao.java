package com.example.licenta.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.licenta.dto.LoanRequest;

import java.util.List;

@Dao
public interface LoanRequestDao {
    @Insert
    long insert(LoanRequest loanRequest);

    @Query("select * from loan_request")
    List<LoanRequest> getAll();
}
