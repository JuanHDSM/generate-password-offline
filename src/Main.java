import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.security.SecureRandom;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class Main {
    public static void main(String[] args) {

        // Frames
        JFrame miJFrame = new JFrame("PasswordGenerator");
        miJFrame.setSize(500, 300);
        miJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Definir ícone personalizado da aplicação
        try {
            java.awt.Image icon = java.awt.Toolkit.getDefaultToolkit().getImage("generate-password-offline\\src\\imagem\\keyy_icon.png");
            miJFrame.setIconImage(icon);
        } catch (Exception ex) {
            System.out.println("Ícone não encontrado, usando padrão");
        }
        
        // Panels
        JPanel mainPanel = new JPanel();
        JPanel passPanel = new JPanel();
        passPanel.setSize(300, 200);

        // Labels
        JLabel textCopiedLabel = new JLabel();
        JLabel textoFixoLabel = new JLabel("");
        JLabel passLabel = new JLabel();
        JLabel tamanhoJLabel = new JLabel("Tamanho da Senha Aqui: ");

        // Layout
        BoxLayout boxLayout = new BoxLayout(passPanel, BoxLayout.Y_AXIS);
        GridBagLayout gridBagLayout = new GridBagLayout();

        // Buttons
        JButton bt = new JButton("Gerar Senha");

        // checkBox
        JCheckBox maiusculosBox = new JCheckBox("LETRAS MAIUSCULAS",true);
        JCheckBox minusculobox = new JCheckBox("LETRAS MINUSCULAS", true);
        JCheckBox numerosBox = new JCheckBox("NÚMEROS", true);
        JCheckBox caracterBox = new JCheckBox("CARACTERES",true);

        // JSpinner substituindo JTextField
        JSpinner tamanhosenha = new JSpinner(new SpinnerNumberModel(16, 1, 60, 1));

        mainPanel.setLayout(gridBagLayout);
        passPanel.setLayout(boxLayout);
        passPanel.add(textoFixoLabel);
        passPanel.add(marginTopBottom());
        passPanel.add(marginTopBottom());
        passPanel.add(tamanhoJLabel);
        passPanel.add(tamanhosenha);
        passPanel.add(marginTopBottom());
        passPanel.add(textCopiedLabel);
        passPanel.add(maiusculosBox);
        passPanel.add(minusculobox);
        passPanel.add(numerosBox);
        passPanel.add(caracterBox);
        passPanel.add(bt);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center the component
        mainPanel.add(passPanel, gbc);
    
        bt.addActionListener(e -> {
            int tamanho = (Integer)tamanhosenha.getValue();
            
            String senha = generatePassword(tamanho, numerosBox.isSelected(), minusculobox.isSelected(), maiusculosBox.isSelected(), caracterBox.isSelected());
            textoFixoLabel.setText("<html>Senha Gerada: <font color='green'>" + senha + "</font></html>");
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

    static String generatePassword(int tamanho , boolean numeros, boolean minusculo, boolean maiusculo, boolean caracteres ) {

        SecureRandom senha = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        byte random8 = (byte) senha.nextInt(8);
        try {
            for (int a = 0; a < tamanho; a++) {
                sb.append(digitos(((byte) senha.nextInt(87)), numeros, maiusculo, minusculo, caracteres));
                if (a % (7 + random8) == 0) {
                    random8 = (byte) senha.nextInt(8);
                    senha = new SecureRandom();
                }
            }
        }
        catch (Exception e) {
            for (byte a = 0; a < 16; a++) {
                sb.append((digitos(((byte) senha.nextInt(87)),numeros, maiusculo, minusculo,caracteres )));
            }
        }
        return sb.toString();
    }

    static String digitos(byte random62, boolean numeros, boolean minusculo, boolean maiusculo, boolean caracteres) {
        String especiais = "!@#$%^&*()-_=+[]{};:,.<>?/";
        StringBuilder sb = new StringBuilder();

        if (random62 < 26 && minusculo ) {
            sb.append(((char) (random62 + 97)));
        }
        else if (random62 < 52 && maiusculo ) {
            sb.append(((char) (random62 + 39)));
        }
        //imprime número
        else if (random62 < 62 && numeros ) {
            sb.append((random62 - 52));
        }
        else if (caracteres){
            int index = random62 % especiais.length();
            sb.append(especiais.charAt(index));
        }

        return sb.toString();
    }
}