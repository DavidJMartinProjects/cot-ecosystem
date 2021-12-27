package com.cot.app.backend.swap;

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
public class SwapDto {

    private String symbol;
    private String longSwap;
    private String shortSwap;

}
