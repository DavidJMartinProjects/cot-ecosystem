package com.cot.app.backend.db.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author davidjmartin
 */
@Entity
@Table
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    private String reportDate;
    private String instrument;

    private String longPositions;
    private String shortPositions;
    private String changeLong;
    private String changeShort;

    private String percentageLong;
    private String percentageShort;
    private String percentageLongChange;
    private String percentageShortChange;

    private String netPositions;

}
