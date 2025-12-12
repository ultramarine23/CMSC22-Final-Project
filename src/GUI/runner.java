package GUI;

import javax.swing.SwingUtilities;

public class runner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new window();
        });
    }
}