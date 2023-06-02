package ru.smak.ui.bwindow.BankWindow;

import ru.smak.data.BankAccount;
import ru.smak.data.Card;
import ru.smak.data.User;
import ru.smak.net.Client;
import ru.smak.ui.bwindow.Card.CardWindow;
import ru.smak.ui.bwindow.InfoClientWindow.InfoClientWindow;
import ru.smak.ui.bwindow.AccountWindow.AccountWindow;
import ru.smak.ui.bwindow.OverviewWindow.OverviewWindow;
import ru.smak.ui.bwindow.LeftPanel.StatusClientPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class BankWindow extends JFrame
{
    private final JPanel mainPanel = new JPanel();
    private  AccountWindow accountWindow;
    private  OverviewWindow mainWindowPanel;
    private  List<BankAccount> ListBankAccount;
    private  List<Card> CardList;
    private final Client client;
    public BankWindow(User user, Client client){
        this.client = client;
        this.setTitle("Bank Window");
        setSize(800,600);
        JPanel controlPanel = new JPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GroupLayout gl = new GroupLayout(getContentPane());
        GroupLayout statusClient = new GroupLayout(controlPanel);
        JButton overviewButton = new JButton("Мои карты");
        JButton scoreButton = new JButton("Мои счета");
        JButton InfoClient = new JButton("Профиль");
        StatusClientPanel statusClientPanel = new StatusClientPanel(statusClient,overviewButton,scoreButton,InfoClient);
        InfoClientWindow infoWindow = new InfoClientWindow(mainPanel,user);
        setLayout(gl);
        controlPanel.setLayout(statusClient);
        controlPanel.setBackground(Color.white);
        controlPanel.setBorder(
                BorderFactory.createLoweredBevelBorder()
        );
        mainPanel.setBorder(
                BorderFactory.createLoweredBevelBorder()
        );
        mainPanel.setBackground(Color.lightGray);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE,100,100)
                        .addGap(8)
                        .addComponent(mainPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                )
                .addGap(8)
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(gl.createParallelGroup()
                        .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(8)
                        .addComponent(mainPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
                )
                .addGap(8)
        );
        overviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    setDataAccount(client,user);
                     mainWindowPanel = new OverviewWindow(mainPanel,user,client,CardList);
                     mainWindowPanel.setListBankAccount(ListBankAccount);
                     mainPanel.removeAll();
            }
        });
        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDataAccount(client,user);
                accountWindow = new AccountWindow(mainPanel,user,client);
                accountWindow.UpdateData();
                mainPanel.removeAll();


            }
        });
        InfoClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoClientWindow infoWindow = new InfoClientWindow(mainPanel,user);
                mainPanel.removeAll();
            }
        });

    }
    public AccountWindow getAccountWindow() {
        return accountWindow;
    }
    public List getAccountList() {
        return ListBankAccount;
    }
    public void setAccountList(List<BankAccount> ListBankAccount)
    {
        this.ListBankAccount = ListBankAccount;
    }
    public void setCardList(List<Card> CardList)
    {
        this.CardList = CardList;
    }
    public OverviewWindow getMainWindowPanel() {
        return mainWindowPanel;
    }
    public void setDataAccount(Client client,User user)
    {
        BankAccount BA = new BankAccount();
        Card card = new Card();
        card.setPhone(user.getPhone());
        BA.setPhone(user.getPhone());
        try {
            client.regAccount(BA,5);
            client.regCard(card,8);

        } catch (IOException ex) {
            throw new RuntimeException(ex);

        }

    }
}
