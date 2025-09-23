import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.security.SecureRandom;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Main {
    public static void main(String[] args) {

        JFrame miJFrame = new JFrame("Exemplo - Java Swing");
        miJFrame.setSize(500, 300);
        JPanel miJPanel = new JPanel();
        JPanel miJPanel2 = new JPanel();
        miJPanel.setSize(300, 400);
        miJPanel2.setSize(300, 20);

        miJPanel2.setLayout(new GridBagLayout());
        miJPanel.setLayout(new FlowLayout());

        JLabel miJLabel = new JLabel();
        JLabel miJLabel2 = new JLabel();

        JTextArea miJTextArea = new JTextArea(1, 5);

        JButton bt = new JButton("Gerar");

        //        miJPanel.add(miJTextArea);
        miJPanel2.add(bt);
        miJPanel2.add(Box.createHorizontalStrut(10));
        miJPanel.add(miJLabel);
        miJPanel2.add(miJLabel2);

        bt.addActionListener(e -> {
            String senha = generatePassword(args);
            miJLabel.setText("Senha: " + senha);
            miJLabel2.setText("Senha cópiada");
            textCopy(senha);

            miJPanel.add(miJLabel);
            miJFrame.add(miJPanel);
        });
        miJLabel.setText("Senha: ");
        miJLabel2.setText("Sen222ha: ");

        miJFrame.add(miJPanel);
        miJFrame.add(miJPanel2);

        miJFrame.setVisible(true);
    }

    static void textCopy(String pass) {
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();

        StringSelection selection = new StringSelection(pass);

        cb.setContents(selection, null);
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