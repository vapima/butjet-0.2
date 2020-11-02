package ru.vapima.butjet.answers;

import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.service.PasswordHash;
import ru.vapima.butjet.service.TokenService;

public class AuthPersonStub extends AuthPerson{


    public AuthPersonStub() {
        super(null, null);
        super.setPasswordHash(new PasswordHash(){
            @Override
            public String getHash(String str) {
                return str;
            }

            @Override
            public Boolean verifyHash(String str, String hash) {
                return true;
            }
        });
        super.setTokenService(new TokenService(){
            @Override
            public String create(Person person) {
                return person.getPassword();
            }

            @Override
            public Integer decode(String swt) throws PersonExeption {
                return 1;
            }
        });
    }

    @Override
    public Integer getPersonID() throws PersonExeption {
        return 1;
    }

    @Override
    public String getNameAuthFromReq() throws PersonExeption {
        return "test";
    }

    @Override
    public String getPasswordAuthFromReq() throws PersonExeption {
        return "test";
    }

    @Override
    public String getTokenAuthFromReq() throws PersonExeption {
        return "1IDtest";
    }
}