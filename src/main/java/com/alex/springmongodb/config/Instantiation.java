package com.alex.springmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.alex.springmongodb.domain.Post;
import com.alex.springmongodb.domain.User;
import com.alex.springmongodb.dto.AuthorDTO;
import com.alex.springmongodb.dto.CommentDTO;
import com.alex.springmongodb.repositories.PostRepository;
import com.alex.springmongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	@Override
	public void run(String... args) throws Exception {
		postRepository.deleteAll();
		userRepository.deleteAll();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post pos1 = new Post(null, sdf.parse("21/03/2018"), "Partiu sp", "vo pra sp", new AuthorDTO(maria));
		Post pos2 = new Post(null, sdf.parse("11/05/2024"), "Enchentes no rs", "Doem por favor!", new AuthorDTO(alex));
		CommentDTO c1 = new CommentDTO("boa viagem",sdf.parse("22/03/2018"),new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("aproveite !",sdf.parse("23/03/2018"),new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("poxa :(",sdf.parse("12/05/2024"),new AuthorDTO(maria));
		
		pos1.getComments().addAll(Arrays.asList(c1,c2));
		pos2.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(pos1,pos2));
		
		maria.addPost(pos1);
		alex.addPost(pos2);
		userRepository.saveAll(Arrays.asList(maria,alex));
	}

}
