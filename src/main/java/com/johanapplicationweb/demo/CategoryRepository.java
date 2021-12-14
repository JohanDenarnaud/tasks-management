package com.johanapplicationweb.demo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;




public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query("SELECT c FROM Category c WHERE c.id = ?1")
	Category findCategoryById(Long categoryId);
	
	@Query("SELECT c FROM Category c WHERE c.id_user = ?1")
	List <Category>findAllCategoryByIdUser(Long idUser);

	@Query("SELECT c FROM Category c WHERE c.title = ?1 AND c.id_user = ?2")
	Category findCategoryByTitle(String titleCategory, Long IdUser);
	
}