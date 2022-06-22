package com.gokhantamkoc.javabootcamp.odevhafta3.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.gokhantamkoc.javabootcamp.odevhafta3.model.Candle;

public class CryptoDataCSVRepository implements CSVRepository {
	
	private final String COMMA_DELIMITER = ",";

	@Override
	public List<Candle> readCSV(String filename) throws FileNotFoundException, IOException {
		List<Candle> candles = new ArrayList<Candle>();
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename);
		// Bu alandan itibaren kodunuzu yazabilirsiniz
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

		try {
			BufferedReader bf =new BufferedReader(inputStreamReader);
			String line;
			while ((line = bf.readLine())!=null){
				String[] temp = line.split(COMMA_DELIMITER);

				// control for ignoring csv headers unix,date,symbol,open,high,low,close,volume,tradecount
				if(temp[0].equals("unix")){
					continue;
				}
				// parsing string to long and double
				long time = Long.parseLong(temp[0]);
				double open = Double.parseDouble(temp[3]);
				double high = Double.parseDouble(temp[4]);
				double low = Double.parseDouble(temp[5]);
				double close = Double.parseDouble(temp[6]);
				double volume = Double.parseDouble(temp[7]);

				Candle candle = new Candle(time,open,high,low,close,volume);
				candles.add(candle);

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// Bu alandan sonra kalan kod'a dokunmayiniz.
		return candles;
	}

}
