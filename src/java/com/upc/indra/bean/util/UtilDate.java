package com.upc.indra.bean.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author RENSO
 * @date 03-nov-2018
 */
public class UtilDate {

    public static int getAnioActual() {
        
        Calendar calendar;
        
        calendar = Calendar.getInstance();
        calendar.setTime(new Date()); 
        
        return calendar.get(Calendar.YEAR);
    }
    
    public static int getAnioSiguiente() {
        return getAnioActual() + Constante.ANIO_ADICIONAL;
    }
}