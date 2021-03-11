package stock.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import stock.data.DataLoad;
import stock.entity.TradeEntry;
import stock.service.Trade;

/**
 * The Class GCBE_Controller.
 */
public class GCBE_Controller {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		boolean programExit = false;
		List<TradeEntry> traceEntryList = new ArrayList<>();
		while (!programExit) {
			System.out.println("Choose from the below options");
			System.out.println("1. List all the shares and corresponding prices");
			System.out.println("2. Record a new trade - Buy or Sell");
			System.out.println("3. Explore Share information and calculations");

			int options = input.nextInt();
			System.out.println("Option chosen is " + options);
			DataLoad drinkData = new DataLoad();

			Trade tradeInfo = new Trade();
			switch (options) {
			case 1:
				drinkData.showStockData();
				break;
			case 2:
				Scanner name = new Scanner(System.in);
				System.out.println("Username of share holder");
				String tradeName = name.nextLine();

				Scanner shareName = new Scanner(System.in);
				System.out.println("Name of the share");
				drinkData.showStockData();
				String tradeShareName = shareName.nextLine();

				Scanner buyOrSell = new Scanner(System.in);
				System.out.println("Buying or Selling: type buy/sell");
				String tradeBuyOrSell = buyOrSell.nextLine();

				Scanner price = new Scanner(System.in);
				System.out.println("Price of the share to buy or sell");
				double tracePrice = price.nextDouble();

				Scanner quantity = new Scanner(System.in);
				System.out.println("Quantity of the share to buy or sell");
				int tradeQuantity = quantity.nextInt();

				traceEntryList = drinkData.loadTraceEntryData(tradeName, tracePrice, tradeBuyOrSell, tradeQuantity,
						tradeShareName);
				break;
			case 3:
				Scanner shares = new Scanner(System.in);
				System.out.println("Choose from the below options");
				System.out.println("1. Choose a stock to present stock calculations");
				System.out.println("2. Calculate share index of all stocks.");

				int sharesInfo = shares.nextInt();
				List<TradeEntry> totalTraceEntryList = drinkData.traceEntryData();
				totalTraceEntryList.addAll(traceEntryList);
				switch (sharesInfo) {
				case 1:
					drinkData.showStockData();
					Scanner shareInfo = new Scanner(System.in);
					String calcShareName = shareInfo.nextLine();
					System.out.println("traceEntryList" + traceEntryList.size());

					tradeInfo.calculateStockInformation(calcShareName, drinkData, totalTraceEntryList);
					break;
				case 2:
					tradeInfo.calculateShareIndexUsingGM(totalTraceEntryList);
				}
				break;
			case 4:
				programExit = true;
				break;
			}
		}
	}
}
