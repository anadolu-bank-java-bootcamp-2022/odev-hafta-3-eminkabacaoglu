package com.gokhantamkoc.javabootcamp.odevhafta3.service;

import com.gokhantamkoc.javabootcamp.odevhafta3.model.Candle;
import com.gokhantamkoc.javabootcamp.odevhafta3.repository.CSVRepository;
import com.gokhantamkoc.javabootcamp.odevhafta3.util.chart.CandleStickChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChartService {
	
	CSVRepository cryptoDataCSVRepository;
	
	public ChartService(CSVRepository cryptoDataCSVRepository) {
		this.cryptoDataCSVRepository = cryptoDataCSVRepository;
	}
	
	public CandleStickChart createChartFromCryptoData() {
		// Bu metodu doldurmanizi bekliyoruz.
		CandleStickChart candleStickChart = new CandleStickChart("BTC/USDT Chart");
		List<Candle> candles = new ArrayList();
		try {
			candles = this.cryptoDataCSVRepository.readCSV("Binance_BTCUSDT_d.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// getting values of every candle as parameter for addCandle method of candleStickChart class
		for (Candle candle : candles){
			candleStickChart.addCandle(
					candle.getTime(),
					candle.getOpen(),
					candle.getHigh(),
					candle.getLow(),
					candle.getClose(),
					candle.getVolume()
			);
		}

		return candleStickChart;
	}
}
