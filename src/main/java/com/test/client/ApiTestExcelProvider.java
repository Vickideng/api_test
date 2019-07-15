package com.test.client;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApiTestExcelProvider implements Iterator<Object[]> {

    private static int index = 0;

    public static List<Map<Integer,String>> steps;


    @Override
    public boolean hasNext() {
        if (null == steps) {
            return Boolean.FALSE;
        }
        return steps.size() > index;
    }

    @Override
    public Object[] next() {
        Map<Integer, String> step = steps.get(index);
        Object[] obj ={ step };
        index++;
        return obj;
    }

    @Override
    public void remove() {

    }
}
