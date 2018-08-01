package com.xiaohe.qd.util;

public class ColorUtil {

	
	public static final String WHITE = "fff";	// 白色
	public static final String PINK = "fcd5d5";	// 粉色
	public static final String RED = "faadad";	// 红色
	public static final String LIGHT_GREEN = "e7f5eb"; // 淡绿
	public static final String GREEN = "ceebd7"; // 翠绿
	
	// 普通颜色区间
	public static final Float[] GENERALLIGHTGREEN = {-0.06f, -0.02f};
	public static final Float[] GENERALPINK = {0.02f, 0.06f};
	public static final Float[] GENERALRED = {0.06f};
	public static final Float[] GENERALWHITE = {-0.02f, 0.02f};
	public static final Float[] GENERALGREEN = {-0.06f};
	
	// ST(特殊)颜色区间
	public static final Float[] SPECIALLIGHTGREEN = {-0.03f, -0.01f};
	public static final Float[] SPECIALPINK = {0.01f, 0.03f};
	public static final Float[] SPECIALRED = {0.03f};
	public static final Float[] SPECIALWHITE = {-0.01f, 0.01f};
	public static final Float[] SPECIALGREEN = {-0.03f};
	
	
	
	/**
	 * 普通
	 * @param num
	 * @return
	 */
	public static String getGeneralColor(float num) {
		num = num/100;
		if (GENERALLIGHTGREEN[0] <= num && num <= GENERALLIGHTGREEN[1]) { // 淡绿
			return LIGHT_GREEN;
		} else if (num < GENERALGREEN[0]) { // 翠绿
			return GREEN;
		}else if (GENERALWHITE[0] < num && num <= GENERALWHITE[1]) { //白
			return WHITE;
		} else if (GENERALPINK[0] < num && num <= GENERALPINK[1]) { // 粉
			return PINK;
		} else if (GENERALRED[0] < num) { // 红
			return RED;
		}
		return WHITE;
	}
	
	/**
	 * 特殊
	 * @param num
	 * @return
	 */
	public static String getSpecialColor(float num) {
		num = num/100;
		if (SPECIALLIGHTGREEN[0] <= num && num <= SPECIALLIGHTGREEN[1]) { // 淡绿
			return LIGHT_GREEN;
		} else if (num < SPECIALGREEN[0]) { // 翠绿
			return GREEN;
		}else if (SPECIALWHITE[0] < num && num <= SPECIALWHITE[1]) { //白
			return WHITE;
		} else if (SPECIALPINK[0] < num && num <= SPECIALPINK[1]) { // 粉
			return PINK;
		} else if (SPECIALRED[0] < num) { // 红
			return RED;
		}
		return WHITE;
	}
}
