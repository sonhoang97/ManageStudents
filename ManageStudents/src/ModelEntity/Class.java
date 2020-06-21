package ModelEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "class")
public class Class implements Serializable {

    @Id
    @Column(name = "Id")
    private String Id;

    @Column(name = "ClassName")
    private String ClassName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "classStd")
    private Set<Student> Students = new HashSet<Student>(0);

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "classSch")
    private Set<Schedule> Schedules = new HashSet<Schedule>(0);

    public Set<Schedule> getSchedules() {
        return Schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        Schedules = schedules;
    }


    public Set<Student> getStudents() {
        return Students;
    }

    public void setStudents(Set<Student> students) {
        this.Students = students;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }
}
