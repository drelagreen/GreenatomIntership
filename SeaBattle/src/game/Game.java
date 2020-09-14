package game;

import game.field.AreaType;
import game.field.Field;
import game.ships.ShipKeeper;
import game.ships.Sizes;

import java.util.Scanner;

public class Game {
    static private final Game instance = new Game();
    private Thread inputThread;
    private Thread outputThread;
    private Player player1;
    private Player player2;
    private int delay = 0;
    private boolean game = false;
    private volatile boolean remote = false;
    private boolean ai2 = false;
    private boolean ai1 = false;
    private final Scanner scanner = new Scanner(System.in);

    public static Game getInstance() {
        return instance;
    }

    public void start(){
        printMenu();
        Connector.getInstance().setExceptionCallback(str -> {
            System.out.println(str);
            printMenu();
        });
        if (!remote) {
            Connector.getInstance().setExceptionCallback(str -> {
                System.out.println(str);
                Connector.getInstance().closeAll();
                start();
            });
            ShipKeeper shipKeeper1 =  new ShipKeeper();
            Field field1 = new Field(shipKeeper1);
            ShipKeeper shipKeeper2 = new ShipKeeper();
            Field field2 = new Field(shipKeeper2);

            player1 = new Player(field1,field2);
            player2 = new Player(field2,field1);


            player2.setRemote(true);

            player1.printText("Первый пользователь расставляет корабли");
            player2.printText("Первый пользователь расставляет корабли");
            if(!ai1) {
                player1.printText("Расставить корабли случайным образом? (д/н)");
                player1.setRandom(player1.getYesOrNoCmd());
            } else {
                player1.setRandom(true);
            }
            putShips(player1);
            if (!ai1) player1.setRandom(false);

            player1.printText("Расстановка кораблей 1го пользователя завершена");
            player2.printText("Расстановка кораблей 1го пользователя завершена");
            player1.printText("Второй пользователь расставляет корабли");
            player2.printText("Второй пользователь расставляет корабли");
           if (!ai2) {
               player2.printText("Расставить корабли случайным образом? (д/н)");
               player2.setRandom(player2.getYesOrNoCmd());
           } else {
               player2.setRandom(true);
           }
            putShips(player2);
           if (!ai2) player2.setRandom(false);

            player1.printText("Расстановка кораблей 2го пользователя завершена");
            player2.printText("Расстановка кораблей 2го пользователя завершена");
            shooting();
        } else {
            Connector.getInstance().setExceptionCallback(str -> {
                System.out.println(str);
                Connector.getInstance().closeAll();
                inputThread.interrupt();
                outputThread.interrupt();
                start();
            });
            inputThread = new Thread(()->{
               while (remote) {
                   System.out.println(Connector.getInstance().getNextLineFromSocket());
               }
            });
            outputThread = new Thread(()->{
                Scanner scanner = new Scanner(System.in);
                while(remote){
                   Connector.getInstance().printTextToSocket(scanner.nextLine());
                }
            });
            inputThread.start();
            outputThread.start();
        }
    }
    void shooting() {
        int score1 = 0;
        int score2 = 0;
        boolean turn = true;
        byte t;
        while (game) {
            if (ai1){
                System.out.println("ОБЩЕЕ ПОЛОЖЕНИЕ");
                player1.printText(player1.getPrinter().getFieldsStringBuilder(false).toString());
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (turn) {
                player1.printText("Ход первого игрока");
                player2.printText("Ход первого игрока");
                player1.printText(player1.getPrinter().getFieldsStringBuilder(true).toString());
                t = player1.getOpponentField().fire(player1.getCoordinates());
                if (t == 0) {
                    turn = false;
                } else if (t == 1) {
                    score1++;
                    player2.printText(player2.getPrinter().getFieldsStringBuilder(true).toString());
                } else {
                    player1.printText("Введены неправильные координаты");
                }


            } else {
                player1.printText("Ход второго игрока");
                player2.printText("Ход второго игрока");
                player2.printText(player2.getPrinter().getFieldsStringBuilder(true).toString());
                t = player2.getOpponentField().fire(player2.getCoordinates());
                if (t == 0) {
                    turn = true;
                } else if (t == 1) {
                    score2++;
                    player1.printText(player1.getPrinter().getFieldsStringBuilder(true).toString());
                } else {
                    player2.printText("Введены неправильные координаты");
                }
            }
            player1.printText(player1.getPrinter().getFieldsStringBuilder(true).toString());
            player2.printText(player2.getPrinter().getFieldsStringBuilder(true).toString());
            player1.printText("\nСчет - " + score1 + "|" + score2 + "\n");
            player2.printText("\nСчет - " + score1 + "|" + score2 + "\n");
            if (score1 == 20) {
                player1.printText("\nПервый игрок победил!\n");
                player2.printText("\nПервый игрок победил!\n");
                String s = player1.getPrinter().getFieldsStringBuilder(false).toString();
                player1.printText(s);
                player2.printText(s);
                game = false;
                remote = false;
            }
            if (score2 == 20) {
                player1.printText("\nВторой игрок победил!\n");
                player2.printText("\nВторой игрок победил!\n");
                String s = player1.getPrinter().getFieldsStringBuilder(false).toString();
                player1.printText(s);
                player2.printText(s);
                game = false;
                remote = false;
            }
        }
        Connector.getInstance().closeAll();
        start();
    }
    private void putShips(Player player){
        for (int i = 0; i < 4; i++) {
           player.printText(player.getPrinter().getFieldsStringBuilder(true).toString());
           player.printText("Расстановка однопалубных кораблей ("+(i+1)+"/4)");
           if(!player.getMyField().put(AreaType.SHIP, Sizes.ONE,player.getCoordinates(),true)){
               player.printText("Неправильная расстановка");
               i--;
           }
        }
        for (int i = 0; i < 3; i++) {
            player.printText(player.getPrinter().getFieldsStringBuilder(true).toString());
            player.printText("Расстановка двухпалубных кораблей ("+(i+1)+"/3)");
            if(!player.getMyField().put(AreaType.SHIP, Sizes.TWO,player.getCoordinates(), player.getOrientation())){
                player.printText("Неправильная расстановка");
                i--;
            }
        }

        for (int i = 0; i < 2; i++) {
            player.printText(player.getPrinter().getFieldsStringBuilder(true).toString());
            player.printText("Расстановка трехпалубных кораблей ("+(i+1)+"/2)");
            if(!player.getMyField().put(AreaType.SHIP, Sizes.THREE,player.getCoordinates(), player.getOrientation())){
                player.printText("Неправильная расстановка");
                i--;
            }
        }

        for (int i = 0; i < 1; i++) {
            player.printText(player.getPrinter().getFieldsStringBuilder(true).toString());
            player.printText("Расстановка четырехпалубного корабля ("+(i+1)+"/1)");
            if(!player.getMyField().put(AreaType.SHIP, Sizes.FOUR,player.getCoordinates(), player.getOrientation())){
                player.printText("Неправильная расстановка");
                i--;
            }
        }
        player.printText(player.getPrinter().getFieldsStringBuilder(true).toString());

    }

    private void printMenu() {
        System.out.println("SEA BATTLE \nMENU\n" +
                "\n1 - Player vs Player (Server)" +
                "\n2 - Player vs Player (Client)" +
                "\n3 - Player vs Computer"+
                "\n4 - Computer vs Computer");
        scanMenuCmd();
    }

    private void scanMenuCmd() {
        String cmd = scanner.nextLine();
        int c = 0;
        try {
            c = Integer.parseInt(cmd);
        } catch (Exception e) {
            scanMenuCmd();
        }
        switch (c) {
            case 1:
                ai1 = false;
                ai2 = false;
                Connector.getInstance().initServer();
                game = true;
                remote = false;
                break;
            case 2:
                ai1 = false;
                ai2 = false;
                System.out.println("Введите ip сервера>");
                Connector.getInstance().connect(scanner.nextLine());
                game = true;
                remote = true;
                break;
            case 3:
                ai1 = false;
                ai2 = true;
                Connector.getInstance().initAiSocket();
                Connector.getInstance().initServer();
                game = true;
                break;
            case 4:
                ai2 = true;
                ai1 = true;
                System.out.println("Введите длительность задержки ходов (мс)>");
                delay = scanner.nextInt();
                Connector.getInstance().initAiSocket();
                Connector.getInstance().initServer();
                game = true;
                break;
            default:
                scanMenuCmd();
        }
    }
}
