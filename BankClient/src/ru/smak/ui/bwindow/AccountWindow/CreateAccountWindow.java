package ru.smak.ui.bwindow.AccountWindow;

import ru.smak.data.BankAccount;
import ru.smak.data.User;
import ru.smak.net.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class CreateAccountWindow extends JFrame
{
    public CreateAccountWindow(User user, Client client)
    {
        setTitle("Открытие счёта");
        setSize(600,450);
        GroupLayout gl = new GroupLayout(getContentPane());
        setLayout(gl);
        int MIN_SZ = GroupLayout.PREFERRED_SIZE;
        int MAX_SZ = GroupLayout.DEFAULT_SIZE;
        String[] items = {
                "RU",
                "EUR",
                "USD"
        };
        var jComboBox = new JComboBox(items);
        JLabel lblPhone = new JLabel("Номер телефона: " + user.getPhone());

        JButton btnCreate = new JButton("Открыть счёт");
        JButton btnCancel = new JButton("Отмена");

        gl.setHorizontalGroup(gl.createSequentialGroup()
                        .addGap(8,8, Integer.MAX_VALUE)
                        .addGroup(gl.createParallelGroup()
                                .addGroup(gl.createSequentialGroup()
                                                .addComponent(lblPhone, MIN_SZ, MIN_SZ, MIN_SZ)
                                )
                                .addGap(8)
                                .addGroup(gl.createSequentialGroup()
                                        .addComponent(jComboBox, MIN_SZ, MIN_SZ, MIN_SZ)
                                )
                                .addGap(8)
                                .addGroup(
                                        gl.createSequentialGroup()
                                                .addGap(8,8,Integer.MAX_VALUE)
                                                .addComponent(btnCreate, MIN_SZ, MIN_SZ, MIN_SZ)
                                                .addGap(8)
                                                .addComponent(btnCancel,MIN_SZ, MIN_SZ, MIN_SZ)
                                                .addGap(8,8,Integer.MAX_VALUE)
                                )
                        )
                        .addGap(8,8, Integer.MAX_VALUE)
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                        .addGap(8,8,Integer.MAX_VALUE)
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(lblPhone,MIN_SZ, MIN_SZ, MIN_SZ)
                        )
                        .addGap(8)
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                         .addComponent(jComboBox,MIN_SZ, MIN_SZ, MIN_SZ)
                       )
                       .addGap(8)
                        .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(btnCreate,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(btnCancel, MIN_SZ, MIN_SZ, MIN_SZ)
                        )
                      .addGap(8,8,Integer.MAX_VALUE)
        );
        gl.linkSize(0, lblPhone);
        gl.linkSize(0,btnCreate,btnCancel);
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankAccount BA = new BankAccount();
                BA.setPhone(user.getPhone());
                BA.setBalanceNumberOne("408");
                BA.setBalanceNumberTwo("02");
                switch (jComboBox.getSelectedIndex()) {
                    case 0-> BA.setOKB(810);
                    case 1-> BA.setOKB(978);
                    case 2-> BA.setOKB(840);
                }
                BA.setBalance(0);
                BA.setCheckDigit("0");
                BA.setCodeBank("6458");
                int digits = 7;
                int x = nDigitRandomNo(digits);
                String m = Integer.toString(x);
                BA.setAccountNumber(m);

                try {
                    client.regAccount(BA,3);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
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
