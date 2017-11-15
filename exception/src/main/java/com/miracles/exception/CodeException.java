package com.miracles.exception;

/**
 * Created by lxw
 */
public class CodeException extends Exception {
    private String mCode;
    private String mLocalizedMsg;

    CodeException(String code, String message) {
        super("CodeException,code:" + code + ",msg:" + message);
        this.mCode = code;
        this.mLocalizedMsg = message;
    }

    public String getCode() {
        return mCode;
    }

    @Override
    public String getLocalizedMessage() {
        return mLocalizedMsg;
    }
}
