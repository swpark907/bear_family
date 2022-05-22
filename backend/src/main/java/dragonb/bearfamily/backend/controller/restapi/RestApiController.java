package dragonb.bearfamily.backend.controller.restapi;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.model.Response;

@RestController
@RequestMapping("/restapi")
@RequiredArgsConstructor
public class RestApiController {

    @PostMapping("/existToken")
    public Response existToken() { // 회원 추가
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("토큰 등록 완료");
        } catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("토큰 등록 실패");
        }
        return response;
    }

}