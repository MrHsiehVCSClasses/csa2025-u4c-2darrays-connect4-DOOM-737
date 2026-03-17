package u8pp;

public class Reservation{
    private String passengerName;
    private boolean frequentFlyer;
    
    //creates a reservation with name and frequent flyer status
    public Reservation(String name, boolean frequentFlyer){
        this.passengerName = name;
        this.frequentFlyer = frequentFlyer;
    }

    //returns the passenger name
    public String getPassengerName(){
        return passengerName;
    }

    //returns if the passenger is a frequent flyer
    public boolean isFrequentFlyer(){
        return frequentFlyer;
    }
}