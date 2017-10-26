package model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;


public class MainController {

    private ArrayList<Member> memberList;
    private FileHandler fileHandler;
    private int nrOFMembers = 0;

    // constructor
    public MainController() {

    }

    public void readFiles() throws IOException, ParseException {
        fileHandler = new FileHandler();
        memberList = fileHandler.readFile();
        nrOFMembers = fileHandler.getMembersNr();
    }

    /**
     * @return an array with a list of the members
     */
    public ArrayList<Member> getMemberList() {
        ArrayList<Member> list = new ArrayList<Member>();
        list.addAll(memberList);
        return list;
    }

    /**
     * delete a member from the system
     */
    public void deleteMember(Member m) {
        //for loop to restructured the numbering of the members in the
        // list while deleting one member from the middle
        for (int i = m.getID() - 1; i < memberList.size(); i++) {
            memberList.get(i).setID(i);
        }
        this.memberList.remove(m);          //remove a member from the members list
        nrOFMembers--;                      //reduce the number of the members
    }

    public Member findMember(int ID) {
        for (Member member : this.memberList) {
            if (member.getID() == ID)  //check if the ID the user entered match any of the members IDs
                return member;
        }
        return null;
    }

    public Member findMemberByPersonalNr(String personaNumber) {

        for (Member member : this.memberList) {
            while (member.getPersonalNumber().equals(personaNumber)) {  //check if the Personal number the user entered match any of the members Personal number
                return member;

            }
        }

        return null;
    }

    /**
     * find a member by the given name from the user
     */
    public Member findMemberByName(String name) throws IOException, IllegalArgumentException {
        for (Member member : this.memberList) {
            while (member.getName().equals(name))
                return member;
        }
        return null;
    }

    /**
     * create a new member
     */
    public void createMember(String name, String personalnumber) {
        // creating a member by adding it to the member list  and increase the number of members
        this.memberList.add(new Member(name, personalnumber, ++nrOFMembers));
    }

    /**
     * Function to update a boat
     */
    public void updateBoat(double length, BoatType type, Boat boat) {
        boat.setLenght(length);
        boat.setType(type);
    }

    /**
     * Function to update a member
     */
    public void updateMember(Member m, String name, String personalnumber) {
        m.setName(name);
        m.setPersonalNumber(personalnumber);

    }

    //save the final file by calling the method from the FileHandler class
    public void saveFile() throws IOException {
        fileHandler.writeFile(memberList, nrOFMembers);
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

