package models;

public class ActionCommand {
    public enum ActionType { INSERT, DELETE, REPLACE }

    private int actionId;
    private ActionType type;
    private int position;
    private String oldText;
    private String newText;
    private long timestamp;

    public ActionCommand(int actionId, ActionType type, int position, String oldText, String newText) {
        this.actionId = actionId;
        this.type = type;
        this.position = position;
        this.oldText = oldText;
        this.newText = newText;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters
    public ActionType getType() { return type; }
    public int getPosition() { return position; }
    public String getOldText() { return oldText; }
    public String getNewText() { return newText; }
}