package pl.almma.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import pl.almma.model.User;
import pl.almma.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("/profile")
	public String viewProfile(@ModelAttribute User user, Model model) {

		User loggedUser = userService.findLoggedUser();

		model.addAttribute("user", loggedUser);
		// model.addAttribute("tostring", loggedUser.toString());
		return "users/profile";
	}

	@PostMapping("/saveImage")
	public String handleFileUpload(@RequestPart("file") MultipartFile file, Model model, User user) {
		String status = null;
		User loggedUser = userService.findLoggedUser();
		
		if (!file.isEmpty()) {
			try {
			//	String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			//	String filename = "E://images/" + loggedUser.getPesel()+type;
				
				UUID uuid = UUID.randomUUID();
				String filename = "E://images/" + uuid.toString();
				
				byte[] bytes = file.getBytes();
				File fsFile = new File(filename);
				fsFile.createNewFile();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fsFile));
				stream.write(bytes);
				stream.close();
				
				userService.setprofileImageFileName(loggedUser, uuid.toString());
				
				status = "Plik załadowano poprawnie!";
				

			} catch (Exception e) {
				System.out.println("File has not been uploaded" +" " + e);
				e.printStackTrace();
				status = "Nie udało się załadować pliku!";
			}
		} else {
			System.out.println("Uploaded file is empty");
			status = "Nie udało się załadować pliku!";
		}

		model.addAttribute("user", loggedUser);
		model.addAttribute("status", status);
		return "/users/profile";
	}
	
	@GetMapping("/saveImage")
	public String goToProfile() {
		return "redirect:/users/profile#settings";
	}
	
	

	
}
