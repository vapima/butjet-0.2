package ru.vapima.butjet.service;

import ru.vapima.butjet.model.Acc;
import ru.vapima.butjet.model.AccRec;
import ru.vapima.butjet.model.Plan;

public class ObjectUpdater {
    public static void update(Object in, Object out){
        if (in instanceof Acc) {
            Acc accIn=(Acc) in;
            Acc accOut=(Acc) out;
            if (accIn.getName() != null) { accOut.setName(accIn.getName()); }
            if (accIn.getActive() != null) { accOut.setActive((accIn.getActive()));}
            if (accIn.getBalance() != null) { accOut.setBalance(accIn.getBalance());}
            if (accIn.getChangeTime() != null) { accOut.setChangeTime(accIn.getChangeTime());}

        }
        if (in instanceof AccRec){
            AccRec accRecIn=(AccRec) in;
            AccRec accRecOut=(AccRec) out;
            if (accRecIn.getBalance()!=null){accRecOut.setBalance(accRecIn.getBalance());}
            if (accRecIn.getChangeTime()!=null){accRecOut.setChangeTime(accRecIn.getChangeTime());}
        }
        if (in instanceof Plan){
            Plan planIn=(Plan) in;
            Plan planOut=(Plan) out;
            if (planIn.getName()!=null){planOut.setName(planIn.getName());}
            if (planIn.getBalance()!=null){planOut.setBalance(planIn.getBalance());}
            if (planIn.getDateExpiration()!=null){planOut.setDateExpiration(planIn.getDateExpiration());}
        }
    }
}
