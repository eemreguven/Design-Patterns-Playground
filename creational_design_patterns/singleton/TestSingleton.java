package singleton;

public class TestSingleton {
    public static void main(String[] args) {
        testBasicSingleton(); // Test the non-thread-safe Singleton
        System.out.println();
        testThreadSafeSingleton(); // Test the thread-safe Singleton
    }

    public static void testBasicSingleton() {
        // Create the first instance
        BasicSingleton singleton1 = BasicSingleton.getInstance("Hello, Singleton!");
        System.out.println("Singleton 1 Data: " + singleton1.getData());

        // Attempt to create a second instance with different data
        BasicSingleton singleton2 = BasicSingleton.getInstance("New Data");
        System.out.println("Singleton 2 Data: " + singleton2.getData());

        // Check if both references point to the same instance
        System.out.println("Are both instances the same? " + (singleton1 == singleton2));
    }

    public static void testThreadSafeSingleton() {
        // Create two threads to test thread-safe initialization
        Thread thread1 = new Thread(() -> {
            ThreadSafeSingleton singleton1 = ThreadSafeSingleton.getInstance("Thread 1 Data");
            System.out.println("Thread 1 Singleton Data: " + singleton1.getData());
        });

        Thread thread2 = new Thread(() -> {
            ThreadSafeSingleton singleton2 = ThreadSafeSingleton.getInstance("Thread 2 Data");
            System.out.println("Thread 2 Singleton Data: " + singleton2.getData());
        });

        // Start the threads
        thread1.start();
        thread2.start();

        // Wait for threads to complete execution
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that the singleton instance is consistent across all threads
        ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance("Main Data");
        System.out.println("Main Thread Singleton Data: " + singleton.getData());
    }
}