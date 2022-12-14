package com.battercodelab.basic.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

public class GetMainCodeHistoryDto {
    @Data
    public static class Response {
        private String name;
        private String expl;
        private Member modified;
        private String revType;
        private LocalDateTime modifiedAt;
    }

    @Data
    public static class Member {
        private Long idx;
        private String id;
        private String name;
    }
}
