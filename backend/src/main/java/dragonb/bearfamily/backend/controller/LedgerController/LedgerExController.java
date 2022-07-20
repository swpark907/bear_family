package dragonb.bearfamily.backend.controller.LedgerController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dragonb.bearfamily.backend.model.common.Response;
import dragonb.bearfamily.backend.model.ledger.Ledger;
import dragonb.bearfamily.backend.model.ledger.LedgerDate;
import dragonb.bearfamily.backend.model.ledger.LedgerMath;
import dragonb.bearfamily.backend.service.LedgerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/ledger")
@Tag(name = "Ledger Expansion API", description = "장부 관련 추가 기능")
public class LedgerExController {
    
    @Autowired
    LedgerService ledgerService;

    @Operation(summary = "all ledger get method top5", description = "장부 결제 금액이 가장 큰 5건의 정보를 조회합니다.")
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

    @Operation(summary = "all ledger get method group by year", description = "장부 결제 금액 연도별 합계 정보를 조회합니다.")
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

    @Operation(summary = "all ledger get method group by month", description = "장부 결제 금액 월별 합계 정보를 조회합니다.")
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

    @Operation(summary = "all ledger get method group by date", description = "장부 결제 금액 일별 합계 정보를 조회합니다.")
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

    @Operation(summary = "all ledger get method at specific date", description = "특정 날짜의 장부 여러 건의 정보를 조회합니다.")
    @GetMapping("/items/date")
    public Response getLedgersDate(LedgerDate ledgerDate, HttpServletRequest request){
        Response response = new Response();

        try {
            List<Ledger> resultLedgers = ledgerService.getLedgersDate(ledgerDate, request);
            
            response.setResponse("success");
            response.setMessage("success get target ledgers");
            response.setData(resultLedgers);
        } catch (Exception e) {
            response.setResponse("fail");
            response.setMessage("fail get target ledgers");
            response.setData(null);
        }
        return response;
    }
}
