package rot13;

//import java.util.Scanner;

import java.util.Scanner;

public class rot13{
    // Arrays de letras
    private static final char[] minus = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] mayus = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    
    // Cifrar ROT13
    public static String cifraROT13(String cadena){
        // Array para manipular
        char[] aux = cadena.toCharArray();
        for (int i = 0 ; i < cadena.length() ; i++){
            char stringROT = aux[i]; // Obtenemos valor actual
            // En cualquier caso, desplazamos letra
            if (Character.isLowerCase(stringROT)){
                aux[i] = DesplazarLetra(stringROT, minus);
            } else if (Character.isUpperCase(stringROT)) {
                aux[i] = DesplazarLetra(stringROT, mayus);
            }
         	}
        return new String(aux);
    }
    
    public static String deszifraRot13(String cadena) {
    	// Al tener un abecedario de 26 letras, si introduzco la palabra cifrada, me dará esta descifrada
        return cifraROT13(cadena); 
    }
    
    // Simplemente para mover 13 posiciones en el abecedario
    private static char DesplazarLetra(char letra, char[] alfabeto){
        for (int i = 0; i < alfabeto.length; i++) {
        	if (alfabeto[i] == letra) {
        		return alfabeto[(i + 13) % alfabeto.length];
        	}
        }
        return letra;
    }
    
   
    
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
			System.out.println("Introduzca texto para cifrar:");
			String palabra=input.nextLine();
            
			String palabraCifrada=cifraROT13(palabra);
			String palabraDescifrada= deszifraRot13(palabraCifrada);
			System.out.println("Palabra Original:  "+palabra);
			System.out.println("Palabra cifrada a traves de ROT13: "+palabraCifrada);
			System.out.println("Palabra descifrada por ROT13: "+palabraDescifrada);
		}catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}

/* Si no te gusta la manera en la que lo he hecho el descifrar la palabra, aqui esta unos bloques de codigo que realizarian la funcion
	de descifrara el codigo, lo cual seria util si el abecedario que se usa no tiene 26 letras, ya que entonces no coincidirian


	public static String descifraROT13(String cadena){
	   char[] aux = cadena.toCharArray();
	   for (int i = 0 ; i < cadena.length() ; i++){
	       char stringROT = aux[i]; // Obtenemos valor actual
	      
	       if (Character.isLowerCase(stringROT)){
	           aux[i] = NegDesplazarLetra(stringROT, minus);
	       } else if (Character.isUpperCase(stringROT)) {
	           aux[i] = NegDesplazarLetra(stringROT, mayus);
	       }
	   }
	   return new String(aux);
	}
	private static char NegDesplazarLetra(char letra, char[] alfabeto){
	   for (int i = 0; i < alfabeto.length; i++) {
	   	if (alfabeto[i] == letra) {
	   		return alfabeto[(i - 13) % alfabeto.length];
	   	}
	   }
	   return letra;
	}
	
 */