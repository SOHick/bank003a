package ru.smak.data;

import java.io.Serializable;

public class BankAccount implements Serializable
{
    private String phone;
    private String BalanceNumberOne;
    private String BalanceNumberTwo;
    private Integer OKB;
    private Integer Balance;
    private String CheckDigit;
    private String CodeBank;
    private String AccountNumber;
    public void setBalanceNumberOne(String BalanceNumberOne)
    {
        this.BalanceNumberOne = BalanceNumberOne;
    }
    public String getBalanceNumberOne()
    {
        return BalanceNumberOne;
    }
    public void setAccountNumber(String AccountNumber)
    {
        this.AccountNumber = AccountNumber;
    }
    public String getAccountNumber()
    {
        return AccountNumber;
    }
    public void setCheckDigit(String CheckDigit)
    {
        this.CheckDigit = CheckDigit;
    }
    public String getCheckDigit()
    {
        return CheckDigit;
    }
    public void setCodeBank(String CodeBank)
    {
        this.CodeBank = CodeBank;
    }
    public String getCodeBank()
    {
        return CodeBank;
    }
    public void setBalanceNumberTwo(String BalanceNumberTwo)
    {
        this.BalanceNumberTwo = BalanceNumberTwo;
    }
    public String getBalanceNumberTwo()
    {
        return BalanceNumberTwo;
    }
    public void setOKB(Integer OKB)
    {
        this.OKB = OKB;
    }
    public Integer getOKB()
    {
        return OKB;
    }
    public void setBalance(Integer Balance)
    {
        this.Balance = Balance;
    }
    public Integer getBalance()
    {
        return Balance;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getPhone()
    {
        return phone;
    }
}
