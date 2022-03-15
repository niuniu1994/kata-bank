package com.exalt.katabank.adapter.port.in.dto;

public record CustomResponse <T>(Integer code,String msg,T data) {
}
