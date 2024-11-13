package PoliAlphabet;
import java.util.Random;

public class PoliAlphabet {

    private static char[] alfabetOriginal = 
        "abcdefghijklmnopqrstuvwxyzàèéíòóúüïçñABCDEFGHIJKLMNOPQRSTUVWXYZÀÈÉÍÒÓÚÜÏÇÑ".toCharArray();
    private static char[] AlphaPermu;
    private static Random random;

    public static void initRandom(String secretKey) {
        random = new Random(secretKey.hashCode());
    }

    public static void permutaAlphabet() {
    	AlphaPermu = alfabetOriginal.clone(); 
        for (int i = 0; i < AlphaPermu.length; i++) {
            int randomIndex = random.nextInt(AlphaPermu.length);
            char temp = AlphaPermu[i];
            AlphaPermu[i] = AlphaPermu[randomIndex];
            AlphaPermu[randomIndex] = temp;
        }
    }

    public static String EncryptPoli(String msg) {
        StringBuilder Encrypt = new StringBuilder();
        for (char lletra : msg.toCharArray()) {
        	permutaAlphabet(); 
            int index = new String(alfabetOriginal).indexOf(lletra);
            if (index != -1) {
            	Encrypt.append(AlphaPermu[index]);
            } else {
            	Encrypt.append(lletra); 
            }
        }
        return Encrypt.toString();
    }

    public static String DecryptPoli(String text) {
        StringBuilder Decrypt = new StringBuilder();
        for (char word : text.toCharArray()) {
        	permutaAlphabet(); 
            int index = new String(AlphaPermu).indexOf(word);
            if (index != -1) {
            	Decrypt.append(alfabetOriginal[index]);
            } else {
            	Decrypt.append(word); 
            }
        }
        return Decrypt.toString();
    }

    public static void main(String[] args) {
        String secretKey = "Rasputin";
        String msgs[] = {
            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(secretKey); 
            msgsXifrats[i] = EncryptPoli(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(secretKey); 
            String msgDesxifrat = DecryptPoli(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msgDesxifrat);
        }
    }
}
