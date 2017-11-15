package com.miracles.exception;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxw
 */
public class CodeInstaller {
    Context mAppContext;
    Map<String, List<Integer>> mCodeMapping;
    Map<String, Map<String, String>> mLocalizedMapping;
    int mCodeBitLimit = 4;

    private CodeInstaller() {
        mCodeMapping = new HashMap<>();
        mLocalizedMapping = new HashMap<>();
    }

    public static CodeInstaller newInstaller() {
        return new CodeInstaller();
    }

    public CodeInstaller codeBitLimit(int bitLimit) {
        this.mCodeBitLimit = bitLimit;
        return this;
    }

    public CodeInstaller addExceptionCode(String exception, @ArrayRes int codeArray) {
        List<Integer> arrays = mCodeMapping.get(exception);
        if (arrays == null) {
            arrays = new ArrayList<>();
            mCodeMapping.put(exception, arrays);
        }
        arrays.add(codeArray);
        return this;
    }

    public void install(@NonNull Context context) {
        this.mAppContext = context.getApplicationContext();
        CodeExceptionFactory.sCodeInstaller = this;
    }
}
