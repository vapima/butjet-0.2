package service;

public class SerialFactory {
    public static Serial createJsonSer() {
        return new JsonSerial();
    }
}
