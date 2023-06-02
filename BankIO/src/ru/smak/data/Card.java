package ru.smak.data;

import java.io.Serializable;

public class Card implements Serializable
{
    private String phone;
    private String AccountNumber;
    private String LastAccountNumber;
    private Integer Balance;
    private String PaymentSystems;
    private String TypeCard;
    private String NumberCard;
    private String NumberChet;

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setAccountNumber(String AccountNumber)
    {
        this.AccountNumber = AccountNumber;
    }
    public String getAccountNumber()
    {
        return AccountNumber;
    }
    public void setBalance(Integer Balance)
    {
        this.Balance = Balance;
    }
    public Integer getBalance(){
        return Balance;
    }
    public void setPaymentSystems(String PaymentSystems)
    {
        this.PaymentSystems = PaymentSystems;
    }
    public String getPaymentSystems()
    {
        return PaymentSystems;
    }
    public void setTypeCard(String TypeCard)
    {
        this.TypeCard = TypeCard;
    }
    public String getTypeCard()
    {
        return TypeCard;
    }
    public void setLastAccountNumber(String LastAccountNumber)
    {
        this.LastAccountNumber = LastAccountNumber;
    }
    public String getLastAccountNumber()
    {
        return LastAccountNumber;
    }
    public void setNumberCard(String NumberCard){
        this.NumberCard = NumberCard;
    }
    public String getNumberCard()
    {
        return NumberCard;
    }
    public void setNumberChet(String NumberChet){
        this.NumberChet = NumberChet;
    }
    public String getNumberChet()
    {
        return NumberChet;
    }
}
