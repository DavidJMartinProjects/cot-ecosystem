package cot.swap.db.entity.chart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ChartSwapEntity")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChartSwapEntity {

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
