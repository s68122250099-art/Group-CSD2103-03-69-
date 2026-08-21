import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TextEditorCommand editor = new TextEditorCommand();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nText: \"" + editor.getText() + "\"");
            System.out.println("1: Insert | 2: Delete | 3: Replace | 4: Undo | 5: Redo | 0: Exit");
            System.out.print("Select: ");
            int choice = scanner.nextInt();

            if (choice == 0) break;
            switch (choice) {
                case 1 -> {
                    System.out.print("Position & Text: ");
                    editor.insert(scanner.nextInt(), scanner.next());
                }
                case 2 -> {
                    System.out.print("Position & Length: ");
                    editor.delete(scanner.nextInt(), scanner.nextInt());
                }
                case 3 -> {
                    System.out.print("Position, Length & New Text: ");
                    editor.replace(scanner.nextInt(), scanner.nextInt(), scanner.next());
                }
                case 4 -> System.out.println(editor.undo() ? "Undo Done" : "Nothing to Undo");
                case 5 -> System.out.println(editor.redo() ? "Redo Done" : "Nothing to Redo");
            }
        }
        scanner.close();
    }
}