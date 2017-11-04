package com.infoshareacademy.pomaranczowi.financialanalyser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


 class Menu {

    private ArrayList<MenuPosition> menuPositions = new ArrayList<>();
    private String title;
    private boolean exit = false;

     Menu(String title) {
        this.title = title;
    }

     void add(String label) {
        menuPositions.add(new MenuPosition(menuPositions.size(),label));
    }

    //menu initialization
     int Init() {
        System.out.println("\n"+title+"\n");
        for (MenuPosition menuPosition : menuPositions) {
            menuPosition.show();
        }

        System.out.println("\nWybierz pozycję (podaj liczbę): ");

        Scanner input = new Scanner(System.in);
        boolean isUserChoiceCorrect = false;
        Integer userChoice = 0;

        //checking if choosen option is correct
        while (!isUserChoiceCorrect) {
            try {
                userChoice = input.nextInt();
                if (userChoice < menuPositions.size()) {
                    isUserChoiceCorrect = true;
                } else {
                    System.out.println("Błędny wybór!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Podaj liczbę!");
                input.next();
            }
        }

        return userChoice;
    }

    //used with while loop to allow user exit from a menu
     boolean wantExit() {
        if (exit) {
            exit = false;
            return true;
        }  else {
            return exit;
        }
    }

     boolean isExitSet() {
        return exit;
    }

    //gives an information that user wants to exit a menu
     void exit() {
        exit = true;
    }

     static void waitAndContinue() {
        System.out.println("\nNaciśnij klawisz ENTER aby kontynować...");

        try
        {
            System.in.read();
        }
        catch(IOException e)
        {
            System.out.println("Wystąpił błąd IO.");
        }

    }
}
