package dragonb.bearfamily.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dragonb.bearfamily.backend.model.category.Category;
import dragonb.bearfamily.backend.model.ledger.Ledger;
import dragonb.bearfamily.backend.model.ledger.LedgerColumnKind;
import dragonb.bearfamily.backend.model.ledger.LedgerColumnPayment;
import dragonb.bearfamily.backend.model.ledger.LedgerDTO;
import dragonb.bearfamily.backend.model.ledger.LedgerDate;
import dragonb.bearfamily.backend.model.ledger.LedgerMath;
import dragonb.bearfamily.backend.repository.LedgerRepository.LedgerRepository;

@Service
public class LedgerService {

    @Autowired
    LedgerRepository ledgerRepository;
    
    @Autowired
    CommonService commonService;

    private String userIdentity;

    public Ledger getLedger(Long id, HttpServletRequest request) throws Exception{
        userIdentity = commonService.getUserIdentity(request);
        Optional<Ledger> resultLedger = ledgerRepository.findLedgerFetch(id, userIdentity);
        if(!resultLedger.isPresent()){
            throw new Exception();
        }
        else{
            return resultLedger.get();
        }
    }

    public List<Ledger> getLedgers(HttpServletRequest request) throws Exception{
        userIdentity = commonService.getUserIdentity(request);
        return ledgerRepository.findLedgersFetch(userIdentity);
    }

    public Ledger postLedger(LedgerDTO ledgerDTO, HttpServletRequest request){
        userIdentity = commonService.getUserIdentity(request);

        Ledger saveLedger = ledgerByEx(ledgerDTO);
        saveLedger.setUserIdentity(userIdentity);

        return ledgerRepository.save(saveLedger);
    }

    public Ledger putLedger(LedgerDTO ledgerDTO, Long id, HttpServletRequest request) throws Exception{
        userIdentity = commonService.getUserIdentity(request);
        Optional<Ledger> resultLedger = ledgerRepository.findLedgerFetch(id, userIdentity);
        if(!resultLedger.isPresent()){
            throw new Exception();
        }

        Ledger saveLedger = resultLedger.get();

        if(ledgerDTO.getCategoryId() != saveLedger.getCategory().getId()) saveLedger.setCategory(Category.builder().id(ledgerDTO.getCategoryId()).build());
        if(ledgerDTO.getDate() != null) saveLedger.setDate(ledgerDTO.getDate());
        if(ledgerDTO.getDescription() != null) saveLedger.setDescription(ledgerDTO.getDescription());
        if(ledgerDTO.getKind() != saveLedger.getKind().getId()) saveLedger.setKind(LedgerColumnKind.builder().id(ledgerDTO.getKind()).build());
        if(ledgerDTO.getLocation() != null) saveLedger.setLocation(ledgerDTO.getLocation());
        if(ledgerDTO.getPayment() != saveLedger.getPayment().getId()) saveLedger.setPayment(LedgerColumnPayment.builder().id(ledgerDTO.getPayment()).build());
        if(ledgerDTO.getPrice() != null) saveLedger.setPrice(ledgerDTO.getPrice());
        if(ledgerDTO.getTitle() != null) saveLedger.setTitle(ledgerDTO.getTitle());
        saveLedger.setUpdated(LocalDateTime.now());

        return ledgerRepository.save(saveLedger);
    }

    public void deleteLedger(Long id, HttpServletRequest request) throws Exception{
        userIdentity = commonService.getUserIdentity(request);
        Optional<Ledger> resultLedger = ledgerRepository.findLedgerFetch(id, userIdentity);
        if(!resultLedger.isPresent()){
            throw new Exception();
        }
        ledgerRepository.delete(resultLedger.get());
    }

    private Ledger ledgerByEx(LedgerDTO ledgerDTO){
        return Ledger.builder()
            .title(ledgerDTO.getTitle())
            .price(ledgerDTO.getPrice())
            .kind(LedgerColumnKind.builder().id(ledgerDTO.getKind()).build())
            .location(ledgerDTO.getLocation())
            .payment(LedgerColumnPayment.builder().id(ledgerDTO.getPayment()).build())
            .description(ledgerDTO.getDescription())
            .date(ledgerDTO.getDate())
            .category(Category.builder().id(ledgerDTO.getCategoryId()).build())
            .userIdentity(userIdentity)
            .build();
    }

    public List<Ledger> getTop5ByUserIdentityOrderByPriceDesc(HttpServletRequest request) throws Exception{
        userIdentity = commonService.getUserIdentity(request);
        return ledgerRepository.findTop5ByUserIdentityOrderByPriceDesc(userIdentity);
    }

    public List<LedgerMath> getLedgersSumGroupByYear(HttpServletRequest request) throws Exception{
        userIdentity = commonService.getUserIdentity(request);
        return ledgerRepository.findLedgersSumGroupByYear(userIdentity);
    }

    public List<LedgerMath> getLedgersSumGroupByMonth(HttpServletRequest request) throws Exception{
        userIdentity = commonService.getUserIdentity(request);
        return ledgerRepository.findLedgersSumGroupByMonth(userIdentity);
    }

    public List<LedgerMath> getLedgersSumGroupByDate(HttpServletRequest request) throws Exception{
        userIdentity = commonService.getUserIdentity(request);
        return ledgerRepository.findLedgersSumGroupByDate(userIdentity);
    }

    public List<Ledger> getLedgersDate(LedgerDate ledgerDate, HttpServletRequest request) throws Exception{
        userIdentity = commonService.getUserIdentity(request);

        String year;
        String month;
        String date;

        if(ledgerDate.getYear() == null || ledgerDate.getYear().toString() == ""){
            year = "";
        }
        else if(ledgerDate.getYear() >= 1000 && ledgerDate.getYear() <= 9999){
            year = ledgerDate.getYear().toString();
        }
        else{
            throw new Exception();
        }

        if(ledgerDate.getMonth() == null || ledgerDate.getMonth().toString() == ""){
            month = "";
        }
        else if(ledgerDate.getMonth() >= 1 && ledgerDate.getMonth() <= 12){
            month = String.valueOf(ledgerDate.getMonth());
        }
        else{
            throw new Exception();
        }

        if(ledgerDate.getDate() == null || ledgerDate.getDate().toString() == ""){
            date = "";
        }
        else if(ledgerDate.getDate() >= 1 && ledgerDate.getDate() <= 31){
            date = String.valueOf(ledgerDate.getDate());
        }
        else{
            throw new Exception();
        }

        if(year != "" && month != "" && date != ""){
            return ledgerRepository.findLedgersByUserIdentityAndDate(userIdentity, year + month + date);
        }
        else if(year != "" && month != ""){
            return ledgerRepository.findLedgersByUserIdentityAndMonth(userIdentity, year + month);
        }
        else if(year != ""){
            return ledgerRepository.findLedgersByUserIdentityAndYear(userIdentity, year);
        }
        else{
            throw new Exception();
        }
    }
}
