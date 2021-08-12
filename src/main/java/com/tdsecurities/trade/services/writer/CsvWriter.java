package com.tdsecurities.trade.services.writer;

import com.opencsv.CSVWriter;
import com.tdsecurities.trade.exceptions.CsvWritingException;
import com.tdsecurities.trade.model.output.TradeAggregator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.tdsecurities.trade.services.writer.DataFunctors.*;

@Slf4j
@Service
public class CsvWriter implements WriterStrategy {

    public static final String OUTPUT_CSV = "./output.csv";

    @Override
    public void write(List<TradeAggregator> aggregatedTrades) {
        try {

            CSVWriter csvWriter = new CSVWriter(
                    new FileWriter(OUTPUT_CSV, false),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END
            );

            String[] headerRecord = {"topOfHouse", "segment", "viceChair", "globalBusiness", "Policy", "desk", "portfolio", "BU",
                    "CLINE", "Inventory", "Book", "System", "LegalEntity", "TradeId", "Version", "TradeStatus", "ProductType",
                    "Resetting Leg", "ProductSubType", "TDSProductType", "SecCodeSubType", "SwapType", "Description", "TradeDate",
                    "StartDate", "MaturityDate", "UQL_OC_MMB_MS", "UQL_OC_MMB_MS_PC", "MS-PC", "BreakStatus", "Term"};
            csvWriter.writeNext(headerRecord);

            aggregatedTrades.forEach(tradeAggregator -> {
                String[] data = {
                        getTopOfHouse().apply(tradeAggregator),
                        getSegment().apply(tradeAggregator),
                        getViceChair().apply(tradeAggregator),
                        getGlobalBusiness().apply(tradeAggregator),
                        getPolicy().apply(tradeAggregator),
                        getDesk().apply(tradeAggregator),
                        getPortfolio().apply(tradeAggregator),
                        getBU().apply(tradeAggregator),
                        getCLINE().apply(tradeAggregator),
                        getInventory().apply(tradeAggregator),
                        getBook().apply(tradeAggregator),
                        getSystem().apply(tradeAggregator),
                        getLegalEntity().apply(tradeAggregator),
                        getTradeId().apply(tradeAggregator),
                        getVersion().apply(tradeAggregator),
                        getTradeStatus().apply(tradeAggregator),
                        getProductType().apply(tradeAggregator),
                        getResettingLeg().apply(tradeAggregator),
                        getProductSubType().apply(tradeAggregator),
                        getTdsProductType().apply(tradeAggregator),
                        getSecCodeSubType().apply(tradeAggregator),
                        getSwapType().apply(tradeAggregator),
                        getDescription().apply(tradeAggregator),
                        getTradeDate().apply(tradeAggregator),
                        getStartDate().apply(tradeAggregator),
                        getMaturityDate().apply(tradeAggregator),
                        getUqlOcMmbMs().apply(tradeAggregator),
                        getUqlOcMmbMsPc().apply(tradeAggregator),
                        getMsPc().apply(tradeAggregator),
                        getBreakStatus().apply(tradeAggregator),
                        getTerm().apply(tradeAggregator),
                };
                csvWriter.writeNext(data);
            });

            log.info("{} trade aggregates were loaded into file {}", aggregatedTrades.size(), OUTPUT_CSV);

            csvWriter.close();

        } catch (IOException e) {
            throw new CsvWritingException(String.format("Error writing csv to %s", OUTPUT_CSV));
        }
    }

    @Override
    public OutputType getOutputType() {
        return OutputType.CSV;
    }
}
