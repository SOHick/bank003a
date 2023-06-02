package ru.smak.ui.bwindow.AccountWindow;

import javax.swing.*;

import java.awt.*;

public class ValueBalanceAndQuantity extends JPanel
{
    private final Integer avg;
    private final Font f1 = new Font("TimesRoman", Font.BOLD, 22),
            f2 = new Font("Courier", Font.ITALIC, 10),
            f3 = new Font("Arial", Font.BOLD + Font.ITALIC, 16);
    public ValueBalanceAndQuantity(Integer avg) {
        this.avg = avg;
        this.setBackground(Color.WHITE);

    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setFont(f3);
        g2.drawString(Integer.toString(avg), 5, 25);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 30);
    }
}
