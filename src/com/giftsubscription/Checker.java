package com.giftsubscription;

public abstract class Checker<T> {
    protected T value;
    String errorMessage;
    public Checker(T value, String errorMessage) {
        this.value = value;
        this.errorMessage = errorMessage;

    }

    public abstract boolean isValid(UserStorage userStorage);

    public String getErrorMessage() {
        return errorMessage;
    }

}
class MailChecker extends Checker<String> {
    ValidationMode validationMode;

    public MailChecker(String mail, ValidationMode validationMode) {
        super(mail, "");
        this.validationMode = validationMode;
    }

    @Override
    public boolean isValid(UserStorage userStorage) {
        boolean exist = userStorage.containsMail(value);
        return (validationMode == ValidationMode.REGISTRATION) ? !exist : exist;
    }

    @Override
    public String getErrorMessage() {
        if(validationMode == ValidationMode.REGISTRATION) {
            return "Такая почта уже зарегистрирована! Используйте другую.";
        } else if(validationMode == ValidationMode.AUTHORIZATION) {
            return "Такой почты нет в системе! Проверьте правильность ввода.";
        }
        return null;
    }
}
class NumberChecker extends Checker<String> {
    public NumberChecker(String value) {
        super(value, "Такой номер уже используется!");
    }

    @Override
    public boolean isValid(UserStorage userStorage) {
        return userStorage.containsNumber(value);
    }
}
class PasswordChecker extends Checker<String> {
    String mail;
    public PasswordChecker(String value, String mail) {
        super(value, "Неверный пароль!");
        this.mail = mail;
    }

    @Override
    public boolean isValid(UserStorage userStorage) {
        return userStorage.containsPassword(mail, value);
    }
}