import add.TextConsumer;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class PlayfairScreens {
    public static void mainScreen(JFrame f) {
        GridBagConstraints gdc = PlayfairCipherMain.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JLabel label = new JLabel("Select between encoding and decoding your text");
        f.add(label, gdc);

        gdc.anchor = GridBagConstraints.WEST;
        gdc.gridy++;
        JButton codeButton = new JButton("Code");
        codeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayfairScreens.secondScreen(f, "Code");
            }
        });
        f.add(codeButton, gdc);

        gdc.anchor = GridBagConstraints.EAST;
        JButton decodeButton = new JButton("Decode");
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayfairScreens.secondScreen(f, "Decode");
            }
        });
        f.add(decodeButton, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }

    private static void secondScreen(JFrame f, String chosen) {
        GridBagConstraints gdc = PlayfairCipherMain.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JLabel keyLabel = new JLabel(((char)0x2193) + " Enter your key here " + ((char)0x2193));
        f.add(keyLabel, gdc);
        gdc.gridx += 2;

        JLabel textLabel = new JLabel(((char)0x2193) + " Enter your text here " + ((char)0x2193));
        f.add(textLabel, gdc);
        gdc.gridx = 0;
        gdc.gridy++;

        JTextArea inputKey = new JTextArea(1, 20);
        inputKey.setEditable(true);
        f.add(inputKey, gdc);
        gdc.gridx += 2;

        JTextArea inputText = new JTextArea(1, 20);
        inputText.setEditable(true);
        f.add(inputText, gdc);
        gdc.gridx = 0;
        gdc.gridy++;

        JTextPane exampleKey = new JTextPane();
        exampleKey.setText("Example: \"playfair example\"");
        exampleKey.setEditable(false);
        f.add(exampleKey, gdc);
        gdc.gridx += 2;

        JTextPane exampleText = new JTextPane();
        exampleText.setText("Example: \"Hide the gold in the tree stump\"");
        exampleText.setEditable(false);
        f.add(exampleText, gdc);
        gdc.gridx--;
        gdc.gridy++;

        //symbols limits
        inputText.addKeyListener(new TextConsumer(inputText));
        inputKey.addKeyListener(new TextConsumer(inputKey));
        //symbols limits

        JButton button = new JButton(chosen);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayfairCipher.makeMatrix(inputKey.getText());
                String str = switch (chosen) {
                    case ("Code") -> PlayfairCipher.code(inputText.getText());
                    case ("Decode") -> PlayfairCipher.decode(inputText.getText());
                    default -> throw new IllegalStateException("Unexpected value: " + chosen);
                };
                PlayfairScreens.thirdScreen(f, str);
            }
        });
        f.add(button, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }

    private static void thirdScreen(JFrame f, String output) {
        GridBagConstraints gdc = PlayfairCipherMain.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JPanel panelForButtons = new JPanel(new GridLayout(0, 1));
        //panel struct
        JTextArea textArea = new JTextArea(1, 20);
        textArea.setText(output);
        textArea.setEditable(false);
        panelForButtons.add(textArea, gdc);

        JButton button = new JButton("Go to main screen");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayfairScreens.mainScreen(f);
            }
        });
        panelForButtons.add(button, gdc);
        //panel struct
        gdc.weighty = 1.0;
        f.add(panelForButtons, gdc);

        gdc.gridy++;
        gdc.weighty = 0.1;
        gdc.anchor = GridBagConstraints.PAGE_END;

        JButton matrixButton = new JButton("Show matrix");
        matrixButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayfairScreens.additionalScreen(f, output);
            }
        });
        f.add(matrixButton, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }

    private static void additionalScreen(JFrame f, String output) {
        GridBagConstraints gdc = PlayfairCipherMain.initializeInterface();
        f.getContentPane().removeAll();
        //remove section|
        JLabel label = new JLabel();
        label.setText(PlayfairCipher.showMatrix());
        f.add(label, gdc);
        gdc.gridy++;

        JButton button = new JButton("Back to previous screen");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayfairScreens.thirdScreen(f, output);
            }
        });
        f.add(button, gdc);
        //remove section|
        f.revalidate();
        f.repaint();
    }
}
