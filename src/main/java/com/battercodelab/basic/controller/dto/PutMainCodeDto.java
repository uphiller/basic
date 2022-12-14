package com.battercodelab.basic.controller.dto;

import lombok.Data;

public class PutMainCodeDto {
    @Data
    public static class Request {
        private String code;
        private String name;
        private String expl;
    }
}
