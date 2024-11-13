package iticbcn.xifratge;

import java.util.Random;

public class XifradorMonoalfabetic implements Xifrador {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String permutatedAlphabet;
    private Random random;

    public XifradorMonoalfabetic() {
        random = new Random();
        permutatedAlphabet = permuteAlphabet();
    }

    private String permuteAlphabet() {
        char[] chars = alphabet.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = random.nextInt(chars.length);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        } else {
            StringBuilder encrypted = new StringBuilder();
            for (char c : msg.toCharArray()) {
                int index = alphabet.indexOf(Character.toUpperCase(c));
                if (index != -1) {
                    encrypted.append(permutatedAlphabet.charAt(index));
                } else {
                    encrypted.append(c); 
                }
            }
            return new TextXifrat(encrypted.toString().getBytes());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
    	if (clau != null) {
    	    throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
    	}else {
    		StringBuilder decrypted = new StringBuilder();
            String encryptedMsg = new String(xifrat.getBytes());

            for (char c : encryptedMsg.toCharArray()) {
                int index = permutatedAlphabet.indexOf(Character.toUpperCase(c));
                if (index != -1) {
                    decrypted.append(alphabet.charAt(index));
                } else {
                    decrypted.append(c); 
                }
            }
            return decrypted.toString();
    	}
    }
}
