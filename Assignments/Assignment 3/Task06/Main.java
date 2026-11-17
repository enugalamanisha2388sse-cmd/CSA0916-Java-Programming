import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

class Wallet {
    final int id; int balance = 1000; final ReentrantLock lock = new ReentrantLock();
    Wallet(int id) { this.id=id; }
}
class Transfers {
    static void transfer(Wallet a, Wallet b, int amount) {
        Wallet first = a.id < b.id ? a : b, second = a.id < b.id ? b : a;
        first.lock.lock(); second.lock.lock();
        try { if (a.balance >= amount) { a.balance -= amount; b.balance += amount; } }
        finally { second.lock.unlock(); first.lock.unlock(); }
    }
}
class TokenCounter {
    private final int[] buffer = new int[5];
    private int count = 0, in = 0, out = 0;
    public synchronized void put(int token) throws InterruptedException {
        while (count == buffer.length) wait();
        buffer[in] = token; in = (in + 1) % buffer.length; count++;
        notifyAll();
    }
    public synchronized int take() throws InterruptedException {
        while (count == 0) wait();
        int token = buffer[out]; out = (out + 1) % buffer.length; count--;
        notifyAll(); return token;
    }
}
public class Main {
    public static void main(String[] args) throws Exception {
        Wallet a = new Wallet(1), b = new Wallet(2);
        Thread t1 = new Thread(() -> { for (int i=0;i<1000;i++) Transfers.transfer(a,b,1); });
        Thread t2 = new Thread(() -> { for (int i=0;i<1000;i++) Transfers.transfer(b,a,1); });
        t1.start(); t2.start(); t1.join(3000); t2.join(3000);
        System.out.println("Transfers completed without deadlock: " + (!t1.isAlive() && !t2.isAlive()));
        TokenCounter counter = new TokenCounter(); int[] collected = new int[2];
        Thread cook = new Thread(() -> { try { for (int t=1;t<=20;t++) counter.put(t); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); } });
        Thread[] students = new Thread[2];
        for (int s=0;s<2;s++) { final int id=s; students[s]=new Thread(() -> {
            try { for (int k=0;k<10;k++) collected[id] += counter.take(); }
            catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }); }
        cook.start(); for (Thread t:students) t.start();
        cook.join(); for (Thread t:students) t.join();
        System.out.println("Sum of tokens collected: " + (collected[0]+collected[1]) + " (expected 210)");
    }
}