package com.techlab.UI;
import java.util.HashMap;
import java.util.Map;
public abstract class Menu {
    protected final MenuController controller;
    protected final Map<Integer, MenuAction> actions = new HashMap<>();
    public Menu(MenuController controller) {
        this.controller = controller;
        setupActions();
    }
    protected abstract void setupActions();
    public abstract void display();
    public void dispatch(int option) {
        MenuAction action = actions.get(option);
        if (action != null) {
            action.execute();
        } else {
            System.out.println("Opción no válida.");
        }
    }
}

