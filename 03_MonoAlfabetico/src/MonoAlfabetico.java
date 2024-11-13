import java.util.*;

public class MonoAlfabetico {

    public static char[] permutaAlfabeto() {
        char[] alfabeto = "abcdefghijklmnopqrstuvwxyzáéíóúàèìòùäëïöüçñABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÄËÏÖÜÇÑ".toCharArray();
        char[] permutacion = alfabeto.clone();

        List<Character> lista = new ArrayList<>();
        for (char c : permutacion) {
            lista.add(c);
        }

        Collections.shuffle(lista);

        for (int i = 0; i < permutacion.length; i++) {
            permutacion[i] = lista.get(i);
        }

        return permutacion;
    }

    public static String cifraMonoAlfa(String cadena, char[] alfabeto, char[] permutacion) {
        StringBuilder cifrado = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            int index = new String(alfabeto).indexOf(c);
            if (index != -1) {
                cifrado.append(permutacion[index]);
            } else {
                cifrado.append(c);
            }
        }

        return cifrado.toString();
    }

    public static String descifraMonoAlfa(String cadena, char[] alfabeto, char[] permutacion) {
        StringBuilder descifrado = new StringBuilder();

        for (char c : cadena.toCharArray()) {
            int index = new String(permutacion).indexOf(c);
            if (index != -1) {
                descifrado.append(alfabeto[index]);
            } else {
                descifrado.append(c);
            }
        }

        return descifrado.toString();
    }

    public static void main(String[] args) {
        char[] alfabeto = "abcdefghijklmnopqrstuvwxyzáéíóúàèìòùäëïöüçñABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÀÈÌÒÙÄËÏÖÜÇÑ".toCharArray();
        char[] permutacion = permutaAlfabeto();

        String textoOriginal = "ÀáÄ / ÈéË / òÓö / ùÜú / ÒóÖ ? ! , . - () TeXtO dE eJeMpLo 1 2 3";
        System.out.println("Texto original: " + textoOriginal);

        String textoCifrado = cifraMonoAlfa(textoOriginal, alfabeto, permutacion);
        System.out.println("Texto cifrado: " + textoCifrado);

        String textoDescifrado = descifraMonoAlfa(textoCifrado, alfabeto, permutacion);
        System.out.println("Texto descifrado: " + textoDescifrado);
    }
}
