package dragonb.bearfamily.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dragonb.bearfamily.backend.model.common.Response;
import dragonb.bearfamily.backend.model.ledger.Ledger;
import dragonb.bearfamily.backend.model.ledger.LedgerDTO;
import dragonb.bearfamily.backend.model.ledger.LedgerMath;
import dragonb.bearfamily.backend.service.LedgerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/ledger")
@Tag(name = "Ledger Controller", description = "ledger api")
public class LedgerController {

    @Autowired
    LedgerService ledgerService;

    @Operation(summary = "ledger get method", description = "get ledger by ledgerDTO and login information in httpservletrequest")
    @GetMapping("/item")
    public Response getLedger(@ModelAttribute LedgerDTO ledgerDTO, HttpServletRequest request){
        Response response = new Response();

        try {
            Ledger resultLedger = ledgerService.getLedger(request, ledgerDTO);

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
    public Response getLedgers(@ModelAttribute LedgerDTO ledgerDTO, HttpServletRequest request){
        Response response = new Response();

        try {
            List<Ledger> resultLedgers = ledgerService.getLedgers(request, ledgerDTO);
            
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
    public Response postLedger(@RequestBody LedgerDTO ledgerDTO, HttpServletRequest request){
        Response response = new Response();

        try {
            Ledger resultLedger = ledgerService.postLedger(request, ledgerDTO);

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

    @PutMapping("/item")
    public Response putLedger(@RequestBody LedgerDTO ledgerDTO, HttpServletRequest request){
        Response response = new Response();
        
        try {
            Ledger resultLedger = ledgerService.putLedger(request, ledgerDTO);

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

    @DeleteMapping("/item")
    public Response deleteLedger(@RequestBody LedgerDTO ledgerDTO, HttpServletRequest request){
        Response response = new Response();

        try {
            ledgerService.deleteLedger(request, ledgerDTO);

            response.setResponse("success");
            response.setMessage("success delete ledger");
            response.setData(ledgerDTO);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail delete ledger");
            response.setData(null);
        }
        return response;
    }

    @GetMapping("/items/top5")
    public Response getLedgersTop5(HttpServletRequest request){
        Response response = new Response();

        try {
            List<Ledger> resultLedgers = ledgerService.getTop5ByUserIdentityOrderByPriceDesc(request);
            
            response.setResponse("success");
            response.setMessage("success get ledgers sum group by month");
            response.setData(resultLedgers);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail get ledgers sum group by month");
            response.setData(null);
        }
        return response;
    }

    @GetMapping("/items/groupbyyear")
    public Response getLedgersSumGroupByYear(HttpServletRequest request){
        Response response = new Response();

        try {
            List<LedgerMath> resultLedgers = ledgerService.getLedgersSumGroupByYear(request);
            
            response.setResponse("success");
            response.setMessage("success get ledgers sum group by year");
            response.setData(resultLedgers);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail get ledgers sum group by year");
            response.setData(null);
        }
        return response;
    }

    @GetMapping("/items/groupbymonth")
    public Response getLedgersSumGroupByMonth(HttpServletRequest request){
        Response response = new Response();

        try {
            List<LedgerMath> resultLedgers = ledgerService.getLedgersSumGroupByMonth(request);
            
            response.setResponse("success");
            response.setMessage("success get ledgers sum group by month");
            response.setData(resultLedgers);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail get ledgers sum group by month");
            response.setData(null);
        }
        return response;
    }

    @GetMapping("/items/groupbydate")
    public Response getLedgersSumGroupByDate(HttpServletRequest request){
        Response response = new Response();

        try {
            List<LedgerMath> resultLedgers = ledgerService.getLedgersSumGroupByDate(request);
            
            response.setResponse("success");
            response.setMessage("success get ledgers sum group by date");
            response.setData(resultLedgers);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail get ledgers sum group by date");
            response.setData(null);
        }
        return response;
    }
}