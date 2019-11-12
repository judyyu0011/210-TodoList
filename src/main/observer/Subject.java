package observer;

import model.ToDoList;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<WeatherObserver> weatherObservers;

    public Subject() {
        weatherObservers = new ArrayList<>();
    }

    public void addObserver(WeatherObserver o) {
        if (!weatherObservers.contains(o)) {
            weatherObservers.add(o);
        }
    }

    public void notifyObservers(ToDoList list) {
        for (WeatherObserver o : weatherObservers) {
            o.update(list);
        }
    }

}
