package com.ecommerce.project.service;

import com.ecommerce.project.dto.CategoryDTO;
import com.ecommerce.project.dto.CategoryResponse;
import com.ecommerce.project.exception.CategoryAlreadyExistsException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse; //wrapper class containing list of category dtos object
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory != null) {
            throw new CategoryAlreadyExistsException("Category with the name " + categoryDTO.getCategoryName() + " already exists!");//method not provided
        }
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryDTO.class);
    }


    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Category", "CategoryId", categoryId
                ));

        categoryRepository.delete(category);
        return modelMapper.map(category, CategoryDTO.class);
    }


    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {

        Optional<Category> result = categoryRepository.findById(categoryId);

        if (result.isPresent()) {
            Category existingCategory = result.get();
            existingCategory.setCategoryName(categoryDTO.getCategoryName());

            // Save the updated entity back to the DB
            categoryRepository.save(existingCategory);
            return modelMapper.map(existingCategory, CategoryDTO.class);
        } else {
            throw new ResourceNotFoundException(
                    "Category", "CategoryId", categoryId
            );
        }
    }

}
