import java.util.Stack;

public class SnapshotTextEditor {

    static Stack<String> undoStack = new Stack<>();

    static Stack<String> redoStack = new Stack<>();

    static String currentText = "";

    static void insert(int position, String newText) {
        saveSnapshot();

        currentText = currentText.substring(0, position)
                + newText
                + currentText.substring(position);

        redoStack.clear();
    }

    static void delete(int position, int length) {
        saveSnapshot();

        currentText = currentText.substring(0, position)
                + currentText.substring(position + length);

        redoStack.clear();
    }

    static void replace(int position, int length, String newText) {
        saveSnapshot();

        currentText = currentText.substring(0, position)
                + newText
                + currentText.substring(position + length);

        redoStack.clear();
    }

    static void saveSnapshot() {
        undoStack.push(currentText);
    }

    static void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText);
            currentText = undoStack.pop();
        }
    }

    static void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText);
            currentText = redoStack.pop();
        }
    }

    public static void main(String[] args) {

        currentText = "HelloWorld";

        System.out.println("Original: " + currentText);

        insert(5, "AI");
        System.out.println("After INSERT: " + currentText);

        undo();
        System.out.println("After UNDO: " + currentText);

        redo();
        System.out.println("After REDO: " + currentText);

        delete(5, 2);
        System.out.println("After DELETE: " + currentText);

        replace(5, 5, "Java");
        System.out.println("After REPLACE: " + currentText);
    }
}