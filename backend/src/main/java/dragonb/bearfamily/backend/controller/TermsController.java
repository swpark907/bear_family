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

import dragonb.bearfamily.backend.model.common.Response;
import dragonb.bearfamily.backend.model.terms.Terms;
import dragonb.bearfamily.backend.service.TermsService;

@RestController
@RequestMapping("/api/terms")
public class TermsController {
    
    @Autowired
    TermsService termsService;

    @GetMapping("/item")
    public Response getTerms(@ModelAttribute Terms terms){
        Response response = new Response();

        try {
            Terms resultTerms = termsService.getTerms(terms);

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

    @PostMapping("/item")
    public Response postTerms(@RequestBody Terms terms){
        Response response = new Response();

        try {
            Terms resultTerms = termsService.postTerms(terms);

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

    @PutMapping("/item")
    public Response putTerms(@RequestBody Terms terms){
        Response response = new Response();
        
        try {
            Terms resultTerms = termsService.putTerms(terms);

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
    
    @DeleteMapping("/item")
    public Response deleteTerms(@RequestBody Terms terms){
        Response response = new Response();

        try {
            termsService.deleteTerms(terms);

            response.setResponse("success");
            response.setMessage("success delete terms");
            response.setData(terms);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail delete terms");
            response.setData(null);
        }
        return response;
    }
}
