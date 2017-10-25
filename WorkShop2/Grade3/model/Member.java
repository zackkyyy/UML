package model;

import java.util.ArrayList;
import java.util.Calendar;
public class Member {
    private String name;
    private String personalNumber;
    private int ID;

    private ArrayList<model.Boat> boatList = new ArrayList<Boat>();

    public Member(String name, String personalNumber, int ID) {
        this.name = name;
        this.ID = ID;
        this.personalNumber = personalNumber;

    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    //method to return a lst of boat that a member has
    public ArrayList<model.Boat> getBoatList() {
        return new ArrayList<Boat>(boatList);
    }

    public void registerBoat(double length, BoatType type) {
        this.boatList.add(new Boat(length, type));
    }

    public void removeBoat(Boat boat) {
        this.boatList.remove(boat);
    }
    /**
     * Function to get the age of a member
     */
    public int getAge(Member member) {
        int age;
        int year = Calendar.getInstance().get(Calendar.YEAR);  // get the current year
        if (member.getPersonalNumber().length() == 12) {       //if the personal number has 12 digit
            age = year - (Integer.parseInt(member.getPersonalNumber().substring(0, 4)));
        } else {    // if it has only 10 digits
            age = (year - 1900) - (Integer.parseInt(member.getPersonalNumber().substring(0, 2)));
        }
        return age;
    }

    public int getNumberOfBoats() {             // get the number of boats each member has
        return boatList.size();
    }


}
