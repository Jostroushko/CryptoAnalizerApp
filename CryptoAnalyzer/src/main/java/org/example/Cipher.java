package org.example;

import java.util.Arrays;

public class Cipher {
    static final Validator validator = new Validator();
    static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        if (!validator.isValidKey(shift, ALPHABET)) throw new IllegalArgumentException("Invalid key");

        shift = shift % ALPHABET.length;

        for (char c : text.toCharArray()) {
            // Приводим к нижнему регистру для поиска
            int index = Arrays.binarySearch(ALPHABET, Character.toLowerCase(c));

            // Если символ найден в алфавите
            if (index >= 0) {
                // Рассчитываем новый индекс, учитывая сдвиг
                int newIndex = (index + shift) % ALPHABET.length;

                // Если newIndex оказался отрицательным, корректируем его
                if (newIndex < 0) {
                    newIndex += ALPHABET.length;
                }

                // Добавляем расшифрованный символ в результат
                result.append(ALPHABET[newIndex]);
            } else {
                // Если символ не найден в алфавите, добавляем его без изменений
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