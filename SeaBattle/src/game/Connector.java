package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Connector {
    private volatile static Connector instance;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ExceptionCallback exceptionCallback;
    private Socket socket;
    private volatile Socket aiSocket;
    public static final int PORT = 7788;
    void initServer(){
        System.out.println("Включаем сервер - порт "+PORT);
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setSoTimeout(20000);
        } catch (IOException e) {
            exceptionCallback.call("Ошибка создания сервера");
            return;
        }
        System.out.println("Ожидаю подключения...");
        try {
            socket = serverSocket.accept();
        } catch (SocketTimeoutException ee){
            exceptionCallback.call("Время ожидания подключение истекло");
            return;
        } catch (IOException e) {
            e.printStackTrace();
            exceptionCallback.call("Ошибка подключения пользователя");
            return;
        }
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            exceptionCallback.call("");
        }

        System.out.println("Подключение установлено");
    }

    void connect(String ip){
        try {
            socket = new Socket(ip,PORT);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            exceptionCallback.call("Ошибка подключения к серверу");
            return;
        }
        System.out.println("Подключение установлено");
    }

    public static Connector getInstance() {
        if (instance == null)
            instance = new Connector();
        return instance;
    }

    String getNextLineFromSocket(){
        try {
            return dataInputStream.readUTF();
        } catch (IOException e) {
            exceptionCallback.call("Ошибка чтения данных из сокета");
            return null;
        }
    }

    void printTextToSocket(String text){
        try {
            dataOutputStream.writeUTF(text);
        } catch (IOException e) {
            exceptionCallback.call("Ошибка записи данных в сокет");
        }
    }

    void setExceptionCallback(ExceptionCallback callback){
        exceptionCallback = callback;
    }

    void closeAll(){
        try {
            dataOutputStream.close();
        } catch (IOException ignored) {}
        try {
            dataInputStream.close();
        } catch (IOException ignored) {}
        try {
            socket.close();
        } catch (IOException ignored) {}
        try{
            aiSocket.close();
        } catch (IOException ignored) {}
    }

    public void initAiSocket(){
        new Thread(()-> {
            try {
                Thread.sleep(400);
                aiSocket = new Socket("localhost", PORT);
            } catch (IOException e) {
                exceptionCallback.call("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ).start();
    }
}
