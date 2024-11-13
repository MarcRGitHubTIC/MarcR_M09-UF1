package ROTX;

import java.util.Scanner;

public class rotx {
	 // Arrays de letras
    private static final char[] minus = "aàáäbcçdeèéëfghiììïjklmnñoòóöpqrstuùúüvwxyz".toCharArray();
    private static final char[] mayus = "AÀÁÄBCÇDEÈÉËFGHIÌÍÏJKLMNÑOÒÓÖPQRSTUÙÚÜVWXYZ".toCharArray();

    public static String cifraRotx(String texto, int xrot) {
    	char[] aux = texto.toCharArray(); // Array auxiliar
    	for (int i=0;i<texto.length();i++) {
    		char cifrado=aux[i]; // Otorgar valor de la posicion en el for
    		if (Character.isLowerCase(cifrado)) {			// Si es minuscula a un lado
    			aux[i]=desplazar(cifrado, xrot, minus);		// si es mayuscula para otro.
    		}else if (Character.isUpperCase(cifrado)) {		// Lanzando tmb el valor ROT
    			aux[i]=desplazar(cifrado, xrot, mayus);		// que queremos
    		}
    	}
    	return new String(aux);
    }
    
    // Lo mismo que cifrar ROT
    public static String descifraRotX(String texto, int xrot) {
    	char[] aux = texto.toCharArray();
    	for (int i=0;i<texto.length();i++){
    		char descifrado=aux[i];
    		if (Character.isLowerCase(descifrado)) {
    			aux[i]=negDesplazar(descifrado, xrot, minus);
    		} else if (Character.isUpperCase(descifrado)) {
    			aux[i]=negDesplazar(descifrado, xrot, mayus);
    		}
    	}
    	return new String(aux);
    }
    
    public static char desplazar(char letra, int xrot, char[] alfabeto){
    	for (int i=0;i<alfabeto.length;i++) {
    		// Comprobamos que tenemos la letra en nuestro array
    		if (alfabeto[i]==letra) {
    			// Devolvemos la letra despues de moverla segun el ROT indicado
    			return alfabeto[(i + xrot) % alfabeto.length];
    		}
    	}return letra;
    }
    
    public static char negDesplazar(char letra, int xrot, char[] alfabeto) {
        for (int i = 0; i < alfabeto.length; i++) {
            if (alfabeto[i] == letra) {
                int nuevaPos = (i - xrot) % alfabeto.length;
                // Si el resultado es negativo, ajusta la posición sumando la longitud del alfabeto
                if (nuevaPos < 0) {
                    nuevaPos += alfabeto.length;
                }
                return alfabeto[nuevaPos];
            }
        }
        return letra;
    }
    
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            boolean salir = false; 
            while (!salir) {
            	System.out.println("****************************");
                System.out.println("What do you want to do?");
                System.out.println("1. Encrypt & Decrypt");
                System.out.println("2. Brute force");
                System.out.println("0. Exit");
                System.out.print("Choose: ");
                int opcion = input.nextInt();
                input.nextLine(); 
                System.out.println("****************************");
                switch (opcion) {
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        System.out.print("Text to encrypt: ");
                        String texto = input.nextLine();
                        System.out.print("ROT number to choose: ");
                        int xRot = input.nextInt();
                        input.nextLine(); 
                        System.out.println("****************************");
                        String palabraCifrada = cifraRotx(texto, xRot);
                        String palabraDescifrada = descifraRotX(palabraCifrada, xRot);
                        System.out.println("Palabra Original:  " + texto);
                        System.out.println("Palabra cifrada a través de ROTX: " + palabraCifrada);
                        System.out.println("Palabra descifrada por ROTX: " + palabraDescifrada);
                        break;
                    case 2:
                        System.out.print("Text to decrypt: ");
                        String bruteText = input.nextLine();
                        System.out.println("****************************");
                        for (int i = 1; i < 28; i++) {
                            String bruteDecrypt = descifraRotX(bruteText, i);
                            System.out.println("ROT " + i + " Palabra: " + bruteDecrypt);
                        }
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocurrió un error inesperado: " + e.getMessage());
        }
    }
}
