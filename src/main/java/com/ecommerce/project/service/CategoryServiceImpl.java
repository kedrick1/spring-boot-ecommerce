package com.ecommerce.project.service;

import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }


    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category", "CategoryId", categoryId
                ));

        categoryRepository.delete(category);
        return "Category with category id " + categoryId + " deleted successfully";
    }


    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> result = categoryRepository.findById(categoryId);

        if (result.isPresent()) {
            Category existingCategory = result.get();
            existingCategory.setCategoryName(category.getCategoryName());

            // Save the updated entity back to the DB
            return categoryRepository.save(existingCategory);
        } else {
            throw new ResourceNotFoundException(
                    "Category", "CategoryId", categoryId
            );
        }
    }

}
