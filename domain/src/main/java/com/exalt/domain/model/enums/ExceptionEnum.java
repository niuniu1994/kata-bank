package com.exalt.domain.model.enums;

import com.exalt.domain.model.exception.BaseErrorInfoInterface;

/**
 * @author kainingxin
 */
public enum ExceptionEnum implements BaseErrorInfoInterface {
    UNDEFINED_ACCOUNT("400", "Undefined account"),
    ILLEGAL_MONEY("400", "Illegal money format"),
    ILLEGAL_MONEY_AMOUNT("400", "Illegal money amount"),


    INTERNAL_SERVER_ERROR("500", "Internal server error"),
    NOT_FOUND("404", "Resource not found!"),
    SUCCESS("200", "Success");

    private final String resultCode;

    private final String resultMsg;

    ExceptionEnum(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }

}
