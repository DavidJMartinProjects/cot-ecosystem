package cot.swap.model;

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

    private long id;

    private String symbol;
    private double longSwap;
    private double shortSwap;

}
