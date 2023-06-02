package ru.smak.ui.bwindow.AccountWindow;

import ru.smak.data.BankAccount;
import ru.smak.data.User;
import ru.smak.net.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class AccountWindow extends JPanel
{
    private final User user;
    private final Client client;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private ValueBalanceAndQuantity jpnBalance;
    private ValueBalanceAndQuantity jpnQuantityAccounts;
    private final static int MIN_SZ = GroupLayout.PREFERRED_SIZE;
    private final static int MAX_SZ = GroupLayout.DEFAULT_SIZE;
    private final JPanel DownPanel;
    private  List<BankAccount> ListBankAccount;

    public AccountWindow(JPanel mainPanel, User user,Client client){
        this.client = client;
        this.user = user;
        JPanel UpPanel = new JPanel();
        DownPanel = new JPanel();
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
        UPuiUpPanel(UpPanel);
        UPuiDownPanel(DownPanel,0,0);
    }
    public void UPuiUpPanel(JPanel UpPanel)
    {
        GroupLayout gl1 = new GroupLayout(UpPanel);
        UpPanel.setLayout(gl1);
        tableModel = new DefaultTableModel();
        JTable WAccount = new JTable(tableModel);
        tableModel.addColumn("Номер телефона");
        tableModel.addColumn("Номер счёта");
        tableModel.addColumn("Баланс");
        scrollPane = new JScrollPane(WAccount);
        JLabel MyAccount = new JLabel();
        MyAccount.setText("Mои счета");
        JButton NewAccount = new JButton();
        NewAccount.setText("Новый счёт");
        JButton btnUpdate = new JButton("Обновить данные страницы");
        WAccount.setBackground(Color.white);
        WAccount.setBorder(
                BorderFactory.createLoweredBevelBorder()
        );
        gl1.setHorizontalGroup(gl1.createSequentialGroup()
                .addGap(20)
                .addGroup(gl1.createSequentialGroup()
                        .addComponent(MyAccount,MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(8)
                .addGroup(gl1.createSequentialGroup()
                        .addGroup(gl1.createParallelGroup()
                                .addComponent(NewAccount,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addGap(8)
                                .addComponent(btnUpdate,MIN_SZ, MIN_SZ, MIN_SZ)
                        )
                        .addGap(8)
                        .addComponent(scrollPane,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                )

                .addGap(20)
        );
        gl1.setVerticalGroup(gl1.createSequentialGroup()
                        .addGap(20)
                        .addGroup(gl1.createParallelGroup()
                                .addComponent(MyAccount,MIN_SZ, MIN_SZ, MIN_SZ)
                        )
                        .addGap(8)
                        .addGroup(gl1.createParallelGroup()
                                .addGroup(gl1.createSequentialGroup()
                                        .addComponent(NewAccount,MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8,8,Integer.MAX_VALUE)
                                        .addComponent(btnUpdate,MIN_SZ, MIN_SZ, MIN_SZ)
                                )
                                        .addComponent(scrollPane,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        )
                        .addGap(20)
        );
        NewAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CreateAccountWindow accountWindow = new CreateAccountWindow(user,client);
                accountWindow.setVisible(true);
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankAccount BA = new BankAccount();
                BA.setPhone(user.getPhone());
                try {
                    client.regAccount(BA,4);
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void UPuiDownPanel(JPanel DownPanel,int k,int l)
    {
        GroupLayout gl = new GroupLayout(DownPanel);
        DownPanel.setLayout(gl);
        JLabel lblBalance = new JLabel("Ваш суммарный баланс: ");
        JLabel lblQuantityAccounts = new JLabel("Колличество счетов на вашем аккаунте: ");
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
    public void UpdateTable(List<BankAccount> bankAccount)
    {
        tableModel.setRowCount(0);
        Integer balance = 0;
        for (BankAccount account : bankAccount) {
            String phone = account.getPhone();
            Integer Balance = account.getBalance();
            String BalanceNumberOne = account.getBalanceNumberOne();
            String BalanceNumberTwo = account.getBalanceNumberTwo();
            Integer OKB = account.getOKB();
            String CheckDigit = account.getCheckDigit();
            String CodeBank = account.getCodeBank();
            String AccountNumber = account.getAccountNumber();
            String Number = BalanceNumberOne + BalanceNumberTwo + OKB.toString() + CheckDigit + CodeBank + AccountNumber;
            tableModel.insertRow(0,new Object[] { phone, Number,Balance });
            balance += account.getBalance();
        }
        DownPanel.removeAll();
        UPuiDownPanel(DownPanel, balance,bankAccount.size());
    }
    public void UpdateData()
    {
        BankAccount BA = new BankAccount();
        BA.setPhone(user.getPhone());
        try {
            client.regAccount(BA,4);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
