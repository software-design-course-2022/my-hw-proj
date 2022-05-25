package ru.java.hse.sd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.java.hse.sd.model.Manager;
import ru.java.hse.sd.model.view.AttemptView;
import ru.java.hse.sd.model.view.HomeworkView;
import ru.java.hse.sd.model.view.SubmissionView;

import java.util.List;

/**
 * Controller class that responds to student actions (requests). Works with HTML.
 **/
@Controller
@RequestMapping(path = "student")
public class StudentHtmlController {
    private Manager manager;

    /**
     * Creates new instance of StudentHtmlController object.
     * Creates new Manager instance in it.
     **/
    StudentHtmlController() {
        manager = new Manager();
    }

    /**
     * Setter for manager. It is used only in tests
     * @param manager manager
     */
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    /**
     * Returns list of homeworks in the correct order.
     *
     * @param model model
     * @return page with list of homeworks
     **/
    @GetMapping("/homeworks")
    public String homeworks(Model model) {
        List<HomeworkView> homeworks = manager.homeworks();
        model.addAttribute("homeworks", homeworks);
        return "homeworks";
    }

    /**
     * Returns list of results, sorted by due date.
     *
     * @param model model
     * @return page with list of results
     **/
    @GetMapping("/results")
    public String results(Model model) {
        List<AttemptView> attempts = manager.results();
        model.addAttribute("attempts", attempts);
        return "student_results";
    }

    /**
     * Returns result which corresponds passed id.
     *
     * @param id    id
     * @param model model
     * @return result page
     **/
    @GetMapping("/results/{id}")
    public String results(@PathVariable Integer id, Model model) {
        AttemptView attempt = manager.results().get(id);
        model.addAttribute("attempt", attempt);
        return "student_result";
    }

    /**
     * Mostly for testing.
     **/
    @GetMapping("/welcome")
    public String welcome(@RequestParam(name = "name", required = false, defaultValue = "Student") String name,
                          Model model) {
        model.addAttribute("name", name);
        return "welcome";
    }

    /**
     * Returns student main menu.
     *
     * @return student main menu page
     **/
    @GetMapping("/")
    public String showStudentMenu() {
        return "student_menu";
    }

    /**
     * Returns submission which corresponds passed id.
     *
     * @return submission page
     **/
    @GetMapping("/submit/{id}")
    public String showSubmission(@PathVariable String id, Model model) {
        Submission submission = new Submission();
        submission.setHomeworkId(id);
        model.addAttribute("submission", submission);
        return "submit";
    }

    /**
     * Submits submission.
     *
     * @return submission page
     * @throws Exception in case of error
     **/
    @PostMapping("/submit")
    public String submitSubmission(@ModelAttribute Submission submission, Model model)
            throws Exception {
        model.addAttribute("submission", submission);
        manager.submit(new SubmissionView(submission.getHomeworkId(), submission.getSolutionUrl()));
        return "submit_result";
    }
}
