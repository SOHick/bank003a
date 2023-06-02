package ru.smak.ui.AuthandReg;

import ru.smak.data.Card;
import ru.smak.data.User;
import ru.smak.net.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AuthWindow extends JFrame {
    private static int MIN_SZ = GroupLayout.PREFERRED_SIZE;
    private static int MAX_SZ = GroupLayout.DEFAULT_SIZE;
    private JLabel lblPhone;
    private JLabel lblPassword;
    private JTextField tfPhone;
    private JTextField tfPassword;

    private JButton btnAuth;
    private JButton btnReg;
    private Client client;
    public AuthWindow(Client client){
        this.client = client;
        setSize(600,450);
        this.setTitle("Авторизация");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GroupLayout gl = new GroupLayout(getContentPane());
        setLayout(gl);
        lblPhone = new JLabel("Номер телефона: ");


        lblPassword = new JLabel("Пароль: ");

        tfPhone = new JTextField();

        tfPassword = new JTextField();
        tfPhone.setText("123");
        tfPassword.setText("123");

        btnAuth = new JButton("Войти");
        btnReg = new JButton("Регистрация");

        gl.setHorizontalGroup(
                gl.createSequentialGroup()
                        .addGap(8,8, Integer.MAX_VALUE)
                        .addGroup(gl.createParallelGroup()
                                .addGroup(
                                        gl.createSequentialGroup()
                                                .addComponent(lblPhone, MIN_SZ, MIN_SZ, MIN_SZ)
                                                .addGap(8)
                                                .addComponent(tfPhone,MAX_SZ, MAX_SZ, MAX_SZ)
                                )

                                .addGroup(
                                        gl.createSequentialGroup()
                                                .addComponent(lblPassword, MIN_SZ, MIN_SZ, MIN_SZ)
                                                .addGap(8)
                                                .addComponent(tfPassword,MAX_SZ, MAX_SZ, MAX_SZ)
                                )
                                .addGroup(
                                        gl.createSequentialGroup()
                                                .addGap(8,8,Integer.MAX_VALUE)
                                                .addComponent(btnAuth, MIN_SZ, MIN_SZ, MIN_SZ)
                                                .addGap(8)
                                                .addComponent(btnReg, MIN_SZ, MIN_SZ, MIN_SZ)
                                                .addGap(8,8,Integer.MAX_VALUE)
                                )
                        )
                        .addGap(8,8, Integer.MAX_VALUE)
        );
        gl.setVerticalGroup(
                gl.createSequentialGroup()
                        .addGap(8,8,Integer.MAX_VALUE)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(lblPhone,MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addComponent(tfPhone, MIN_SZ, MIN_SZ, MIN_SZ)
                        )
                        .addGap(4)

                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(lblPassword,MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addComponent(tfPassword, MIN_SZ, MIN_SZ, MIN_SZ)
                        )

                        .addGap(8)
                        .addGroup(
                                gl.createParallelGroup()
                                        .addComponent(btnReg,MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addComponent(btnAuth, MIN_SZ, MIN_SZ, MIN_SZ)
                        )
                        .addGap(8,8,Integer.MAX_VALUE)
        );
        gl.linkSize(0, lblPhone, lblPassword);
        gl.linkSize(0, btnReg, btnAuth);
        btnReg.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var rw=new RegWindow(AuthWindow.this, client);
                rw.setVisible(true);
                setVisible(false);
            }
        });
        btnAuth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                User user = new User();
                Card card = new Card();
                user.setPhone(tfPhone.getText());
                card.setPhone(tfPhone.getText());
                user.setPassword(tfPassword.getText());
                try {
                    client.setJFrame(AuthWindow.this);
                    client.regUser(user,2);
                    client.regCard(card,8);
                    client.setClient(client);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);

                }

            }
        });
    }
}
