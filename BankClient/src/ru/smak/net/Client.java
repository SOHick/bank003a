package ru.smak.net;

import ru.smak.data.BankAccount;
import ru.smak.data.Card;
import ru.smak.data.Transfer;
import ru.smak.data.User;
import ru.smak.ui.bwindow.AccountWindow.AccountWindow;
import ru.smak.ui.bwindow.BankWindow.BankWindow;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client
{
    private String _host;
    private int _port;
    private Socket s;
    private NetIO nio;
    private JFrame frame;
    private JFrame frameReg;
    private Client client;
    private List<BankAccount> ListBankAccount;
    private List<Card> ListCard;
    private BankWindow bankWindow;
    private Integer puy =0;
    private  User user;

    public Client(String host, int port) throws IOException {
        _host = host;
        _port = port;
        s = new Socket(_host, _port);
        nio = new NetIO(s);
    }

    public void regUser(User user,int i) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos);
        ous.writeInt(i);
        ous.writeObject(user);
        var ba = baos.toByteArray();
        nio.sendData(ba);
    }
    public void regAccount(BankAccount BA, int i) throws IOException {
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos2);
        ous.writeInt(i);
        ous.writeObject(BA);
        var ba = baos2.toByteArray();
        nio.sendData(ba);
    }
    public void regCard(Card card, int i) throws IOException {
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos2);
        ous.writeInt(i);
        ous.writeObject(card);
        var ba = baos2.toByteArray();
        nio.sendData(ba);
    }
    public void regTransfer(Transfer card, int i) throws IOException {
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        ObjectOutputStream ous = new ObjectOutputStream(baos2);
        ous.writeInt(i);
        ous.writeObject(card);
        var ba = baos2.toByteArray();
        nio.sendData(ba);
    }
    public Void parse(Integer d,Object data){
        switch(d)
        {
            case 1->{
                User u =(User) data;
                if(u != null)
                {
                    this.user = u;
                    bankWindow = new BankWindow(u,client);
                    bankWindow.setVisible(true);
                    frame.setVisible(false);
                }
                else JOptionPane.showMessageDialog(frame, "Пользователь с таким номером телефона не существует или неправильно введён пароль");

            }
            case 2->{

                AccountWindow accountWindow = bankWindow.getAccountWindow();
                ListBankAccount = (List<BankAccount>) data;
                bankWindow.setAccountList(ListBankAccount);
                accountWindow.UpdateTable(ListBankAccount);
                accountWindow.repaint();

            }
            case 3->{
                ListBankAccount = (List<BankAccount>) data;
                bankWindow.setAccountList(ListBankAccount);

            }
            case 4->{
               if (bankWindow !=null)
               {
                   ListCard = (List<Card>) data;
                   bankWindow.setCardList(ListCard);
               }
               else JOptionPane.showMessageDialog(frame, "Пользователь с таким номером телефона не существует или неправильно введён пароль");
            }
            case 5->{
                Boolean b = (Boolean) data;
                bankWindow.getMainWindowPanel().setB(b);
            }

        }

        return null;
    }
    public void startReceiving(){
        new Thread(()->{
            try {
                nio.startReceiving(this::parse);
            } catch (IOException e) {
                System.out.println("Ошибка: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
    public void setJFrame(JFrame frame)
    {
        this.frame = frame;
    }
    public void setClient(Client client){
        this.client = client;
    }
    public List<BankAccount> getListBankAccount()
    {
        return ListBankAccount;
    }

}
