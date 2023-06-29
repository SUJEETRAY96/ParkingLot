package exceptions;

public class ParkingSpotNotAvailableException extends Exception{
    public ParkingSpotNotAvailableException(String msj){
        super(msj);
    }
}
