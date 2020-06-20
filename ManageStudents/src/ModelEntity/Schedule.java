package ModelEntity;

public class Schedule {

    private int Id;
    private Class ClassSch;
    private Subject SubjectSch;
    private String Room;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Class getClassSch() {
        return ClassSch;
    }

    public void setClassSch(Class classSch) {
        ClassSch = classSch;
    }

    public Subject getSubjectSch() {
        return SubjectSch;
    }

    public void setSubjectSch(Subject subjectSch) {
        SubjectSch = subjectSch;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

}
