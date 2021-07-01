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
import sg.edu.iss.caps.student.StudentService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
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
    private StudentService sservice;

    @Autowired
    CourseRepository crepo;

    @Autowired
    private CourseInterface cservice;

    @Autowired
    public void setEnrolmentService(EnrolmentServiceImpl eserviceImpl) {this.eservice = eserviceImpl; }


    @GetMapping(value="")
    public String listCourseEnrol(Model model) {
    	
    ArrayList<String> clist = cservice.findAllCourseNames(); // new added line for modal
    model.addAttribute("coursenames", clist); // new added line for modal
    model.addAttribute("courseenrol", new CourseEnrolment()); // new added line for modal
    
    List<CourseEnrolment> elist = eservice.findAllEnrolment();
    //update status if enrolment full
    elist.stream().forEach(x -> {
        if(eservice.findStudentsByEnrol(x).size() == x.getCapacity()) {
            x.setStatus(Status.FULL);
        }
    });
    //update status to ongoing if past start date
    LocalDate today = LocalDate.now(ZoneId.of("Asia/Singapore")) ;
    elist.stream().forEach(x -> {
        if(today.isAfter(x.getStartDate()) && today.isBefore(x.getEndDate())  ) {
            x.setStatus(Status.ONGOING);
        }
    });
    //update status to complete if past end date
    elist.stream().forEach(x -> {
        if(today.isAfter(x.getEndDate())) {
            x.setStatus(Status.COMPLETE);
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

        Map<CourseEnrolment, List<Student>> studentList = new HashMap<CourseEnrolment, List<Student>>();
        elist.stream().forEach(x -> {
            List<Student> students = eservice.findStudentsByEnrol(x);
            studentList.put(x, students);
        });
        model.addAttribute("studentList", studentList);

    return "course-enrol/course-enrol";
    }

    @GetMapping(value = "/add")
    public String addCourseEnrol(Model model) {
        model.addAttribute("enrol", new CourseEnrolment());
        ArrayList<String> clist = cservice.findAllCourseNames();
        model.addAttribute("cnames", clist);
        model.addAttribute("func", "add");
        return "course-enrol/course-enrol-form";
    }
    
    
    

    @PostMapping(value="/save")
    public String saveCourseEnrol(@ModelAttribute @Valid CourseEnrolment enrol, BindingResult result) {
        if (result.hasErrors())
            return "redirect:/admin/enrol";
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
        return "course-enrol/course-enrol-form";
    }

    @GetMapping("/addstudent/{id}")
    public String addStudent(@PathVariable("id") int id, Model model) {
        CourseEnrolment enrol = eservice.findEnrolmentById(id);
        model.addAttribute("coursename",enrol.getCourse().getName());
        model.addAttribute("enrolid", id);
        List<Student> students = sservice.findAllStudent();
        List<Student> existingstudents = eservice.findStudentsByEnrol(enrol);


        List<String> studentnames = new ArrayList<String>();
        for(Student student:students){
            if(!existingstudents.contains(student)) {
                studentnames.add(student.getFirstName());
            }
        }
        model.addAttribute("studentnames", studentnames);
        return "course-enrol/enrol-student-form";
    }

    @GetMapping(value="/addstudent/save")
    public String enrolStudent(@RequestParam("studentname") String studentname, @RequestParam int enrolid) {
//        if (result.hasErrors())
//            return "redirect:/admin/enrol";

        Student student = sservice.findStudentByFirstName(studentname);
        CourseEnrolment enrol = eservice.findEnrolmentById(enrolid);
        sservice.setEnrol(enrol, student);
        return "redirect:/admin/enrol";
    }
}