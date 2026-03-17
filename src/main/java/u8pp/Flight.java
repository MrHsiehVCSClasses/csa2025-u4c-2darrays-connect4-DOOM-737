package u8pp;

import java.util.ArrayList;

public class Flight{

    private Reservation[][] seats;
    private int aisleIndex;

    //creates the flight with rows and seats, and adds aisle
    public Flight(int row, int seatsPerRow){
        seats = new Reservation[row][seatsPerRow +1];
        aisleIndex = (seatsPerRow + 1) / 2;

        for (int r = 0; r < seats.length; r++){
            seats[r][aisleIndex] = new Reservation("AISLE", false);
        }
    }

    //returns the seat array
    public Reservation[][] getSeats(){
        return seats;
    }

    //returns list of frequent flyer names
    public ArrayList<String> getFrequentFlyers(){
        ArrayList<String> list = new ArrayList<>();
        
        for (int r = 0; r < seats.length; r++){
            for(int c = 0; c < seats[r].length; c++){
                if(seats[r][c] != null && !seats[r][c].getPassengerName().equals("AISLE") && seats[r][c].isFrequentFlyer()){
                    list.add(seats[r][c].getPassengerName());
                }
            }
        }
        return list;
    }

    //reserves the nest open seat
    public boolean reserveNextAvailableSeat(String name, boolean freq){
        for (int r = 0; r < seats.length; r++){
            for (int c = 0; c < seats[r].length; c++){
                if (c == aisleIndex) continue;

                if (seats[r][c] == null){
                    seats[r][c] = new Reservation(name, freq);
                    return true;
                }
            }
        }
        return false;
    }

    //reserves the nest pair of adjacent seats
    public boolean reserveAdjacentSeats(String n1, boolean f1, String n2, boolean f2){
        for (int r = 0; r < seats.length; r++){
            for (int c = 0; c < seats[r].length -1; c++){
                if (c == aisleIndex || c +1 == aisleIndex) continue;

                if (seats[r][c] == null && seats[r][c + 1] == null){
                    seats[r][c] = new Reservation (n1, f1);
                    seats[r][c+1] = new Reservation (n2, f2);
                    return true;
                }
            }
        }
        return false;
    }

    //reserves a seat next to the aisle
    public boolean reserveAisleSeat(String name, boolean freq){
        for (int r = 0; r < seats.length; r++){
            //left of aisle
            if(aisleIndex - 1 >= 0 && seats[r][aisleIndex - 1] == null){
                seats[r][aisleIndex - 1] = new Reservation(name, freq);
                return true;
            }

            //right of aisle
            if (aisleIndex + 1 < seats[r].length && seats[r][aisleIndex + 1] == null){
                seats[r][aisleIndex + 1] = new Reservation(name, freq);
                return true;
            }
        }
        return false;
    }

    //returns passengers with no adjacent neighbors
    public ArrayList<String> getIsolatedPassengers(){
        ArrayList<String> list = new ArrayList<>();

        for (int r = 0; r < seats.length; r++){
            for (int c = 0; c < seats[r].length; c++){
                if(seats[r][c] == null) continue;
                if (seats[r][c].getPassengerName().equals("AISLE")) continue;

                boolean leftEmpty = (c -1 < 0) || seats[r][c - 1] == null || seats[r][c - 1].getPassengerName().equals("AISLE");

                boolean rightEmpty = (c + 1 >= seats[r].length) || seats[r][c + 1] == null || seats[r][c + 1].getPassengerName().equals("AISLE");

                if (leftEmpty && rightEmpty){
                    list.add(seats[r][c].getPassengerName());
                }
            }
        }
        return list;
    }

    //returns a formatted string of the plane
    public String toString(){
        String result = "";

        for (int r = 0; r < seats.length; r++){
            for (int c = 0; c < seats[r].length; c++){
                if (seats[r][c] == null)
                    result += "EMPTY";
                else
                    result += seats[r][c].getPassengerName();

                if (c < seats[r].length - 1)
                    result += " ";
            }
            if (r < seats.length - 1)
                result += "\n";
        }
        return result;
    }




}