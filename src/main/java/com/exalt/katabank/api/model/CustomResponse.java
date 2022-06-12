package com.exalt.katabank.api.model;

public record CustomResponse <T>(Integer code,String msg,T data) {
}
