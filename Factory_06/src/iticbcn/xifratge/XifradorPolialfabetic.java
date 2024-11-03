package iticbcn.xifratge;

import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Random random;

    public XifradorPolialfabetic() {
        random = new Random();
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null.");
        }

        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long.");
        }

        random.setSeed(seed);
        StringBuilder encrypted = new StringBuilder();

        for (char c : msg.toCharArray()) {
            int index = alphabet.indexOf(Character.toUpperCase(c));
            if (index != -1) {
                int shift = random.nextInt(alphabet.length());
                encrypted.append(alphabet.charAt((index + shift) % alphabet.length()));
            } else {
                encrypted.append(c); 
            }
        }
        return new TextXifrat(encrypted.toString().getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null.");
        }

        long seed;
        try {
            seed = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per desxifrat Polialfabètic ha de ser un String convertible a long.");
        }

        random.setSeed(seed);
        StringBuilder decrypted = new StringBuilder();
        String encryptedMsg = new String(xifrat.getBytes());

        for (char c : encryptedMsg.toCharArray()) {
            int index = alphabet.indexOf(Character.toUpperCase(c));
            if (index != -1) {
                int shift = random.nextInt(alphabet.length());
                decrypted.append(alphabet.charAt((index - shift + alphabet.length()) % alphabet.length()));
            } else {
                decrypted.append(c); 
            }
        }
        return decrypted.toString();
    }
}
