package models;

import java.time.LocalDateTime;

public class Snapshot {
    private String textState;
    private LocalDateTime timestamp;

    public Snapshot(String textState) {
        this.textState = textState;
        this.timestamp = LocalDateTime.now();
    }

    public String getTextState() {
        return textState;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}