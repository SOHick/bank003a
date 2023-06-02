package ru.smak.ui.bwindow.Card;

import ru.smak.data.BankAccount;
import ru.smak.data.Card;
import ru.smak.data.User;
import ru.smak.net.Client;
import ru.smak.ui.bwindow.AccountWindow.ValueBalanceAndQuantity;
import ru.smak.ui.bwindow.OverviewWindow.OverviewWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CardWindow extends JPanel
{
    private final String NumbCard;
    private List<Card> ListCard;
    private ValueBalanceAndQuantity jpnBalance;
    private JPanel DownPanel;
    private OverviewWindow overviewWindow;

    public CardWindow(User user, Client client, String PaymentSystems, String TypeCard, String NumbCard)
    {
        this.NumbCard = NumbCard;
        this.setOpaque(true);
        switch(TypeCard){
            case "Visa Gold","MasterCard Gold","МИР Gold"->{
                this.setBackground(Color.orange);
            }
            case "Visa Classic","MasterCard Classic","МИР Classic"->{
                this.setBackground(Color.lightGray);
            }
        }
        JLabel VisaStatus = new JLabel();
        JLabel NumberCard = new JLabel();
        JLabel NameClient = new JLabel();
        JLabel VisaMCMir = new JLabel();
        JLabel DataCard = new JLabel();
        NumberCard.setText(NumbCard);
        VisaStatus.setText(TypeCard);
        NameClient.setText(user.getFirstName() + " " + user.getLastName());
        VisaMCMir.setText(PaymentSystems);
        DataCard.setText("5/28");
        GroupLayout card = new GroupLayout(this);
        this.setLayout(card);
        card.setHorizontalGroup(card.createParallelGroup()
                        .addGroup(card.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGap(4,4,Integer.MAX_VALUE)
                                .addComponent(VisaStatus,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                .addGap(4,4,Integer.MAX_VALUE)
                        )
                        .addGroup(card.createSequentialGroup()
                                .addGap(8)
                                .addComponent(NumberCard,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                        )
                        .addGroup(card.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGap(4,4,Integer.MAX_VALUE)
                                .addComponent(DataCard,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                .addGap(4,4,Integer.MAX_VALUE)
                        )
                        .addGroup(card.createSequentialGroup()
                                .addGap(8)
                                .addComponent(NameClient,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                .addGap(4,4,Integer.MAX_VALUE)
                                .addComponent(VisaMCMir,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                        )

        );
        card.setVerticalGroup(card.createSequentialGroup()
                        .addGroup(card.createSequentialGroup()
                                .addGap(8)
                                .addComponent(VisaStatus,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                        )
                        .addGroup(card.createSequentialGroup()
                                .addGap(8)
                                .addComponent(NumberCard,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                        )
                        .addGroup(card.createSequentialGroup()
                                .addGap(8)
                                .addComponent(DataCard,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                        )
                        .addGroup(card.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addGap(8)
                                .addComponent(NameClient,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                .addComponent(VisaMCMir,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                                .addGap(8)
                        )
        );

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Card card = new Card();
                card.setPhone(user.getPhone());
                card.setNumberCard(NumbCard);
                try {
                    client.regCard(card,9);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                for (Card card1 : ListCard) {
                   if (Objects.equals(card1.getNumberCard(), card.getNumberCard())) {
                       DownPanel.removeAll();
                       overviewWindow.UPuiDownPanel(DownPanel,card1.getBalance(),ListCard.size());
                   }
                }
                repaint();
            }
        });

    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250, 125);
    }
    public void setListCard(List<Card> ListCard) {
        this.ListCard = ListCard;

    }
    public void setDownPanel(JPanel DownPanel) {
        this.DownPanel = DownPanel;

    }
    public void setOverviewWindow(OverviewWindow OverviewWindow) {
        this.overviewWindow = OverviewWindow;
    }
}
