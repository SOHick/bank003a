package ru.smak.net;

import ru.smak.data.BankAccount;
import ru.smak.data.Card;
import ru.smak.data.Transfer;
import ru.smak.data.User;
import ru.smak.db.DBHelper;

import javax.xml.crypto.Data;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectedClient {
    private static final ArrayList<ConnectedClient> clients = new ArrayList<>();
    private final Socket cs;
    private final NetIO nio;
    private String name = null;
    private static DBHelper dbh;

    static {
        try {
            dbh = new DBHelper("localhost",3306,"root","root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectedClient(Socket client){
        cs = client;
        nio = new NetIO(cs);
        clients.add(this);
    }

    public void start(){
        new Thread(()->{
            try {
                nio.startReceiving(this::parse);
            } catch (IOException | ClassNotFoundException e) {
                clients.remove(this);
            }
        }).start();
    }
    public Void parse(Integer type,Object data)
    {
        switch (type)
        {
            case 1 -> {
                User u = (User) data;
                try {

                    dbh.addUser(u);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            }
            case 2 ->
            {
                User v= (User) data;
                try {
                ResultSet result = dbh.getUsers(v);
                int counter = 0;
                User reverseUser = new User();
                List<Object> records =new ArrayList<Object>();
                int cols = result.getMetaData().getColumnCount();
                Object[] arr = new Object[cols];
                while(result.next()) {
                    counter++;
                    for(int i=0; i<cols; i++)
                    {
                        arr[i] = result.getObject(i+1);

                    }
                    reverseUser.setPhone(String.valueOf(arr[0]));
                    reverseUser.setLastName(String.valueOf(arr[1]));
                    reverseUser.setFirstName(String.valueOf(arr[2]));
                    reverseUser.setMiddleName(String.valueOf(arr[3]));
                    reverseUser.setBirth((Date) arr[4]);
                    reverseUser.setPassword(String.valueOf(arr[5]));
                    records.add(arr);

                }
                if (counter == 1)
                {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream ous = new ObjectOutputStream(baos);
                    ous.writeInt(counter);
                    ous.writeObject(reverseUser);
                    var ba = baos.toByteArray();
                    nio.sendData(ba);
                }
                } catch (SQLException | IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            case 3 -> {
                BankAccount v= (BankAccount) data;
                try {
                    dbh.addBankAccount(v);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case 4 -> {
                BankAccount v = (BankAccount) data;
                try {
                    ResultSet result = dbh.getAccountNumber(v);
                    int counter = 0;
                    List<BankAccount> ListBankAccount  = new ArrayList<BankAccount>();
                    while(result.next()) {
                        counter++;
                        BankAccount reverseUser = new BankAccount();
                        List<Object> records =new ArrayList<Object>();
                        int cols = result.getMetaData().getColumnCount();
                        Object[] arr = new Object[cols];
                        for(int i=0; i<cols; i++)
                        {
                            arr[i] = result.getObject(i+1);

                        }
                        reverseUser.setPhone(String.valueOf(arr[1]));
                        reverseUser.setBalanceNumberOne(String.valueOf(arr[2]));
                        reverseUser.setBalanceNumberTwo(String.valueOf(arr[3]));
                        reverseUser.setOKB((Integer) arr[4]);
                        reverseUser.setCheckDigit(String.valueOf(arr[5]));
                        reverseUser.setCodeBank(String.valueOf(arr[6]));
                        reverseUser.setAccountNumber(String.valueOf(arr[7]));
                        reverseUser.setBalance((Integer) arr[8]);
                        records.add(arr);
                        ListBankAccount.add(reverseUser);
                    }
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ObjectOutputStream ous = new ObjectOutputStream(baos);
                        ous.writeInt(2);
                        ous.writeObject(ListBankAccount);
                        var ba = baos.toByteArray();
                        nio.sendData(ba);

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            case 5 -> {
                BankAccount v = (BankAccount) data;
                try {
                    ResultSet result = dbh.getAccountNumber(v);
                    int counter = 0;
                    List<BankAccount> ListBankAccount  = new ArrayList<BankAccount>();
                    while(result.next()) {
                        counter++;
                        BankAccount reverseUser = new BankAccount();
                        List<Object> records =new ArrayList<Object>();
                        int cols = result.getMetaData().getColumnCount();
                        Object[] arr = new Object[cols];
                        for(int i=0; i<cols; i++)
                        {
                            arr[i] = result.getObject(i+1);

                        }
                        reverseUser.setBalanceNumberOne(String.valueOf(arr[2]));
                        reverseUser.setBalanceNumberTwo(String.valueOf(arr[3]));
                        reverseUser.setOKB((Integer) arr[4]);
                        reverseUser.setCheckDigit(String.valueOf(arr[5]));
                        reverseUser.setCodeBank(String.valueOf(arr[6]));
                        reverseUser.setAccountNumber(String.valueOf(arr[7]));
                        reverseUser.setBalance((Integer) arr[8]);
                        records.add(arr);
                        ListBankAccount.add(reverseUser);

                    }
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream ous = new ObjectOutputStream(baos);
                    ous.writeInt(3);
                    ous.writeObject(ListBankAccount);
                    var ba = baos.toByteArray();
                    nio.sendData(ba);

                }

                catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            case 6 -> {
                Card v= (Card) data;
                try {
                    dbh.addCard(v);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case 7 -> {
                BankAccount v= (BankAccount) data;
                try {
                    dbh.addBankAccountBalance(v);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            case 8 -> {
                Card v = (Card) data;
                try {
                    ResultSet result = dbh.getCard(v);
                    int counter = 0;
                    List<Card> ListCard  = new ArrayList<Card>();
                    while(result.next()) {
                        counter++;
                        Card reverseUser = new Card();
                        List<Object> records =new ArrayList<Object>();
                        int cols = result.getMetaData().getColumnCount();
                        Object[] arr = new Object[cols];
                        for(int i=0; i<cols; i++)
                        {
                            arr[i] = result.getObject(i+1);

                        }
                        reverseUser.setPhone(String.valueOf(arr[1]));
                        reverseUser.setAccountNumber(String.valueOf(arr[2]));
                        reverseUser.setBalance((Integer) arr[3]);
                        reverseUser.setPaymentSystems(String.valueOf(arr[4]));
                        reverseUser.setTypeCard(String.valueOf(arr[5]));
                        reverseUser.setLastAccountNumber(String.valueOf(arr[6]));
                        reverseUser.setNumberCard(String.valueOf(arr[7]));
                        reverseUser.setNumberChet(String.valueOf(arr[8]));
                        records.add(arr);
                        ListCard.add(reverseUser);
                    }
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream ous = new ObjectOutputStream(baos);
                    ous.writeInt(4);
                    ous.writeObject(ListCard);
                    var ba = baos.toByteArray();
                    nio.sendData(ba);


                }

                catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            case 9 ->
            {
                Card v = (Card) data;
                try {
                    ResultSet result = dbh.getCardBalance(v);
                    int counter = 0;
                    List<Card> ListCard  = new ArrayList<Card>();
                    while(result.next()) {
                        counter++;
                        Card reverseUser = new Card();
                        List<Object> records =new ArrayList<Object>();
                        int cols = result.getMetaData().getColumnCount();
                        Object[] arr = new Object[cols];
                        for(int i=0; i<cols; i++)
                        {
                            arr[i] = result.getObject(i+1);

                        }
                        reverseUser.setPhone(String.valueOf(arr[1]));
                        reverseUser.setAccountNumber(String.valueOf(arr[2]));
                        reverseUser.setBalance((Integer) arr[3]);
                        reverseUser.setPaymentSystems(String.valueOf(arr[4]));
                        reverseUser.setTypeCard(String.valueOf(arr[5]));
                        reverseUser.setLastAccountNumber(String.valueOf(arr[6]));
                        reverseUser.setNumberCard(String.valueOf(arr[7]));
                        reverseUser.setNumberChet(String.valueOf(arr[8]));
                        records.add(arr);
                        ListCard.add(reverseUser);
                    }
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream ous = new ObjectOutputStream(baos);
                    ous.writeInt(4);
                    ous.writeObject(ListCard);
                    var ba = baos.toByteArray();
                    nio.sendData(ba);


                }

                catch (SQLException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            case 10 -> {
                Transfer v= (Transfer) data;
                try {
                     boolean b = dbh.UpdateBalanceCardAccountPush(v);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream ous = new ObjectOutputStream(baos);
                    ous.writeInt(5);
                    ous.writeObject(b);
                    var ba = baos.toByteArray();
                    nio.sendData(ba);
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        return null;
    }
}
