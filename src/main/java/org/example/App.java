package org.example;

/*
 *  UCF COP3330 Fall 2021 Exercise 25 Solution
 *  Copyright 2021 Merrek DeBolt
 */

import java.util.Scanner;
import java.util.regex.*;

public class App {

    public static int passwordValidator(String password)
    {
        int returnVal = 0;

        boolean containsNum = false, containsLetter = false, containsSymbol = false;

        // Determines if there are numbers in the string
        containsNum = password.matches(".*\\d.*");

        // Determines if there are letters in the string.
        for (int index = 0; index < password.length(); index++)
        {
            if ((Character.isLetter(password.charAt(index))))
            {
                containsLetter = true;
                break;
            }
        }

        // Determines if there are special characters in the string.
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(password);
        containsSymbol = m.find();

        if (containsNum && !containsLetter && !containsSymbol && password.length() < 8)
            returnVal = 1;
        else if (containsLetter && !containsNum && !containsSymbol && password.length() < 8)
            returnVal = 2;
        else if (containsLetter && containsNum && !containsSymbol && password.length() >= 8)
            returnVal = 3;
        else if (containsLetter && containsNum && containsSymbol && password.length() >= 8)
            returnVal = 4;

        return returnVal;
    }

    public static void main(String[] args)
    {
        String input, strengthWord = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your password: ");
        input = scanner.nextLine();

        int strength = passwordValidator(input);

        switch (strength)
        {
            case 1:
                strengthWord = "very weak";
                break;
            case 2:
                strengthWord = "weak";
                break;
            case 3:
                strengthWord = "strong";
                break;
            case 4:
                strengthWord = "very strong";
                break;
        }

        System.out.println(String.format("The password '%s' is a %s password.", input, strengthWord));
    }
}