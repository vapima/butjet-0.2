package ru.vapima.butjet.service.json;

import com.google.gson.GsonBuilder;
import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.Person;
import ru.vapima.butjet.model.Plan;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// go TO GSON
public class JsonSerial implements Serial {


    GsonBuilder gsonBuilder;

    public JsonSerial(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
    }

    @Override
    public String parse(Person person) {
        JSONObject jb = new JSONObject();
        jb.put("id", person.getId());
        jb.put("name", person.getName());
        jb.put("name", person.getName());
        return jb.toString();
    }

    @Override
    public String parse(Acc acc) {
        JSONObject jb = new JSONObject();
        jb.put("id", acc.getId());
        jb.put("name", acc.getName());
        jb.put("balance", acc.getBalance());
        jb.put("changeTime", acc.getChangeTime());
        jb.put("active", acc.getActive());
        jb.put("personId", acc.getPersonId());
        return jb.toString();
    }

    @Override
    public String parse(Plan plan) {
        JSONObject jb = new JSONObject();
        jb.put("id", plan.getId());
        jb.put("name", plan.getName());
        jb.put("balance", plan.getBalance());
        jb.put("dateExpiration", plan.getDateExpiration());
        jb.put("personId", plan.getIdPerson());
        return jb.toString();
    }


    @Override
    public String parceAccsArray(ArrayList<Acc> accs) {
        return getAccArray(accs).toString();
    }

    @Override
    public String parcePlansArray(ArrayList<Plan> plans) {
        return getPlanArray(plans).toString();
    }


    private JSONArray getAccArray(ArrayList<Acc> accs) {
        JSONArray jsonArray = new JSONArray();
        for (Acc a : accs) {
            jsonArray.put(new JSONObject(parse(a)));
        }
        return jsonArray;
    }

    private JSONArray getPlanArray(ArrayList<Plan> plans) {
        JSONArray jsonArray = new JSONArray();
        for (Plan p : plans) {
            jsonArray.put(new JSONObject(parse(p)));
        }
        return jsonArray;
    }
}
