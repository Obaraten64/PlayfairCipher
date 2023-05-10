import javax.swing.*;

import java.awt.*;

public class PlayfairCipherMain {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame("Playfair cipher");
                //set icon img
                ImageIcon img = new ImageIcon("E:\\Komp\\UFM\\Coffe.jpg");
                f.setIconImage(img.getImage());
                //set icon img
                f.setLocation(650, 200);
                f.setSize(new Dimension(750, 750));
                f.setLayout(new GridBagLayout());
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                f.setVisible(true);
                PlayfairScreens.mainScreen(f);
            }
        });
    }

    public static GridBagConstraints initializeInterface() {
        GridBagConstraints gdc = new GridBagConstraints();
        gdc.gridx = 0;
        gdc.gridy = 0;
        gdc.insets = new Insets(8, 8, 8, 8);
        return gdc;
    }
}
