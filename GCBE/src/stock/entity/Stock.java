/**
 * 
 */
package stock.entity;

/**
 * The Class Stock.
 */
public class Stock {

	/** The stock name. */
	String stockName;

	/** The stock price. */
	double stockPrice;

	/** The stock market price. */
	double stockMarketPrice;

	/**
	 * Gets the stock name.
	 *
	 * @return the stockName
	 */
	public String getStockName() {
		return stockName;
	}

	/**
	 * Sets the stock name.
	 *
	 * @param stockName
	 *            the stockName to set
	 */
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	/**
	 * Gets the stock price.
	 *
	 * @return the stockPrice
	 */
	public double getStockPrice() {
		return stockPrice;
	}

	/**
	 * Sets the stock price.
	 *
	 * @param stockPrice
	 *            the stockPrice to set
	 */
	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}

	/**
	 * Gets the stock market price.
	 *
	 * @return the stockMarketPrice
	 */
	public double getStockMarketPrice() {
		return stockMarketPrice;
	}

	/**
	 * Sets the stock market price.
	 *
	 * @param stockMarketPrice
	 *            the stockMarketPrice to set
	 */
	public void setStockMarketPrice(double stockMarketPrice) {
		this.stockMarketPrice = stockMarketPrice;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stock [stockName=" + stockName + ", stockPrice=" + stockPrice + ", stockMarketPrice=" + stockMarketPrice
				+ "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(stockMarketPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((stockName == null) ? 0 : stockName.hashCode());
		temp = Double.doubleToLongBits(stockPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (Double.doubleToLongBits(stockMarketPrice) != Double.doubleToLongBits(other.stockMarketPrice))
			return false;
		if (stockName == null) {
			if (other.stockName != null)
				return false;
		} else if (!stockName.equals(other.stockName))
			return false;
		if (Double.doubleToLongBits(stockPrice) != Double.doubleToLongBits(other.stockPrice))
			return false;
		return true;
	}

}
