package ru.smak.ui.bwindow.OverviewWindow;

import ru.smak.data.BankAccount;
import ru.smak.data.Card;
import ru.smak.data.Transfer;
import ru.smak.data.User;
import ru.smak.net.Client;
import ru.smak.ui.AuthandReg.RegWindow;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.*;
import java.util.List;
import java.util.Objects;

public class PushBalanceWindow extends JFrame
{
    private final static int MIN_SZ = GroupLayout.PREFERRED_SIZE;
    private final static int MAX_SZ = GroupLayout.DEFAULT_SIZE;
    private  JTextField tfPhone;
    private Boolean b;
    public PushBalanceWindow(User user, Client client, JPanel WCard, List<BankAccount> ListBankAccount,List<Card> ListCard)
    {
        this.setTitle("Перевод другому клиенту");
        setSize(600,450);
        GroupLayout gl = new GroupLayout(getContentPane());
        setLayout(gl);
        JLabel lblTransferMethod = new JLabel("Укажите способ перевода:");
        JLabel lblPhone = new JLabel("Укажите номер карты:");
        JLabel lblBalance = new JLabel("Укажите какую сумму хотите перевести:");
        JLabel lblPhonePress = new JLabel("Укажите карту с которой будут списаны средства:");
        JTextField tfPress = new JTextField();
        JTextField tfBalance = new JTextField();
        String[] TransferMethod = {
                "По номеру карты",
                "По номеру телефона",
        };
        JButton btnPush = new JButton("Отправить средства");
        JButton btnCancel = new JButton("Отмена");
        JComboBox jcbTransferMethod = new JComboBox(TransferMethod);
        //edt_validate.setText(df.format(progress).replaceAll(",", " ")));
         tfPhone = new JFormattedTextField();
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8,8, Integer.MAX_VALUE)
                .addGroup(gl.createParallelGroup()
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(lblTransferMethod, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(jcbTransferMethod,MAX_SZ, MAX_SZ, MAX_SZ)
                        )
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(lblPhonePress, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(tfPress,MAX_SZ, MAX_SZ, MAX_SZ)
                        )
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(lblPhone, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(tfPhone,MAX_SZ, MAX_SZ, MAX_SZ)
                        )
                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(lblBalance,MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(tfBalance, MAX_SZ, MAX_SZ, MAX_SZ)
                        )

                        .addGroup(
                                gl.createSequentialGroup()
                                        .addComponent(btnPush,MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(btnCancel, MIN_SZ, MIN_SZ, MIN_SZ)
                        )


                )
                .addGap(8,8, Integer.MAX_VALUE)
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8,8,Integer.MAX_VALUE)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lblTransferMethod,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(jcbTransferMethod, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lblPhonePress,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(tfPress, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lblPhone,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(tfPhone, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lblBalance,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(tfBalance, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(btnPush,MIN_SZ, MIN_SZ, MIN_SZ)
                                .addComponent(btnCancel, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(8,8,Integer.MAX_VALUE)
        );
        jcbTransferMethod.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                lblPhone.setText("");
                tfPhone.removeAll();
                switch (e.getItem().toString()) {
                    case "По номеру карты" -> {
                        lblPhone.setText("Укажите номер карты:");
                    }

                    case "По номеру телефона" -> {
                        lblPhone.setText("Укажите номер телефона:");
                    }

                }
            }
        });

        btnPush.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String infoCardORAccountPress = tfPress.getText();
                String infoCardORAccount = tfPhone.getText();
                String infoBalance = tfBalance.getText();
                Integer infoBalanceInt = Integer.valueOf(infoBalance);
                if (jcbTransferMethod.getSelectedIndex() == 0)
                    {

                        Transfer TF = new Transfer();
                        TF.setAcc1(infoCardORAccount);
                        TF.setAcc2(infoCardORAccountPress);
                        TF.setSum(infoBalanceInt);
                        TF.setFee(0);
                        try {
                            client.regTransfer(TF,10);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }


                    }

            }
        });
        DocumentFilter documentFilter = new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                // Проверка, соответствует ли вставляемая строка формату числа с плавающей точкой
                if (isValidFloat(string)) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Проверка, соответствует ли заменяемая строка формату числа с плавающей точкой
                if (isValidFloat(text)) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            private boolean isValidFloat(String text) {
                // Регулярное выражение для проверки формата числа с плавающей точкой
                String floatRegex = "\\d*(\\.\\d{0,2})?";
                return text.matches(floatRegex);
            }
        };
        AbstractDocument document1 = (AbstractDocument) tfBalance.getDocument();
        document1.setDocumentFilter(documentFilter);

    }
    public void setB(Boolean b)
    {
        this.b = b;
        if(!b) {
            JOptionPane.showMessageDialog(this, "Неккоректно введены данные( или на карте недостаточно средств");
        }
        else JOptionPane.showMessageDialog(this, "Транзакция прошла успешна!");
    }

}
