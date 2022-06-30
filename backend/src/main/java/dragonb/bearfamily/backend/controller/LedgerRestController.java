package dragonb.bearfamily.backend.controller;

import lombok.RequiredArgsConstructor;

import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import dragonb.bearfamily.backend.model.Ledger;
import dragonb.bearfamily.backend.model.Response;
import dragonb.bearfamily.backend.repository.LedgerRepository;

@RestController
@RequestMapping("/api/ledger")
@RequiredArgsConstructor
public class LedgerRestController {

    @Autowired
    private LedgerRepository ledgerRepository;

    private String getIdentity(HttpServletRequest request){
        String accesstoken = request.getHeader("authorization").substring(7);
        DecodedJWT decodedJWT = JWT.decode(accesstoken);
        return decodedJWT.getSubject();
    }

    @GetMapping("/item")
    public Response getLedger(@ModelAttribute Ledger ledger, HttpServletRequest request){
        Response response = new Response();
        String userIdentity = getIdentity(request);

        try {
            Optional<Ledger> resultLedger = ledgerRepository.findByUserIdentity(userIdentity);
            response.setResponse("success");
            response.setMessage("success get ledger");
            response.setData(resultLedger);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail get ledger");
            response.setData(e.getMessage());
        }
        return response;
    }

    @GetMapping("/items")
    public Response getLedgers(@ModelAttribute Ledger ledger, HttpServletRequest request){
        Response response = new Response();
        String userIdentity = getIdentity(request);

        try {
            List<Ledger> resultLedgers = ledgerRepository.findAllByUserIdentity(userIdentity);
            response.setResponse("success");
            response.setMessage("success get ledgers");
            response.setData(resultLedgers);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail get ledgers");
            response.setData(e.getMessage());
        }
        return response;
    }

    @PostMapping("/item")
    public Response postLedger(@RequestBody Ledger ledger, HttpServletRequest request){
        Response response = new Response();
        String userIdentity = getIdentity(request);

        try {
            ledger.setUserIdentity(userIdentity);
            ledgerRepository.save(ledger);
            response.setResponse("success");
            response.setMessage("success post ledger");
            response.setData(ledger);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail post ledger");
            response.setData(e.getMessage());
        }
        return response;
    }

    @PutMapping("/item")
    public Response putLedger(@RequestBody Ledger ledger, HttpServletRequest request){
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("토큰 등록 완료");
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("토큰 등록 실패");
        }
        return response;
    }

    @DeleteMapping("/item")
    public Response deleteLedger(@RequestBody Ledger ledger, HttpServletRequest request){
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("토큰 등록 완료");
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("토큰 등록 실패");
        }
        return response;
    }
}