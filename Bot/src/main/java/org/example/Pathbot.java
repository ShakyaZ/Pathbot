package org.example;

import java.util.Scanner;

public class Pathbot {
    private static int x = 0; // Initial x-coordinate
    private static int y = 0; // Initial y-coordinate
    private static Direction direction = Direction.NORTH; // Initial direction

    private enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter movement command: ");
        String command = scanner.nextLine().toUpperCase();
        Command(command);
        finalPosition();
    }

    private static void Command(String command) {
        for (int i = 0; i < command.length(); i++) {
            char cmd = command.charAt(i);
            if (Character.isDigit(cmd)) {
                int j = i + 1;
                while (j < command.length() && (Character.isDigit(command.charAt(j)) || command.charAt(j) == ' ')) {
                    j++;
                }

                int steps = Integer.parseInt(command.substring(i, j).trim());
                move(steps);
                i = j - 1;
            } else {
                executeCommand(cmd);
            }
        }
    }

    private static void executeCommand(char command) {
        switch (command) {
            case 'R':
                turnRight();
                break;
            case 'L':
                turnLeft();
                break;
            case 'M':
                move(1);
                break;
            default:
                System.out.println("Invalid command: " + command);
        }
    }

    private static void turnRight() {
        direction = Direction.values()[(direction.ordinal() + 1) % 4];
    }

    private static void turnLeft() {
        direction = Direction.values()[(direction.ordinal() + 3) % 4];
    }

    private static void move(int steps) {
        switch (direction) {
            case NORTH:
                y += steps;
                break;
            case EAST:
                x += steps;
                break;
            case SOUTH:
                y -= steps;
                break;
            case WEST:
                x -= steps;
                break;
        }
    }

    private static void finalPosition() {
        System.out.println("Final position: " + direction.name() + " (" + x + "," + y + ")");
    }
}
