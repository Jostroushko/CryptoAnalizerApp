package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class BruteForce {
    public String decryptByBruteForce(String encryptedText, char[] alphabet) {
        int alphabetLength = alphabet.length;
        // перебор сдвигов
        for (int shift = 0; shift < alphabetLength; shift++) {
            String decrypt = new Cipher().decrypt(encryptedText, alphabet[shift]);
             if (isDecryptedCorrectly(decrypt)) {
                return decrypt;
            }
        }
        return "Расшифровку выполнить невозможно";
    }

    private boolean isDecryptedCorrectly(String decryptedText) {
        Set<String> dictionary = loadDictionary();
        String[] words = decryptedText.split("\\s+");
        int wordMatch = 0;
        System.out.println("Словарь загружен и в нем : " + dictionary.size() + " слов!");
        for (String word : words) {
            if (dictionary.contains(word)) {
                wordMatch++;
            }
        }
        // определим "порог" в 0.7
        return ((double) wordMatch / words.length) > 0.7;
    }

    private Set<String> loadDictionary() {
        Set<String> dictionary = new HashSet<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("russian.txt"))))) {
            String word;
            while ((word = br.readLine()) != null) {
                // переводим в lowerCase и удаляем лишние пробелы
                dictionary.add(word.trim().toLowerCase());
            }
        } catch (Exception e) {
            System.out.println("Произошла ошибка при загрузки словаря: " + e.getMessage());
        }
        return dictionary;
    }
}