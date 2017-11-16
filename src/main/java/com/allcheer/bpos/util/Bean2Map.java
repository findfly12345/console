package com.allcheer.bpos.util;

import jodd.util.StringUtil;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.util.*;

/**
 * Created by fireWorks on 2016/10/25.
 */
public class Bean2Map {

    private static final Logger logger = LoggerFactory.getLogger(Bean2Map.class);

    public static Map<String, String> beanToMapNoFill(Object obj) {
        Map<String, String> params = new HashMap<String, String>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    String value = String.valueOf(propertyUtilsBean.getNestedProperty(obj, name));
                    if(StringUtils.isNotBlank(value)&&!"null".equals(value)) {
                        logger.debug("value={}",value);
                        params.put(name, String.valueOf(propertyUtilsBean.getNestedProperty(obj, name)));
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return params;
    }


    public static Map<String, String> beanToMapWithFill(Object obj) {
        Map<String, String> params = new HashMap<String, String>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, String.valueOf(propertyUtilsBean.getNestedProperty(obj, name)));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return params;
    }

    private static void removeNullValue(Map map){
        Set set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {
            Object obj = (Object) iterator.next();
            Object value =(Object)map.get(obj);
            remove(value, iterator);
        }
    }

    private static void remove(Object obj,Iterator iterator){
        if(obj instanceof String){
            String str = (String)obj;
            if(StringUtil.isEmpty(str)){
                iterator.remove();
            }
        }else if(obj instanceof Collection){
            Collection col = (Collection)obj;
            if(col==null||col.isEmpty()){
                iterator.remove();
            }

        }else if(obj instanceof Map){
            Map temp = (Map)obj;
            if(temp==null||temp.isEmpty()){
                iterator.remove();
            }

        }else if(obj instanceof Object[]){
            Object[] array =(Object[])obj;
            if(array==null||array.length<=0){
                iterator.remove();
            }
        }else{
            if(obj==null){
                iterator.remove();
            }
        }
    }
}
