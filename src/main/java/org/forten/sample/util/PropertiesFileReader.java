package org.forten.sample.util;

import java.util.ResourceBundle;

public class PropertiesFileReader {
	// 要读取的属性文件名（不用带.properties后缀）
	private String filename;

	// 构造方法，可以指定要读取的属性文件
	public PropertiesFileReader(String filename) {
		this.filename = filename;
	}

	/**
	 * 按照键读取其值
	 * 
	 * @param key
	 *            键
	 * @return 字符串类型的值
	 */
	public String getString(String key) {
		return ResourceBundle.getBundle(filename).getString(key);
	}

	/**
	 * 按照键读取其整型值
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public int getInt(String key) {
		String str = getString(key);
		return Integer.parseInt(str);
	}

	/**
	 * 按照键读取其boolean值
	 * 
	 * @param key
	 *            键
	 * @return boolean型的值
	 */
	public boolean getBoolean(String key) {
		String str = getString(key);
		return Boolean.parseBoolean(str);
	}
}
