package dragonb.bearfamily.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dragonb.bearfamily.backend.model.Category;
import dragonb.bearfamily.backend.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository categoryRepository;

    public Category getCategory(Category category) throws Exception{
        Optional<Category> resultCategory = categoryRepository.findById(category.getId());
        if(!resultCategory.isPresent()){
            throw new Exception();
        }
        else{
            return resultCategory.get();
        }
    }

    public List<Category> getCategorys() throws Exception{
        return categoryRepository.findAll();
    }

    public Category postCategory(Category category) throws Exception{
        return categoryRepository.save(category);
    }

    public Category putCategory(Category category) throws Exception{
        Optional<Category> resultCategory = categoryRepository.findById(category.getId());
        if(!resultCategory.isPresent()){
            throw new Exception();
        }

        Category saveCategory = resultCategory.get();
        if(category.getName() != null) saveCategory.setName(category.getName());
        if(category.getColor() != null) saveCategory.setColor(category.getColor());
        
        Category test = categoryRepository.save(saveCategory);

        return test;
    }

    public void deleteCategory(Category category) throws Exception{
        categoryRepository.deleteById(category.getId());
    }
}
