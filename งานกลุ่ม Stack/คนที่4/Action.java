public class Action {
    public enum Type { INSERT, DELETE, REPLACE }
    
    public String id;
    public Type type;
    public int position;
    public String oldText;
    public String newText;

    public Action(String id, Type type, int position, String oldText, String newText) {
        this.id = id;
        this.type = type;
        this.position = position;
        this.oldText = oldText;
        this.newText = newText;
    }
}