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
    private Class classSch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SubjectId", referencedColumnName = "Id")
    private Subject subjectSch;

    @Column(name = "Room")
    private String Room;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Class getClassSch() {
        return classSch;
    }

    public void setClassSch(Class classSch) {
        classSch = classSch;
    }

    public Subject getSubjectSch() {
        return subjectSch;
    }

    public void setSubjectSch(Subject subjectSch) {
        subjectSch = subjectSch;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

}
