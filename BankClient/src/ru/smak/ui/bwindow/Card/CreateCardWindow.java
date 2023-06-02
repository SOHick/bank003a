package ru.smak.ui.bwindow.Card;

import ru.smak.data.BankAccount;
import ru.smak.data.Card;
import ru.smak.data.User;
import ru.smak.net.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class CreateCardWindow extends JFrame
{
    private final static int MIN_SZ = GroupLayout.PREFERRED_SIZE;
    private final static int MAX_SZ = GroupLayout.DEFAULT_SIZE;
    private  final User user;
    private final JPanel WCard;
    private  String NumberChet;
    public CreateCardWindow(User user,Client client,JPanel WCard,List<BankAccount> ListBankAccount)
    {
        this.WCard = WCard;
        this.user = user;
        this.setTitle("Открытие новой карты");
        setSize(600,450);
        GroupLayout gl = new GroupLayout(getContentPane());
        setLayout(gl);
        JButton btnCreateCard = new JButton("Открыть карту");
        JButton btnCancel = new JButton("Отмена");
        JLabel lblPhone = new JLabel("Номер телефона: ");
        JLabel lblPhoneData = new JLabel(user.getPhone());
        JLabel lblNumberAccount = new JLabel("Укажите к какому счёты вы привязываете карту:");
        JLabel lblPaymentSystem = new JLabel("Укажите платёжная систему:");
        JLabel lblTypeCard = new JLabel("Укажите тип карты:");
        String[] PaymentSystems = {
                "Visa",
                "MasterCard",
                "МИР"
        };
        String[] TypeCard = {
                "Visa Gold",
                "Visa Classic",
        };
        String[] NumberAccount = new String[ListBankAccount.size()];
        String[] LastNumberAccount = new String[ListBankAccount.size()];
        int k= 0;
        for (BankAccount account : ListBankAccount) {

            String BalanceNumberOne = account.getBalanceNumberOne();
            String BalanceNumberTwo = account.getBalanceNumberTwo();
            Integer OKB = account.getOKB();
            String CheckDigit = account.getCheckDigit();
            String CodeBank = account.getCodeBank();
            String AccountNumber = account.getAccountNumber();
            String Number = BalanceNumberOne + BalanceNumberTwo + OKB.toString() + CheckDigit + CodeBank + AccountNumber;
            NumberAccount[k] = Number;
            LastNumberAccount[k] = AccountNumber;
            k++;

        }
        JComboBox jcbPaymentSystems = new JComboBox(PaymentSystems);
        JComboBox jcbTypeCard = new JComboBox(TypeCard);
        JComboBox jcbNumberAccount = new JComboBox(NumberAccount);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8,8, Integer.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(lblPhone, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(lblPhoneData,MAX_SZ, MAX_SZ, MAX_SZ)
                        )
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(lblPaymentSystem, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(jcbPaymentSystems,MAX_SZ, MAX_SZ, MAX_SZ)
                        )
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(lblTypeCard, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(jcbTypeCard,MAX_SZ, MAX_SZ, MAX_SZ)
                        )
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(lblNumberAccount, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(jcbNumberAccount,MAX_SZ, MAX_SZ, MAX_SZ)
                        )
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addGap(8,8,Integer.MAX_VALUE)
                                        .addComponent(btnCreateCard, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(btnCancel,MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8,8,Integer.MAX_VALUE)
                        )
                )
                .addGap(8,8, Integer.MAX_VALUE)
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8,8,Integer.MAX_VALUE)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lblPhone,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(lblPhoneData, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lblPaymentSystem,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(jcbPaymentSystems, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lblTypeCard,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(jcbTypeCard, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lblNumberAccount,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(jcbNumberAccount, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(btnCreateCard,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(btnCancel, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(8,8,Integer.MAX_VALUE)
        );
       jcbPaymentSystems.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
               jcbTypeCard.removeAllItems();
               switch (e.getItem().toString()) {
                   case "Visa" -> {
                       jcbTypeCard.addItem("Visa Gold");
                       jcbTypeCard.addItem("Visa Classic");
                       jcbPaymentSystems.setSelectedItem("Visa");

                   }
                   case "MasterCard" -> {
                       jcbTypeCard.addItem("MasterCard Gold");
                       jcbTypeCard.addItem("MasterCard Classic");
                       jcbPaymentSystems.setSelectedItem("MasterCard");
                   }
                   case "МИР" -> {
                       jcbTypeCard.addItem("МИР Gold");
                       jcbTypeCard.addItem("МИР Classic");
                       jcbPaymentSystems.setSelectedItem("МИР");

                   }
               }
           }
       });

       btnCreateCard.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                Card card = new Card();
                BankAccount BA = new BankAccount();
                card.setPhone(user.getPhone());
                BA.setPhone(user.getPhone());
                BA.setBalance(1000);
                card.setAccountNumber((String) jcbNumberAccount.getSelectedItem());
                card.setBalance(1000);
                card.setPaymentSystems((String) jcbPaymentSystems.getSelectedItem());
                card.setTypeCard((String) jcbTypeCard.getSelectedItem());
               int index = jcbNumberAccount.getSelectedIndex();
               if (index != -1)
               {
                   String b = LastNumberAccount[index];
                   String g =setNumberCard(jcbPaymentSystems.getSelectedIndex());
                   card.setNumberChet(NumberChet);
                   BA.setAccountNumber(b);
                   card.setLastAccountNumber(b);
                   card.setNumberCard(g);
                   try {
                       client.regCard(card,6);
                       client.regAccount(BA,7);
                   } catch (IOException ex) {
                       throw new RuntimeException(ex);
                   }
                   CardWindow cw = new CardWindow(user,client, (String) jcbPaymentSystems.getSelectedItem(),(String) jcbTypeCard.getSelectedItem(),g);
                   WCard.add(cw);
                   WCard.revalidate();
               }
               else   JOptionPane.showMessageDialog(CreateCardWindow.this, "Сначал создайте счёт в банке");

           }
       });

    }
    public String setNumberCard(Integer i)
    {

        String PlatSystemsNumber = "";
        switch (i)
        {
            case 1->  PlatSystemsNumber = "4";
            case 2->  PlatSystemsNumber = "2";
            case 3->  PlatSystemsNumber = "5";

        }

        String NumberBank = "200 38";


        String m1= Integer.toString(nDigitRandomNo(2));
        String m2= Integer.toString(nDigitRandomNo(4));
        String m3= Integer.toString(nDigitRandomNo(3));
        String AlgLuna = Integer.toString(nDigitRandomNo(1));
        this.NumberChet = m1 + m2+ m3;
        String M =m1 + " " + m2 + " " + m3 + AlgLuna;
        return PlatSystemsNumber + NumberBank + M;
    }
    private int nDigitRandomNo(int digits){
        int max = (int) Math.pow(10,(digits)) - 1; //for digits =7, max will be 9999999
        int min = (int) Math.pow(10, digits-1); //for digits = 7, min will be 1000000
        int range = max-min; //This is 8999999
        Random r = new Random();
        int x = r.nextInt(range);// This will generate random integers in range 0 - 8999999
        return x+min;
    }
}
