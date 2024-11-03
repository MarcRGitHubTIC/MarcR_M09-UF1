package iticbcn.xifratge;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class XifradorAES implements Xifrador {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private SecretKey secretKey;
    private IvParameterSpec iv;

    public XifradorAES() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(128);
            secretKey = keyGen.generateKey();
            iv = new IvParameterSpec(new byte[16]);
        } catch (Exception e) {
            System.err.println("Error initializing XifradorAES: " + e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null.");
        }

        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] encrypted = cipher.doFinal(msg.getBytes());
            return new TextXifrat(encrypted);
        } catch (Exception e) {
            System.err.println("Error encrypting: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null.");
        }

        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] decrypted = cipher.doFinal(xifrat.getBytes());
            return new String(decrypted);
        } catch (Exception e) {
            System.err.println("Error decrypting: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
