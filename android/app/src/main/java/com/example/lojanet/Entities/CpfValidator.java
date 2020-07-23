package com.example.lojanet.Entities;

public class CpfValidator {

    public boolean cpfValidator(String cpf) {


        if(!cpf.matches("[0-9]+")){
            return false;
        }

        if(cpf.isEmpty()){
            return false;
        }
        if(cpf.length() > 11){
            return false;
        }

        if(cpf.length() < 11){
            return false;
        }
        int sum = 0, firstDigit, secondDigit;

        char[] valueArray = cpf.toCharArray();
        int[] intValues = new int[valueArray.length];
        int[] intValues2 = new int[valueArray.length];

        for (int i = 0; i < valueArray.length; i++) {
            intValues[i] = Integer.parseInt(String.valueOf(valueArray[i]));
        }

        for (int i = 0; i < valueArray.length; i++) {
            intValues2[i] = Integer.parseInt(String.valueOf(valueArray[i]));
        }


        for (int i = 0; i < 9; i++) {
            sum += intValues[i] * (i + 1);
        }

        firstDigit = sum % 11;

        if (firstDigit == 10) {
            firstDigit = 0;
        }
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += intValues[i] * i;
        }
        secondDigit = sum % 11;

        if (secondDigit == 10) {
            secondDigit = 0;
        }

        intValues[9] = firstDigit;
        intValues[10] = secondDigit;

        if(intValues[9] == intValues2[9] && intValues[10] == intValues2[10]){
            return true;
        }else{
            return false;
        }
    }
}
