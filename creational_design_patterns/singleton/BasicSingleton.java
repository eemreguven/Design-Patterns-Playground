package singleton;

public class BasicSingleton {
    private static BasicSingleton instance;
    private String data;

    private BasicSingleton(String data) {
        this.data = data;
    }

    public static BasicSingleton getInstance(String data) {
        if (instance == null) {
            instance = new BasicSingleton(data);
        }
        return instance;
    }

    public String getData() {
        return data;
    }
}