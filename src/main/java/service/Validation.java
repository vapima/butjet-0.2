package service;

import exeptions.PersonExeption;
import exeptions.ValidExeption;
import model.Acc;
import model.Person;
import model.Plan;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean check(Person person) throws PersonExeption {
        isNameOK(person.getName());
        isPasswordOK(person.getPassword());
        return true;
    }

    public static JSONObject check(JSONObject body) throws PersonExeption {
        try {
            isNameOK(body.getString("name"));
        } catch (JSONException e) {
        }
        try {
            isPasswordOK(body.getString("password"));
        } catch (JSONException e) {
        }
        return body;
    }

    public static boolean check(Acc acc) {
        return true;
    }

    public static boolean check(Plan plan) {
        return true;
    }

    static String isNameOK(String str) throws PersonExeption {
        Matcher m = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$").matcher(str);
        if (!m.matches()) {
            throw new ValidExeption("Name is not correct. Only latin and digits. First letter not digit. 1-20 letters.");
        }
        return str;
    }

    static String isPasswordOK(String str) throws PersonExeption {
        Matcher m = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$").matcher(str);
        if (!m.matches()) {
            throw new ValidExeption("Password is not corrrect. Only latin. UpperCase, LowerCase and Number should be.");
        }
        return str;
    }

    static String isTokenOK(String str) throws PersonExeption {
        Matcher m = Pattern.compile("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$").matcher(str);
        if (!m.matches()) {
            throw new ValidExeption("Token is not token. Token is red tablet.");
        }
        return str;
    }
}
