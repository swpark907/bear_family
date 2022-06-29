package dragonb.bearfamily.backend.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.model.Ledger;
import dragonb.bearfamily.backend.model.Response;
import dragonb.bearfamily.backend.repository.LedgerRepository;

@RestController
@RequestMapping("/api/ledger")
@RequiredArgsConstructor
public class LedgerRestController {

    @Autowired
    private LedgerRepository ledgerRepository;

    @GetMapping("/item")
    public Response getLedger(@RequestBody Ledger ledger){
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