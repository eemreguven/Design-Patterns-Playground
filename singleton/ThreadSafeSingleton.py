import threading
from time import sleep

class ThreadSafeSingleton:
    _instance = None
    # lock object to synchronize threads during the first initialization
    _lock = threading.Lock()

    def __init__(self, data):
        # prevent direct instantiation from outside the class
        if ThreadSafeSingleton._instance is not None:
            raise Exception("This class is a singleton.")
        self.data = data

    @classmethod
    def get_instance(cls, data):
        # avoid acquiring the lock if the instance is already initialized
        if cls._instance is None:
            with cls._lock:  # synchronize threads
                # ensure only one thread creates the instance
                if cls._instance is None:
                    cls._instance = cls(data)
        return cls._instance

    def get_data(self):
        return self.data

def test_thread_safe_singleton():
    def create_singleton_instance(data):
        try:
            singleton = ThreadSafeSingleton.get_instance(data)
            print(f"Instance created with data: {singleton.get_data()}")
        except Exception as e:
            print(e)

    # Create two threads attempting to initialize the singleton with different data
    thread1 = threading.Thread(target=create_singleton_instance, args=("Thread 1 Data",))
    thread2 = threading.Thread(target=create_singleton_instance, args=("Thread 2 Data",))

    # Start the threads
    thread1.start()
    thread2.start()

    # Wait for threads to complete
    thread1.join()
    thread2.join()

    # Verify that the singleton instance is consistent
    singleton = ThreadSafeSingleton.get_instance("Main Data")
    print(f"Main thread singleton data: {singleton.get_data()}")

# Run the test
if __name__ == "__main__":
    test_thread_safe_singleton()