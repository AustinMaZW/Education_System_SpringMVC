package sg.edu.iss.caps.enrolment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseEnrolment;
import sg.edu.iss.caps.model.Status;
import sg.edu.iss.caps.model.Student;
import sg.edu.iss.caps.student.StudentRepository;
import sg.edu.iss.caps.viewcourse.CourseRepository;

public class EnrolmentServiceImpl implements EnrolmentService {
    @Autowired
    EnrolRepository erepo;
    @Autowired
    StudentRepository srepo;
    @Autowired
    CourseRepository crepo;

    @Override
    @Transactional
    public List<CourseEnrolment> findAllEnrolment() {
        List<CourseEnrolment> list = erepo.findAll();
        return list;
    }

    @Override
    @Transactional
    public List<CourseEnrolment> findEnrolmentByStartDate(LocalDate start) {
        List<CourseEnrolment> list = erepo.findAll();
        List<CourseEnrolment> query = new ArrayList<>();
        list.stream().filter(x -> x.getStartDate().isEqual(start)).forEach(x -> query.add(x));
        return query;
    }

    @Override
    @Transactional
    public List<CourseEnrolment> findEnrolmentByEndDate(LocalDate end) {
        List<CourseEnrolment> list = erepo.findAll();
        List<CourseEnrolment> query = new ArrayList<>();
        list.stream().filter(x -> x.getEndDate().isEqual(end)).forEach(x -> query.add(x));
        return query;
    }

    @Override
    @Transactional
    public List<CourseEnrolment> findEnrolmentByDuration(LocalDate start, LocalDate end) {
        List<CourseEnrolment> list = erepo.findAll();
        List<CourseEnrolment> query = new ArrayList<>();
        list.stream()
                .filter(x -> (x.getEndDate().isBefore(end) || x.getEndDate().isEqual(end))
                        && (x.getStartDate().isAfter(start) || x.getStartDate().isEqual(start)))
                .forEach(x -> query.add(x));
        return query;
    }

    @Override
    @Transactional
    public List<CourseEnrolment> findEnrolmentByCourse(Course course) {
        List<CourseEnrolment> list = erepo.findEnrolmentByCourse(course.getId());
        return list;
    }

    @Override
    @Transactional
    public List<CourseEnrolment> findEnrolmentByStudent(Student student) {
        Student stu = srepo.findById(student.getId()).get();
        List<CourseEnrolment> enroList = new ArrayList<>(stu.getGrades().keySet());
        return enroList;
    }

    @Override
    @Transactional
    public boolean UpdateEnrolment(CourseEnrolment enrol) {
        CourseEnrolment curr = erepo.findById(enrol.getId()).get();
        if (curr == null)
            return false;
        curr.setCapacity(enrol.getCapacity());
        curr.setCourse(crepo.findCourseByName(enrol.getCourse().getName()));
        curr.setEndDate(enrol.getEndDate());
        curr.setStartDate(enrol.getStartDate());
        curr.setStatus(enrol.getStatus());
        if (erepo.save(curr) == null)
            return false;
        return true;

    }

    @Override
    @Transactional
    public boolean CreateEnrolment(CourseEnrolment enrol) {
        if (erepo.save(enrol) == null)
            return false;
        return true;

    }

    @Override
    @Transactional
    public void DeleteEnrolment(int id) {
        CourseEnrolment enrol = erepo.findById(id).get();
        erepo.delete(enrol);
    }

    @Override
    public CourseEnrolment findEnrolmentById(int id) {
        return erepo.findById(id).get();
    }

    @Override
    public boolean cancelEnrol(int id) {
        CourseEnrolment enrol = erepo.findById(id).get();
        if (enrol.getStatus() == Status.AVAILABLE)
            return false;
        enrol.setStatus(Status.NOTAVAILABLE);
        erepo.save(enrol);
        return true;

    }
}
