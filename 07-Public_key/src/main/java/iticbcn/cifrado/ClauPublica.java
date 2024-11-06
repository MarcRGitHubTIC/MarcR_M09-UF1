package iticbcn.cifrado;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import javax.crypto.Cipher;

public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);  
        return keyGen.generateKeyPair();  
    }

    public byte[] xifraRSA(String msg, PublicKey ClauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, ClauPublica);
        return cipher.doFinal(msg.getBytes("UTF-8"));  
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada); 
        byte[] desencriptat = cipher.doFinal(msgXifrat);
        return new String(desencriptat, "UTF-8"); 
    }
}

