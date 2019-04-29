package application;

import java.util.HashMap;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class ScreenController {
    private HashMap<String, Parent> screenMap = new HashMap<>();
    private Scene main;

    public ScreenController(Scene main) {
        this.main = main;
    }

    protected void addScreen(String name, Parent pane){
         screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        Parent pane = screenMap.get(name);
    	main.setRoot(pane);
        pane.fireEvent(new OnShowEvent());
    }
}
