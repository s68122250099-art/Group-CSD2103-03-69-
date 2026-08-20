import algorithms.CommandTextEditor;
import algorithms.SnapshotTextEditor;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testing Algorithm B: Command/Delta Method ===");
        CommandTextEditor editor = new CommandTextEditor();

        // 1. Insert Text
        editor.insert(0, "HelloWorld");
        System.out.println("After Insert: " + editor.getDocument());

        // 2. Insert "Al" at position 5 (ตามตัวอย่างในโจทย์)
        editor.insert(5, "Al");
        System.out.println("After Insert 'Al' at 5: " + editor.getDocument());

        // 3. Undo Test
        long startTime = System.nanoTime();
        editor.undo();
        long endTime = System.nanoTime();
        System.out.println("After Undo: " + editor.getDocument());
        System.out.println("Undo Time: " + (endTime - startTime) + " ns");

        // 4. Redo Test
        editor.redo();
        System.out.println("After Redo: " + editor.getDocument());

        // 5. Operation Counter Check
        System.out.println("Push Ops: " + editor.getUndoStack().getPushCount());
        System.out.println("Pop Ops: " + editor.getUndoStack().getPopCount());
    }
}