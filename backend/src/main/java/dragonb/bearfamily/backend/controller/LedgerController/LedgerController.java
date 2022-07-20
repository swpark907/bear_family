package dragonb.bearfamily.backend.controller.LedgerController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dragonb.bearfamily.backend.model.common.Response;
import dragonb.bearfamily.backend.model.ledger.Ledger;
import dragonb.bearfamily.backend.model.ledger.LedgerDTO;
import dragonb.bearfamily.backend.service.LedgerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/ledger")
@Tag(name = "Ledger API", description = "장부 관련 기능")
public class LedgerController {

    @Autowired
    LedgerService ledgerService;

    @Operation(summary = "ledger get method", description = "장부 한 건의 정보를 조회합니다.")
    @GetMapping("/item/{id}")
    public Response getLedger(@PathVariable Long id, HttpServletRequest request){
        Response response = new Response();

        try {
            Ledger resultLedger = ledgerService.getLedger(id, request);

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

    @Operation(summary = "all ledger get method", description = "장부 여러 건의 정보를 조회합니다.")
    @GetMapping("/items")
    public Response getLedgers(HttpServletRequest request){
        Response response = new Response();

        try {
            List<Ledger> resultLedgers = ledgerService.getLedgers(request);
            
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

    @Operation(summary = "ledger post method", description = "장부 한 건의 정보를 등록합니다.")
    @PostMapping("/item")
    public Response postLedger(@RequestBody LedgerDTO ledgerDTO, HttpServletRequest request){
        Response response = new Response();

        try {
            Ledger resultLedger = ledgerService.postLedger(ledgerDTO, request);

            response.setResponse("success");
            response.setMessage("success post ledger");
            response.setData(resultLedger);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail post ledger");
            response.setData(null);
        }
        return response;
    }

    @Operation(summary = "ledger post method", description = "장부 한 건의 정보를 수정합니다.")
    @PutMapping("/item/{id}")
    public Response putLedger(@RequestBody LedgerDTO ledgerDTO, @PathVariable Long id, HttpServletRequest request){
        Response response = new Response();
        
        try {
            Ledger resultLedger = ledgerService.putLedger(ledgerDTO, id, request);

            response.setResponse("success");
            response.setMessage("success put ledger");
            response.setData(resultLedger);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail put ledger");
            response.setData(null);
        }
        return response;
    }

    @Operation(summary = "ledger post method", description = "장부 한 건의 정보를 삭제합니다.")
    @DeleteMapping("/item/{id}")
    public Response deleteLedger(@PathVariable Long id, HttpServletRequest request){
        Response response = new Response();

        try {
            ledgerService.deleteLedger(id, request);

            response.setResponse("success");
            response.setMessage("success delete ledger");
            response.setData(true);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail delete ledger");
            response.setData(null);
        }
        return response;
    }
}