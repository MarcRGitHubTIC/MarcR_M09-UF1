package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    private int rot;
    public XifradorRotX(int rot) {
        this.rot = rot;
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null.");
        }

        int clauInt;
        try {
            clauInt = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau ha de ser un número vàlid.");
        }

        if (clauInt < 0 || clauInt > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40.");
        }

        this.rot = clauInt;
        StringBuilder encrypted = new StringBuilder();

        for (char c : msg.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char encryptedChar = (char) (((c - 'A' + rot) % 26) + 'A');
                encrypted.append(encryptedChar);
            } else if (Character.isLowerCase(c)) {
                char encryptedChar = (char) (((c - 'a' + rot) % 26) + 'a');
                encrypted.append(encryptedChar);
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

        int clauInt;
        try {
            clauInt = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau ha de ser un número vàlid.");
        }

        if (clauInt < 0 || clauInt > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40.");
        }

        this.rot = clauInt;
        StringBuilder decrypted = new StringBuilder();
        String encryptedMsg = new String(xifrat.getBytes());

        for (char c : encryptedMsg.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char decryptedChar = (char) (((c - 'A' - rot + 26) % 26) + 'A');
                decrypted.append(decryptedChar);
            } else if (Character.isLowerCase(c)) {
                char decryptedChar = (char) (((c - 'a' - rot + 26) % 26) + 'a');
                decrypted.append(decryptedChar);
            } else {
                decrypted.append(c);
            }
        }

        return decrypted.toString();
    }
}
