package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.time.YearMonth;

public class GUIManager {
    private JFrame frame;
    private JButton nextMonth, prevMonth;
    private JLabel monthLabel, worthLabel;
    private FinanceManager financeManager;
    private ArrayList<JButton> dayButtons;
    private GridBagConstraints constraints;


    public static void main(String[] args) {
        GUIManager guiManager = new GUIManager();
    }

    public GUIManager() {
        this.financeManager = new FinanceManager();
        this.constraints = new GridBagConstraints();
        this.constraints.fill = GridBagConstraints.BOTH;
        this.constraints.gridx = 0;
        this.constraints.gridy = 0;
        this.constraints.weightx = 0.5;
        this.frame = new JFrame();
        this.frame.setLayout(new GridBagLayout());
        this.frame.setVisible(true);
        this.frame.setBackground(Color.white);
        this.frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                financeManager.deleteEmpty();
                System.exit(0);
            }
        });
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.worthLabel = new JLabel();
        this.worthLabel.setBounds(3,0,1,1);
        this.worthLabel.setAlignmentX(JLabel.CENTER);
        this.monthLabel = new JLabel();
        this.updateDate();
        this.monthLabel.setBounds(3,0,1,1);
        this.monthLabel.setAlignmentX(JLabel.CENTER);

        this.nextMonth = new JButton(">");
        this.nextMonth.addActionListener(new  ActionListener() {
            public void actionPerformed(ActionEvent e) {
                financeManager.incrementDate();
                updateDate();
            }
        });

        this.prevMonth = new JButton("<");
        this.prevMonth.addActionListener(new  ActionListener() {
            public void actionPerformed(ActionEvent e) {
                financeManager.decrementDate();
                updateDate();
            }
        });

        this.frame.add(this.prevMonth,this.constraints);
        this.constraints.gridx = 1;
        this.frame.add(this.monthLabel, this.constraints);
        this.constraints.gridx = 2;
        this.frame.add(this.nextMonth, this.constraints);

    }

    public void updateDate() {
        switch (financeManager.getDate().split("-")[1]) {
            case "1": this.monthLabel.setText("January" + financeManager.getDate().split("-")[0]); break;
            case "2": this.monthLabel.setText("February" + financeManager.getDate().split("-")[0]); break;
            case "3": this.monthLabel.setText("March" + financeManager.getDate().split("-")[0]); break;
            case "4": this.monthLabel.setText("April" + financeManager.getDate().split("-")[0]); break;
            case "5": this.monthLabel.setText("May" + financeManager.getDate().split("-")[0]); break;
            case "6": this.monthLabel.setText("June" + financeManager.getDate().split("-")[0]); break;
            case "7": this.monthLabel.setText("July" + financeManager.getDate().split("-")[0]); break;
            case "8": this.monthLabel.setText("August" + financeManager.getDate().split("-")[0]); break;
            case "9": this.monthLabel.setText("September" + financeManager.getDate().split("-")[0]); break;
            case "10": this.monthLabel.setText("October" + financeManager.getDate().split("-")[0]); break;
            case "11": this.monthLabel.setText("November" + financeManager.getDate().split("-")[0]); break;
            case "12": this.monthLabel.setText("December" + financeManager.getDate().split("-")[0]); break;
        }
        this.constraints.gridy = 1;
        if (this.dayButtons != null){
            for (JButton dayButton : this.dayButtons) {
                this.frame.remove(dayButton);
            }
        }
        this.dayButtons = new ArrayList<>();
        YearMonth yearMonth = YearMonth.of(Integer.parseInt(this.financeManager.getDate().split("-")[0])
                , Integer.parseInt(this.financeManager.getDate().split("-")[1]));
        for (int i = 0; i < yearMonth.lengthOfMonth(); i++) {
            this.constraints.gridx += 1;
            if (i%3 == 0){
                this.constraints.gridy += 1;
                this.constraints.gridx = 0;
            }
            JButton dayButton = new JButton(String.valueOf(i+1));
            dayButton.addActionListener(new  ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    worthLabel.setText("£" + financeManager.generateWorth());
                }
            });
            this.dayButtons.add(dayButton);
            this.frame.add(this.dayButtons.get(i), this.constraints);
        }
        this.constraints.gridy += 1;
        this.frame.remove(this.worthLabel);
        this.frame.add(this.worthLabel, this.constraints);
    }
}
