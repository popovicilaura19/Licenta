package com.example.licenta.services;

import android.content.Context;

import com.example.licenta.async.AsyncTaskRunner;
import com.example.licenta.async.Callback;
import com.example.licenta.database.DatabaseManager;
import com.example.licenta.database.UserDao;
import com.example.licenta.dto.User;

import java.util.List;
import java.util.concurrent.Callable;

public class UserService {

    private final UserDao userDao;
    private final AsyncTaskRunner asyncTaskRunner;

    public UserService(Context context) {
        this.userDao = DatabaseManager.getInstance(context).getUserDao();
        asyncTaskRunner = new AsyncTaskRunner();
    }

    public void insert(User user, Callback<User> activityThread) {
        Callable<User> insertOperation = new Callable<User>() {
            @Override
            public User call() throws Exception {
                if (user == null || user.getUserId() > 0) {
                    return null;
                }
                long id = userDao.insert(user);
                if (id < 0) {
                    return null;
                }
                user.setUserId(id);
                return user;
            }
        };
        asyncTaskRunner.executeAsync(insertOperation, activityThread);
    }

    public void getAll(Callback<List<User>> activityThread){
        Callable<List<User>> getAllOperation = new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return userDao.getAll();
            }
        };
        asyncTaskRunner.executeAsync(getAllOperation, activityThread);
    }
}
