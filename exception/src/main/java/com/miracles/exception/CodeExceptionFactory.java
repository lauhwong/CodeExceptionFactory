package com.miracles.exception;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lxw
 */
public class CodeExceptionFactory {
    @SuppressLint("StaticFieldLeak") static CodeInstaller sCodeInstaller;

    private CodeExceptionFactory() {

    }

    public static CodeException create(String type, String code) throws UnsupportedOperationException {
        return new CodeException(code, getLocalizedMsg(type, code));
    }

    public static CodeException splitCreate(String code, String localizeMsg) {
        return new CodeException(code, localizeMsg);
    }

    public static CodeException create(String type, String code, Throwable ex) throws UnsupportedOperationException {
        return (CodeException) new CodeException(code, getLocalizedMsg(type, code)).initCause(ex);
    }

    public static CodeException splitCreate(String code, String localizeMsg, Throwable ex) {
        return (CodeException) new CodeException(code, localizeMsg).initCause(ex);
    }

    @NonNull
    private static String getLocalizedMsg(String type, String code) {
        if (sCodeInstaller == null) {
            throw new UnsupportedOperationException("please install CodeInstaller first!");
        }
        String localizedMsg = null;
        Map<String, Map<String, String>> mLocalizedMapping = sCodeInstaller.mLocalizedMapping;
        Map<String, String> localized = mLocalizedMapping.get(type);
        if (localized != null) {
            localizedMsg = localized.get(code);
        } else {
            Pattern pattern = Pattern.compile("(\\d{" + sCodeInstaller.mCodeBitLimit + "}):(.*)");
            Map<String, List<Integer>> mCodeMapping = sCodeInstaller.mCodeMapping;
            List<Integer> localizedList = mCodeMapping.get(type);
            if (localizedList != null) {
                HashMap<String, String> innerLocalizedMap = new HashMap<>();
                mLocalizedMapping.put(type, innerLocalizedMap);
                for (Integer aId : localizedList) {
                    String[] locals = sCodeInstaller.mAppContext.getResources().getStringArray(aId);
                    for (String local : locals) {
                        Matcher matcher = pattern.matcher(local);
                        if (matcher.matches()) {
                            String lCode = matcher.group(1);
                            String lMsg = matcher.group(2);
                            innerLocalizedMap.put(lCode, lMsg);
                        }
                    }
                }
                localizedMsg = innerLocalizedMap.get(code);
            }
        }
        if (localizedMsg == null) {
            throw new UnsupportedOperationException("there is no mapping local msg for code:" + code);
        }
        return localizedMsg;
    }
}
