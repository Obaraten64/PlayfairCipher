package add;

import javax.swing.*;

import java.awt.event.*;

public class TextConsumer implements KeyListener {
    JTextArea input;

    public TextConsumer(JTextArea textArea) {
        input = textArea;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int max = 40;
        if(input.getText().length() > max+1) {
            e.consume();
            String shortened = input.getText().substring(0, max);
            input.setText(shortened);
        }else if(input.getText().length() >= max) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
