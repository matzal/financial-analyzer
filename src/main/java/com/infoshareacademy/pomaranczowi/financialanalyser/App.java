package com.infoshareacademy.pomaranczowi.financialanalyser;

public class App
{
    public static void main( String[] args ) {

        Menu menu = new Menu("Analizator Finansowy");
        menu.add("Notowania giełdowe");
        menu.add("Notowania walut");
        menu.add("Wyjście");

        //setting up new menu
        Menu menu2 = new Menu("Notowania giełdowe");
        menu2.add("WIG20");
        menu2.add("WIG30");
        menu2.add("WIG-ENERG");
        menu2.add("Powrót");

        /* Here is an example of using my menu.
        You can create so many menus and sub-menus as you wish.
        Using this schema make possible to enter a menu, then go back to previous,
        then jump to another menu, go back once again etc.
         */

        while (!menu.wantExit()) { //exits menu when .exit() is used
            switch (menu.Init()) { //menu initialization
                case 0: //menu returns user choice as an int so you can use switch case
                    while(!menu2.wantExit()) { //exits sub-menu when .exit() is used
                        switch (menu2.Init()) { //sub-menu initialization
                            case 0 : //case for a sub-menu
                                System.out.println("Wybrano opcję 0");
                                menu.exit(); //exits menu
                                menu2.exit(); //exits sub-menu
                                break;
                            case 1: //case for a sub-menu
                                System.out.println("Wybrano opcję 1");
                                menu.exit(); //exits menu
                                menu2.exit(); //exits sub-menu
                                //here you can create another sub-menu...
                                break;
                            case 2: //case for a sub-menu
                                System.out.println("Wybrano opcję 2");
                                //no exiting from menu... menu will just appear once again
                                break;
                            default:
                                menu2.exit(); //exits sub-menu
                                //no exiting from menu -> back level up to main menu
                        }
                    }
                    break;
                case 1: //case for a menu
                    System.out.println("Wybrano opcję 1");
                    menu.exit(); //exits menu
                    break;
                default:
                    menu.exit(); //exits menu
            }
        }

        /* End of the example */

        /* Here is an example of using a Currency class */
      
        Currency aud = new Currency("data/currency/AUD.txt");
        String date = "20160414";
        System.out.println("Wczytano notowania dla "+aud.countPrices()+" dni.");
        System.out.println("Wczytana waluta: "+aud.getName());
        System.out.println("Data pierwszego wczytanego notowania to: "+aud.firstDate());
        System.out.println("Data ostatniego wczytanego notowania to: "+aud.lastDate()+"\n");
        System.out.println("Wartość open dla notowania z dnia "+date+" wynosi: "+aud.getOpen(date));
        System.out.println("Wartość high dla notowania z dnia "+date+" wynosi: "+aud.getHigh(date));
        System.out.println("Wartość low dla notowania z dnia "+date+" wynosi: "+aud.getLow(date));
        System.out.println("Wartość close dla notowania z dnia "+date+" wynosi: "+aud.getClose(date));
      
        /* End of the example */
    }
}