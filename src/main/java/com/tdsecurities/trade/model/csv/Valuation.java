package com.tdsecurities.trade.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.tdsecurities.trade.model.csv.base.BaseData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class Valuation extends BaseData {

    @CsvBindByName(column = "TradeId")
    private Long tradeId;

    @CsvBindByName(column = "UQL_OC_MMB_MS")
    private BigDecimal uqlOcMmbMs;

    @CsvBindByName(column = "UQL_OC_MMB_MS_PC")
    private BigDecimal uqlOcMmbMsPc;

}
