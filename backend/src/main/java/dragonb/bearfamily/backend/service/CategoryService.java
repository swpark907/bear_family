package dragonb.bearfamily.backend.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dragonb.bearfamily.backend.model.Category;
import dragonb.bearfamily.backend.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository categoryRepository;

    private String userIdentity;

    public Category getCategory(Category category) throws Exception{
        Optional<Category> resultCategory = categoryRepository.findById(category.getId());
        if(!resultCategory.isPresent()){
            throw new Exception();
        }
        else{
            return resultCategory.get();
        }
    }

    public List<Category> getCategorys(HttpServletRequest request) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        return categoryRepository.findAllByUserIdentityOrUserIdentityIsNull(userIdentity);
    }

    public Category postCategory(Category category, HttpServletRequest request) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        category.setUserIdentity(userIdentity);
        return categoryRepository.save(category);
    }

    public Category putCategory(Category category, HttpServletRequest request) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        Optional<Category> resultCategory = categoryRepository.findById(category.getId());
        if(!resultCategory.isPresent()){
            throw new Exception();
        }

        Category saveCategory = resultCategory.get();
        if(saveCategory.getUserIdentity() != userIdentity){
            throw new Exception();
        }

        if(category.getName() != null) saveCategory.setName(category.getName());
        if(category.getColor() != null) saveCategory.setColor(category.getColor());
        
        Category test = categoryRepository.save(saveCategory);

        return test;
    }

    public void deleteCategory(Category category, HttpServletRequest request) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        categoryRepository.deleteByIdAndUserIdentity(category.getId(), userIdentity);
    }
}
