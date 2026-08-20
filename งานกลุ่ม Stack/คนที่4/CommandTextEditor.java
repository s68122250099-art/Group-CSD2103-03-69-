package algorithms;

import models.ActionCommand;
import models.ActionCommand.ActionType;
import utils.MyStack;

public class CommandTextEditor {
    private StringBuilder document = new StringBuilder();
    private MyStack<ActionCommand> undoStack = new MyStack<>();
    private MyStack<ActionCommand> redoStack = new MyStack<>();
    private int actionCounter = 0;

    public void insert(int pos, String text) {
        if (pos < 0 || pos > document.length()) throw new IndexOutOfBoundsException("Invalid position");
        ActionCommand cmd = new ActionCommand(++actionCounter, ActionType.INSERT, pos, "", text);
        document.insert(pos, text);
        undoStack.push(cmd);
        redoStack.clear();
    }

    public void delete(int pos, int length) {
        if (pos < 0 || pos + length > document.length()) throw new IndexOutOfBoundsException("Invalid position or length");
        String deletedText = document.substring(pos, pos + length);
        ActionCommand cmd = new ActionCommand(++actionCounter, ActionType.DELETE, pos, deletedText, "");
        document.delete(pos, pos + length);
        undoStack.push(cmd);
        redoStack.clear();
    }

    public void replace(int pos, int length, String newText) {
        if (pos < 0 || pos + length > document.length()) throw new IndexOutOfBoundsException("Invalid position or length");
        String oldText = document.substring(pos, pos + length);
        ActionCommand cmd = new ActionCommand(++actionCounter, ActionType.REPLACE, pos, oldText, newText);
        document.replace(pos, pos + length, newText);
        undoStack.push(cmd);
        redoStack.clear();
    }

    public boolean undo() {
        if (undoStack.isEmpty()) return false;
        ActionCommand cmd = undoStack.pop();
        applyInverse(cmd);
        redoStack.push(cmd);
        return true;
    }

    public boolean redo() {
        if (redoStack.isEmpty()) return false;
        ActionCommand cmd = redoStack.pop();
        applyForward(cmd);
        undoStack.push(cmd);
        return true;
    }

    private void applyInverse(ActionCommand cmd) {
        switch (cmd.getType()) {
            case INSERT:
                document.delete(cmd.getPosition(), cmd.getPosition() + cmd.getNewText().length());
                break;
            case DELETE:
                document.insert(cmd.getPosition(), cmd.getOldText());
                break;
            case REPLACE:
                document.replace(cmd.getPosition(), cmd.getPosition() + cmd.getNewText().length(), cmd.getOldText());
                break;
        }
    }

    private void applyForward(ActionCommand cmd) {
        switch (cmd.getType()) {
            case INSERT:
                document.insert(cmd.getPosition(), cmd.getNewText());
                break;
            case DELETE:
                document.delete(cmd.getPosition(), cmd.getPosition() + cmd.getOldText().length());
                break;
            case REPLACE:
                document.replace(cmd.getPosition(), cmd.getPosition() + cmd.getOldText().length(), cmd.getNewText());
                break;
        }
    }

    public String getDocument() { return document.toString(); }
    public MyStack<ActionCommand> getUndoStack() { return undoStack; }
}