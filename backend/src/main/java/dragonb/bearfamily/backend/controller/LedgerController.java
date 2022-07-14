package dragonb.bearfamily.backend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import dragonb.bearfamily.backend.model.Ledger;
import dragonb.bearfamily.backend.model.LedgerEx;
import dragonb.bearfamily.backend.model.Response;
import dragonb.bearfamily.backend.service.LedgerService;

@RestController
@RequestMapping("/api/ledger")
public class LedgerController {

    @Autowired
    LedgerService ledgerService;

    @GetMapping("/item")
    public Response getLedger(@ModelAttribute LedgerEx ledgerEx, HttpServletRequest request){
        Response response = new Response();

        try {
            Ledger resultLedger = ledgerService.getLedger(request, ledgerEx);

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
    public Response getLedgers(@ModelAttribute LedgerEx ledgerEx, HttpServletRequest request){
        Response response = new Response();

        try {
            List<Ledger> resultLedgers = ledgerService.getLedgers(request, ledgerEx);
            
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
    public Response postLedger(@RequestBody LedgerEx ledgerEx, HttpServletRequest request){
        Response response = new Response();

        try {
            Ledger resultLedger = ledgerService.postLedger(request, ledgerEx);

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
    public Response putLedger(@RequestBody LedgerEx ledgerEx, HttpServletRequest request){
        Response response = new Response();
        
        try {
            Ledger resultLedger = ledgerService.putLedger(request, ledgerEx);

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
    public Response deleteLedger(@RequestBody LedgerEx ledgerEx, HttpServletRequest request){
        Response response = new Response();

        try {
            ledgerService.deleteLedger(request, ledgerEx);

            response.setResponse("success");
            response.setMessage("success delete ledger");
            response.setData(ledgerEx);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail delete ledger");
            response.setData(null);
        }
        return response;
    }

    @GetMapping("/items/groupbymonth")
    public Response getLedgersSumGroupByMonth(HttpServletRequest request){
        Response response = new Response();

        try {
            List<Object> resultLedgers = ledgerService.getLedgersSumGroupByMonth(request);
            
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
}