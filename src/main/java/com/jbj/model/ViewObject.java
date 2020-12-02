package com.jbj.model;

import org.springframework.jdbc.support.nativejdbc.OracleJdbc4NativeJdbcExtractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewObject {
    private Map<String,Object> objs = new HashMap<>();
    public void set(String key,Object value){
            objs.put(key, value);
    }
    public Object get(String key){
            return objs.get(key);
    }


}
