package observer;

import model.ToDoList;

public interface WeatherObserver {
    void update(ToDoList list);
}
