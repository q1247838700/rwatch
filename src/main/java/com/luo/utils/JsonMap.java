package com.luo.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/**
 * Depiction:模仿NSdictionary实现json解析，使用方法类似HashMap
 * <p/>
 * Modify:
 * <p/>
 * Author: Kevin Lynn
 * <p/>
 * Create Date：2014-4-8 下午5:28:12
 * <p/>
 * 
 * @version 1.0
 * @since 1.0
 */
public class JsonMap extends HashMap<String, Object> {
	private static final long serialVersionUID = 4567321902312180302L;
	
	public boolean getBoolean(String key) {
		try {
			return getString(key).equals("true");
		} catch (Exception e) {
		}
		return false;
	}
	
	public int getInt(String key) {
		try {
			return (int) getFloat(key);
		} catch (Exception e) {
		}
		return 0;
	}
	
	public float getFloat(String key) {
		try {
			return Float.parseFloat(get(key).toString());
		} catch (Exception e) {
		}
		return 0.0f;
	}
	
	public double getDouble(String key) {
		try {
			return Double.parseDouble(get(key).toString());
		} catch (Exception e) {
		}
		return 0.0f;
	}
	
	public long getLong(String key) {
		try {
			return (long) getDouble(key);
		} catch (Exception e) {
		}
		return 0l;
	}
	
	public String getString(String key) {
		return get(key) != null ? get(key).toString() : null;
	}
	
	@SuppressWarnings ("unchecked")
	public JsonMap getMap(String key) {
		try {
			Map<String, Object> map = (Map<String, Object>) get(key);
			JsonMap data = new JsonMap();
			for (Iterator<String> keys = map.keySet().iterator(); keys.hasNext();) {
				String k = (String) keys.next();
				Object v = map.get(k);
				data.put(k, v);
			}
			return data;
		} catch (Exception e) {
		}
		return null;
	}
	
	@SuppressWarnings ("unchecked")
	public List<JsonMap> getListMap(String key) {
		try {
			List<Map<String, Object>> maps = (List<Map<String, Object>>) get(key);
			List<JsonMap> listMap = new ArrayList<JsonMap>();
			for (Map<String, Object> map : maps) {
				JsonMap data = new JsonMap();
				for (Iterator<String> keys = map.keySet().iterator(); keys.hasNext();) {
					String k = (String) keys.next();
					Object v = map.get(k);
					data.put(k, v);
				}
				listMap.add(data);
			}
			return listMap;
		} catch (Exception e) {
		}
		return null;
	}
	
	public static JsonMap toJsonMap(Map<String, Object> map) {
		JsonMap data = new JsonMap();
		for (Iterator<String> keys = map.keySet().iterator(); keys.hasNext();) {
			String k = (String) keys.next();
			Object v = map.get(k);
			data.put(k, v);
		}
		return data;
	}
	
	/**
	 * Json数据解析
	 * 
	 * @param json
	 *            json源串
	 * @return JsonMap
	 */
	public static JsonMap parseJson(String json) {
		return parseJson(json, JsonMap.class);
	}
	
	/**
	 * Json数据解析
	 * 
	 * @param json
	 *            json源串
	 * @return List<JsonMap>
	 */
	public static List<JsonMap> parseJsonArray(String json) {
		List<JsonMap> listMap = new ArrayList<JsonMap>();
		try {
			Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
			List<Map<String, Object>> list = parseJson(json, listType);
			for (Map<String, Object> map : list) {
				listMap.add(toJsonMap(map));
			}
		} catch (Exception e) {
		}
		return listMap;
	}
	
	/**
	 * Json数据解析
	 * 
	 * @param json
	 *            json源串
	 * @param cls
	 *            存储json的实体类
	 * @return 相应的实体类对象
	 */
	public static <T> T parseJson(String json, Type cls) {
		try {
			Gson gson = new Gson();
			return gson.fromJson(json, cls);
		} catch (Exception e) {
		}
		return null;
	}
	
	@Override
	public Object get(Object key) {
		return super.get(key);
	}
	
	@Override
	public String toString() {
		try {
			Gson gson = new Gson();
			return gson.toJson(this);
		} catch (Exception e) {
		}
		return super.toString();
	}
}
