package sg.edu.iss.caps.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sg.edu.iss.caps.enrolment.EnrolmentService;
import sg.edu.iss.caps.enrolment.EnrolmentServiceImpl;
import sg.edu.iss.caps.model.Course;
import sg.edu.iss.caps.model.CourseEnrolment;
import sg.edu.iss.caps.model.Status;
import sg.edu.iss.caps.viewcourse.CourseInterface;
import sg.edu.iss.caps.viewcourse.CourseRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/enrol")
public class ManageEnrolmentController {
    @Autowired
    private EnrolmentService eservice;

    @Autowired
    CourseRepository crepo;

    @Autowired
    private CourseInterface cservice;

    @Autowired
    public void setEnrolmentService(EnrolmentServiceImpl eserviceImpl) {this.eservice = eserviceImpl; }

    public ManageEnrolmentController() {
        //empty constructor
    }

    @GetMapping(value="")
    public String listCourseEnrol(Model model) {
    List<CourseEnrolment> elist = eservice.findAllEnrolment();
    model.addAttribute("elist", elist);
    ArrayList<Course> courses = (ArrayList<Course>) crepo.findAll();
    model.addAttribute("courses", courses);
    model.addAttribute("enrol", new CourseEnrolment()); //used for add course enrol modal in view
    return "course-enrol";
    }

    @PostMapping(value="/save")
    public String saveCourse(@ModelAttribute @Valid CourseEnrolment enrol, BindingResult result) {
        if(result.hasErrors()) {return "course-enrol";}
        Course course = crepo.findCourseByName(enrol.getCourse().getName());
        enrol.setCourse(course);
        eservice.updateEnrolment(enrol);
//        if (result.hasErrors()) {
//            return "course-enrol";
//        }
//        Course course = crepo.findCourseByName(enrol.getCourse().getName());
//        eservice.CreateEnrolment(new CourseEnrolment(course, enrol.getStartDate(), enrol.getEndDate(),
//                enrol.getCapacity(), enrol.getStatus()));
        return "redirect:/enrol";
    }

    @GetMapping("/delete/{id}")
    public String deleteMethod(@PathVariable("id") Integer id) {
        eservice.cancelEnrol(id);
        return "redirect:/admin/course";
    }

//    @RequestMapping(value = "")
//    public String list(Model model) {
//        List<CourseEnrolment> courseenrol = eservice.findAllEnrolment();
//        model.addAttribute("courseenrol", courseenrol);
//        return "course-enrol";
//    }

//    @RequestMapping(value = "/open")
//    public String openEnrolment(Model model) {
//        model.addAttribute("enrolment", new CourseEnrolment());
//        ArrayList<String> clist = cservice.findAllCourseNames();
//        model.addAttribute("cnames", clist);
//        return "open-course";
//    }
//
//    @RequestMapping(value = "/save")
//    public String saveEnrolCourse(@ModelAttribute("enrolment") @Valid CourseEnrolment enrolment, BindingResult bindingResult, Model model) {
//        if(bindingResult.hasErrors()) {
//            return "open-course";
//        }
//        Course course = cservice.findCourseByName(enrolment.getCourse().getName());
//        course = cservice.findCourseById(course.getId());
//        enrolment.setCourse(course);
//        enrolment.setStatus(Status.AVAILABLE);
//        eservice.CreateEnrolment(enrolment);
//        return "redirect:/admin/course/enrol/";
//    }

//    @RequestMapping(value = "/close/{id}")
//    public String closeEnrolment(@PathVariable("id") Integer id) {
//        eservice.cancelEnrol(eservice.findCourseEnrolmentById(id));
//        return "redirect:/admin/course/enrol";
//    }


}
