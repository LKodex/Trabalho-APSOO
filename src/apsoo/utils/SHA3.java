package apsoo.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Esta classe gera hashes hexadecimais de 256 caracteres utilizando
 * o algoritimo SHA3-256 e também faz comparações de hashes
 */
public class SHA3 {
    private static Charset UTF_8 = StandardCharsets.UTF_8;

    /**
     * Recebe um conjunto qualquer de bytes e gera um hash hexadecimal utilizando SHA3-256
     * @param input String - Texto para ser gerado o hash
     * @return String - Texto de 32 bytes representados em hexadecimal de 256 caracteres
     * @throws NoSuchAlgorithmException
     */
    public static String hash(String input) throws NoSuchAlgorithmException {
        return SHA3.hash(input.getBytes(UTF_8));
    }

    /**
     * Recebe um conjunto qualquer de bytes e gera um hash hexadecimal utilizando SHA3-256
     * @param input byte - Bytes para ser gerado o hash
     * @return String - Texo de 32 bytes representados em hexadecimal de 256 caracteres
     * @throws NoSuchAlgorithmException
     */
    public static String hash(byte[] input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA3-256");
        messageDigest.update(input);
        byte[] resultBytes = messageDigest.digest();

        return SHA3.byteToHex(resultBytes);
    }

    /**
     * Compara um texto com um hash
     * @param input String - Texto para ser comparado com o hash
     * @param hash String - Texto de 32 bytes representados em hexadecimal de 256 caracteres
     * @return Boolean - Verdadeiro caso o input e o hash sejam iguais
     */
    public static Boolean compare(String input, String hash){
        final int HASH_256_STRING_LENGTH = 256;
        if(hash.length() != HASH_256_STRING_LENGTH) return Boolean.FALSE;
        String inputHash = "";
        try {
            inputHash = SHA3.hash(input);
        } catch (NoSuchAlgorithmException e){
            System.out.println("Algoritmo SHA3-256 não disponível nesse ambiente!");
            e.printStackTrace();
        }
        return inputHash.equals(hash);
    }

    /**
     * Compara um texto com um hash em bytes
     * @param input String - Texto para ser comparado com o hash
     * @param hash byte[] - Lista de 32 bytes codificados 
     * @return Boolean - Verdadeiro caso o input e o hash tenham valor em bytes iguais
     */
    public static Boolean compare(String input, byte[] hash){
        String hashString = SHA3.byteToHex(hash);
        return compare(input, hashString);
    }

    /**
     * 
     * @param input byte[] - Lista de bytes para ser convertidos para hexadecimal em String
     * @return String - Representação hexadecimal do input em String
     */
    private static String byteToHex(byte[] input){
        StringBuilder stringBuilder = new StringBuilder();
        for(byte b : input){
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();
    }
}