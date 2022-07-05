package dragonb.bearfamily.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.model.Category;
import dragonb.bearfamily.backend.model.Response;
import dragonb.bearfamily.backend.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;

    @GetMapping("/item")
    public Response getCategory(@ModelAttribute Category category){
        Response response = new Response();

        try {
            Category resultCategory = categoryService.getCategory(category);

            response.setResponse("success");
            response.setMessage("success get category");
            response.setData(resultCategory);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail get category");
            response.setData(null);
        }
        return response;
    }

    @GetMapping("/items")
    public Response getCategorys(){
        Response response = new Response();

        try {
            List<Category> resultCategorys = categoryService.getCategorys();
            
            response.setResponse("success");
            response.setMessage("success get categorys");
            response.setData(resultCategorys);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail get categorys");
            response.setData(null);
        }
        return response;
    }

    @PostMapping("/item")
    public Response postCategory(@RequestBody Category category){
        Response response = new Response();

        try {
            Category resultCategory = categoryService.postCategory(category);

            response.setResponse("success");
            response.setMessage("success post category");
            response.setData(resultCategory);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail post category");
            response.setData(null);
        }
        return response;
    }

    @PutMapping("/item")
    public Response putCategory(@RequestBody Category category){
        Response response = new Response();
        
        try {
            Category resultCategory = categoryService.putCategory(category);

            response.setResponse("success");
            response.setMessage("success put category");
            response.setData(resultCategory);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail put category");
            response.setData(null);
        }
        return response;
    }
    
    @DeleteMapping("/item")
    public Response deleteCategory(@RequestBody Category category){
        Response response = new Response();

        try {
            categoryService.deleteCategory(category);

            response.setResponse("success");
            response.setMessage("success delete category");
            response.setData(category);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail delete category");
            response.setData(null);
        }
        return response;
    }
}
