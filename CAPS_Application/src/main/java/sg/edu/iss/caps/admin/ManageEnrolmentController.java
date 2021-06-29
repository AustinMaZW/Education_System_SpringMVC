package sg.edu.iss.caps.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import sg.edu.iss.caps.course.Course;
import sg.edu.iss.caps.course.CourseInterface;
import sg.edu.iss.caps.course.CourseRepository;
import sg.edu.iss.caps.enrolment.CourseEnrolment;
import sg.edu.iss.caps.enrolment.EnrolmentService;
import sg.edu.iss.caps.enrolment.EnrolmentServiceImpl;
import sg.edu.iss.caps.model.Status;
import sg.edu.iss.caps.student.Student;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/enrol")
public class ManageEnrolmentController {
    @Autowired
    private EnrolmentService eservice;

    @Autowired
    CourseRepository crepo;

    @Autowired
    private CourseInterface cservice;

    @Autowired
    public void setEnrolmentService(EnrolmentServiceImpl eserviceImpl) {this.eservice = eserviceImpl; }


    @GetMapping(value="")
    public String listCourseEnrol(Model model) {
    List<CourseEnrolment> elist = eservice.findAllEnrolment();
    elist.stream().forEach(x -> {
        if(eservice.findStudentsByEnrol(x).size() == x.getCapacity()) {
            x.setStatus(Status.FULL);
        }
    });
    model.addAttribute("elist", elist);
    Map<CourseEnrolment, Integer> list = new HashMap<CourseEnrolment, Integer>();
    elist.stream().forEach(x -> {
        List<Student> students = eservice.findStudentsByEnrol(x);
        int numStudents = students.size();
        list.put(x,numStudents);
    });
    model.addAttribute("numStudents", list);
    return "course-enrol";
    }

    @GetMapping(value = "/add")
    public String addCourseEnrol(Model model) {
        model.addAttribute("enrol", new CourseEnrolment());
        ArrayList<String> clist = cservice.findAllCourseNames();
        model.addAttribute("cnames", clist);
        model.addAttribute("func", "add");
        return "open-course";
    }

    @PostMapping(value="/save")
    public String saveCourseEnrol(@ModelAttribute @Valid CourseEnrolment enrol, BindingResult result) {
        if (result.hasErrors())
            return "open-course";
        if (enrol.getId() != 0) {
            eservice.UpdateEnrolment(enrol);
            return "redirect:/admin/enrol";
        }
        System.out.println(enrol.getId());
        Course course = crepo.findCourseByName(enrol.getCourse().getName());

        if(enrol.getCapacity() == 0) {
            eservice.CreateEnrolment(new CourseEnrolment(course, enrol.getStartDate(), enrol.getEndDate(),
                    enrol.getCapacity(), Status.FULL));
        }

        if(enrol.getCapacity() > 0) {
            eservice.CreateEnrolment(new CourseEnrolment(course, enrol.getStartDate(), enrol.getEndDate(),
                    enrol.getCapacity(), Status.AVAILABLE));
        }

        return "redirect:/admin/enrol";
    }

    @GetMapping("/cancel/{id}")
    public String cancel(@PathVariable("id") Integer id) {
        eservice.cancelEnrol(eservice.findEnrolmentById(id));
        return "redirect:/admin/enrol";
    }

    @GetMapping("/delete/{id}")
	public String removeEnrol(@PathVariable("id") int id) {
		if(eservice.findStudentsByEnrol(eservice.findEnrolmentById(id)).size()==0) {
            eservice.DeleteEnrolment(id);
        }
		return "redirect:/admin/enrol";
	}

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        CourseEnrolment enrol = eservice.findEnrolmentById(id);
        model.addAttribute("enrol", enrol);
        ArrayList<Course> courses = (ArrayList<Course>) crepo.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("func", "edit");
        return "open-course";
    }
}
