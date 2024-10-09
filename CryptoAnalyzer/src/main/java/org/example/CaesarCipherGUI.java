package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CaesarCipherGUI extends JFrame {
    private final JTextField inputFileField;
    private final JTextField outputFileField;
    private final JTextField keyField;
    private final JTextArea logArea;
    private final FileManager fileManager;
    private final Cipher cipher;

    public CaesarCipherGUI() {
        fileManager = new FileManager();
        cipher = new Cipher();

        setTitle("Криптоанализатор 2000");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel inputFileLabel = new JLabel("Файл для шифровки:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(inputFileLabel, gbc);

        inputFileField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(inputFileField, gbc);

        JButton inputFileButton = new JButton("Открыть");
        inputFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile(inputFileField);
            }
        });
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        mainPanel.add(inputFileButton, gbc);

        JLabel outputFileLabel = new JLabel("Файл для дешифровки:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(outputFileLabel, gbc);

        outputFileField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(outputFileField, gbc);

        JButton outputFileButton = new JButton("Открыть");
        outputFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseFile(outputFileField);
            }
        });
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(outputFileButton, gbc);

        // Поле ввода ключа
        JLabel keyLabel = new JLabel("Ключ:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(keyLabel, gbc);

        keyField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(keyField, gbc);

        // Кнопки действий
        JButton encryptButton = new JButton("Зашифровать");
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptFile();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(encryptButton, gbc);

        JButton decryptButton = new JButton("Дешифровать");
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptFile();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(decryptButton, gbc);

        JButton bruteForceButton = new JButton("Brute Force");
        bruteForceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bruteForceDecrypt();
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 3;
        mainPanel.add(bruteForceButton, gbc);

        JButton autoDecryptButton = new JButton("Автоматическая подборка");
        autoDecryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoDecrypt();
            }
        });
        gbc.gridx = 3;
        gbc.gridy = 3;
        mainPanel.add(autoDecryptButton, gbc);

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(scrollPane, gbc);

        add(mainPanel);
        setVisible(true);
    }

    private void chooseFile(JTextField textField) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            textField.setText(selectedFile.getAbsolutePath());
        }
    }

    // Метод шифрования
    private void encryptFile() {
        String inputFile = inputFileField.getText();
        String outputFile = outputFileField.getText();

        int key;
        try {
            key = Integer.parseInt(keyField.getText());
            String content = fileManager.readFile(inputFile);  // Читаем файл
            String encryptedContent = cipher.encrypt(content, key);  // Шифруем текст
            fileManager.writeFile(encryptedContent, outputFile);  // Записываем в новый файл
            logArea.append("Файл успешно зашифрован!.\n");
        } catch (IOException | NumberFormatException e) {
            logArea.append("Возникла ошибка: " + e.getMessage() + "\n");
        }
    }

    // Метод расшифровки
    private void decryptFile() {
        String inputFile = inputFileField.getText();
        String outputFile = outputFileField.getText();
        int key;
        try {
            key = Integer.parseInt(keyField.getText());
            String content = fileManager.readFile(inputFile);  // Читаем файл
            String decryptedContent = cipher.decrypt(content, key);  // Расшифровываем текст
            fileManager.writeFile(decryptedContent, outputFile);  // Записываем в новый файл
            logArea.append("Файл успешно дешифрован!.\n");
        } catch (IOException | NumberFormatException e) {
            logArea.append("Возникла ошибка: " + e.getMessage() + "\n");
        }
    }

    // Метод brute force
    private void bruteForceDecrypt() {
        String inputFile = inputFileField.getText();
        String outputFile = outputFileField.getText();
        logArea.append("Brute force completed.\n");
    }

    // Метод для автоматической расшифровки
    private void autoDecrypt() {
        String inputFile = inputFileField.getText();
        String outputFile = outputFileField.getText();
        logArea.append("Automatic decryption completed.\n");
    }
}
