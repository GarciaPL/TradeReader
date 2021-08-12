package com.tdsecurities.trade.services.reader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.tdsecurities.trade.exceptions.CsvReadingException;
import com.tdsecurities.trade.model.csv.RefData;
import com.tdsecurities.trade.model.csv.Trade;
import com.tdsecurities.trade.model.csv.Valuation;
import com.tdsecurities.trade.model.csv.base.BaseData;
import com.tdsecurities.trade.model.output.TradeData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CsvReader implements Reader {

    @Override
    public TradeData readData(ReaderContext readerContext) {

        List<Trade> trades = readFile(readerContext.getTradeFile(), Trade.class);
        List<RefData> refDatas = readFile(readerContext.getRefDataFile(), RefData.class);
        List<Valuation> valuations = readFile(readerContext.getValuationFile(), Valuation.class);

        log.info("Read {} trades from CSV file", trades.size());
        log.info("Read {} ref data from CSV file", refDatas.size());
        log.info("Read {} valuations from CSV file", valuations.size());

        return new TradeData(trades, refDatas, valuations);
    }

    private <T extends BaseData> List<T> readFile(String fileName, Class<T> targetClass) {
        try {

            InputStreamReader inputStreamReader = getFile(fileName);

            CsvToBean<T> csvToBean = getCsvToBean(inputStreamReader, targetClass);

            List<CsvException> capturedExceptions = csvToBean.getCapturedExceptions();
            if (!capturedExceptions.isEmpty()) {
                String lineNumbers = capturedExceptions.stream()
                        .map(a -> String.valueOf(a.getLineNumber()))
                        .collect(Collectors.joining(","));
                throw new CsvReadingException(String.format("Exception reading %s at lines %s", fileName, lineNumbers));
            }

            return csvToBean.parse();

        } catch (IOException e) {
            throw new CsvReadingException("Exception reading csv", e);
        }
    }

    private InputStreamReader getFile(String fileName) throws FileNotFoundException {
        try {
            InputStream is = new ClassPathResource(fileName).getInputStream();
            return new InputStreamReader(is, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new CsvReadingException("Exception reading file", e);
        }
    }

    private <T extends BaseData> CsvToBean<T> getCsvToBean(InputStreamReader inputStreamReader, Class<T> targetClass) {
        return new CsvToBeanBuilder<T>(inputStreamReader)
                .withType(targetClass)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
    }
}
