import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorCommand {
    private StringBuilder document = new StringBuilder();
    private Deque<Action> undoStack = new ArrayDeque<>();
    private Deque<Action> redoStack = new ArrayDeque<>();
    private int actionCount = 0;

    public void insert(int pos, String text) {
        if (pos < 0) return;
        if (pos > document.length()) {
            document.setLength(0);
            pos = 0;
        }
        document.insert(pos, text);
        Action act = new Action("A" + (++actionCount), Action.Type.INSERT, pos, "", text);
        undoStack.push(act);
        redoStack.clear();
    }

    public void delete(int pos, int length) {
        if (pos < 0 || pos + length > document.length() || length <= 0) return;
        String deletedText = document.substring(pos, pos + length);
        document.delete(pos, pos + length);
        Action act = new Action("A" + (++actionCount), Action.Type.DELETE, pos, deletedText, "");
        undoStack.push(act);
        redoStack.clear();
    }

    public void replace(int pos, int length, String newText) {
        if (pos < 0 || pos + length > document.length()) return;
        String oldText = document.substring(pos, pos + length);
        document.replace(pos, pos + length, newText);
        Action act = new Action("A" + (++actionCount), Action.Type.REPLACE, pos, oldText, newText);
        undoStack.push(act);
        redoStack.clear();
    }

    public boolean undo() {
        if (undoStack.isEmpty()) return false;
        Action act = undoStack.pop();
        applyInverse(act);
        redoStack.push(act);
        return true;
    }

    public boolean redo() {
        if (redoStack.isEmpty()) return false;
        Action act = redoStack.pop();
        applyForward(act);
        undoStack.push(act);
        return true;
    }

    private void applyForward(Action act) {
        if (act.getType() == Action.Type.INSERT) {
            document.insert(act.getPosition(), act.getNewText());
        } else if (act.getType() == Action.Type.DELETE) {
            document.delete(act.getPosition(), act.getPosition() + act.getOldText().length());
        } else if (act.getType() == Action.Type.REPLACE) {
            document.replace(act.getPosition(), act.getPosition() + act.getOldText().length(), act.getNewText());
        }
    }

    private void applyInverse(Action act) {
        if (act.getType() == Action.Type.INSERT) {
            document.delete(act.getPosition(), act.getPosition() + act.getNewText().length());
        } else if (act.getType() == Action.Type.DELETE) {
            document.insert(act.getPosition(), act.getOldText());
        } else if (act.getType() == Action.Type.REPLACE) {
            document.replace(act.getPosition(), act.getPosition() + act.getNewText().length(), act.getOldText());
        }
    }

    public String getText() {
        return document.toString();
    }
}