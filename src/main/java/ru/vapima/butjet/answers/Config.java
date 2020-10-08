package ru.vapima.butjet.answers;

import ru.vapima.butjet.dao.DaoFactory;
import ru.vapima.butjet.service.Validation;
import ru.vapima.butjet.service.json.SerialFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Config {
    private DaoFactory daoFactory = new DaoFactory();
    private SerialFactory serialFactory = new SerialFactory();
    private Validation validation = new Validation();
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private AuthPerson authPerson;


    public Config(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        this.authPerson = new AuthPerson(this, req);
    }

    public AuthPerson getAuthPerson() {
        return authPerson;
    }

    public void setAuthPerson(AuthPerson authPerson) {
        this.authPerson = authPerson;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public SerialFactory getSerialFactory() {
        return serialFactory;
    }

    public void setSerialFactory(SerialFactory serialFactory) {
        this.serialFactory = serialFactory;
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }

    public HttpServletResponse getResp() {
        return resp;
    }

    public void setResp(HttpServletResponse resp) {
        this.resp = resp;
    }
}
