package com.bmw.common;

public class BMWPocConstants {

	private BMWPocConstants() {

	}

	public static final String ACCEPT_LANG_ZH_CH = "zh-CN";
	public static final String API_V1 = "/v1";
	public static final String API_V2 = "/v2";
	public static final String KEY_NAME_VALUE = "value";
	public static final Integer REST_ERROR_CODE = -1;
	public static final Integer REST_SUCCESS_CODE = 0;
	public static final Integer REPAIRORDER_PAGE_SIZE = 200;
	public static final String DEFAULT_JULIAN_DATE = "2458000";
	public static final Integer STOCK_PRICE_PER_DAY = 80;
	public static final String ERROR_MSG_STOCK_DELETE = "Failed to delete stock";
	public static final String ERROR_MSG_STOCK_UPDATE = "Failed to update stock";
	public static final String REDIS_STOCK_LIST_KEY = "stockList";
	public static final String REDIS_STOCKINSIGHT_LIST_KEY = "stockInsightList";
}
