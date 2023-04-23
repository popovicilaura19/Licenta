package com.example.licenta.database;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.licenta.dto.Client;

@Dao
public interface ClientDao {

    @Insert
    void insert(Client client);
}
