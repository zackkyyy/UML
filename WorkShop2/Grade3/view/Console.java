package view;


import model.Boat;
import model.BoatType;
import model.MainController;
import model.Member;
import model.searchStrategies.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Console {

    private Scanner scan = new Scanner(System.in);
    private MainController mainController;
    private Member member;
    private Boat boat;
    private Boolean memberLogedIn = false;        //will be used to check whether the member or the admin is logged in

    // Constructor

    public Console() throws IOException, ParseException {
        mainController = new MainController();
        mainController.readFiles();

    }

    /**
     * Show the authentication list to the user
     */
    public void showAuthentication() throws IOException {

        System.out.println("\nSELECT THE OPTION!!!!!!!!!!!!!!!");
        System.out.println("1: Are you a member");
        System.out.println("2: Are you a administrator");

        String input = scan.next();
        switch (input) {
             /* if the user is a member will get access just
            to his information by using his personal number*/
            case ("1"):
                memberLogedIn = true;
                System.out.println("Welcome to the jolly pirate");
                System.out.println("Enter your personal number");
                String input1 = scan.next();
                Member member;
                while (!checkIndex(input1)) {
                    System.err.println("Incorrect personal number");
                    input1 = scan.next();
                }
                member = mainController.findMemberByPersonalNr((input1));
                showChosenMembers(member);
                editMemberInfo(member);
                break;
            case ("2"):         // the user is an administrator and need a password to access
                memberLogedIn = false;
                System.out.println("Enter your password");
                String input2 = scan.next();
                while (!input2.equals("admin")) {       // the password is always sat to be admin
                    System.err.println("Wrong password....  try (admin)");
                    input2 = scan.next();
                }

                showMainMenu();


                break;
            default:
                System.err.println("Wrong option... Try Again");
                showAuthentication();
                break;
        }
    }

    /**
     * Show the main menu list to the user
     */
    public void showMainMenu() throws IOException {

        System.out.println("\nSELECT THE OPTION");
        System.out.println("1: CREATE A MEMBER");
        System.out.println("2: DISPLAY COMPACT LIST");
        System.out.println("3: DISPLAY VERBOSE LIST");
        System.out.println("4: Find a member");
        System.out.println("e: SAVE AND EXIT\n>");

        String input = scan.next();
        switch (input) {
            case ("1"):
                mainController.createMember(setName(), setPersonalNumber()); //calling the method create a member from main controller class
                mainController.saveFile();
                showMemberOptions();
                break;
            case ("2"):
                listMembersByCompact(mainController.getMemberList());
                showMemberOptions();
                break;
            case ("3"):
                listMembersByVerbose(mainController.getMemberList());

                showMemberOptions();
                break;
            case ("4"):
                // showFindMemberOption();
                ArrayList<Member> m = mainController.search(showSearchOption());
                listMembersByVerbose(m);
                break;

            case ("e"):
                saveAndExit();
                break;
            default:
                System.err.println("Wrong option... Try Again");
                showAuthentication();
                break;
        }

    }


    /**
     * Show the option list for a member to the user
     */
    private void showMemberOptions() throws IOException {
        if (!mainController.getMemberList().isEmpty()) {

            System.out.println("\nSELECT THE OPTION");
            System.out.println("1: CREATE A MEMBER");
            System.out.println("2: UPDATE A MEMBER");
            System.out.println("3: DELETE A MEMBER");
            System.out.println("r" + ": RETURN");
            System.out.print("e" + ": SAVE AND EXIT\n>");


            String input = scan.next();

            switch (input) {
                case ("1"):
                    mainController.createMember(setName(), setPersonalNumber());
                    listMembersByCompact(mainController.getMemberList());
                    break;
                case ("2"):
                    member = getMemberbyId();
                    showChosenMembers(member);
                    editMemberInfo(member);
                    break;
                case ("3"):
                    member = getMemberbyId();
                    mainController.deleteMember(member);
                    mainController.saveFile();
                    listMembersByCompact(mainController.getMemberList());
                    showMainMenu();
                    break;
                case ("r"):
                    showMainMenu();
                    break;
                case ("e"):
                    saveAndExit();
                    break;
                default:
                    System.err.println("Wrong option... Try Again");
                    showMemberOptions();
                    break;
            }

        } else {
            System.out.println("List is Empty");
            showMainMenu();
        }
    }

    /**
     * Show the  menu list for editing a member to the user
     */
    private void editMemberInfo(Member member) throws IOException {

        System.out.println("\nSELECT AN OPTION");
        System.out.println("1: UPDATE NAME");
        System.out.println("2: UPDATE PERSONAL NUMBER");
        if (!memberLogedIn) {
            System.out.println("3: REGISTER A BOAT");
            System.out.println("4: UPDATE A BOAT");
            System.out.println("5: DELETE A BOAT");
            System.out.println("r: RETURN");
        }
        System.out.println("e: SAVE AND EXIT\n>");

        String input = scan.next();

        // After each operations we display member's information again to show the changes.
        switch (input) {
            case ("1"):

                mainController.updateMember(member, setName(), member.getPersonalNumber());
                mainController.saveFile();
                showChosenMembers(member);
                editMemberInfo(member);

                break;
            case ("2"):
                mainController.updateMember(member, member.getName(), setPersonalNumber());
                mainController.saveFile();
                showChosenMembers(member);
                editMemberInfo(member);
                break;
            case ("3"):
                member.registerBoat(setLengthOfBoat(), setBoatType());
                mainController.saveFile();
                showChosenMembers(member);
                editMemberInfo(member);

                break;
            case ("4"):
                boat = getBoat(member);
                mainController.updateBoat(setLengthOfBoat(), setBoatType(), boat);
                mainController.saveFile();
                editMemberInfo(member);

                break;
            case ("5"):
                boat = getBoat(member);
                member.removeBoat(boat);
                mainController.saveFile();
                showChosenMembers(member);
                editMemberInfo(member);

                break;
            case ("r"):
                listMembersByCompact(mainController.getMemberList());
                showMemberOptions();

                break;
            case ("e"):
                saveAndExit();
                break;
            default:
                System.err.println("Wrong option... Try Again");
                editMemberInfo(member);
                break;
        }

    }


    /**
     * list the members by compact way
     */
    public void listMembersByCompact(ArrayList<Member> m) throws IOException {
        //first check if there are members in the system
        if (m.isEmpty()) {
            System.err.println("There are no members in the list ");
            showMainMenu();
            return;
        }

        for (Member member : m)
            // listing them by name, boat, number of boats and age
            System.out.print("\nMEMBER ID: " + member.getID() + "\n NAME:  " + member.getName() + " BOATS: " + member.getNumberOfBoats() + " Age:  " + member.getAge());

    }

    /**
     * list the members by verbose way
     */
    public void listMembersByVerbose(ArrayList<Member> m) throws IOException {
        if (m.isEmpty()) {
            System.out.println("There are no members in the list ");
            showMainMenu();
            return;
        }
        for (Member member : m)
            showChosenMembers(member);
    }

    /**
     * this function to close the program after saving all the changes
     */
    public void saveAndExit() {
        try {
            mainController.saveFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scan.close();
        System.exit(1);
    }

    private String setName() {
        System.out.print("Enter your name: \n");
        String name = scan.next();
        int i;
        for (i = 0; i < name.length(); i++) {
            while (!Character.isLetter(name.charAt(i))) {
                System.err.println("Name should only contain letters");
                System.out.print("Enter your name: \n");
                i = 0;
                name = scan.next();

            }
        }

        return name;
    }

    /**
     * @get the personal number from the user
     */
    private String setPersonalNumber() {

        System.out.print("Enter your personal number (yyyymmddxxxx or yymmddxxxx) : \n");
        String input = scan.next();
        for (Member member : mainController.getMemberList()) {
            // check if the length of the personal number .. It should be either 10 or 12 digits
            // or if the personal number is already assigned to an exist member
            while ((input.length() != 12 && input.length() != 10) || (member.getPersonalNumber().equals(input))) {
                System.err.println("Invalid personal number");
                input = scan.next();
            }
        }
        return input;
    }

    /**
     * find member by ID chosen by user
     */
    private Member getMemberbyId() throws IOException {
        if (mainController.getMemberList().isEmpty()) {
            System.err.println("LIST IS EMPTY");
            showMemberOptions();
        }
        listMembersByCompact(mainController.getMemberList());
        System.out.println("\nPLEASE TYPE THE MEMBER ID>");
        String input = scan.next();
        while (mainController.findMember(Integer.parseInt(input)) == null) {
            System.err.println("The ID you intered is invalid !! Try again");
            input = scan.next();
        }

        return mainController.findMember(Integer.parseInt(input));
    }

    /**
     * @get the length of the boat from the user
     */
    private double setLengthOfBoat() {
        System.out.print("LENGTH(m)\n>");
        String input = scan.next();
        for (int i = 0; i < input.length(); i++) {

            if ((!Character.isDigit(input.charAt(i)) && (!(input.charAt(i) == '.')))) {
                System.out.println("The length contains only digits ");
                input = scan.next();


            }
        }
        return Double.parseDouble(input);

    }

    /**
     * show the boat type in a list so the user can choose
     */
    private BoatType setBoatType() {
        // Prints out types of boats
        System.out.println("BOAT TYPE");
        System.out.println("+ ID | Boat type +");

        // receives an input
        System.out.print("\n1: Sail Boat");
        System.out.print("\n2: MotorSailer");
        System.out.print("\n3: Canoe");
        System.out.print("\n4: Ohter");

        String input = scan.next();
        switch (input) {
            case ("1"):
                return BoatType.values()[Integer.parseInt(input) - 1];
            case ("2"):
                return BoatType.values()[Integer.parseInt(input) - 1];
            case ("3"):
                return BoatType.values()[Integer.parseInt(input) - 1];
            case ("4"):
                return BoatType.values()[Integer.parseInt(input) - 1];
            default:
                System.err.println("Wrong option... Try Again");
                break;
        }

        return null;
    }

    /**
     * get a boat for a chosen member
     */
    private Boat getBoat(Member member) throws IOException {
        if (member.getNumberOfBoats() == 0) {
            System.err.println("The member you chose has no boats");
            showChosenMembers(member);
            editMemberInfo(member);
            return null;
        }
        System.out.print("Enter the boat ID\n>");
        String input = scan.next();
        if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > member.getBoatList().size()) {
            System.err.println("Invalid Boat ID number");
            input = scan.next();
        }
        return member.getBoatList().get(Integer.parseInt(input) - 1);
    }

    /**
     * function to check if the given username or personal number
     * belongs to one of the members in the system
     */
    public Boolean checkIndex(String index) {
        for (Member member : mainController.getMemberList())
            while (member.getPersonalNumber().equals(index) || member.getName().equals(index)) {
                return true;
            }
        return false;
    }

    public ISearchStrategy showSearchOption() throws IOException {
        ISearchStrategy searchStrategy = null;

        System.out.println("\nSELECT THE OPTION");


        System.out.println("1: By Name");
        System.out.println("2: By Age");
        System.out.println("3: By Boat type");
        System.out.println("4: By Personal Number");
        System.out.println("r: RETURN");
        System.out.print("q: SAVE & QUIT\n>");
        String input = scan.next();

        if (input.equals("r"))
            showMainMenu();
        else if (input.equals("q"))
            saveAndExit();
        else {
            try {
                switch (input) {

                    case ("1"):
                        ByName bn = new ByName(setName());
                        searchStrategy = bn.getSearchByName();
                        break;
                    case ("2"):
                        ByAgeEqual age = new ByAgeEqual(setAge());
                        searchStrategy = age.getSearchByAgeEqualTo();
                        break;
                    case ("3"):
                        ByBoatType bbt = new ByBoatType(setBoatType());
                        searchStrategy = bbt.getSearchByBoatType(setBoatType());
                        break;
                    case ("4"):
                        ByPersonalNumber bpn = new ByPersonalNumber((setPersonalNumber()));
                        searchStrategy = bpn.getSearchByPersonalNr();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println("INVALID OPTION");
                showSearchOption();
            }
        }
        return searchStrategy;
    }

    private int setAge() {
        System.out.println("Enter your age");
        int input = scan.nextInt();
        return input;
    }
    /**
     * method to show the user all the information about the member he asked
     */
    public void showChosenMembers(Member member) {
        System.out.println("\nMEMBER ID: " + member.getID());
        System.out.println("Name: \"" + member.getName() + "\" ,  Personal Number: " + member.getPersonalNumber() + "  has " + member.getNumberOfBoats() + " boats");
        System.out.println(" No. |  BOAT TYPE  | LENGTH (m) ");
        int i = 0;
        // a list of the member's boats
        for (Boat boat : member.getBoatList())
            System.out.println(++i + "    |" + boat.getBoatType() + "        |" + boat.getLength());
    }


}
