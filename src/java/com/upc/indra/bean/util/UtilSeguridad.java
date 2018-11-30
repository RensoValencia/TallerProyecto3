package com.upc.indra.bean.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @author RVALENCIA
 * @since 25/11/2018
 * @description Metodos para salvaguardar la seguridad del sistema
 */
public class UtilSeguridad {

    public static String encriptar(String pass) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(Constante.CODIGO_ENCRIPTACION);
            md.update(pass.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}