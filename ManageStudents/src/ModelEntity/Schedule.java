package ModelEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

    @Id
    @Column(name = "Id")
    private int Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClassId", referencedColumnName = "Id")
    private Class ClassSch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SubjectId", referencedColumnName = "Id")
    private Subject SubjectSch;

    @Column(name = "Room")
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
