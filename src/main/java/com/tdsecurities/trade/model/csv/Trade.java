package com.tdsecurities.trade.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.tdsecurities.trade.model.csv.base.BaseData;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Trade extends BaseData {

    @CsvBindByName(column = "Inventory")
    private String inventory;

    @CsvBindByName(column = "Book")
    private String book;

    @CsvBindByName(column = "System")
    private String system;

    @CsvBindByName(column = "LegalEntity")
    private String legalEntity;

    @CsvBindByName(column = "TradeId")
    private Long tradeId;

    @CsvBindByName(column = "Version")
    private Long version;

    @CsvBindByName(column = "TradeStatus")
    private String tradeStatus;

    @CsvBindByName(column = "ProductType")
    private String productType;

    @CsvBindByName(column = "Resetting Leg")
    private String resettingLeg;

    @CsvBindByName(column = "ProductSubType")
    private String productSubType;

    @CsvBindByName(column = "TDSProductType")
    private String tdsProductType;

    @CsvBindByName(column = "SecCodeSubType")
    private String secCodeSubType;

    @CsvBindByName(column = "SwapType")
    private String swapType;

    @CsvBindByName(column = "Description")
    private String description;

    @CsvBindByName(column = "TradeDate")
    private String tradeDate;

    @CsvBindByName(column = "StartDate")
    private String startDate;

    @CsvBindByName(column = "MaturityDate")
    private String maturityDate;

}
