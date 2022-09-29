package org.example;

public class Customer {
    public void order(String mneuName, Menu menu, Cooking cooking) {
        MenuItem menuItem = menu.choose(mneuName);
        Cook cook = cooking.makeCook(menuItem);
    }
}
