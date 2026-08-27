public class Todo {
    protected String description;
    protected boolean isDone;


    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Todo(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
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
    // convert any task into format ready for storage in the file, can js do as a regular desc, then
    public String toFileFormat() {
        // 1 for done, 0 for undone
        int isDone = this.isDone ? 1 : 0;
        return "T | " + isDone + " | " + this.description;
    }

}
