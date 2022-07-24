package com.exalt.domain.model.exception;

/**
 * @author kainingxin
 */
public interface BaseErrorInfoInterface {

    /**
     * error code
     * @return
     */
    String getResultCode();

    /**
     * error msg
     * @return
     */
    String getResultMsg();
}
