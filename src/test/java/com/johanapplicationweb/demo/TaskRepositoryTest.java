package com.johanapplicationweb.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TaskRepositoryTest {

	@Autowired
	private TaskRepository repo;
	
	@Autowired
	private CategoryRepository repoCat;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateTask() {
		
		Category category = repoCat.findCategoryById(1L);
		
				
		Task task = new Task();
		task.setTitle("Faire la vaisselle");
		task.setDescription("Faire la vaisselle");
		task.setId_user(3L);
		task.setCategory(category);
				
		Task savedTask = repo.save(task);
		
		Task existTask = entityManager.find(Task.class, savedTask.getId());
		
		assertThat(existTask.getTitle()).isEqualTo(task.getTitle());
	}
}
