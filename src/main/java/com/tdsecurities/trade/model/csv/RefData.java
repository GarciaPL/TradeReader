package com.tdsecurities.trade.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.tdsecurities.trade.model.csv.base.BaseData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RefData extends BaseData {

    @CsvBindByName(column = "topOfHouse")
    private String topOfHouse;

    @CsvBindByName(column = "segment")
    private String segment;

    @CsvBindByName(column = "viceChair")
    private String viceChair;

    @CsvBindByName(column = "globalBusiness")
    private String globalBusiness;

    @CsvBindByName(column = "Policy")
    private String policy;

    @CsvBindByName(column = "desk")
    private String desk;

    @CsvBindByName(column = "portfolio")
    private String portfolio;

    @CsvBindByName(column = "BU")
    private Long bu;

    @CsvBindByName(column = "CLINE")
    private String cline;

    @CsvBindByName(column = "Inventory")
    private String inventory;

}
