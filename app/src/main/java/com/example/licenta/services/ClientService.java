package com.example.licenta.services;

import android.content.Context;

import com.example.licenta.async.AsyncTaskRunner;
import com.example.licenta.async.Callback;
import com.example.licenta.database.ClientDao;
import com.example.licenta.database.DatabaseManager;
import com.example.licenta.dto.Client;

import java.util.List;
import java.util.concurrent.Callable;

public class ClientService {
    private final ClientDao clientDao;
    private final AsyncTaskRunner asyncTaskRunner;

    public ClientService(Context context) {
        this.clientDao = DatabaseManager.getInstance(context).getClientDao();
        asyncTaskRunner = new AsyncTaskRunner();
    }

    public void insert(Client client, Callback<Client> activityThread) {
        Callable<Client> insertOperation = new Callable<Client>() {
            @Override
            public Client call() throws Exception {
                if (client == null || client.getId() > 0) {
                    return null;
                }
                clientDao.insert(client);
                return client;
            }
        };
        asyncTaskRunner.executeAsync(insertOperation, activityThread);
    }

    public void getAll(Callback<List<Client>> activityThread){
        Callable<List<Client>> getAllOperation = new Callable<List<Client>>() {
            @Override
            public List<Client> call() throws Exception {
                return clientDao.getAll();
            }
        };
        asyncTaskRunner.executeAsync(getAllOperation, activityThread);
    }
}
