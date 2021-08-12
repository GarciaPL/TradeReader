package com.tdsecurities.trade;

import com.tdsecurities.trade.services.TradeReader;
import com.tdsecurities.trade.services.reader.ReaderContext;
import com.tdsecurities.trade.services.writer.OutputType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

import static com.tdsecurities.trade.constants.CsvBundledFiles.*;

@Slf4j
@Component
public class TradeReaderCommandLineRunner implements CommandLineRunner {

    private final TradeReader tradeReader;

    public TradeReaderCommandLineRunner(TradeReader tradeReader) {
        this.tradeReader = tradeReader;
    }

    @Override
    public void run(String... args) throws Exception {
        Option help = Option.builder("h").required(false).hasArg(false).longOpt("help").build();
        Option tradeOption = Option.builder("t").required(false).hasArg(true).argName("Trades").longOpt("trades").desc("Path to trades file").build();
        Option refDataOption = Option.builder("r").required(false).hasArg(true).argName("RefData").longOpt("refdata").desc("Path to ref data file").build();
        Option valuationsOption = Option.builder("v").required(false).hasArg(true).argName("Valuations").longOpt("valuations").desc("Path to valuations file").build();
        Option bundledFilesOption = Option.builder("b").required(false).hasArg(false).argName("BundledCSV").longOpt("bundled_csv").desc("Use csv files bundled into jar").build();
        Option outputOption = Option.builder("o").required(false).hasArg(true).argName("Output").longOpt("output").desc("Output as console or csv").build();

        Options options = new Options();
        options.addOption(help);
        options.addOption(tradeOption);
        options.addOption(refDataOption);
        options.addOption(valuationsOption);
        options.addOption(bundledFilesOption);
        options.addOption(outputOption);

        CommandLineParser parser = new DefaultParser();
        CommandLine line = parser.parse(options, args);

        if (line.hasOption("h") || args.length == 0) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar tradereader.jar", options, true);
            return;
        }


        OutputType outputType = OutputType.CONSOLE;
        if (line.hasOption("o")) {
            String outputValue = line.getOptionValue("o");
            boolean isValidEnum = EnumUtils.isValidEnumIgnoreCase(OutputType.class, outputValue);
            if (isValidEnum) {
                outputType = EnumUtils.getEnumIgnoreCase(OutputType.class, outputValue);
            } else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("Wrong output type defined. Please use console or csv", options, true);
                return;
            }
        }

        log.info("Output generated as {}", outputType);

        if (line.hasOption("b")) {
            tradeReader.processTrades(outputType, new ReaderContext(TRADE_CSV, REF_DATA_CSV, VALUATION_CSV));
            return;
        }

        if (line.hasOption("t") && line.hasOption("r") && line.hasOption("v")) {
            String trade = line.getOptionValue("t");
            String refData = line.getOptionValue("r");
            String valuations = line.getOptionValue("v");
            if (isFileExists(trade) && isFileExists(refData) && isFileExists(valuations)) {
                ReaderContext readerContext = new ReaderContext(trade, refData, valuations);
                tradeReader.processTrades(outputType, readerContext);
            } else {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("One of the files does not exist", options, true);
            }
        } else {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Please specify all required files for processing", options, true);
        }
    }

    private boolean isFileExists(String path) {
        File file = new File(path);
        return file.exists() && !file.isDirectory();
    }
}
