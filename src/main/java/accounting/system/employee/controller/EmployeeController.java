package accounting.system.employee.controller;

import accounting.system.employee.entity.Employee;
import accounting.system.employee.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("employee", employeeService.findByLogin(authentication.getName()));
        return "homePage";
    }

    @RequestMapping(value = "/employee/photo/{employeeId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> downloadUserAvatarImage(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        return ResponseEntity.ok()
                .contentLength(employee.getPhoto().length)
                .contentType(MediaType.IMAGE_JPEG)
                .body(employee.getPhoto());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/create/employee")
    public String createEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "createEmployee";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/create/employee")
    public String createEmployee(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "createEmployee";
        employeeService.saveNewEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String err, Model model, String logout) {
        if (err != null)
            model.addAttribute("error", "Login or password incorrect");
        if (logout != null)
            model.addAttribute("message", "Logged successful");
        return "login";
    }

    @GetMapping("/search/employee")
    public String search() {
        return "search";
    }

    @PostMapping("/search/employee")
    public String search(@RequestParam("search") String search, Model model) {
        model.addAttribute("employees", employeeService.findAllByName(search));
        return "search";
    }

    @GetMapping("/view/employee/{id}")
    public String viewEmployee(@PathVariable int id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "viewEmployee";
    }

    @GetMapping("/edit/employee")
    public String edit(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("employee", employeeService.findByLogin(authentication.getName()));
        return "edit";
    }

    @PostMapping("/edit/employee")
    public String edit(@ModelAttribute Employee employee, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        employee.setPhoto(imageFile.getBytes());
        employeeService.updateEmployee(employee);
        return "redirect:/";
    }
}
