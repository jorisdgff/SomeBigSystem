package jorisdgffdemos.somebigsystem.cqrskit;

public class Acknowledgement {

    public final String refferingEvent;
    public final String message;

    public Acknowledgement(String refferingEvent, String message){

        this.refferingEvent = refferingEvent;
        this.message = message;
    }
}
