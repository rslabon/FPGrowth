package org.algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Transaction> transactions = Stream.of("A B C D F H", "A D F", "C D F", "B H", "A C F G H", "C D E G", "A C D I")
                .map(line -> Arrays.stream(line.split("\\s+")).toList())
                .map(Transaction::new)
                .toList();
//        List<Transaction> transactions = new ArrayList<>();
//        transactions.add(new Transaction(List.of("E", "K", "M", "N", "O", "Y")));
//        transactions.add(new Transaction(List.of("D", "E", "K", "N", "O", "Y")));
//        transactions.add(new Transaction(List.of("A", "E", "K", "M")));
//        transactions.add(new Transaction(List.of("C", "K", "M", "U", "Y")));
//        transactions.add(new Transaction(List.of("C", "E", "I", "O", "K")));
//        transactions.add(new Transaction(List.of("C", "K", "I")));
//        for (int i = 0; i < 6 * 1000; i++) {
//            List<String> items = List.of(
//                    "A", "B", "C", "D",
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString(),
//                    UUID.randomUUID().toString(), UUID.randomUUID().toString()
//            );
//            items = new ArrayList<>(items);
//            Collections.shuffle(items);
//            transactions.add(new Transaction(items));
//        }
        long start = System.currentTimeMillis();
        System.err.println("Patterns:");
        System.err.println(FPGrowth.findPatterns(3, 2, 10, transactions));
        long end = System.currentTimeMillis();
        System.err.println("COMPLETED IN " + (end - start) + " [ms]");
    }
}
