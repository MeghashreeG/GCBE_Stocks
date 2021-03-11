package stock.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import stock.data.DataLoad;
import stock.entity.Stock;
import stock.entity.TradeEntry;

/**
 * The Class Trade.
 */
public class Trade {

	/**
	 * Calculate stock information.
	 *
	 * @param calcShareName
	 *            the calc share name
	 * @param drinkData
	 *            the drink data
	 * @param traceEntryList
	 *            the trace entry list
	 */
	public void calculateStockInformation(String calcShareName, DataLoad drinkData, List<TradeEntry> traceEntryList) {

		Stock stock = drinkData.fetchShockEntry(calcShareName);
		System.out.println("The annual divident paid per share is Rupees 10");
		double dividend = 10 * 100 / stock.getStockPrice();
		System.out.println("The dividend if the share " + calcShareName + " is " + dividend);

		System.out.println("The Market price of the share" + calcShareName + " is " + stock.getStockMarketPrice());
		double earnings = stock.getStockMarketPrice() - stock.getStockPrice();
		double peRatio = stock.getStockMarketPrice() / earnings;
		System.out.println("The P/E ratio of the share " + calcShareName + " is " + peRatio);

		List<TradeEntry> trades = traceEntryList;
		System.out.println("size of trades" + trades.size());
		Double volumeWeightedAverage = calculateWeightedAvgeragePerStock(trades, calcShareName, true, 5);

		System.out.println("The Volume Weighted Avereage for shares of a given stock within the last 5 minutes is "
				+ volumeWeightedAverage);

	}

	/**
	 * Calculate weighted avgerage per stock.
	 *
	 * @param trades
	 *            the trades
	 * @param calcShareName
	 *            the calc share name
	 * @param stockDaysLimitCheck
	 *            the stock days limit check
	 * @param stockDaysLimit
	 *            the stock days limit
	 * @return the double
	 */
	private Double calculateWeightedAvgeragePerStock(List<TradeEntry> trades, String calcShareName,
			boolean stockDaysLimitCheck, int stockDaysLimit) {

		double totalShareMoney = 0.0;
		int totalShares = 0;
		trades = trades.stream().filter(t -> t.getShareName().equals(calcShareName)).collect(Collectors.toList());
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
		for (TradeEntry entry : trades) {
			if (stockDaysLimitCheck) {
				if (LocalDateTime.parse(entry.getTimestamp(), dateTimeFormat).isEqual(LocalDateTime.now())
						|| LocalDateTime.parse(entry.getTimestamp(), dateTimeFormat).plusMinutes(stockDaysLimit)
								.isAfter(LocalDateTime.now())) {
					// cumulative volume
					totalShares = totalShares + entry.getQuantity();
					// typical price x volume
					totalShareMoney = totalShareMoney + entry.getQuantity() * entry.getPrice();
				}
			} else {
				// cumulative volume
				totalShares = totalShares + entry.getQuantity();
				// typical price x volume
				totalShareMoney = totalShareMoney + entry.getQuantity() * entry.getPrice();
			}
		}
		// Volume-weighted average price = typical price x volume/cumulative
		return totalShareMoney / totalShares;
	}

	/**
	 * Calculate share index using GM.
	 *
	 * @param traceEntryList
	 *            the trace entry list
	 */
	public void calculateShareIndexUsingGM(List<TradeEntry> traceEntryList) {
		List<TradeEntry> trades = traceEntryList;
		// Calculate Volume-weighted average price for all the stocks
		List<String> stockNames = trades.stream().map(TradeEntry::getShareName).distinct().collect(Collectors.toList());
		Map<String, Double> shareWithVolumeWeightedAvg = new HashMap<>();
		for (String stockName : stockNames) {
			Double volumeWeightedAverage = calculateWeightedAvgeragePerStock(trades, stockName, false, 0);
			shareWithVolumeWeightedAvg.put(stockName, volumeWeightedAverage);
		}
		List<Double> logOfshareWithVolumeWeightedAvg = new ArrayList<>();
		for (Map.Entry<String, Double> entry : shareWithVolumeWeightedAvg.entrySet()) {
			logOfshareWithVolumeWeightedAvg.add(Math.log(entry.getValue()));
		}

		// Calculate Share Index using the geometric mean of the Volume Weighted
		// Stock Price for all stocks
		double shareIndex = calculateShareIndex(logOfshareWithVolumeWeightedAvg);
		System.out.println("Share Index using the geometric mean of the Volume Weighted Stock Price for all stocks is "
				+ shareIndex);
	}

	/**
	 * Calculate share index.
	 *
	 * @param logOfshareWithVolumeWeightedAvg
	 *            the log ofshare with volume weighted avg
	 * @return the double
	 */
	private double calculateShareIndex(List<Double> logOfshareWithVolumeWeightedAvg) {
		int sizeOfShares = logOfshareWithVolumeWeightedAvg.size();
		double sumOflogOfshareWithVolumeWeightedAvg = 0.0;
		for (double entry : logOfshareWithVolumeWeightedAvg) {
			sumOflogOfshareWithVolumeWeightedAvg = sumOflogOfshareWithVolumeWeightedAvg + entry;
		}
		return sumOflogOfshareWithVolumeWeightedAvg / sizeOfShares;
	}

}
