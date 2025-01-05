package singleton;

public class ThreadSafeSingleton {
    // volatile keyword ensures that instance variable is read directly from
    // main memory, not from a thread's local cache
    private static volatile ThreadSafeSingleton instance;

    private String data;

    private ThreadSafeSingleton(String data) {
        this.data = data;
    }

    public static ThreadSafeSingleton getInstance(String data) {
        // minimize repeated access to the volatile instance variable
        ThreadSafeSingleton result = instance;

        // avoid synchronization once the instance is initialized
        if (result == null) {
            // synchronize only the first time to ensure thread-safe initialization
            synchronized (ThreadSafeSingleton.class) {
                // recheck if instance is null to handle potential race conditions
                result = instance;
                if (result == null) {
                    instance = result = new ThreadSafeSingleton(data);
                }
            }
        }
        return result;
    }

    public String getData() {
        return data;
    }
}