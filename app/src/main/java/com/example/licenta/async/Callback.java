package com.example.licenta.async;

public interface Callback<R> {

    void runResultOnUiThread(R result);
}
