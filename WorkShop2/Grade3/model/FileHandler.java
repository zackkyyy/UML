package model;


import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private int membersNr;
    private File membersFile = new File("WorkShop2/Grade3/Members.txt");
    private File boatsFile = new File("WorkShop2/Grade3/Boats.txt");

    public FileHandler() throws UnsupportedEncodingException, FileNotFoundException {

    }

    public ArrayList<Member> readFile() throws IOException, NumberFormatException, ParseException {
        ArrayList<Member> membersList = new ArrayList<Member>();
        if (!membersFile.exists() || !boatsFile.exists()) {
            membersFile.createNewFile();
            boatsFile.createNewFile();
            return membersList;
        }

        Scanner scan = new Scanner(membersFile);
        if (scan.hasNext()) {
            this.membersNr = (Integer.parseInt(scan.nextLine()));
        }
        while (scan.hasNext()) {
            String[] temp = scan.nextLine().split(";");
            membersList.add(new Member(temp[1], temp[2], Integer.parseInt(temp[0])));
        }
        scan.close();

        scan = new Scanner(boatsFile);

        while (scan.hasNext()) {
            String[] temp = scan.nextLine().split(";");
            for (Member m : membersList) {
                if (m.getID() == Integer.parseInt(temp[2])) {
                    m.registerBoat(Double.parseDouble(temp[0]), BoatType.valueOf(temp[1]));
                    break;
                }
            }
        }
        scan.close();
        return membersList;
    }


    public void writeFile(ArrayList<Member> memberlist, int maxID) throws IOException {
        StringBuilder boats = new StringBuilder();
        StringBuilder members = new StringBuilder();
        PrintWriter writer = new PrintWriter(membersFile);
        members.append(maxID + "\n");
        for (Member m : memberlist) {
            members.append(m.getID() + ";" + m.getName() + ";" + m.getPersonalNumber() + "\n");

            for (Boat b : m.getBoatList()) {
                boats.append(b.getLength() + ";" + b.getBoatType().toString() + ";" + m.getID() + "\n");

            }
        }
        //write the members file
        membersFile.createNewFile();
        writer.print(members.toString());
        writer.close();
        writer = new PrintWriter(boatsFile);
        //write the Boats file
        boatsFile.createNewFile();
        writer.print(boats.toString());
        writer.close();
    }

    public int getMembersNr() {
        return membersNr;
    }
}