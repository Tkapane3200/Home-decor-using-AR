package com.example.arproject1.utils;

public class DecoderUtil {

    // Decoder base64
    public static byte[] Base64Decoder(String text) {
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(text);
        return decodedBytes;
    }
    // public static String Base64Decoder(String text) {
    // byte[] decodedBytes = java.util.Base64.getDecoder().decode(text);
    // return new String(decodedBytes);
    // }

}
