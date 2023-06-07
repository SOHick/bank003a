package ru.smak.ui.bwindow.OverviewWindow;


import ru.smak.data.BankAccount;
import ru.smak.data.Card;
import ru.smak.data.User;
import ru.smak.net.Client;
import ru.smak.ui.bwindow.AccountWindow.ValueBalanceAndQuantity;
import ru.smak.ui.bwindow.Card.CardWindow;
import ru.smak.ui.bwindow.Card.CreateCardWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class OverviewWindow extends JPanel
{
    private  final User user;
    private  final Client client;
    private  List<BankAccount> ListBankAccount;
    private final   List<Card> CardList;
    private final   List<Card> CardAllList;
    private  Boolean b;
    private final static int MIN_SZ = GroupLayout.PREFERRED_SIZE;
    private final static int MAX_SZ = GroupLayout.DEFAULT_SIZE;
    private ValueBalanceAndQuantity jpnBalance;
    private ValueBalanceAndQuantity jpnQuantityAccounts;
    private  PushBalanceWindow pushBalanceWindow;
    public OverviewWindow(JPanel mainPanel, User user, Client client,List<Card> CardList,List<Card> CardAllList)
    {
        try {
            Card card = new Card();
            card.setPhone(user.getPhone());
            client.regCard(card,8);
            client.regCard(card,11);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.CardAllList= CardAllList;
        this.CardList = CardList;
        this.user = user;
        this.client = client;
        JPanel UpPanel = new JPanel();
        JPanel DownPanel = new JPanel();
        GroupLayout status = new GroupLayout(mainPanel);
        mainPanel.setLayout(status);
        DownPanel.setBorder(
                BorderFactory.createLoweredBevelBorder()
        );

        status.setHorizontalGroup(status.createSequentialGroup()
                .addGap(8)
                .addGroup(status.createParallelGroup()
                        .addComponent(UpPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addComponent(DownPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                )
                .addGap(8)
        );
        status.setVerticalGroup(status.createSequentialGroup()
                .addGap(8)
                .addGroup(status.createSequentialGroup()
                        .addComponent(UpPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(8)
                        .addComponent(DownPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                )
                .addGap(8)
        );

        UPui(UpPanel,DownPanel);
        UPuiDownPanel(DownPanel,0,CardList.size());


    }
    public void UPui(JPanel UpPanel,JPanel DownPanel)
    {
        GroupLayout gl1 = new GroupLayout(UpPanel);
        UpPanel.setLayout(gl1);
        JPanel WCard = new JPanel();
        JLabel MyCard = new JLabel();
        MyCard.setText("Mои карты");
        JButton NewCard = new JButton();
        NewCard.setText("Новая Карта");
        JButton btnPush = new JButton("Перевести средства");
        WCard.setBackground(Color.white);
        WCard.setBorder(
                BorderFactory.createLoweredBevelBorder()
        );

        gl1.setHorizontalGroup(gl1.createSequentialGroup()
                .addGap(8)
                .addGroup(gl1.createParallelGroup()
                        .addComponent(MyCard,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(8)
                        .addComponent(NewCard,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(8)
                        .addComponent(btnPush,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                )
                .addGroup(gl1.createSequentialGroup()
                        .addGap(8)
                                .addComponent(WCard,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                )
                .addGap(8)
        );
        gl1.setVerticalGroup(gl1.createParallelGroup()
                .addGap(20)
                .addGroup(gl1.createSequentialGroup()
                        .addComponent(MyCard,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(8)
                        .addComponent(NewCard,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(8,8,Integer.MAX_VALUE)
                        .addComponent(btnPush,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(8)
                )
                .addGroup(gl1.createSequentialGroup()
                                .addComponent(WCard,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                )
                .addGap(20)
        );
        if(CardList != null)
        {
            UpDateCard(CardList,WCard,DownPanel);
        }
        NewCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CreateCardWindow createCardWindow = new CreateCardWindow(user,client,WCard,ListBankAccount);
                createCardWindow.setVisible(true);
            }
        });
        btnPush.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pushBalanceWindow = new PushBalanceWindow(user,client,WCard,ListBankAccount,CardList,CardAllList);
                pushBalanceWindow.setVisible(true);
            }
        });

    }
    public void UPuiDownPanel(JPanel DownPanel,int k,int l)
    {
        GroupLayout gl = new GroupLayout(DownPanel);
        DownPanel.setLayout(gl);
        JLabel lblBalance = new JLabel("Баланс на карте RU: ");
        JLabel lblQuantityAccounts = new JLabel("Колличество карт на аккаунте: ");
        jpnBalance = new ValueBalanceAndQuantity(k);
        jpnQuantityAccounts = new ValueBalanceAndQuantity(l);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8,8, Integer.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(lblBalance, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(jpnBalance,MAX_SZ, MAX_SZ, MAX_SZ)
                        )
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(lblQuantityAccounts, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(jpnQuantityAccounts,MAX_SZ, MAX_SZ, MAX_SZ)
                        )
                        .addGap(8,8, Integer.MAX_VALUE)
                ));
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8,8,Integer.MAX_VALUE)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lblBalance,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(jpnBalance, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lblQuantityAccounts,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(jpnQuantityAccounts, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
        );
        gl.linkSize(0, lblBalance, lblQuantityAccounts, jpnBalance, jpnQuantityAccounts);
        DownPanel.repaint();
    }
    public void UpDateCard(List<Card> cards,JPanel WCard,JPanel DownPanel)
    {
        for (Card card : cards) {
            CardWindow cw = new CardWindow(user,client,card.getPaymentSystems(), card.getTypeCard(),card.getNumberCard());
            cw.setListCard(CardList);
            cw.setDownPanel(DownPanel);
            cw.setOverviewWindow(OverviewWindow.this);
            WCard.add(cw);
            WCard.revalidate();
        }

    }
    public void setListBankAccount(List<BankAccount> ListBankAccount)
    {
        this.ListBankAccount = ListBankAccount;
    }
    public void setB(Boolean b)
    {
        this.b = b;
        pushBalanceWindow.setB(b);
    }
}
