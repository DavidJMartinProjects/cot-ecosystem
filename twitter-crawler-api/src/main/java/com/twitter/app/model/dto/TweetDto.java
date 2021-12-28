package com.twitter.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author davidjmartin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetDto {

    private long id;

    private String createdAt;
    private String text;
    private String place;
    private String user;

}
