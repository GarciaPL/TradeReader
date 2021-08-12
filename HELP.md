# Trade Reader

Program allows reading and joining data coming from CSV files of trade.csv, refdata.csv and valuations.csv into one output set.

Refdata and Trade are joined by column Inventory
Trade and Valuation are joined by column TradeId

The output will be based on the Trade file. If no valuation or refdata for a given trade, then leave the "refdata" and "valuation" fields BLANK

### Usage

Help
>java -jar tradereader.jar --help

Use bundled CSV files (Trade, RefData and Valuation) from jar to get output into console
>java -jar tradereader.jar --bundled_csv<br/><br/>
or<br/><br/>
>java -jar tradereader.jar --bundled_csv --output console

Use bundled CSV files (Trade, RefData and Valuation) from jar to get output into CSV named output.csv
>java -jar tradereader.jar --bundled_csv --output csv

Provide external CSV files (Trade, RefData and Valuation) to get output into console
>java -jar tradereader-0.0.1-SNAPSHOT.jar -t path\to\trade.csv -r path\to\refdata.csv -v path\to\valuation.csv<br/><br/>
or<br/><br/>
>java -jar tradereader-0.0.1-SNAPSHOT.jar -t path\to\trade.csv -r path\to\refdata.csv -v path\to\valuation.csv --output console

Provide external CSV files (Trade, RefData and Valuation) to get output into CSV named output.csv
>java -jar tradereader-0.0.1-SNAPSHOT.jar -t path\to\trade.csv -r path\to\refdata.csv -v path\to\valuation.csv<br/><br/>
or<br/><br/>
>java -jar tradereader-0.0.1-SNAPSHOT.jar -t path\to\trade.csv -r path\to\refdata.csv -v path\to\valuation.csv --output csv

### Input

Columns of Trade.csv
>Inventory,Book,System,LegalEntity,TradeId,Version,TradeStatus,ProductType,Resetting Leg,ProductSubType,TDSProductType,SecCodeSubType,SwapType,Description,TradeDate,StartDate,MaturityDate

Columns of RefData.csv
>topOfHouse,segment,viceChair,globalBusiness,Policy,desk,portfolio,BU,CLINE,Inventory

Columns of Valuation.csv
>TradeId,UQL_OC_MMB_MS,UQL_OC_MMB_MS_PC

### Output

Available output :

* Console by using command line option --output console
* CSV by using command line option --output csv

Output columns:

>topOfHouse,segment,viceChair,globalBusiness,Policy,desk,portfolio,BU,CLINE,Inventory,Book,System,
LegalEntity,TradeId,Version,TradeStatus,ProductType,Resetting Leg,ProductSubType,TDSProductType,<br /> 
SecCodeSubType,SwapType,Description,TradeDate,StartDate,MaturityDate,<br />UQL_OC_MMB_MS, 
UQL_OC_MMB_MS_PC, MS-PC, BreakStatus,Term

Calculated columns:
>MS-PC = the difference between column UQL_OC_MMB_MS and UQL_OC_MMB_MS_PC

>BreakStatus = the difference in Absolute (MS-PC) as following buckets:<br />
0-99<br />
100-999<br />
1000-9999<br />
10000-99999<br />
100000+

>Term = Column MaturityDate - Today. If MaturityDate (MaturityDate < Today) is blank or already matured, set it as blank<br />
0m-1m<br />
1m-6m<br />
6m-1yr<br />
1yr-10yr<br />
10yr-30yr<br />
30yr-50yr<br />
50yr+

### Misc

Getting output into console does scale better by using IntelliJ/Eclipse comparing to standard Shell

### Libraries

* [Spring Boot](https://spring.io/projects/spring-boot)
* [OpenCSV](http://opencsv.sourceforge.net/)
* [Lombok](https://projectlombok.org/)
* [Guava](https://guava.dev/)
* [Ascii Table](https://github.com/freva/ascii-table)
* [Joda Time](https://www.joda.org/joda-time/)
* [Commons CLI](https://commons.apache.org/proper/commons-cli/)
* [AssertJ](https://joel-costigliola.github.io/assertj/)
* [EasyRandom](https://github.com/j-easy/easy-random)