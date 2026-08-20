package algorithms;

import utils.MyStack;

public class SnapshotTextEditor {
    private StringBuilder document = new StringBuilder();
    private MyStack<String> undoStack = new MyStack<>();
    private MyStack<String> redoStack = new MyStack<>();

    public void insert(int pos, String text) {
        if (pos < 0 || pos > document.length()) throw new IndexOutOfBoundsException("Invalid position");
        saveState();
        document.insert(pos, text);
    }

    public void delete(int pos, int length) {
        if (pos < 0 || pos + length > document.length()) throw new IndexOutOfBoundsException("Invalid position or length");
        saveState();
        document.delete(pos, pos + length);
    }

    public void replace(int pos, int length, String newText) {
        if (pos < 0 || pos + length > document.length()) throw new IndexOutOfBoundsException("Invalid position or length");
        saveState();
        document.replace(pos, pos + length, newText);
    }

    private void saveState() {
        undoStack.push(document.toString());
        redoStack.clear(); // Clear Redo Stack เมื่อมี Actionใหม่
    }

    public boolean undo() {
        if (undoStack.isEmpty()) return false;
        redoStack.push(document.toString());
        document = new StringBuilder(undoStack.pop());
        return true;
    }

    public boolean redo() {
        if (redoStack.isEmpty()) return false;
        undoStack.push(document.toString());
        document = new StringBuilder(redoStack.pop());
        return true;
    }

    public String getDocument() { return document.toString(); }
    public MyStack<String> getUndoStack() { return undoStack; }
}