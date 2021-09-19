package com.cot.app.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tweet {

    private String createdAt;
    private String text;
    private String place;
    private String user;

}
