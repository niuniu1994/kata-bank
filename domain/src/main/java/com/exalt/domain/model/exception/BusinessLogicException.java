package com.exalt.domain.model.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author kainingxin
 */

@Getter
@Setter
public class BusinessLogicException extends RuntimeException{

    private String errorCode;

    private String errorMsg;

    public BusinessLogicException() {
        super();
    }

    public BusinessLogicException(BaseErrorInfoInterface errorInfoInterface){
        super(errorInfoInterface.getResultCode());
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }

    public BusinessLogicException(BaseErrorInfoInterface errorInfoInterface, Throwable cause) {
        super(errorInfoInterface.getResultCode(), cause);
        this.errorCode = errorInfoInterface.getResultCode();
        this.errorMsg = errorInfoInterface.getResultMsg();
    }


}
