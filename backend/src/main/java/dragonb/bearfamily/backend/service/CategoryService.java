package dragonb.bearfamily.backend.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dragonb.bearfamily.backend.model.category.Category;
import dragonb.bearfamily.backend.model.category.CategoryDTO;
import dragonb.bearfamily.backend.repository.CategoryRepository;

@Service
public class CategoryService {
    
    @Autowired
    CategoryRepository categoryRepository;

    private String userIdentity;

    public Category getCategory(Long id, HttpServletRequest request) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        Optional<Category> resultCategory = categoryRepository.findById(id);
        
        if(!resultCategory.isPresent()){
            throw new Exception();
        }
        else{
            if(!resultCategory.get().getUserIdentity().equals(userIdentity)){
                throw new Exception();
            }
            else{
                return resultCategory.get();
            }
        }
    }

    public List<Category> getCategorys(HttpServletRequest request) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        return categoryRepository.findAllByUserIdentityOrUserIdentityIsNull(userIdentity);
    }

    public Category postCategory(CategoryDTO categoryDTO, HttpServletRequest request) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        return categoryRepository.save(Category.builder()
        .userIdentity(userIdentity)
        .name(categoryDTO.getName())
        .color(categoryDTO.getColor())
        .build());
    }

    public Category putCategory(CategoryDTO categoryDTO, HttpServletRequest request, Long id) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        Optional<Category> resultCategory = categoryRepository.findById(id);
        if(!resultCategory.isPresent()){
            throw new Exception();
        }

        Category saveCategory = resultCategory.get();
        if(!saveCategory.getUserIdentity().equals(userIdentity)){
            throw new Exception();
        }

        if(categoryDTO.getName() != null) saveCategory.setName(categoryDTO.getName());
        if(categoryDTO.getColor() != null) saveCategory.setColor(categoryDTO.getColor());
        
        Category test = categoryRepository.save(saveCategory);

        return test;
    }

    public void deleteCategory(Long id, HttpServletRequest request) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);

        Optional<Category> targetCategory = categoryRepository.findById(id);

        if(!targetCategory.isPresent()){
            throw new Exception();
        }
        else{
            if(!targetCategory.get().getUserIdentity().equals(userIdentity)){
                throw new Exception();
            }
            else{
                categoryRepository.deleteByIdAndUserIdentity(id, userIdentity);
            }
        }

        if(categoryRepository.findById(id).isPresent()){
            throw new Exception();
        }
    }
}
