package org.example.functional_programming.dto;

public class Phone {
    private Operator operator;
    private String number;

    public Phone(Operator operator, String number) {
        this.operator = operator;
        this.number = number;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return operator + ": " + number;
    }
}
