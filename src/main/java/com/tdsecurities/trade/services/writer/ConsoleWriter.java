package com.tdsecurities.trade.services.writer;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.ColumnData;
import com.github.freva.asciitable.HorizontalAlign;
import com.tdsecurities.trade.model.output.TradeAggregator;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ConsoleWriter implements WriterStrategy {

    @Override
    public void write(List<TradeAggregator> aggregatedTrades) {

        List<ColumnData<TradeAggregator>> columnsData = Arrays.asList(
                new Column().header("topOfHouse").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getTopOfHouse()),
                new Column().header("segment").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getSegment()),
                new Column().header("viceChair").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getViceChair()),
                new Column().header("globalBusiness").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getGlobalBusiness()),
                new Column().header("Policy").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getPolicy()),
                new Column().header("desk").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getDesk()),
                new Column().header("portfolio").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getPortfolio()),
                new Column().header("BU").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getBU()),
                new Column().header("CLINE").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getCLINE()),
                new Column().header("Inventory").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getInventory()),
                new Column().header("Book").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getBook()),
                new Column().header("System").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getSystem()),
                new Column().header("LegalEntity").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getLegalEntity()),
                new Column().header("TradeId").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getTradeId()),
                new Column().header("Version").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getVersion()),
                new Column().header("TradeStatus").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getTradeStatus()),
                new Column().header("ProductType").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getProductType()),
                new Column().header("Resetting Leg").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getResettingLeg()),
                new Column().header("ProductSubType").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getProductSubType()),
                new Column().header("TDSProductType").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getTdsProductType()),
                new Column().header("SecCodeSubType").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getSecCodeSubType()),
                new Column().header("SwapType").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getSwapType()),
                new Column().header("Description").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getDescription()),
                new Column().header("TradeDate").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getTradeDate()),
                new Column().header("StartDate").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getStartDate()),
                new Column().header("MaturityDate").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getMaturityDate()),
                new Column().header("UQL_OC_MMB_MS").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getUqlOcMmbMs()),
                new Column().header("UQL_OC_MMB_MS_PC").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getUqlOcMmbMsPc()),
                new Column().header("MS-PC").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getMsPc()),
                new Column().header("BreakStatus").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getBreakStatus()),
                new Column().header("Term").headerAlign(HorizontalAlign.CENTER).with(DataFunctors.getTerm())
        );

        System.out.println(AsciiTable.getTable(aggregatedTrades, columnsData));
    }

    @Override
    public OutputType getOutputType() {
        return OutputType.CONSOLE;
    }

}

