package ru.smak.ui.bwindow.LeftPanel;

import javax.swing.*;
import java.awt.*;

public class StatusClientPanel extends JPanel {

    public StatusClientPanel(GroupLayout status,JButton overviewButton,JButton scoreButton,JButton infoClient)
    {
        infoClient.setBackground(Color.pink);
        overviewButton.setBackground(Color.pink);
        scoreButton.setBackground(Color.pink);
        overviewButton.setPreferredSize(new Dimension(100, 27));
        scoreButton.setPreferredSize(new Dimension(100, 27));
        infoClient.setPreferredSize(new Dimension(100, 27));

        status.setHorizontalGroup(status.createSequentialGroup()
                .addGap(8)
                        .addGroup(status.createParallelGroup()
                                .addComponent(overviewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(8)
                                .addComponent(scoreButton,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(8)
                                .addComponent(infoClient,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        )
                .addGap(8)
        );
        status.setVerticalGroup(status.createSequentialGroup()
                .addGap(8,8,Integer.MAX_VALUE)
                .addGroup(status.createSequentialGroup()
                        .addComponent(overviewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(8)
                        .addComponent(scoreButton,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(8)
                        .addComponent(infoClient,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(8,8,Integer.MAX_VALUE)
        );
    }

}
