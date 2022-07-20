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

import dragonb.bearfamily.backend.model.common.Response;
import dragonb.bearfamily.backend.model.terms.Terms;
import dragonb.bearfamily.backend.model.terms.TermsDTO;
import dragonb.bearfamily.backend.service.TermsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/terms")
@Tag(name = "Terms API", description = "약관 관련 기능")
public class TermsController {
    
    @Autowired
    TermsService termsService;

    @Operation(summary = "terms get method", description = "약관 한 건의 정보를 조회합니다.")
    @GetMapping("/item/{id}")
    public Response getTerms(@PathVariable Long id){
        Response response = new Response();

        try {
            Terms resultTerms = termsService.getTerms(id);

            response.setResponse("success");
            response.setMessage("success get terms");
            response.setData(resultTerms);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail get terms");
            response.setData(null);
        }
        return response;
    }

    @Operation(summary = "all terms get method", description = "약관 여러 건의 정보를 조회합니다.")
    @GetMapping("/items")
    public Response getTermss(){
        Response response = new Response();

        try {
            List<Terms> resultTermss = termsService.getTermss();
            
            response.setResponse("success");
            response.setMessage("success get termss");
            response.setData(resultTermss);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail get termss");
            response.setData(null);
        }
        return response;
    }

    @Operation(summary = "terms post method", description = "약관 한 건의 정보를 등록합니다."
    +"<br/> 약관을 관리하려면 관리자 권한이 필요합니다.")
    @PostMapping("/item")
    public Response postTerms(@RequestBody TermsDTO termsDTO, HttpServletRequest request){
        Response response = new Response();

        try {
            Terms resultTerms = termsService.postTerms(termsDTO, request);

            response.setResponse("success");
            response.setMessage("success post terms");
            response.setData(resultTerms);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail post terms");
            response.setData(null);
        }
        return response;
    }

    @Operation(summary = "terms put method", description = "약관 한 건의 정보를 수정합니다."
    +"<br/> 약관을 관리하려면 관리자 권한이 필요합니다.")
    @PutMapping("/item/{id}")
    public Response putTerms(@RequestBody TermsDTO termsDTO, @PathVariable Long id, HttpServletRequest request){
        Response response = new Response();
        
        try {
            Terms resultTerms = termsService.putTerms(termsDTO, id, request);

            response.setResponse("success");
            response.setMessage("success put terms");
            response.setData(resultTerms);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail put terms");
            response.setData(null);
        }
        return response;
    }
    
    @Operation(summary = "terms delete method", description = "약관 한 건의 정보를 삭제합니다."
    +"<br/> 약관을 관리하려면 관리자 권한이 필요합니다.")
    @DeleteMapping("/item/{id}")
    public Response deleteTerms(@PathVariable Long id, HttpServletRequest request){
        Response response = new Response();

        try {
            termsService.deleteTerms(id, request);

            response.setResponse("success");
            response.setMessage("success delete terms");
            response.setData(true);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail delete terms");
            response.setData(null);
        }
        return response;
    }
}
