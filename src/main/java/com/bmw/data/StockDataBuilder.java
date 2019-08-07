package com.bmw.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bmw.model.Stock;
import com.bmw.model.StockInsight;
import com.bmw.utils.DateUtil;

public class StockDataBuilder {

	private StockDataBuilder() {

	}

	private static String[] dealerIds = new String[] {"DL-10006661", "D-10006662", "D-10006663", "D-10006664"};
	private static String[] groupIds = new String[] {"GP-10001", "GP-10002", "GP-10003"};
	private static String[] regionIds = new String[] {"RG-10001", "RG-10002"};

	private static String[] dealerNames = new String[] {"北京京宝行汽车服务有限公司", "北京博瑞宝汽车销售服务有限公司", "深圳博瑞宝汽车销售服务有限公司", "广州博瑞宝汽车销售服务有限公司"};
	private static String[] groupNames = new String[] {"京宝行集团", "博瑞宝集团", "宝马销售集团"};
	private static String[] regionNames = new String[] {"华北区", "华南区"};

	private static String[] licensePlateCodes = new String[] {"京A37G03","辽B66778","鄂A33669"};

	private static String[] vehicleSeriesCodes = new String[] {"E60", "E66", "E70", "E46", "E90"};
	private static String[] vehicleModelCodes = new String[] {"NU15", "HN21", "GY01", "AY97", "VA96"};
	private static String[] vehicleModelConfigs = new String[] {"NU15A0", "HN21_Z1LX", "GY01_Z3VZ", "AY97A7", "VA96B8"};
	private static String[] vehicleModels = new String[] {"523Li Luxury", "730Li", "X5 M GY01 E70 M 07/09", "318i", "320i Vantage Xenon"};

	private static String[] colors = new String[] {"Alpine White", "Black Sapphire Metallic", "Steel Blue", "Kalahari Beige metallic"};
	private static String[] decorations = new String[] {"Light Beige", "Leather Nasca Beige",
													"Leather Merino extended Silverstone"};

	private static String[] statusCodes = new String[] {"0", "50", "55", "60", "70", "75", "80", "84", "88", "89", "90", "95", "96", "97", "98", "99", "100"};

	// 订金
	private static Double[] deposits = new Double[] { 2000.00, 6000.00, 10000.00, 5000.00 };

	// 签约金额
	private static Double[] contractAmounts = new Double[] { 568000.00, 837500.00, 738000.00, 249800.00 };
	// 价格
	private static Double[] costs = new Double[] { 600000.00, 850000.00, 800000.00, 300000.00 };;
	// 备注字段
	private static String[] comments = new String[] {"VIP客户","高利润","紧急","顶配"};

	private static String stockIdPrefix = "ST-10000";
	private static String chassisNumberPrefix = "WBAHN81027DT225";

 	public static List<Stock> buildStockList(){
		List<Stock> stockList = new ArrayList<>();

		LocalDate now = LocalDate.now();
		int count = 0;
		for(int m = 0; m < dealerIds.length ; m++) {
			String dealerId = dealerIds[m];
			String dealerName = dealerNames[m];
			for(int i = 0 ; i < 10 ; i++) {
				count++;
				Stock stock = new Stock();
				String id = count < 10 ? "0" + count : String.valueOf(count);
				stock.setStockId(stockIdPrefix + id);
				String groupId = null;
				String regionId = null;
				String groupName = null;
				String regionName = null;
				if(m == 0 || m == 1) {
					groupId = groupIds[0];
					regionId = regionIds[0];
					groupName = groupNames[0];
					regionName = regionNames[0];
				}

				if(m == 2) {
					groupId = groupIds[1];
					regionId = regionIds[1];
					groupName = groupNames[1];
					regionName = regionNames[1];
				}

				if(m == 3) {
					groupId = groupIds[2];
					regionId = regionIds[1];
					groupName = groupNames[2];
					regionName = regionNames[1];
				}

				stock.setDealerId(dealerId);
				stock.setGroupId(groupId);
				stock.setRegionId(regionId);
				stock.setGroupName(groupName);
				stock.setRegionName(regionName);
				stock.setDealerName(dealerName);
				stock.setVehicleSeriesCode(vehicleSeriesCodes[count % 5]);
				stock.setVehicleModelCode(vehicleModelCodes[count % 5]);
				stock.setVehicleModelConfig(vehicleModelConfigs[count % 5]);
				stock.setModel(vehicleModels[count % 5]);
				stock.setColor(colors[count % 4]);
				stock.setDecoration(decorations[count % 3]);
				stock.setVehicleChassisNumber(chassisNumberPrefix + id);

				stock.setStatus(statusCodes[count % 17]);
				stock.setProductionDate(DateUtil.dateToString(now.minusDays(count + 100L)));
				if(count % 4 == 0) {
					stock.setLicensePlate(licensePlateCodes[count % 3]);
				}
				stock.setStorageDate(DateUtil.dateToString(now.minusDays(count*10L)));
				stock.setDeletable(count % 3 == 0 ? Boolean.FALSE : Boolean.TRUE);
				stock.setIsBelongTo(!stock.getDeletable());
				stock.setDeleted(Boolean.FALSE);
				stockList.add(stock);
			}
		}
		return stockList;
	}

	public static List<StockInsight> buildStock360List(List<Stock> stockList) {
		List<StockInsight> stock360List = new ArrayList<>();

		for (Stock stock : stockList) {
			StockInsight stock360 = new StockInsight(stock);

			Random random = new Random();
			// 订金
			stock360.setDeposit(deposits[random.nextInt(3)]);
			// 签约日期
			stock360.setContractDate(DateUtil.dateToString(LocalDate.now().minusDays(random.nextInt(5))));
			// 签约金额
			stock360.setContractAmount(contractAmounts[random.nextInt(3)]);
			// 价格
			stock360.setCost(costs[random.nextInt(3)]);
			// 备注字段
			stock360.setComment(comments[random.nextInt(3)]);

			stock360List.add(stock360);
		}
		return stock360List;

	}
}