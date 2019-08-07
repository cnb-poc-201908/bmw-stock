package com.bmw.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bmw.model.Stock;
import com.bmw.model.StockInsight;
import com.bmw.utils.DateUtil;

public class StockDataBuilder {

	private StockDataBuilder() {

	}

	private static String[] dealerIds = new String[] {"DL-10006661", "D-10006662", "D-10006663", "D-10006664"};
	private static String[] groupIds = new String[] {"GP-10001", "GP-10002", "GP-10003"};
	private static String[] regionIds = new String[] {"RG-10001", "RG-10002"};

	private static String[] licensePlateCodes = new String[] {"京A37G03","辽B66778","鄂A33669"};

	private static String[] vehicleSeriesCodes = new String[] {"E60", "E66", "E70", "E46", "E90"};
	private static String[] vehicleModelCodes = new String[] {"NU15", "HN21", "GY01", "AY97", "VA96"};
	private static String[] vehicleModelConfigs = new String[] {"NU15A0", "HN21_Z1LX", "GY01_Z3VZ", "AY97A7", "VA96B8"};
	private static String[] vehicleModels = new String[] {"523Li Luxury", "730Li", "X5 M GY01 E70 M 07/09", "318i", "320i Vantage Xenon"};

	private static String[] colors = new String[] {"Alpine White", "Black Sapphire Metallic", "Steel Blue", "Kalahari Beige metallic"};
	private static String[] decorations = new String[] {"Light Beige", "Leather Nasca Beige",
													"Leather Merino extended Silverstone"};

	private static String[] statusCodes = new String[] {"0", "50", "55", "60", "70", "75", "80", "84", "88", "89", "90", "95", "96", "97", "98", "99", "100"};

	private static String stockIdPrefix = "ST-10000";
	private static String chassisNumberPrefix = "WBAHN81027DT225";

 	public static List<Stock> buildStockList(){
		List<Stock> stockList = new ArrayList<>();

		LocalDate now = LocalDate.now();
		int count = 0;
		for(String dealerId: dealerIds) {

			for(int i = 0 ; i < 10 ; i++) {
				count++;
				Stock stock = new Stock();
				String id = count < 10 ? "0" + count : String.valueOf(count);
				stock.setStockId(stockIdPrefix + id);
				stock.setDealerId(dealerId);
				stock.setGroupId(groupIds[count % 3]);
				stock.setRegionId(regionIds[count % 2]);
				stock.setVehicleSeriesCode(vehicleSeriesCodes[count % 5]);
				stock.setVehicleModelCode(vehicleModelCodes[count % 5]);
				stock.setVehicleModelConfig(vehicleModelConfigs[count % 5]);
				stock.setModel(vehicleModels[count % 5]);
				stock.setColor(colors[count % 4]);
				stock.setDecoration(decorations[count % 3]);
				stock.setVehicleChassisNumber(chassisNumberPrefix + id);
				stock.setIsBelongTo(true);
				stock.setStatus(statusCodes[count % 17]);
				stock.setProductionDate(DateUtil.dateToString(now.minusDays(count + 100L)));
				if(count % 4 == 0) {
					stock.setLicensePlate(licensePlateCodes[count % 3]);
				}
				stock.setStorageDate(DateUtil.dateToString(now.minusDays(count + 1L)));
				stock.setDeletable(count % 3 == 0 ? Boolean.FALSE : Boolean.TRUE);
				stock.setDeleted(Boolean.FALSE);
				stockList.add(stock);
			}
		}
		return stockList;
	}

 	public static List<StockInsight> buildStock360List(List<Stock> stockList){
 		List<StockInsight> stock360List = new ArrayList<>();

 		for(Stock stock: stockList) {
 			StockInsight stock360 = new StockInsight(stock);
 			stock360List.add(stock360);
 		}
 		return stock360List;

	}
}