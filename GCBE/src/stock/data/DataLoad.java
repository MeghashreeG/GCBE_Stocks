package stock.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import stock.entity.Stock;
import stock.entity.TradeEntry;

/**
 * The Class DataLoad.
 */
public class DataLoad {

	/** The stock list. */
	List<Stock> stockList = new ArrayList<>();

	/** The trace entry list. */
	List<TradeEntry> traceEntryList = new ArrayList<>();

	/** The date time format. */
	DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");

	/**
	 * Load trace entry data.
	 *
	 * @param tradeName
	 *            the trade name
	 * @param tracePrice
	 *            the trace price
	 * @param tradeBuyOrSell
	 *            the trade buy or sell
	 * @param tradeQuantity
	 *            the trade quantity
	 * @param tradeShareName
	 *            the trade share name
	 * @return the list
	 */
	public List<TradeEntry> loadTraceEntryData(String tradeName, double tracePrice, String tradeBuyOrSell,
			int tradeQuantity, String tradeShareName) {
		LocalDateTime now = LocalDateTime.now();
		TradeEntry entry = new TradeEntry();
		entry.setPrice(tracePrice);
		entry.setQuantity(tradeQuantity);
		entry.setTimestamp(dateTimeFormat.format(now).toString());
		entry.setTradeType(tradeBuyOrSell);
		entry.setUsername(tradeName);
		entry.setShareName(tradeShareName);
		traceEntryList.add(entry);
		System.out.println("Details for the new Trade recorded");
		System.out.println("The User Name" + tradeName);
		System.out.println("The Stock name " + tradeShareName);
		System.out.println("The price of the stock " + tradeQuantity);
		System.out.println("The quality of the stock " + tradeQuantity);
		System.out.println("Type of the trade captured " + tradeBuyOrSell);
		System.out.println("The timestamp of the trade captured " + entry.getTimestamp());
		return traceEntryList;
	}

	/**
	 * Fetch trade entry.
	 *
	 * @param tradeEntry
	 *            the trade entry
	 * @param traceEntryList
	 *            the trace entry list
	 * @return the list
	 */
	public List<TradeEntry> fetchTradeEntry(String tradeEntry, List<TradeEntry> traceEntryList) {
		List<TradeEntry> resultList = new ArrayList<>();
		for (TradeEntry entry : traceEntryList) {
			if (entry.getShareName().equals(tradeEntry)) {
				resultList.add(entry);
			}
		}
		return resultList;
	}

	/**
	 * Fetch shock entry.
	 *
	 * @param stockEntry
	 *            the stock entry
	 * @return the stock
	 */
	public Stock fetchShockEntry(String stockEntry) {
		Stock result = null;
		for (Stock entry : stockList) {
			if (entry.getStockName().equals(stockEntry)) {
				result = entry;
			}
		}
		return result;
	}

	/**
	 * Show stock data.
	 */
	public void showStockData() {
		List<Stock> stockList = loadStockData();
		for (Stock entry : stockList) {
			System.out.println("Stock name " + entry.getStockName());
			System.out.println("Stock price " + entry.getStockPrice());
			System.out.println("Market Stock price " + entry.getStockMarketPrice());
		}
	}

	/**
	 * Load stock data.
	 *
	 * @return the list
	 */
	public List<Stock> loadStockData() {
		Stock gcbeWine = new Stock();
		gcbeWine.setStockName("GCBE Wine");
		gcbeWine.setStockPrice(800.0);
		gcbeWine.setStockMarketPrice(850.0);
		Stock gcbeBeer = new Stock();
		gcbeBeer.setStockName("GCBE Beer");
		gcbeBeer.setStockPrice(400.0);
		gcbeBeer.setStockMarketPrice(450.0);

		stockList.add(gcbeBeer);
		stockList.add(gcbeWine);
		return stockList;
	}

	/**
	 * Trace entry data.
	 *
	 * @return the list
	 */
	public List<TradeEntry> traceEntryData() {

		LocalDateTime now = LocalDateTime.now();

		TradeEntry entryOne = new TradeEntry();
		entryOne.setPrice(450.0);
		entryOne.setQuantity(100);
		entryOne.setTimestamp(dateTimeFormat.format(now).toString());
		entryOne.setTradeType("Buy");
		entryOne.setUsername("Megha");
		entryOne.setShareName("GCBE Beer");

		TradeEntry entryTwo = new TradeEntry();
		entryTwo.setPrice(460.0);
		entryTwo.setQuantity(50);
		entryTwo.setTimestamp(dateTimeFormat.format(now).toString());
		entryTwo.setTradeType("Buy");
		entryTwo.setUsername("Radha");
		entryTwo.setShareName("GCBE Wine");

		TradeEntry entryThree = new TradeEntry();
		entryThree.setPrice(470.0);
		entryThree.setQuantity(60);
		entryThree.setTimestamp(dateTimeFormat.format(now).toString());
		entryThree.setTradeType("Sell");
		entryThree.setUsername("Megha");
		entryThree.setShareName("GCBE Beer");

		TradeEntry entryFour = new TradeEntry();
		entryFour.setPrice(450.0);
		entryFour.setQuantity(10);
		entryFour.setTimestamp(dateTimeFormat.format(now).toString());
		entryFour.setTradeType("Buy");
		entryFour.setUsername("Deepraj");
		entryFour.setShareName("GCBE Beer");

		TradeEntry entryFive = new TradeEntry();
		entryFive.setPrice(455.0);
		entryFive.setQuantity(80);
		entryFive.setTimestamp(dateTimeFormat.format(now).toString());
		entryFive.setTradeType("Buy");
		entryFive.setUsername("Sri");
		entryFive.setShareName("GCBE Beer");

		TradeEntry entrySix = new TradeEntry();
		entrySix.setPrice(475.0);
		entrySix.setQuantity(20);
		entrySix.setTimestamp(dateTimeFormat.format(now.minusMinutes(10)).toString());
		entrySix.setTradeType("Sell");
		entrySix.setUsername("Radha");
		entrySix.setShareName("GCBE Beer");

		traceEntryList.add(entryOne);
		 traceEntryList.add(entryTwo);
		 traceEntryList.add(entryThree);
		 traceEntryList.add(entryFour);
		 traceEntryList.add(entryFive);
		 traceEntryList.add(entrySix);
		return traceEntryList;
	}

}
