package ru.vapima.butjet.service;

import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.exeptions.ValidExeption;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//TODO NEED TEST
public class Validation {
    public boolean check(Person person) throws PersonExeption {
        isEmailOK(person.getName());
        isPasswordOK(person.getPassword());
        return true;
    }

    public boolean check(Acc acc) throws PersonExeption {
        isNameOK(acc.getName());
        return true;
    }

    public boolean check(Plan plan) {
        return true;
    }

    private String isNameOK(String str) throws PersonExeption {
        Matcher m = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$").matcher(str);
        if (!m.matches()) {
            throw new ValidExeption("Name is not correct. Only latin and digits. First letter not digit. 1-20 letters.");
        }
        return str;
    }

    private String isPasswordOK(String str) throws PersonExeption {
        Matcher m = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$").matcher(str);
        if (!m.matches()) {
            throw new ValidExeption("Password is not corrrect. Only latin. UpperCase, LowerCase and Number should be.");
        }
        return str;
    }
    private String isEmailOK(String str) throws PersonExeption {
        String emailPattern =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Matcher m = Pattern.compile(emailPattern).matcher(str);
        if (!m.matches()) {
            throw new ValidExeption("E-mail is not corrrect. Only latin. UpperCase, LowerCase and Number should be.");
        }
        return str;
    }

    public String isTokenOK(String str) throws PersonExeption {
        if (str==null){throw new ValidExeption("Token is not found. ");}
        Matcher m = Pattern.compile("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*$").matcher(str);
        if (!m.matches()) {
            throw new ValidExeption("Token is not token. ");
        }
        return str;
    }
}
