package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    public String getSHA512AmbSalt(String pw, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] hashedBytes = md.digest(pw.getBytes());
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hashedBytes);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 10000, 512);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash);
    }

    public int npass = 0;  
    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        String charset = "abcdefABCDEF1234567890!";
        char[] password = new char[6];
        String result = null;
        outerLoop:
        for (char c1 : charset.toCharArray()) {
            for (char c2 : charset.toCharArray()) {
                for (char c3 : charset.toCharArray()) {
                    for (char c4 : charset.toCharArray()) {
                        for (char c5 : charset.toCharArray()) {
                            for (char c6 : charset.toCharArray()) {
                                password[0] = c1;
                                password[1] = c2;
                                password[2] = c3;
                                password[3] = c4;
                                password[4] = c5;
                                password[5] = c6;
                                String attempt = new String(password);
                                npass++;
                                if (alg.equals("SHA-512")) {
                                    if (getSHA512AmbSalt(attempt, salt).equals(hash)) {
                                        result = attempt;
                                        break outerLoop;
                                    }
                                } else if (alg.equals("PBKDF2")) {
                                    if (getPBKDF2AmbSalt(attempt, salt).equals(hash)) {
                                        result = attempt;
                                        break outerLoop;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (diff % (1000 * 60)) / 1000;
        long millis = diff % 1000;
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", days, hours, minutes, seconds, millis);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = {
            h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)
        };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}
