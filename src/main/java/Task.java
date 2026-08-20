public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void changeStatusIcon(){ this.isDone = !isDone; }
    public String getStatusIcon() {
        return (isDone ? "[ X ]" : "[  ]"); // mark done task with X
    }
    public String getTaskDescription(){
        return this.getStatusIcon() + " " + description + "\n";
    }
    //public String markTask();
    //public String unmarkTask();

}
