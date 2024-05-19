import java.util.*;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.showMenu();
    }
}

//    Примененные принципы SOLID:
//        Single Responsibility Principle (SRP):
//        Класс Menu отвечает за меню.
//        Класс Delivery отвечает за доставку.
//        Класс Cart отвечает за управление корзиной.
//        Класс ProductService отвечает за управление продуктами.

//        Open/Closed Principle (OCP):
//        Классы открыты для расширения (можно добавлять новые методы, например, в ProductService), но закрыты для модификации (не изменяем существующую логику).

//        Interface Segregation Principle (ISP):
//        Применено через конкретные классы. Например, ProductService управляет продуктами, а Delivery управляет доставкой.

//        Dependency Inversion Principle (DIP):
//        Delivery зависит от абстракции Cart, а не от конкретной реализации списка продуктов в корзине.