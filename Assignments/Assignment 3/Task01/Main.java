import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Main {
    static class InsufficientFundsException extends RuntimeException {
        InsufficientFundsException(String m) { super(m); }
    }
    static void withdraw(String account, String amountText, double balance, Path cardFile) {
        System.out.println("\nInput: account=" + account + ", amount=" + amountText);
        try {
            if (!Files.exists(cardFile)) throw new IOException("Card-reader file missing: " + cardFile);
            int accountNo = Integer.parseInt(account);
            double amount = Double.parseDouble(amountText);
            if (accountNo <= 0) throw new IllegalArgumentException("Account number must be positive");
            if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
            if (amount > balance) throw new InsufficientFundsException("Insufficient balance");
            System.out.println("Cash dispensed: " + amount);
        } catch (NumberFormatException e) {
            System.out.println("Number format error: enter numeric values");
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } catch (InsufficientFundsException | IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        } finally {
            System.out.println("ATM session cleanup completed");
        }
    }
    public static void main(String[] args) throws Exception {
        Path card = Files.createTempFile("atm-card-reader", ".dat");
        withdraw("12345", "500", 1000, card);
        withdraw("abc", "500", 1000, card);
        withdraw("12345", "-20", 1000, card);
        withdraw("12345", "1500", 1000, card);
        withdraw("12345", "100", 1000, card.resolveSibling("missing-card-reader.dat"));
        Files.deleteIfExists(card);
    }
}