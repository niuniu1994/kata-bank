package com.exalt.application.model;

import com.exalt.domain.model.enums.ExceptionEnum;
import lombok.Data;

@Data
public class CustomResponse {

     String code;

     String msg;

     Object data;

    public static CustomResponse success(Object data) {
        CustomResponse cr = new CustomResponse();
        cr.setCode(ExceptionEnum.SUCCESS.getResultCode());
        cr.setMsg(ExceptionEnum.SUCCESS.getResultMsg());
        cr.setData(data);
        return cr;
    }

    public static CustomResponse error() {
        CustomResponse cr = new CustomResponse();
        cr.setCode(ExceptionEnum.INTERNAL_SERVER_ERROR.getResultCode());
        cr.setMsg(ExceptionEnum.INTERNAL_SERVER_ERROR.getResultMsg());
        return cr;
    }


    public static CustomResponse error(String errorCode, String errorMsg) {
        CustomResponse cr = new CustomResponse();
        cr.setCode(errorCode);
        cr.setMsg(errorMsg);
        return cr;
    }
}
