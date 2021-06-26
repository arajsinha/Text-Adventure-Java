package game;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import game.Game.ChoiceHandler;

import java.awt.GridLayout;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.awt.Color;

public class UI {

    JFrame window;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName, itemLabel, itemLabelName;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 20);
    Font monument, karla, normalFont, pixel;
    JButton startButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    int playerHP;
    String weapon, position;

    public void createUI(ChoiceHandler chHandler){

        try {
            pixel = Font.createFont(Font.TRUETYPE_FONT, new File("pixel.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("pixel.ttf")));
        } catch (Exception e) {
            //
        }
        try {
            normalFont = Font.createFont(Font.TRUETYPE_FONT, new File("pixel.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("pixel.ttf")));
        } catch (Exception e) {
            //
        }
        try {
            karla = Font.createFont(Font.TRUETYPE_FONT, new File("karla.ttf")).deriveFont(23f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("karla.ttf")));
        } catch (Exception e) {
            //
        }

        //Window
        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);

        //Title Screen
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(90, 200, 600, 150);
        titleNamePanel.setBackground(Color.black);

        titleNameLabel = new JLabel("THE HUNT FOR AGENT X");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(pixel);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(325, 400, 150, 40);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.black);
        startButton.setFont(karla);
        startButton.addActionListener(chHandler);
        startButton.setActionCommand("start");
        startButton.setFocusPainted(false);

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        window.add(titleNamePanel);
        window.add(startButtonPanel);

        window.setVisible(true);

        //GAME
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        window.add(mainTextPanel);

        mainTextArea = new JTextArea("ALL THE TEXT IS GONNA BE HERE. THIS IS GONNA BE AWESOME!!");
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.green);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextArea.setEditable(false);
        mainTextArea.setWrapStyleWord(true);
        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4, 1));
        window.add(choiceButtonPanel);

        // Buttons for Choices
        choice1 = new JButton("CHOICE 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(karla);
        choice1.setFocusPainted(false);
        choice1.addActionListener(chHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);

        choice2 = new JButton("CHOICE 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(karla);
        choice2.setFocusPainted(false);
        choice2.addActionListener(chHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);

        choice3 = new JButton("CHOICE 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(karla);
        choice3.setFocusPainted(false);
        choice3.addActionListener(chHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);

        choice4 = new JButton("CHOICE 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(karla);
        choice4.setFocusPainted(false);
        choice4.addActionListener(chHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

        // Player Info Panel
        playerPanel = new JPanel();
        playerPanel.setBounds(100, 20, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        window.add(playerPanel);

        hpLabel = new JLabel("HP: ");
        hpLabel.setFont(karla);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);

        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(karla);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);

        weaponLabel = new JLabel("Weapon: ");
        weaponLabel.setFont(karla);
        weaponLabel.setForeground(Color.white);
        playerPanel.add(weaponLabel);

        weaponLabelName = new JLabel();
        weaponLabelName.setFont(karla);
        weaponLabelName.setForeground(Color.white);
        playerPanel.add(weaponLabelName);

        itemLabel = new JLabel("Item: ");
        itemLabel.setFont(karla);
        itemLabel.setForeground(Color.white);
        playerPanel.add(itemLabel);

        itemLabelName = new JLabel();
        itemLabelName.setFont(karla);
        itemLabelName.setForeground(Color.white);
        playerPanel.add(itemLabelName);
    }
}
