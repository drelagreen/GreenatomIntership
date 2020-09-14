public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Thread(Main::asyncMethod).start();
        while(true){
            System.out.println("Работает основная программа");
            Thread.sleep(1000);
        }
    }

    static void asyncMethod(){
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignored) {
            }
            System.out.println("Асинхронный привет!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            }
            System.out.println("Асинхронный пока!");
        }
    }
}
