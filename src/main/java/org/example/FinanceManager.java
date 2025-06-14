package org.example;

import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class FinanceManager {
    private File file;
    private BufferedWriter writer;
    private ArrayList<String> readFile;
    private ArrayList<Expense> expenses;
    private ArrayList<Income> incomes;
    private Boolean view;
    private String currentDate;



    public FinanceManager(){
        //start up main gui
        //types: loan, saving, oneOffIncome, regularIncome, oneOffExpense, regularExpense, debt#
        this.view = true;
        if (LocalDate.now().toString().split("-")[1].charAt(0) != '0') this.currentDate = LocalDate.now().toString();
        else this.currentDate = LocalDate.now().toString().split("-")[0]
                    + "-" + LocalDate.now().toString().split("-")[1].charAt(1)
                    + "-" + LocalDate.now().toString().split("-")[2];
        //Calendar calendar = new Calendar(this.currentDate);
        //Graph graph = new Graph();
        this.expenses = new ArrayList<Expense>();
        this.incomes = new ArrayList<Income>();
        this.readFile = new ArrayList<String>();
        try{
            this.file = new File("config" + File.separator + "finance.txt");
            if (!this.file.exists()) this.file.createNewFile();
            else this.loadExisting();
            this.writer = new BufferedWriter(new FileWriter(this.file, true));
        }
        catch(Exception ex){System.err.println("Error: " + ex.getMessage());}
    }

    public void loadExisting() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.file));
        String line;
        while((line = reader.readLine()) != null){
            this.readFile.add(line);
            String name = line.split(":")[1], date = line.split(":")[2];
            int value = Integer.parseInt(line.split(":")[3]);
            switch(line.split(":")[0]){
                case "expense": this.expenses.add(new Expense(name, value, date)); break;
                case "income": this.incomes.add(new Income(name, value, date)); break;
            }
        }
        reader.close();
    }

    public void addExpense(String name, int value, String date) throws IOException {
        //add to array
        this.expenses.add(new Expense(name, value, date));
        //write to file
        this.writer.write("expense:" + name + String.valueOf(value) + date + ":\n");
    }

    public void removeExpense(String name) throws IOException {
        //remove from array
        for (Expense expense: this.expenses){
            if(expense.getName().equals(name)) this.expenses.remove(expense);
        }
        //erase from file
        for (String line: this.readFile){
            if (this.readFile.indexOf(line) == 0) this.writer = new BufferedWriter(new FileWriter(this.file, false));
            this.writer.write(line);
            this.writer = new BufferedWriter(new FileWriter(this.file, true));
        }
    }

    public void addIncome(){

    }

    public void removeIncome(){

    }

    public String generateWorth(){
        float worth = 0;
        for (Income income : this.incomes){
            if(income.getDate().equals(this.currentDate)) worth += income.getValue();
        }
        for (Expense expense : this.expenses){
            if(expense.getDate().equals(this.currentDate)) worth -= expense.getValue();
        }
        return String.format(String.valueOf(worth), "%.2f");
    }

    public String getDate(){
        return this.currentDate;
    }

    public void deleteEmpty(){
        if (readFile.isEmpty()) this.file.delete();
    }

    public void incrementDate(){
        if (!this.currentDate.split("-")[1].equals("12")){
            this.currentDate = this.currentDate.split("-")[0]
                    + "-" + String.valueOf(Integer.parseInt(this.currentDate.split("-")[1])+1)
                    + "-" + this.currentDate.split("-")[2];
        }
        else this.currentDate = String.valueOf(Integer.parseInt(this.currentDate.split("-")[0])+1)
                + "-" + "1" + "-" + this.currentDate.split("-")[2];
    }

    public void decrementDate(){
        if (!this.currentDate.split("-")[1].equals("1")){
            this.currentDate = this.currentDate.split("-")[0]
                    + "-" + String.valueOf(Integer.parseInt(this.currentDate.split("-")[1])-1)
                    + "-" + this.currentDate.split("-")[2];
        }
        else this.currentDate = String.valueOf(Integer.parseInt(this.currentDate.split("-")[0])-1)
                + "-" + "12" + "-" + this.currentDate.split("-")[2];
    }
    //calendar function

    //add regular expense
    //add one off expense
    //remove expense
    //remove one off expense

    //add one off addition to account
    //add regular income stream
    //add savings with interest

    //have account

    //save all of this to a file
    //ability to edit expense or income

    //has calendar view
    //has graph view

    //Potentially have multiple currencies
}