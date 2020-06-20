package ModelEntity;

import java.util.HashSet;
import java.util.Set;

public class Class {

    private String Id;
    private String ClassName;
    private Set<Student> Students = new HashSet<Student>(0);
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
