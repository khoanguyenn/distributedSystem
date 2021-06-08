package Peer2Peer;

import java.io.Serializable;
import java.util.List;

public class StudentListWrapper implements Serializable {
    private List<Student> studentList;
    public StudentListWrapper(List<Student> studentList) {
        this.studentList = studentList;
    }
    public List<Student> getList() {
        return this.studentList;
    }
}
