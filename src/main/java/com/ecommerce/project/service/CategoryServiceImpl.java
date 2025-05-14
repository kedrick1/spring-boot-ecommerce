package com.ecommerce.project.service;

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

//    @Override
//    public String deleteCategory(Long categoryId) {
//        Optional<Category> result = categoryRepository.findById(categoryId);
//
//        if (result.isPresent()) {
//            categoryRepository.deleteById(categoryId);
//            return "Category with category id " + categoryId + " deleted successfully";
//
//        }else {
//                throw new ResponseStatusException(
//                        HttpStatus.NOT_FOUND,
//                        String.format("Category with id %s not found", categoryId)
//                );
//        }
//    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Category with id %s not found", categoryId)
                ));

        categoryRepository.delete(category);
        return "Category with category id " + categoryId + " deleted successfully";
    }
    ///✅ Why This Is Better
    /// Eliminates if-else:
    ///
    /// Optional.orElseThrow() is a concise and expressive way to handle missing values.
    ///
    /// Avoids extra Optional variable:
    ///
    /// You directly get the entity or throw an exception.
    ///
    /// Avoids calling deleteById:
    ///
    /// By using delete(entity) instead of deleteById(id), you also ensure:
    ///
    /// It won't silently do nothing if the entity doesn't exist.
    ///
    /// It plays nicer with JPA if there are cascading rules or associations.


    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> result = categoryRepository.findById(categoryId);

        if (result.isPresent()) {
            Category existingCategory = result.get();
            existingCategory.setCategoryName(category.getCategoryName());

            // Save the updated entity back to the DB
            return categoryRepository.save(existingCategory);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Category with id %s not found", categoryId)
            );
        }
    }


//    private List<Category> categories = new ArrayList<>();
//    private Long nextId = 1L;
//
//    @Override
//    public List<Category> getAllCategories() {
//        return categories;
//    }
//
//    @Override
//    public void createCategory(Category category) {
//
//        category.setCategoryId(nextId++);
//        categories.add(category);
//    }
//
//    @Override
//    public String deleteCategory(Long categoryId) {
//        //find category
//        Category category = categories.stream().
//                filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %s not found", categoryId)));
//
//        //not needed since not returning null anymore
////        if (category == null) {
////            return "Category with category id " + categoryId + " not found";
////        }
//
//        categories.remove(category);
//        return "Category with category id " + categoryId + " deleted successfully";
//    }
//
//    @Override
//    public Category updateCategory(Category category, Long categoryId) {
//        Optional<Category> optionalCategory = categories.stream().
//                filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst();
//
//        if (optionalCategory.isPresent()) {
//            Category existingCategory = optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            return existingCategory;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %s not found", categoryId));
//        }
//
//    }
}
