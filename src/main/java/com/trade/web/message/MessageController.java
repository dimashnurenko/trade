package com.trade.web.message;

import com.trade.domain.chat.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Controller
@RequestMapping("/message")
public class MessageController {
	private final EntityManagerFactory entityManagerFactory;

	public MessageController(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@PostMapping
	public ResponseEntity create() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		Message message = new Message();
		message.setContent("content");

		entityManager.persist(message);
		entityManager.getTransaction().commit();

		return ResponseEntity.ok(message.getId());
	}

	@PutMapping
	public ResponseEntity update(@RequestParam Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();

		Message message = entityManager.find(Message.class, id);
		message.setContent("content4");

		entityManager.persist(message);
		entityManager.getTransaction().commit();

		return ResponseEntity.ok().build();
	}
}
