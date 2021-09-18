package com.cot.app.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author DavidJMartin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private String instrument;
    private String  reportDate;

    private double longPositions;
    private double shortPositions;
    private String changeLong;
    private String changeShort;

    private String percentageLong;
    private String percentageShort;
    private String percentageLongChange;
    private String percentageShortChange;

    private double netPositions;

    public void setInstrument(String instrument) {
        extractBlacklistedFields(instrument);
    }

    private void extractBlacklistedFields(String instrument) {
        this.instrument = instrument
            .replace(" - CHICAGO MERCANTILE EXCHANGE", "")
            .replace(" - COMMODITY EXCHANGE INC.", "")
            .replace(" - CHICAGO BOARD OF TRADE", "")

            .replace("EURO FX", "EUR")
            .replace("CANADIAN DOLLAR", "CAD")
            .replace("SWISS FRANC", "CHF")
            .replace("BRITISH POUND STERLING", "GBP")
            .replace("JAPANESE YEN", "JPY")
            .replace("AUSTRALIAN DOLLAR", "AUD")
            .replace("RUSSIAN RUBLE", "RUB")
            .replace("MEXICAN PESO", "MXN")
            .replace("SOUTH AFRICAN RAND", "ZAR")
            .replace("BITCOIN", "BTC")
            .replace("NEW ZEALAND DOLLAR", "NZD");
    }

}
