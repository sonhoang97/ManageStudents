package ModelEntity;

import javax.persistence.*;

@Entity
@Table(name = "transcript")
public class Transcript {
    @Id
    @Column(name = "Id")
    private int id;

    @Column(name = "MidTerm")
    private double midTerm;

    @Column(name = "EndTerm")
    private double endTerm;

    @Column(name = "Bonus")
    private double bonus;

    @Column(name = "Point")
    private double point;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Mssv", referencedColumnName = "Mssv")
    private Student studentTrans;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClassId", referencedColumnName = "Id")
    private Class classTrans;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SubjectId", referencedColumnName = "Id")
    private Subject subjectTrans;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMidTerm() {
        return midTerm;
    }
    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
    public void setMidTerm(double midTerm) {
        this.midTerm = midTerm;
    }

    public double getEndTerm() {
        return endTerm;
    }

    public void setEndTerm(double endTerm) {
        this.endTerm = endTerm;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public Student getStudentTrans() {
        return studentTrans;
    }

    public void setStudentTrans(Student studentTrans) {
        this.studentTrans = studentTrans;
    }

    public Class getClassTrans() {
        return classTrans;
    }

    public void setClassTrans(Class classTrans) {
        this.classTrans = classTrans;
    }

    public Subject getSubjectTrans() {
        return subjectTrans;
    }

    public void setSubjectTrans(Subject subjectTrans) {
        this.subjectTrans = subjectTrans;
    }


}
