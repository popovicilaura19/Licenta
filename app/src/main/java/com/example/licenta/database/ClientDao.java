package com.example.licenta.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.licenta.dto.Client;

import java.util.List;

@Dao
public interface ClientDao {

    @Insert
    void insert(Client client);

    @Query("select * from client")
    List<Client> getAll();

}
