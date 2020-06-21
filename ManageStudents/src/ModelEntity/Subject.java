package ModelEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject implements Serializable {
    @Id
    @Column(name = "Id")
    private String Id;

    @Column(name = "SubjectName")
    private String SubjectName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "subjectSch")
    private Set<Schedule> Schedules = new HashSet<Schedule>(0);

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "subjectTrans")
    private Set<Transcript> transcripts = new HashSet<Transcript>(0);

    public Set<Transcript> getTranscripts() {
        return transcripts;
    }

    public void setTranscripts(Set<Transcript> transcripts) {
        this.transcripts = transcripts;
    }

    public Set<Schedule> getSchedules() {
        return Schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        Schedules = schedules;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }
}
