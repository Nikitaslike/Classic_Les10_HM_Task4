package org.example;
//Створіть систему управління інвентарем, де буде класи для товарів, складів і операцій з інвентарем.
// Реалізуйте класи "Товар" та "Склад". Клас "Товар" повинен мати поля для назви, кількості, ціни та складу,
// на якому він знаходиться. Клас "Склад" повинен мати поля для назви та списку товарів.
// Напишіть методи для додавання, видалення та переміщення товарів між складами,
// а також для виведення інформації про товари на конкретному складі.
import java.util.ArrayList;
import java.util.Scanner;
class Product {
    private String name;
    private int countOfAvailable;
    private double cost;
    private Stock stocksLocation;
    private ArrayList<String> operations;
    public Product(String name, int countOfAvailable, double cost, Stock stocksLocation) {
        this.name = name;
        this.countOfAvailable = countOfAvailable;
        this.cost = cost;
        this.stocksLocation = stocksLocation;
        this.operations = new ArrayList<String>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int setCountOfAvailable(int countOfAvailable) {
        if(countOfAvailable > 0){
            return countOfAvailable;
        }
        return countOfAvailable;
    }

    public int getCountOfAvailable() {
        return countOfAvailable;
    }

    public void setCost(double cost) {
        if(cost>0){
            this.cost = cost;
        }
    }

    public double getCost() {
        return cost;
    }

    public void setStocksLocation(Stock stocksLocation) {
        this.stocksLocation = stocksLocation;
    }

    public Stock getStocksLocation() {
        return stocksLocation;
    }

    public void printInfo() {
        System.out.println("Назва: " + name);
        System.out.println("В наявності: " + countOfAvailable);
        System.out.println("Ціна: " + cost+"₴");
        System.out.println("Локація: " + stocksLocation.getName());
    }

    public void addOperation(String operation) {
        operations.add(operation);
    }
}
class Stock {
    private String nameSTock;
    private ArrayList<Product> products;
    public Stock(String nameSTock) {
        this.nameSTock = nameSTock;
        this.products = new ArrayList<Product>();
    }
    public String getName() {
        return nameSTock;
    }

    public void setName(String nameSTock) {
        this.nameSTock = nameSTock;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    public void addProducts(Product task) {
        products.add(task);
    }
    public void printInfo() {
        System.out.println("Локація (назва) складу: " + nameSTock);
        System.out.println("Кількість товару: " + products.size());
    }

}
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Stock stock;

    public static void main(String[] args) {
        System.out.print("Введіть ім'я користувача: ");
        String userName = scanner.next();
        stock = new Stock(userName);
        System.out.println("Вітаю, "+userName+", в системі управління магазина “Смачні мрії”!");
        System.out.println("Для того щоб створити новий товар натисніть 1. Для того щоб редагувати існуючий товар- натисніть 2.");
        System.out.println("Для того щоб видалити існуючий товар натисніть 3. Для того щоб вивести список товарів- натисніть 4.");
        System.out.println("Для того щоб перекинути товар з одного складу на інший натисніть 5. Для того щоб вийти натисніть 6.");
        int choice = 0;
        do {
            System.out.print("Введіть номер операції: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createProduct();
                    break;
                case 2:
                    editProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    printProducts();
                    break;
                case 5:
                    withdrawProducts();
                    break;
                case 6:
                    System.out.println("Дякую. Гарного дня!");
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
                    break;
            }
        } while (choice != 6);
    }

    public static void createProduct() {
        System.out.print("Введіть назву товару: ");
        String name = scanner.next();
        System.out.print("Введіть локацію склада: ");
        String nameStock = scanner.next();
        System.out.print("Введіть кількість наявності: ");
        int countOfAvailable = scanner.nextInt();
        System.out.print("Введіть ціну товару: ");
        double cost = scanner.nextDouble();
        Product product = new Product(name, countOfAvailable, cost, stock);
        stock.addProducts(product);
        System.out.println("Товар успішно створено!");
    }

    public static void editProduct() {
        System.out.print("Введіть номер товару, яке потрібно редагувати: ");
        int index = scanner.nextInt();
        if (index >= 0 && index < stock.getProducts().size()) {
            Product product = stock.getProducts().get(index);
            System.out.println("Ви обрали наступний товар: ");
            product.printInfo();
            System.out.print("Введіть нову назву товару або - для залишити стару: ");
            String newName = scanner.next();
            if (!newName.equals("-")) {
                product.setName(newName);
            }
            System.out.print("Введіть нову кількість товарів: ");
            int newCountOfAvailable = scanner.nextInt();
            product.setCountOfAvailable(newCountOfAvailable);
            System.out.print("Введіть нову ціну товару: ");
            double newCost = scanner.nextDouble();
            product.setCost(newCost);
            System.out.println("Товар успішно відредаговано!");
        } else {
            System.out.println("Невірний номер товару. Спробуйте ще раз.");
        }
    }

    public static void deleteProduct() {
        System.out.print("Введіть номер товару, яке потрібно видалити: ");
        int index = scanner.nextInt();
        if (index >= 0 && index < stock.getProducts().size()) {
            Product product = stock.getProducts().remove(index);
            System.out.println("Ви видалили наступний товар: ");
            product.printInfo();
            System.out.println("Товар успішно видалено!");
        } else {
            System.out.println("Невірний номер товару. Спробуйте ще раз.");
        }
    }

    public static void withdrawProducts() {
        System.out.print("Введіть номер товару, з якого потрібно зняти кількість: ");
        int index = scanner.nextInt();
        if (index >= 0 && index < stock.getProducts().size()) {
            Product product = stock.getProducts().get(index);
            System.out.println("Ви обрали наступний товар: ");
            product.printInfo();
            System.out.print("Введіть кількість товару, яку потрібно зняти: ");
            int count = scanner.nextInt();
            if (count > 0 && count <= product.getCountOfAvailable()) {
                product.setCountOfAvailable(product.getCountOfAvailable() - count);
                product.addOperation("Знято " + count + " одиниць товару " + product.getName());
                System.out.println("Операція успішно виконана!");
            } else {
                System.out.println("Невірна кількість товару. Спробуйте ще раз.");
            }
        } else {
            System.out.println("Невірний номер товару. Спробуйте ще раз.");
        }
    }

    public static void printProducts() {
        if (stock.getProducts().isEmpty()) {
            System.out.println("У вас немає жодного товару.");
        } else {
            System.out.println("Список всіх товарів на складі:");
            for (int i = 0; i < stock.getProducts().size(); i++) {
                System.out.println("Товар №" + (i + 1) + ":");
                stock.getProducts().get(i).printInfo();
            }
        }
    }
}

