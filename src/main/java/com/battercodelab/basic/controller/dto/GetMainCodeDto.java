package com.battercodelab.basic.controller.dto;

import lombok.Data;

public class GetMainCodeDto {

    @Data
    public static class Response {
        private String code;
        private String name;
        private String expl;
    }
}
