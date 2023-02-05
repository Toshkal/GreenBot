package ru.home.constant;

public enum ButtonName {
    START ("/start"),

    TASKS ("Задачи"),
    CURSES ("Показать");
    private final String buttonName;

    ButtonName(String buttonName) {
        this.buttonName = buttonName;
    }
}
