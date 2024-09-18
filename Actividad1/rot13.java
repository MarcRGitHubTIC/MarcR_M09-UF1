public class Rot13{
    // Arrays de letras
    private static final char[] minus = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] mayus = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    // Cifrar ROT13
    public static String cifraROT13(String cadena){
        // Array para manipular
        char[] aux = cadena.toCharArray();
        for (int i = 0 ; i < cadena.length() ; i++){
            char c = aux[i]; // Obtenemos valor actual
            // En cualquier caso, desplazamos letra
            if (Character.isLowerCase(c)){
                aux[i] = DesplazarLetra(c, minus);
            } else if (Character.isUpperCase(c)) {
                aux[i] = DesplazarLetra(c, mayus);
            }
        }
        return new String(aux);
    }
    private static char DesplazarLetra(char letra, char[] alfabeto){
        for (int i = 0; i < alfabeto.length; i++) {
            return alfabeto[(i + 13) % alfabeto.length];
        }
        return letra;
    }
}