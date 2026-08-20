public class Todo {
    protected String description;
    protected boolean isDone;


    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void changeStatusIcon(){ this.isDone = !isDone; }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[]"); // mark done task with X
    }
    public String getTaskDescription(){
        return this.getTaskType() + this.getStatusIcon() + " " + description + "\n";
        //print task
    }

    public String getTaskType(){return "[T]";}
    //public String markTask();
    //public String unmarkTask();

}
