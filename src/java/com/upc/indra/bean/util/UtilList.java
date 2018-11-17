package com.upc.indra.bean.util;

import java.util.List;

/**
 *
 * @author RVALENCIA
 */
public class UtilList {

    public static boolean isEmpty(List coll) {
        return (coll == null || coll.isEmpty());
    }
    
    public static boolean isNotEmpty(List coll) {
        return (coll != null && coll.size() > 0);
    }    
}
