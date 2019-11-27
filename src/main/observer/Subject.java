package observer;

import model.ToDoList;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<WeatherObserver> weatherObservers;

    protected Subject() {
        weatherObservers = new ArrayList<>();
    }

    protected void addObserver(WeatherObserver o) {
        if (!weatherObservers.contains(o)) {
            weatherObservers.add(o);
        }
    }

    protected void notifyObservers(ToDoList list) {
        for (WeatherObserver o : weatherObservers) {
            o.update(list);
        }
    }

}
