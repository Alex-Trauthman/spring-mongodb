package com.alex.springmongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.springmongodb.domain.Post;
import com.alex.springmongodb.exceptions.ObjectNotFoundException;
import com.alex.springmongodb.repositories.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;

	public Post findById(String id) {
		Optional<Post> post = repository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	public List<Post> findByTitle(String title){
		return repository.findByTitleContainingIgnoreCase(title);
	}
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repository.fullSearch(text, minDate, maxDate);
	}

}
