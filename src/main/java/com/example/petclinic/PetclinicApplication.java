package com.example.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class PetclinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetclinicApplication.class, args);
	}

	@Controller
	public class HomeController {

		@GetMapping("/")
		public String home() {
			return "home";
		}


        @GetMapping("/about")
        public String about() {
            return "about";
        }
	}

}
