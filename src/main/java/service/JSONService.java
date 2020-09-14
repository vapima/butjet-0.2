package service;

import exeptions.PersonExeption;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;


public class JSONService {
    public static JSONObject getBody(HttpServletRequest req) throws IOException, PersonExeption {
        BufferedReader reader = req.getReader();
        int intValueOfChar;
        StringBuilder data = new StringBuilder();
        while ((intValueOfChar = reader.read()) != -1) {
            data.append((char) intValueOfChar);
        }
        return Validation.check(new JSONObject(data.toString()));
    }
}
