package EncryptAES;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.security.SecureRandom;

public class EncryptAES {
	
	public static final String ALGORITMO_CRYPT = "AES";
    public static final String ALGORITMO_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "SecretKey";

    public static byte[] encryptAES(String msg, String llave) throws Exception {
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        byte[] key = generaKey(llave);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITMO_CRYPT);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);

        byte[] encryptedMessage = cipher.doFinal(msg.getBytes());

        byte[] ivAndEncryptedMessage = new byte[iv.length + encryptedMessage.length];
        System.arraycopy(iv, 0, ivAndEncryptedMessage, 0, iv.length);
        System.arraycopy(encryptedMessage, 0, ivAndEncryptedMessage, iv.length, encryptedMessage.length);

        return ivAndEncryptedMessage;
    }

    public static String decryptAES(byte[] bIvIMsgrypt, String llave) throws Exception {
        byte[] iv = Arrays.copyOfRange(bIvIMsgrypt, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        byte[] encryptedMessage = Arrays.copyOfRange(bIvIMsgrypt, MIDA_IV, bIvIMsgrypt.length);

        byte[] key = generaKey(llave);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITMO_CRYPT);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);

        byte[] decryptedMessage = cipher.doFinal(encryptedMessage);

        return new String(decryptedMessage);
    }

    private static byte[] generaKey(String llave) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(ALGORITMO_HASH);
        byte[] key = llave.getBytes("UTF-8");
        key = sha.digest(key);
        return Arrays.copyOf(key, 32); 
    }

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet", 
        		"Hola Andrés cómo está tu cuñado", 
        		"Àgora ïlla Ôtto"};
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = encryptAES(msg, CLAU);

                desxifrat = decryptAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + Base64.getEncoder().encodeToString(bXifrats)); 
            System.out.println("DEC: " + desxifrat);
        }
    }
}


