package exeptions;

public class AccExeption extends Exception {
    public AccExeption(String message) {
        super(message);
        System.out.println(this.toString());
    }
}
