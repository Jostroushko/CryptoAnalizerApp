package org.example;

public class BruteForce {
    public String decryptByBruteForce(String encryptedText, char[] alphabet) {
        int alphabetLength = alphabet.length;
        // перебор сдвигов
        for (int shift = 0; shift < alphabetLength; shift++) {
            String decrypt = new Cipher().decrypt(encryptedText, alphabet[shift]);
            // if () {
                return decrypt;
           // }
        }
        return "Расшифровку выполнить невозможно";
    }
}