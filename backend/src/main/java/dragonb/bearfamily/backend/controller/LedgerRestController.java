package dragonb.bearfamily.backend.controller;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import dragonb.bearfamily.backend.model.Ledger;
import dragonb.bearfamily.backend.model.LedgerEx;
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
    public Response getLedger(@ModelAttribute LedgerEx ledger, HttpServletRequest request){
        Response response = new Response();
        String userIdentity = getIdentity(request);

        try {
            long id = ledger.getId();
            Optional<Ledger> resultLedger = ledgerRepository.findByIdAndUserIdentity(id, userIdentity);
            if(!resultLedger.isPresent()){
                throw new Exception();
            }
            response.setResponse("success");
            response.setMessage("success get ledger");
            response.setData(resultLedger);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail get ledger");
            response.setData(null);
        }
        return response;
    }

    @GetMapping("/items")
    public Response getLedgers(@ModelAttribute LedgerEx ledger, HttpServletRequest request){
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
            response.setData(null);
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
            response.setData(null);
        }
        return response;
    }

    @PutMapping("/item")
    public Response putLedger(@RequestBody Ledger ledger, HttpServletRequest request){
        Response response = new Response();
        String userIdentity = getIdentity(request);
        
        try {
            long id = ledger.getId();
            Optional<Ledger> resultLedger = ledgerRepository.findByIdAndUserIdentity(id, userIdentity);
            if(!resultLedger.isPresent()){
                throw new Exception();
            }

            Ledger saveLedger = resultLedger.get();
            if(saveLedger.getCategoryId() != ledger.getCategoryId()) saveLedger.setCategoryId(ledger.getCategoryId());
            if(saveLedger.getDate().equals(ledger.getDate())) saveLedger.setDate(ledger.getDate());
            if(saveLedger.getDescription() != ledger.getDescription()) saveLedger.setDescription(ledger.getDescription());
            if(saveLedger.getKind() != ledger.getKind()) saveLedger.setKind(ledger.getKind());
            if(saveLedger.getLocation() != ledger.getLocation()) saveLedger.setLocation(ledger.getLocation());
            if(saveLedger.getPayment() != ledger.getPayment()) saveLedger.setPayment(ledger.getPayment());
            if(saveLedger.getPrice() != ledger.getPrice()) saveLedger.setPrice(ledger.getPrice());
            if(saveLedger.getTitle() != ledger.getTitle()) saveLedger.setTitle(ledger.getTitle());
            saveLedger.setUpdated(LocalDateTime.now());

            ledgerRepository.save(saveLedger);
            response.setResponse("success");
            response.setMessage("success put ledger");
            response.setData(ledger);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail put ledger");
            response.setData(null);
        }
        return response;
    }

    @DeleteMapping("/item")
    public Response deleteLedger(@RequestBody LedgerEx ledger, HttpServletRequest request){
        Response response = new Response();
        String userIdentity = getIdentity(request);

        try {
            long id = ledger.getId();
            Optional<Ledger> resultLedger = ledgerRepository.findByIdAndUserIdentity(id, userIdentity);
            if(!resultLedger.isPresent()){
                throw new Exception();
            }
            ledgerRepository.delete(resultLedger.get());
            response.setResponse("success");
            response.setMessage("success delete ledger");
            response.setData(ledger);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail delete ledger");
            response.setData(null);
        }
        return response;
    }
}