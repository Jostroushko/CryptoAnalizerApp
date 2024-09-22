package org.example;
import javax.swing.*;
import java.awt.*;

    public class MenuView extends JFrame {

        public MenuView() {
            setTitle("Криптоанализатор");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);

            JMenuBar menuBar = new JMenuBar();
            JMenu fileMenu = new JMenu("Файл");
            JMenuItem exitItem = new JMenuItem("Выход");
            exitItem.addActionListener(e -> System.exit(0));
            fileMenu.add(exitItem);
            menuBar.add(fileMenu);

            JMenu cryptoMenu = new JMenu("Криптография");
            JMenuItem encryptItem = new JMenuItem("Шифрование");
            JMenuItem decryptItem = new JMenuItem("Расшифровка с ключом");
            JMenuItem bruteForceItem = new JMenuItem("Brute Force");
            JMenuItem analyzeItem = new JMenuItem("Статистический анализ");
            cryptoMenu.add(encryptItem);
            cryptoMenu.add(decryptItem);
            cryptoMenu.add(bruteForceItem);
            cryptoMenu.add(analyzeItem);
            menuBar.add(cryptoMenu);

            setJMenuBar(menuBar);
            setVisible(true);
        }
    }

