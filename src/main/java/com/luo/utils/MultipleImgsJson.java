package com.luo.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * Depiction: 多图json数据重组实现，依赖gson库
 * <p>
 * Modify:
 * <p>
 * Author: Kevin Lynn
 * <p>
 * Create Date：2015年11月26日 下午5:45:16
 * <p>
 * 
 * @version 1.0
 * @since 1.0
 */
public class MultipleImgsJson {
	
	private MultipleImgsJson() {
		
	}
	
	/**
	 * 重组多图的json数据，为每个图片对象添加duration字段，单位分钟。
	 * 
	 * @param oldJson 原有json数据
	 * @return 重组后的json数据
	 */
	public static String rebuildJson(String oldJson) {
		JsonMap data = JsonMap.parseJson(oldJson);
		if (data != null) {
			List<JsonMap> imgs = data.getListMap("imgs");
			if (imgs != null) {
				List<JsonMap> newImgs = new ArrayList<JsonMap>();
				for (JsonMap img : imgs) {
					String n = img.getString("n");
					//11月26日16时55分
					if (n != null && n.trim().length() > 0 && n.contains("日")) {
						String[] array = n.split("日");
						if (array != null && array.length > 1) {
							String time = array[1].replace("时", "").replace("分", "");
							int hour = Integer.parseInt(time.substring(0, 2));
							int min = Integer.parseInt(time.substring(2, 4));
							int total = hour * 60 + min;
							img.put("duration", total);
							newImgs.add(img);
						}
					}
				}
				data.put("imgs", newImgs);
				return data.toString();
			}
		}
		
		return null;
	}
	
	/**
	 * 重组多图的json数据，为每个图片对象添加duration字段，单位分钟。
	 * 
	 * @param oldJson 原有json数据
	 * @return 重组后的json数据
	 */
	public static JsonMap rebuildJson2JsonMap(String oldJson) {
		JsonMap data = JsonMap.parseJson(oldJson);
		if (data != null) {
			List<JsonMap> imgs = data.getListMap("imgs");
			if (imgs != null) {
				List<JsonMap> newImgs = new ArrayList<JsonMap>();
				for (JsonMap img : imgs) {
					String n = img.getString("n");
					//11月26日16时55分
					if (n != null && n.trim().length() > 0 && n.contains("日")) {
						String[] array = n.split("日");
						if (array != null && array.length > 1) {
							String time = array[1].replace("时", "").replace("分", "");
							int hour = Integer.parseInt(time.substring(0, 2));
							int min = Integer.parseInt(time.substring(2, 4));
							int total = hour * 60 + min;
							img.put("duration", total);
							newImgs.add(img);
						}
					}
				}
				data.put("imgs", newImgs);
				return data;
			}
		}
		
		return null;
	}
}
