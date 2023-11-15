package com.catolica.sc.spaum.utils;

import java.util.List;

public class CollectionsUtils {
    public static boolean isNotEmpty(List<?> list){
        return list != null && !list.isEmpty();
    }
}
