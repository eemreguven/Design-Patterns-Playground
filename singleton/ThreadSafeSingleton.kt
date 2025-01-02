package singleton

class ThreadSafeSingleton private constructor(private val data: String) {

    companion object {
        @Volatile
        private var instance: ThreadSafeSingleton? = null

        fun getInstance(data: String): ThreadSafeSingleton {
            var result = instance

            if (result == null) {
                synchronized(this) {
                    result = instance
                    if (result == null) {
                        result = ThreadSafeSingleton(data)
                        instance = result
                    }
                }
            }
            return result!!
        }
    }

    fun getData(): String = data
}

// kotlin simple alternative with lazy delegate
object LazySingleton {
    val data: String by lazy { "Singleton Data" }
}

fun main() {
    testThreadSafeSingleton()
    testLazySingleton()
}

fun testThreadSafeSingleton() {
    // Create two threads to test thread-safe initialization
    val thread1 = Thread {
        val singleton1 = ThreadSafeSingleton.getInstance("Thread 1 Data")
        println("Thread 1 Singleton Data: ${singleton1.getData()}")
    }

    val thread2 = Thread {
        val singleton2 = ThreadSafeSingleton.getInstance("Thread 2 Data")
        println("Thread 2 Singleton Data: ${singleton2.getData()}")
    }

    // Start the threads
    thread1.start()
    thread2.start()

    // Wait for threads to complete
    thread1.join()
    thread2.join()

    // Verify that the singleton instance is consistent across all threads
    val mainThreadSingleton = ThreadSafeSingleton.getInstance("Main Data")
    println("Main Thread Singleton Data: ${mainThreadSingleton.getData()}")
}

fun testLazySingleton() {
    // Access the lazy singleton
    println("LazySingleton Data: ${LazySingleton.data}")
}
