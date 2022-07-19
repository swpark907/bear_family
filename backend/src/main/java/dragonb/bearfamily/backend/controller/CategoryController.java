package dragonb.bearfamily.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.model.category.Category;
import dragonb.bearfamily.backend.model.category.CategoryDTO;
import dragonb.bearfamily.backend.model.common.Response;
import dragonb.bearfamily.backend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/category")
@Tag(name = "Category API", description = "카테고리 관련 기능")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;

    @Operation(summary = "category get method", description = "카테고리 한 건의 정보를 조회합니다.")
    @GetMapping("/item/{id}")
    public Response getCategory(@PathVariable Long id, HttpServletRequest request){
        Response response = new Response();

        try {
            Category resultCategory = categoryService.getCategory(id, request);

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

    @Operation(summary = "categories get method", description = "카테고리 여러 건의 정보를 조회합니다."
    +"<br/> 아이디가 설정되어 있지 않은 공용 카테고리와, 로그인한 아이디가 설정된 개인 카테고리가 조회됩니다.")
    @GetMapping("/items")
    public Response getCategorys(HttpServletRequest request){
        Response response = new Response();

        try {
            List<Category> resultCategorys = categoryService.getCategorys(request);
            
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

    @Operation(summary = "category post method", description = "카테고리 한 건의 정보를 등록합니다.")
    @PostMapping("/item")
    public Response postCategory(@RequestBody CategoryDTO categoryDTO, HttpServletRequest request){
        Response response = new Response();

        try {
            Category resultCategory = categoryService.postCategory(categoryDTO, request);

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

    @Operation(summary = "category put method", description = "카테고리 한 건의 정보를 수정합니다.")
    @PutMapping("/item/{id}")
    public Response putCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long id, HttpServletRequest request){
        Response response = new Response();
        
        try {
            Category resultCategory = categoryService.putCategory(categoryDTO, id, request);

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
    
    @Operation(summary = "category delete method", description = "카테고리 한 건의 정보를 삭제합니다.")
    @DeleteMapping("/item/{id}")
    public Response deleteCategory(@PathVariable Long id, HttpServletRequest request){
        Response response = new Response();

        try {
            categoryService.deleteCategory(id, request);

            response.setResponse("success");
            response.setMessage("success delete category");
            response.setData(true);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail delete category");
            response.setData(null);
        }
        return response;
    }
}
