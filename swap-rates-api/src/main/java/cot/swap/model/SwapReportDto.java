package cot.swap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SwapReportDto {

    @Id
    @Column(name = "symbol", nullable = false)
    private String symbol;

    @Column(name = "timeStamp", nullable = false)
    private String timeStamp;

    @Column(name = "long_swap")
    private String longSwap;

    @Column(name = "short_swap")
    private String shortSwap;

}
