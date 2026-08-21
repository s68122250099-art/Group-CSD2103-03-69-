public class Action {
    public enum Type { INSERT, DELETE, REPLACE }
    
    private String id;
    private Type type;
    private int position;
    private String oldText;
    private String newText;

    public Action(String id, Type type, int position, String oldText, String newText) {
        this.id = id;
        this.type = type;
        this.position = position;
        this.oldText = oldText;
        this.newText = newText;
    }

    public String getId() { return id; }
    public Type getType() { return type; }
    public int getPosition() { return position; }
    public String getOldText() { return oldText; }
    public String getNewText() { return newText; }
}