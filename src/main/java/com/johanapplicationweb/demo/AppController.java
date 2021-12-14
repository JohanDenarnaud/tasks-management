package com.johanapplicationweb.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private CategoryRepository repoCat;
	
	@Autowired
	private TaskRepository repoTask;
	
	@Autowired
	private HttpServletRequest request;
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/inscription")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
		
		return "register_success";
	}
	
	@GetMapping("/liste_utilisateurs")
	public String viewUserList(Model model) {
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users_list";
	}

	@GetMapping("/liste_taches")
	public String viewUserTasks(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		Long idUserLong = (Long) session.getAttribute("userId");
 		List<Task> listTasks = repoTask.findUserTasksById(idUserLong);
 		System.out.println(listTasks);
 		
		model.addAttribute("listTasks", listTasks);
		return "user_tasks";
	}
	
	@PostMapping("/deleteTask")
	public String deleteTask(Model model) {
		Long idTask = Long.parseLong(request.getParameter("idTask"));
		repoTask.deleteTaskById(idTask);
		viewUserTasks(model, request);
		return "user_tasks";
	}
	
	@PostMapping("/save_task")
	public String saveTask(Model model, Task task) {
		HttpSession session = request.getSession();
		Long userId = (Long)session.getAttribute("userId");
		task.setId_user(userId);
		
		Category category = new Category();
		if ( request.getParameter("newCategory") != null && !request.getParameter("newCategory").isEmpty()) {
			category.setId_user(userId);
			category.setTitle(request.getParameter("newCategory"));
			repoCat.save(category);
			category = repoCat.findCategoryByTitle(request.getParameter("newCategory"), userId);
		} else {
			category = repoCat.findCategoryById(Long.parseLong(request.getParameter("idCategory")));
		}
		
		task.setCategory(category);		
		repoTask.save(task);
		viewUserTasks(model, request);
		return "user_tasks";
	}
	
	@GetMapping("/update_Form_tache")
	public String showUpdateTaskForm(Model model) {
		
		Long idTask = Long.parseLong(request.getParameter("idTask"));
		Task task = repoTask.findTaskById(idTask);
		model.addAttribute("task", task);
		model.addAttribute("newTask", new Task());
		return "edit_task_form";
	}
	
	@GetMapping("/ajout_tache")
	public String showAddTaskForm(Model model) {
		model.addAttribute("task", new Task());
		HttpSession session = request.getSession();
		Long userId = (Long) session.getAttribute("userId");
		List<Category> listCategoriesUser = repoCat.findAllCategoryByIdUser(userId);
		model.addAttribute("listCategoriesUser", listCategoriesUser);
		return "add_task";
	}
	
	@PostMapping("/update_task")
	public String updateTask(Model model) {
		Long idTask = Long.parseLong(request.getParameter("idTask"));
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		repoTask.updateTaskById(idTask, title, category, description);
		viewUserTasks(model, request);
		return "user_tasks";
	}
}
