package com.giftsubscription;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserStorage userStorage = new UserStorage();
        Scanner scanner = new Scanner(System.in);
        Account account = new Account(userStorage, scanner);

        while (true) {
            System.out.println("Добро пожаловать в наш сервис!\n1.Регистрация\n2.Авторизация\n3.Выход");
            int answer = scanner.nextInt();
            scanner.nextLine();
            switch (answer) {
                case 1 -> account.registration();
                case 2 -> {
                    account.authorization();
                    if(account.isValid) {
                        showUserMenu(account, scanner);
                    }
                }
                case 3 -> {
                    System.out.println("Выход из системы...");
                    return;
                }
            }
        }
    }
    public static void showUserMenu(Account account, Scanner scanner) {
        while (true) {
            System.out.println("1.Аккаунт\n2.Приобрести подписку\n3.Назад");
            int answer = scanner.nextInt();
            scanner.nextLine();
            switch (answer) {
                case 1 -> {
                    account.aboutAccount();
//                    accountActing(account, scanner);
                }
                case 2 -> account.buySubscription();
                case 3 -> {
                    System.out.println("Назад...");
                    return;
                }
            }
        }
    }
//    public static void accountActing(Account account, Scanner scanner) {
//        while (true) {
//            System.out.println("1.Изменить 2.Назад");
//            int answer = scanner.nextInt();
//            scanner.nextLine();
//            switch (answer) {
//                case 1 ->
//            }
//        }
//    }
}