package main;

import algorithms.TextEditorCommand;
import java.util.Scanner;

public class MainAlgorithmB {
    public static void main(String[] args) {
        TextEditorCommand editor = new TextEditorCommand();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n[Algorithm B - Command/Delta Method]");
            System.out.println("Text: \"" + editor.getText() + "\"");
            System.out.println("1: Insert | 2: Delete | 3: Replace | 4: Undo | 5: Redo | 0: Exit");
            System.out.print("Select: ");

            if (!scanner.hasNextInt()) {
                System.out.println("❌ เลือกผิด! ตัวอย่างที่ถูก: 1");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            if (choice == 0) break;

            switch (choice) {
                case 1 -> {
                    System.out.print("Insert (Pos Text): ");
                    if (scanner.hasNextInt()) {
                        int pos = scanner.nextInt();
                        String text = scanner.next();
                        editor.insert(pos, text);
                    } else {
                        System.out.println("❌ พิมพ์ผิด! ตัวอย่างที่ถูก: 0 Hello");
                        scanner.next();
                    }
                }
                case 2 -> {
                    System.out.print("Delete (Pos Length): ");
                    if (scanner.hasNextInt()) {
                        int pos = scanner.nextInt();
                        if (scanner.hasNextInt()) {
                            int len = scanner.nextInt();
                            editor.delete(pos, len);
                        } else {
                            System.out.println("❌ พิมพ์ผิด! ตัวอย่างที่ถูก: 0 5");
                            scanner.next();
                        }
                    } else {
                        System.out.println("❌ พิมพ์ผิด! ตัวอย่างที่ถูก: 0 5");
                        scanner.next();
                    }
                }
                case 3 -> {
                    System.out.print("Replace (Pos Length Text): ");
                    if (scanner.hasNextInt()) {
                        int pos = scanner.nextInt();
                        if (scanner.hasNextInt()) {
                            int len = scanner.nextInt();
                            String text = scanner.next();
                            editor.replace(pos, len, text);
                        } else {
                            System.out.println("❌ พิมพ์ผิด! ตัวอย่างที่ถูก: 0 5 World");
                            scanner.next();
                        }
                    } else {
                        System.out.println("❌ พิมพ์ผิด! ตัวอย่างที่ถูก: 0 5 World");
                        scanner.next();
                    }
                }
                case 4 -> System.out.println(editor.undo() ? "Undo Success" : "Undo Failed (Stack Empty)");
                case 5 -> System.out.println(editor.redo() ? "Redo Success" : "Redo Failed (Stack Empty)");
                default -> System.out.println("❌ เลือกผิด! กรุณากด 0 - 5");
            }
        }
        scanner.close();
    }
}
