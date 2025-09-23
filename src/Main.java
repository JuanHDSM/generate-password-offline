import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) {

        JFrame miJFrame = new JFrame("Exemplo - Java Swing");
        miJFrame.setSize(500, 300);
        JPanel miJPanel = new JPanel();
        miJPanel.setSize(300, 400);

        miJPanel.setLayout(new GridBagLayout());

        JLabel miJLabel = new JLabel();

        JTextArea miJTextArea = new JTextArea(1,5);

        JButton bt = new JButton("Gerar");

//        miJPanel.add(miJTextArea);
        miJPanel.add(bt);
        miJPanel.add(Box.createHorizontalStrut(10));
        miJPanel.add(miJLabel);


        bt.addActionListener(e -> {
                    miJLabel.setText("Senha: " + generatePassword(args));

                    miJPanel.add(miJLabel);
                    miJFrame.add(miJPanel);
                }
        );
        miJLabel.setText("Senha: ");

        miJFrame.add(miJPanel);

        miJFrame.setVisible(true);
    }

    static String generatePassword(String[] args) {

        SecureRandom senha = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        byte random8 = (byte) senha.nextInt(8);
        try {
            for (int a = 0; a < Integer.parseInt(args[0]); a++) {
                digitos((byte) senha.nextInt(87));
                if (a % (7 + random8) == 0) {
                    random8 = (byte) senha.nextInt(8);
                    senha = new SecureRandom();
                }
            }
        }
        catch (Exception e) {
            for (byte a = 0; a < 16; a++) {
                sb.append((digitos((byte) senha.nextInt(87))));
            }
        }
        return sb.toString();
    }

    static String digitos(byte random62) {
        String especiais = "!@#$%^&*()-_=+[]{};:,.<>?/";
        StringBuilder sb = new StringBuilder();

        if (random62 < 26) {
            sb.append(((char) (random62 + 97)));
        }
        else if (random62 < 52) {
            sb.append(((char) (random62 + 39)));
        }
        //imprime número
        else if (random62 < 62) {
            sb.append((random62 - 52));
        }
        else {
            int index = random62 % especiais.length();
            sb.append(especiais.charAt(index));
        }

        return sb.toString();
    }
}