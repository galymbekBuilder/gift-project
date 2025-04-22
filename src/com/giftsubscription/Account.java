package com.giftsubscription;

import java.util.Scanner;

public class Account {
    User user;
    boolean isValid = true;
    private final UserStorage userStorage;
    Scanner scanner;
    Subscription subscription;

    Account(UserStorage userStorage, Scanner scanner) {
        this.userStorage = userStorage;
        this.scanner = scanner;
        subscription = Subscription.STANDART;
    }

    public void registration() {
        isValid = true;
        System.out.println("Введите ваше имя: ");
        String name = scanner.nextLine();
        System.out.println("Введите вашу почту: ");
        String mail = scanner.nextLine();
        System.out.println("Введите ваш номер: ");
        String number = scanner.nextLine();
        System.out.println("Создайте будущий пароль: ");
        String password = scanner.nextLine();

        Checker<String> mailChecker = new MailChecker(mail, ValidationMode.REGISTRATION);
        if (!mailChecker.isValid(userStorage)) {
            isValid = false;
            System.out.println(mailChecker.getErrorMessage());
        }
        Checker<String> numChecker = new NumberChecker(number);
        if (numChecker.isValid(userStorage)) {
            isValid = false;
            System.out.println(numChecker.getErrorMessage());
        }

        if (isValid) {
            user = new User(name, mail, number, password);
            userStorage.addUser(user);
            System.out.println(name + ", Аккаунт успешно зарегистировался!");
        } else {
            System.out.println("Повторите попытку!");
        }
    }

    public void authorization() {
        isValid = true;
        System.out.println("Введите вашу почту: ");
        String mail = scanner.nextLine();
        System.out.println("Введите ваш пароль: ");
        String password = scanner.nextLine();
        Checker<String> mailChecker = new MailChecker(mail, ValidationMode.AUTHORIZATION);
        if (!mailChecker.isValid(userStorage)) {
            isValid = false;
            System.out.println(mailChecker.getErrorMessage());
        }

        Checker<String> passwordChecker = new PasswordChecker(password, mail);
        if (!passwordChecker.isValid(userStorage)) {
            isValid = false;
            System.out.println(passwordChecker.getErrorMessage());
        }

        if (isValid) {
            user = userStorage.getUser(mail);
            System.out.println(user.getName() + ", Вы успешно вошли в систему!");
        } else {
            System.out.println("Повторите попытку!");
        }

    }

    public void aboutAccount() {
        System.out.println(user.toString());
        // какая подписка стоит, изменить данные, пополнить баланс, выйти из системы
        switch (subscription) {
            case STANDART -> System.out.println("На текущий момент у вас отсутствует подписка!");
            case PREMIUM -> System.out.println("У вас тип подписки Премиум!");
            case VIP -> System.out.println("У вас тип подписки Вип!");
        }
    }

    public void buySubscription() {

    }


}




