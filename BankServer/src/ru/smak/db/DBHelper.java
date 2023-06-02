package ru.smak.db;

import ru.smak.data.BankAccount;
import ru.smak.data.Card;
import ru.smak.data.Transfer;
import ru.smak.data.User;

import java.sql.*;
import java.util.Objects;

public class DBHelper {
    private Connection connection;
    private Boolean balance;
    private String LastAccountNumberPlus;
    private String LastAccountNumberMinus;
    private String dbName = "bank003a";
    public DBHelper(String host, int port, String user, String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+dbName, user, password);
    }
    public void addUser(User user) throws SQLException
    {
        var sql = "INSERT INTO users (phone, lastName, firstName, middleName, birth, password)" +
                " VALUES (?,?,?,?,?,?)";
        connection.setAutoCommit(false);
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, user.getPhone());
        stmt.setString(2, user.getLastName());
        stmt.setString(3, user.getFirstName());
        stmt.setString(4, user.getMiddleName());
        stmt.setDate(5, user.getBirth());
        stmt.setString(6, user.getPassword());
        try {
            stmt.executeUpdate();
        } catch (Exception e){
            connection.rollback();
            throw new SQLException(e);
        }
        connection.commit();
    }
    public void addBankAccount(BankAccount bankAccount) throws SQLException
    {
        var sql = "INSERT INTO bankAccount (usersPhone,BalanceNumberOne, BalanceNumberTwo, OKB, CheckDigit, CodeBank,AccountNumber,Balance)" +
                " VALUES (?,?,?,?,?,?,?,?)";
        connection.setAutoCommit(false);
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, bankAccount.getPhone());
        stmt.setString(2, bankAccount.getBalanceNumberOne());
        stmt.setString(3, bankAccount.getBalanceNumberTwo());
        stmt.setInt(4, bankAccount.getOKB());
        stmt.setString(5, bankAccount.getCheckDigit());
        stmt.setString(6,bankAccount.getCodeBank());
        stmt.setString(7,bankAccount.getAccountNumber());
        stmt.setInt(8,bankAccount.getBalance());
        try {
            stmt.executeUpdate();
        } catch (Exception e){
            connection.rollback();
            throw new SQLException(e);
        }
        connection.commit();
    }
    public void addCard(Card card) throws SQLException
    {
        var sql = "INSERT INTO cardaccount (usersPhone,AccountNumber, Balance, PaymentSystems, TypeCard,LastAccountNumber,NumberCard,NumberChet)" +
                " VALUES (?,?,?,?,?,?,?,?)";
        connection.setAutoCommit(false);
        var stmt = connection.prepareStatement(sql);
        stmt.setString(1, card.getPhone());
        stmt.setString(2, card.getAccountNumber());
        stmt.setInt(3, card.getBalance());
        stmt.setString(4, card.getPaymentSystems());
        stmt.setString(5, card.getTypeCard());
        stmt.setString(6, card.getLastAccountNumber());
        stmt.setString(7, card.getNumberCard());
        stmt.setString(8, card.getNumberChet());
        try {
            stmt.executeUpdate();
        } catch (Exception e){
            connection.rollback();
            throw new SQLException(e);
        }
        connection.commit();
    }
    public void addBankAccountBalance(BankAccount bankAccount) throws SQLException
    {
        System.out.println(bankAccount.getBalance());
        var sql = "UPDATE bankaccount  SET Balance = Balance + " + bankAccount.getBalance() +" WHERE AccountNumber = "+bankAccount.getAccountNumber();
        connection.setAutoCommit(false);
        var stmt = connection.createStatement();
        stmt.executeUpdate(sql);
        try {
        } catch (Exception e){
            connection.rollback();
            throw new SQLException(e);
        }
        connection.commit();
    }
    public Boolean UpdateBalanceCardAccountPush(Transfer transfer) throws SQLException
    {

        var sql1 = "UPDATE cardaccount  SET Balance = Balance + ?  WHERE NumberCard = ? ";
        var sql2 = "UPDATE cardaccount  SET Balance = Balance - ?  WHERE NumberCard = ? ";
        var SQL = " SELECT * FROM cardaccount WHERE  NumberCard = ?";
        connection.setAutoCommit(false);

        var stmt1 = connection.prepareStatement(sql1);
        var stmt2 = connection.prepareStatement(sql2);
        var stmt3 = connection.prepareStatement(SQL);
        var stmt4 = connection.prepareStatement(SQL);
        stmt3.setString(1, transfer.getAcc1());
        ResultSet resSet = stmt3.executeQuery();
        stmt4.setString(1, transfer.getAcc2());
        ResultSet resSet2 = stmt4.executeQuery();
        stmt1.setFloat(1, transfer.getSum());
        stmt1.setString(2, transfer.getAcc1());
        stmt2.setFloat(1, transfer.getSum());
        stmt2.setString(2, transfer.getAcc2());
        boolean isAccFound =false;
        String sql3 = "SELECT `Balance` FROM cardaccount WHERE `NumberCard` = ?";
        var stmt5 = connection.prepareStatement(sql3);
        stmt5.setString(1, transfer.getAcc2());
        ResultSet rs3 = stmt5.executeQuery();
        if(rs3.next()){
             balance = rs3.getInt("Balance") < transfer.getSum();
        }
        //Обновление баланса счёта на аккаунтах
        var SQLAccountNumber = "SELECT LastAccountNumber FROM cardaccount WHERE NumberCard = ?";
        var SQL3 = "UPDATE bankaccount  SET Balance = Balance + ?  WHERE AccountNumber = ? ";
        var SQL4 = "UPDATE bankaccount  SET Balance = Balance - ?  WHERE AccountNumber = ? ";
        var stmt6 = connection.prepareStatement(SQLAccountNumber);
        var stmt8 = connection.prepareStatement(SQLAccountNumber);
        stmt6.setString(1, transfer.getAcc1());
        stmt8.setString(1, transfer.getAcc2());
        ResultSet rs4 = stmt6.executeQuery();
        ResultSet rs5 = stmt8.executeQuery();
        if(rs4.next()){
            LastAccountNumberPlus = rs4.getString("LastAccountNumber") ;
        }
        if(rs5.next()){
            LastAccountNumberMinus = rs5.getString("LastAccountNumber") ;
        }
        var stmt7 = connection.prepareStatement(SQL3);
        stmt7.setFloat(1,  transfer.getSum());
        stmt7.setString(2,LastAccountNumberPlus);
        var stmt9 = connection.prepareStatement(SQL4);
        stmt9.setFloat(1,  transfer.getSum());
        stmt9.setString(2,LastAccountNumberMinus);
        if(balance == null){
            return isAccFound = false;

        }
        if(resSet.next() && resSet2.next() || !balance)
        {
            if(LastAccountNumberPlus.equals(LastAccountNumberMinus))//Если совпадают
            {
                stmt1.executeUpdate();
                stmt2.executeUpdate();
            }
            else {
                stmt1.executeUpdate();
                stmt2.executeUpdate();
                stmt7.executeUpdate();
                stmt9.executeUpdate();
            }
            isAccFound = true;
        }

        try {
        } catch (Exception e){
            connection.rollback();
            throw new SQLException(e);
        }
        connection.commit();
        return isAccFound;
    }
    public ResultSet getUsers (User user) throws SQLException
    {
        ResultSet resSet = null;
        String SQL = "SELECT * FROM users WHERE phone=? and password = ?";
       try {
           PreparedStatement pst = connection.prepareStatement(SQL);
           pst.setString(1, user.getPhone());
           pst.setString(2, user.getPassword());
           resSet = pst.executeQuery();

       }
       catch (SQLException e)
       {
           e.printStackTrace();
       }
       return resSet;

    }
    public ResultSet getAccountNumber (BankAccount BA) throws SQLException
    {
        ResultSet resSet = null;
        String SQL = "SELECT * FROM bankaccount WHERE usersPhone=?";
        try {
            PreparedStatement pst = connection.prepareStatement(SQL);
            pst.setString(1, BA.getPhone());

            resSet = pst.executeQuery();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return resSet;

    }
    public ResultSet getCard (Card card) throws SQLException
    {
        ResultSet resSet = null;
        String SQL = "SELECT * FROM cardaccount WHERE usersPhone=?";
        try {
            PreparedStatement pst = connection.prepareStatement(SQL);
            pst.setString(1, card.getPhone());
            resSet = pst.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return resSet;

    }
    public ResultSet getCardBalance (Card card) throws SQLException
    {
        ResultSet resSet = null;
        String SQL = "SELECT * FROM cardaccount WHERE usersPhone=? and NumberCard=?";
        try {
            PreparedStatement pst = connection.prepareStatement(SQL);
            pst.setString(1, card.getPhone());
            pst.setString(2, card.getNumberCard());
            resSet = pst.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return resSet;
    }
}
