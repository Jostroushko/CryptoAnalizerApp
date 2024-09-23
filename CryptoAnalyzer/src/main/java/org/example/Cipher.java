package org.example;

import java.util.Arrays;

public class Cipher {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        // пусть сдвиг ограничивает размер алфавита, чтобы не уйти за пределы
        shift = shift % ALPHABET.length;
        for (char c : text.toCharArray()) {
            // toLowerCase, ведь прописные буквы - другой символ, упростим поиск
            int index = Arrays.binarySearch(ALPHABET, Character.toLowerCase(c));
            if (index >= 0) {
                // рассчитываем новый индекс, взяв старый и сдвиг с остатком от деления на размер алфавита
                int newIndex = (index + shift) % ALPHABET.length;
                // символ найден
                result.append(ALPHABET[newIndex]);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    public String decrypt(String encryptedText, int shift) {
        // Логика расшифровки состоит в том, чтобы выполнить сдвиг в обратном направлении
        // также, ограничим сдвиг алфавитом
        shift = shift % ALPHABET.length;
        // используем функцию, которая уже есть
        return encrypt(encryptedText, -shift);
    }
}