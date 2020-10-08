package ru.vapima.butjet.service.json;

public class SerialFactoryStub extends SerialFactory {

    @Override
    public Serial createJsonSer() {
        return new JsonSerialStub();
    }

    @Override
    public Deserial createJsonDeser() {
        return new JsonDeserialStub();
    }
}