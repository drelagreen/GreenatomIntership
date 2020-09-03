package game;

import game.field.Field;
import game.field.Printer;

import java.util.Random;
import java.util.Scanner;

public class Player {
    private boolean random;
    private final Scanner scanner = new Scanner(System.in);
    private final Field myField;
    private final Field opponentField;
    private final Printer printer;
    private boolean remote = false;
    Player(Field myField, Field opponentField){
        this.myField = myField;
        this.opponentField = opponentField;
        printer = new Printer(myField, opponentField);
    }

    Pare<Integer,Integer> translateCommand(String command){
        String[] strings = command.split(" ");
        try {
            int x = translateLetter(strings[0].charAt(0));
            int y = Integer.parseInt(strings[strings.length - 1]) + 1;
            if (x<2||x>11||y<2||y>11) return new Pare<>(0,0);
            return new Pare<>(y,x);
        } catch (Exception e){
            return new Pare<>(0,0);
        }
    }

    private int translateLetter(char letter){
        switch (Character.toLowerCase(letter)){
            case 'а':
                return 2;
            case 'б':
                return 3;
            case 'в':
                return 4;
            case 'г':
                return 5;
            case 'д':
                return 6;
            case 'е':
                return 7;
            case 'ж':
                return 8;
            case 'з':
                return 9;
            case 'и':
                return 10;
            case 'к':
                return 11;
            default:
                return 1;
        }
    }

    public Field getMyField() {
        return myField;
    }

    public Field getOpponentField() {
        return opponentField;
    }

    public Printer getPrinter() {
        return printer;
    }

    public boolean isRemote() {
        return remote;
    }

    public Pare<Integer,Integer> getCoordinates(){
        if (random) return getRandomCoordinates();
        printText("Введите координаты следующего корабля");
        return translateCommand(getTextLine());
    }

    public boolean getOrientation(){
        if (random) return getRandomOrientation();
        printText("Выберите расположение корабля в пространстве (true/h/t/г - горизонтальное, false/v/f/в - вертикальное)");
        String orientation = getTextLine();

        if(orientation.equals("true")||orientation.equalsIgnoreCase("t")||orientation.equalsIgnoreCase("h")||orientation.equalsIgnoreCase("г")){
            return true;
        } else if (orientation.equalsIgnoreCase("false")||orientation.equalsIgnoreCase("f")||orientation.equalsIgnoreCase("v")||orientation.equalsIgnoreCase("в")){
            return false;
        } else return getOrientation();

    }

    public void printText(String text){
        if(isRemote()){
            Connector.getInstance().printTextToSocket(text);
        } else {
            System.out.println(text);
        }
    }

    String getTextLine(){
        if (isRemote()){
           return Connector.getInstance().getNextLineFromSocket();
        } else {
            return scanner.nextLine();
        }
    }

    boolean getYesOrNoCmd(){
        return getTextLine().equalsIgnoreCase("д");
    }

    public Pare<Integer,Integer> getRandomCoordinates(){
        Random randomizer = new Random();
        int y = randomizer.nextInt(10)+2;
        int x = randomizer.nextInt(10)+2;
        return new Pare<>(y,x);
    }

    public boolean getRandomOrientation(){
        Random randomizer = new Random();
        return randomizer.nextBoolean();
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }
}
