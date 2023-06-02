package ru.smak.ui.bwindow.InfoClientWindow;

import ru.smak.data.User;

import javax.swing.*;

public class InfoClientWindow extends JPanel
{


    public InfoClientWindow(JPanel mainPanel, User user)
    {
        JLabel lblPhone = new JLabel("Номер телефона: " + user.getPhone());
        JLabel lblLastName = new JLabel("Фамилия: " + user.getLastName());
        JLabel lblFirstName = new JLabel("Имя: " + user.getFirstName());
        JLabel lblMiddleName = new JLabel("Отчество: " + user.getMiddleName());
        JLabel lblBirth = new JLabel("Дата рождения: " + user.getBirth());
        JLabel lblScore = new JLabel("Колличество счётов: ");
        JLabel lblCard = new JLabel("Колличество карт: ");
        GroupLayout data = new GroupLayout(mainPanel);
        mainPanel.setLayout(data);
        data.setHorizontalGroup(data.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(data.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGap(4,4,Integer.MAX_VALUE)
                        .addComponent(lblPhone,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(4)
                        .addComponent(lblFirstName,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(4)
                        .addComponent(lblLastName,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(4)
                        .addComponent(lblMiddleName,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(4)
                        .addComponent(lblBirth,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(4,4,Integer.MAX_VALUE)
                )
        );
        data.setVerticalGroup(data.createSequentialGroup()
                .addGroup(data.createSequentialGroup()
                        .addGap(4,4,Integer.MAX_VALUE)
                        .addComponent(lblPhone,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(4)
                        .addComponent(lblFirstName,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(4)
                        .addComponent(lblLastName,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(4)
                        .addComponent(lblMiddleName,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(4)
                        .addComponent(lblBirth,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                        .addGap(4,4,Integer.MAX_VALUE)
                )
        );

    }
}
