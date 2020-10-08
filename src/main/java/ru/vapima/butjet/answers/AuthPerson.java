package ru.vapima.butjet.answers;

import ru.vapima.butjet.exeptions.PersonExeption;
import ru.vapima.butjet.service.PasswordHash;
import ru.vapima.butjet.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.StringTokenizer;

public class AuthPerson {
    private Config config;
    private HttpServletRequest req;
    private PasswordHash passwordHash=new PasswordHash();
    private TokenService tokenService=new TokenService();

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }

    public PasswordHash getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(PasswordHash passwordHash) {
        this.passwordHash = passwordHash;
    }

    public TokenService getTokenService() {
        return tokenService;
    }

    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    public AuthPerson(Config config, HttpServletRequest req) {
        this.config = config;
        this.req = req;
    }

    public Integer getPersonID() throws PersonExeption {
        String token = config.getValidation().isTokenOK(req.getParameter("token"));
        return tokenService.decode(token);
    }

    public String getNameAuthFromReq() throws PersonExeption {
        String[] nameAndPassword = takeUserAndPasswordFromReq().split(":");
        if (nameAndPassword.length != 2) {
            throw new PersonExeption("Name and password fail. " + nameAndPassword.length);
        }
        return nameAndPassword[0];
    }

    public String getPasswordAuthFromReq() throws PersonExeption {
        String[] nameAndPassword = takeUserAndPasswordFromReq().split(":");
        if (nameAndPassword.length != 2) {
            throw new PersonExeption("Name and password fail. " + nameAndPassword.length);
        }
        return nameAndPassword[1];
    }

    private String takeUserAndPasswordFromReq() {
        String authHeader = req.getHeader("Authorization");
        if (authHeader != null) {
            StringTokenizer st = new StringTokenizer(authHeader);
            if (st.hasMoreTokens()) {
                String basic = st.nextToken();
                if (basic.equalsIgnoreCase("Basic")) {
                    return new String(Base64.getDecoder().decode(st.nextToken()), StandardCharsets.UTF_8);
                }
            }
        }
        return " : ";
    }
}
