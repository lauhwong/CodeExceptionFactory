package com.miracles.exceptions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.miracles.exception.CodeException;
import com.miracles.exception.CodeExceptionFactory;
import com.miracles.exception.CodeInstaller;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CodeInstaller.newInstaller()
                .addExceptionCode("http", R.array.http)
                .codeBitLimit(4)
                .install(getApplicationContext());

    }

    String[] mCodes = {"1001", "1002", "1003", "1004"};

    public void showException(View view) {
        int index = new Random().nextInt(4);
        CodeException codeException = CodeExceptionFactory.create("http", mCodes[index]);
        String localizedMessage = codeException.getLocalizedMessage();
        Log.d(getClass().getSimpleName(), localizedMessage);
    }
}
