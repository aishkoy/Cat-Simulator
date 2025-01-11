package services;

import exceptions.IncorrectInputException;

import java.util.Scanner;

public class IOManager {
    private final static Scanner sc = new Scanner(System.in);
    private IOManager() {}

    public static String getValidString(String regex, String message) {
        try {
            System.out.print(message);
            String input = sc.nextLine().strip();
            if (!input.matches(regex) || input.isBlank()) {
                throw new IncorrectInputException("\nНеверный ввод! Попробуйте еще раз!");
            }
            return input;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return getValidString(regex, message);
        }
    }

    public static int getValidInt(String regex, String message) {
        return Integer.parseInt(getValidString(regex, message));
    }
}
