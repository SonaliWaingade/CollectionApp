package com.example.collectxnew;

public class withdraw {

    String agentName;
    String agentNumber;
    String accountNumber;
    String accountName;
    String availableBalance;
    String accountOpening;
    String penalty;
    String withdrawl;
    String penaltyAmount;
    String mobNum;

    public withdraw (){

    }

    public withdraw(String agentName, String agentNumber, String accountNumber, String accountName, String availableBalance, String accountOpening, String penalty, String withdrawl, String penaltyAmount, String mobNum) {
        this.agentName = agentName;
        this.agentNumber = agentNumber;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.availableBalance = availableBalance;
        this.accountOpening = accountOpening;
        this.penalty = penalty;
        this.withdrawl = withdrawl;
        this.penaltyAmount = penaltyAmount;
        this.mobNum = mobNum;
    }

    public String getAgentName() {
        return agentName;
    }

    public String getAgentNumber() {
        return agentNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAvailableBalance() {
        return availableBalance;
    }

    public String getAccountOpening() {
        return accountOpening;
    }

    public String getPenalty() {
        return penalty;
    }

    public String getWithdrawl() {
        return withdrawl;
    }

    public String getPenaltyAmount() {
        return penaltyAmount;
    }

    public String getMobNum() {
        return mobNum;
    }
}
