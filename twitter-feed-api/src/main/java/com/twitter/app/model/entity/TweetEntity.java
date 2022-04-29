package com.twitter.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author davidjmartin
 */
@Entity
@Table(name = "tweet")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TweetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String createdAt;
    private String text;
    private String place;

    @Column(name = "user_")
    private String user;

}
