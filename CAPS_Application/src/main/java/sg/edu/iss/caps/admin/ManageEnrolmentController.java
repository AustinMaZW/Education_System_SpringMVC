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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    model.addAttribute("elist", elist);
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
        if (eservice.CreateEnrolment(new CourseEnrolment(course, enrol.getStartDate(), enrol.getEndDate(),
                enrol.getCapacity(), Status.AVAILABLE)))
            return "redirect:/admin/enrol";
        return "forward:/admin/enrol";
    }

    @GetMapping("/delete/{id}")
    public String deleteMethod(@PathVariable("id") Integer id) {
        eservice.cancelEnrol(eservice.findEnrolmentById(id));
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
