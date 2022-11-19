package Pattern;

import java.util.ArrayList;

public class Observable {

    public ArrayList<Observer> obs = new ArrayList<>();

    public void addObs(Observer obser) {
        obs.add(obser);
    }

    public void notify(Object obj) {
        for(Observer temp : obs) {
            temp.update(obj);
        }
    }

    /* 
    public Object getUpdate(Observer obj) {
        return obj.getMessage;
    }
    */
}
