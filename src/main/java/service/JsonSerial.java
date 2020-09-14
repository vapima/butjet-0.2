package service;

import model.Acc;
import model.Person;
import model.Plan;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonSerial implements Serial {
    @Override
    public String go(Person person) {
        JSONObject jb = new JSONObject();
        jb.put("id", person.getId());
        jb.put("name", person.getName());
        jb.put("name", person.getName());
        jb.put("Accs", getAccArray(person.getAccs()));
        jb.put("Plans", getPlanArray(person.getPlans()));
        return jb.toString();
    }

    @Override
    public String go(Acc acc) {
        JSONObject jb = new JSONObject();
        jb.put("id", acc.getId());
        jb.put("name", acc.getName());
        jb.put("balance", acc.getBalance());
        jb.put("changeTime", acc.getChangTime());
        jb.put("active", acc.getActive());
        return jb.toString();
    }

    @Override
    public String go(Plan plan) {
        JSONObject jb = new JSONObject();
        jb.put("id", plan.getId());
        jb.put("name", plan.getName());
        jb.put("balance", plan.getBalance());
        jb.put("dateExpiration", plan.getDateExpiration());
        return jb.toString();
    }

    private JSONArray getAccArray(ArrayList<Acc> accs) {
        JSONArray jsonArray = new JSONArray();
        for (Acc a : accs) {
            jsonArray.put(go(a));
        }
        return jsonArray;
    }

    private JSONArray getPlanArray(ArrayList<Plan> plans) {
        JSONArray jsonArray = new JSONArray();
        for (Plan p : plans) {
            jsonArray.put(go(p));
        }
        return jsonArray;
    }
}
