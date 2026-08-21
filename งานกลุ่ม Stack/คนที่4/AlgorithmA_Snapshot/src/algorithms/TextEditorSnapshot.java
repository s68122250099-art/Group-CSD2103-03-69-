package algorithms;

import models.Snapshot;
import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorSnapshot {
    private StringBuilder document = new StringBuilder();
    private Deque<Snapshot> undoStack = new ArrayDeque<>();
    private Deque<Snapshot> redoStack = new ArrayDeque<>();

    public void insert(int pos, String text) {
        if (pos < 0 || pos > document.length())
            return;
        saveState();
        document.insert(pos, text);
        redoStack.clear();
    }

    public void delete(int pos, int length) {
        if (pos < 0 || pos + length > document.length() || length <= 0)
            return;
        saveState();
        document.delete(pos, pos + length);
        redoStack.clear();
    }

    public void replace(int pos, int length, String newText) {
        if (pos < 0 || pos + length > document.length())
            return;
        saveState();
        document.replace(pos, pos + length, newText);
        redoStack.clear();
    }

    private void saveState() {
        undoStack.push(new Snapshot(document.toString()));
    }

    public boolean undo() {
        if (undoStack.isEmpty())
            return false;
        redoStack.push(new Snapshot(document.toString()));
        Snapshot previous = undoStack.pop();
        document = new StringBuilder(previous.getTextState());

        // พิมพ์เวลาที่ย้อนกลับไปออกมาดู
        System.out.println("⏪ Restored to state from: " + previous.getTimestamp());
        return true;
    }

    public boolean redo() {
        if (redoStack.isEmpty())
            return false;
        undoStack.push(new Snapshot(document.toString()));
        Snapshot next = redoStack.pop();
        document = new StringBuilder(next.getTextState());
        return true;
    }

    public String getText() {
        return document.toString();
    }

}
