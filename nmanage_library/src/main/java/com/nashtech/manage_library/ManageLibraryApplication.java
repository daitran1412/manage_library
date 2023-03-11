package com.nashtech.manage_library;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.nashtech.manage_library.Entity.Reader.User;
import com.nashtech.manage_library.service.UserService;

@SpringBootApplication
public class ManageLibraryApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ManageLibraryApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// User user = new User();
		// user.setUsername("John");
		// user.setEmail("mail123@gmail.com");
		// user.setPhone("123456789");
		// user.setAddress("Ha Noi");
		// userService.createUser(user);

		// User user1 = new User();
		// user1.setUsername("Jane");
		// user1.setEmail("chicken123@gmail.com");
		// user1.setPhone("987654321");
		// user1.setAddress("TP HCM");
		// userService.createUser(user1);
		// userService.deleteUser(1L);

	}

}
