package com.metmit.xp.utils;

import java.io.InputStream;
import java.util.Properties;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Container {
    /**
     * 日志标签
     */
    public static String LOG_TAG = "easy_xp";

    /**
     * APP包信息
     */
    public static XC_LoadPackage.LoadPackageParam loadPackageParam = null;

    /**
     * 抖音包名
     */
    public static final String AWEME_NAME = "com.ss.android.ugc.aweme";

    /**
     * 快手包名
     */
    public static final String KWAI_NAME = "com.smile.gifmaker";

    /**
     * 要hook的包名
     */
    public static final String[] HANDLE_APP_NAME = {AWEME_NAME, KWAI_NAME};

    /**
     * 自定义配置
     */
    protected static Properties properties = null;

    /**
     * 加载&获取配置项
     */
    public static Object getConfig(String key, Object dft) {
        Object config = getConfig(key);
        if (config == null) return dft;
        return config;
    }

    /**
     * 加载&获取配置项
     */
    public static Object getConfig(String key) {
        if (properties == null) {
            properties = new Properties();
            try {
                InputStream in = Helper.class.getResourceAsStream("/assets/config.properties");
                properties.load(in);
            } catch (Exception e) {
                properties = null;
            }
        }
        if (key == null || properties == null) {
            return properties;
        }
        return properties.getProperty(key, null);
    }
}
