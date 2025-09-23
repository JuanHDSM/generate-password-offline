import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.security.SecureRandom;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {

        // Frames
        JFrame miJFrame = new JFrame("Exemplo - Java Swing");
        miJFrame.setSize(500, 300);
        miJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panels
        JPanel mainPanel = new JPanel();
        JPanel passPanel = new JPanel();
        passPanel.setSize(300, 200);

        // Labels
        JLabel textCopiedLabel = new JLabel();
        JLabel passLabel = new JLabel();

        // Layout
        BoxLayout boxLayout = new BoxLayout(passPanel, BoxLayout.Y_AXIS);
        GridBagLayout gridBagLayout = new GridBagLayout();

        // Buttons
        JButton bt = new JButton("Gerar Senha");

        mainPanel.setLayout(gridBagLayout);
        passPanel.setLayout(boxLayout);
        passPanel.add(marginTopBottom());
        passPanel.add(passLabel);
        passPanel.add(marginTopBottom());
        passPanel.add(bt);
        passPanel.add(marginTopBottom());
        passPanel.add(textCopiedLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center the component
        mainPanel.add(passPanel, gbc);

        bt.addActionListener(e -> {
            String senha = generatePassword(args);
            passLabel.setText("Senha: " + senha);
            textCopiedLabel.setText("\nSenha cópiada");
            copyPass(senha);

        });
        
        miJFrame.add(mainPanel);
        miJFrame.setLocationRelativeTo(null);

        miJFrame.setVisible(true);
    }
    
    static Component marginTopBottom() {
        return Box.createVerticalStrut(10);
    }

    static void copyPass(String pass) {
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
                sb.append(digitos((byte) senha.nextInt(87)));
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