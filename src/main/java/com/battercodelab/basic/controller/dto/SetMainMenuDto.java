package com.battercodelab.basic.controller.dto;

import lombok.Data;

public class SetMainMenuDto {
    @Data
    public static class Request {
        private String name;
        private String expl;
    }
}
