package com.cot.app.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
    private String reportDate;

    private double longPositions;
    private double shortPositions;
    private String changeLong;
    private String changeShort;

    private String percentageLong;
    private String percentageShort;
    private String percentageLongChange;
    private String percentageShortChange;

    private double netPositions;
    private double netPositionsChange;

    public void translateSymbol(String instrument) {
        this.instrument = instrument
            .replace(" - CHICAGO MERCANTILE EXCHANGE", "")
            .replace(" - COMMODITY EXCHANGE INC.", "")
            .replace(" - CHICAGO BOARD OF TRADE", "")
            .replace(" - ICE FUTURES U.S.", "")
            .replace("EURO FX", "EUR")
            .replace("CANADIAN DOLLAR", "CAD")
            .replace("SWISS FRANC", "CHF")
            .replace("BRITISH POUND STERLING", "GBP")
            .replace("BRITISH POUND", "GBP")
            .replace("JAPANESE YEN", "JPY")
            .replace("AUSTRALIAN DOLLAR", "AUD")
            .replace("RUSSIAN RUBLE", "RUB")
            .replace("MEXICAN PESO", "MXN")
            .replace("SOUTH AFRICAN RAND", "ZAR")
            .replace("BITCOIN", "BTC")
            .replace("NEW ZEALAND DOLLAR", "NZD")
            .replace("NZ DOLLAR", "NZD")
            .replace("USD INDEX", "USD")
            .replace("U.S. DOLLAR INDEX", "USD");            
    }

}