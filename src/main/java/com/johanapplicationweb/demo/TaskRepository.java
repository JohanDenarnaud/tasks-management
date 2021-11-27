package com.johanapplicationweb.demo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface TaskRepository extends JpaRepository<Task, Long> {
	@Query("SELECT t FROM Task t WHERE t.id_user = ?1")
	List<Task> findUserTasksById(Long userId);

	@Transactional
	@Modifying
	@Query("DELETE FROM Task WHERE id = ?1")
	void deleteTaskById(Long idTask);

	@Query("SELECT t FROM Task t WHERE t.id = ?1")
	Task findTaskById(Long idTask);

	@Transactional
	@Modifying
	@Query("UPDATE Task SET title = ?2, category = ?3, description = ?4 WHERE id = ?1")
	void updateTaskById(Long idTask, String title, String category, String description);
}