package models;

import java.time.LocalDateTime;

public class Action {
    public enum Type {
        INSERT, DELETE, REPLACE
    }

    private String id;
    private Type type;
    private int position;
    private String oldText;
    private String newText;
    private LocalDateTime timestamp;

    public Action(String id, Type type, int position, String oldText, String newText) {
        this.id = id;
        this.type = type;
        this.position = position;
        this.oldText = oldText;
        this.newText = newText;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public int getPosition() {
        return position;
    }

    public String getOldText() {
        return oldText;
    }

    public String getNewText() {
        return newText;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}