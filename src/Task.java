public class Task {
    private int id;
    private int priority;
    private String description;
    private boolean completed;

    public Task(int id, int priority, String description) {
        this.id = id;
        this.priority = priority;
        this.description = description;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        return status + " Task #" + id + " (Priority: " + priority + "): " + description;
    }
}
