package dragonb.bearfamily.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dragonb.bearfamily.backend.model.Category;
import dragonb.bearfamily.backend.model.Ledger;
import dragonb.bearfamily.backend.model.LedgerColumnKind;
import dragonb.bearfamily.backend.model.LedgerColumnPayment;
import dragonb.bearfamily.backend.model.LedgerEx;
import dragonb.bearfamily.backend.repository.LedgerRepository;

@Service
public class LedgerService {

    @Autowired
    LedgerRepository ledgerRepository;
    
    private String userIdentity;

    public Ledger getLedger(HttpServletRequest request, LedgerEx ledgerEx) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        long id = ledgerEx.getId();
        Optional<Ledger> resultLedger = ledgerRepository.findLedgerFetch(id, userIdentity);
        if(!resultLedger.isPresent()){
            throw new Exception();
        }
        else{
            return resultLedger.get();
        }
    }

    public List<Ledger> getLedgers(HttpServletRequest request, LedgerEx ledgerEx) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        return ledgerRepository.findLedgersFetch(userIdentity);
    }

    public Ledger postLedger(HttpServletRequest request, LedgerEx ledgerEx){
        userIdentity = CommonService.getUserIdentity(request);
        ledgerEx.setUserIdentity(userIdentity);
        return ledgerRepository.save(ledgerByEx(ledgerEx));
    }

    public Ledger putLedger(HttpServletRequest request, LedgerEx ledgerEx) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        long id = ledgerEx.getId();
        Optional<Ledger> resultLedger = ledgerRepository.findLedgerFetch(id, userIdentity);
        if(!resultLedger.isPresent()){
            throw new Exception();
        }

        Ledger saveLedger = resultLedger.get();

        if(ledgerEx.getCategoryId() != saveLedger.getCategory().getId()) saveLedger.setCategory(Category.builder().id(ledgerEx.getCategoryId()).build());
        if(ledgerEx.getDate() != null) saveLedger.setDate(ledgerEx.getDate());
        if(ledgerEx.getDescription() != null) saveLedger.setDescription(ledgerEx.getDescription());
        if(ledgerEx.getKind() != saveLedger.getKind().getId()) saveLedger.setKind(LedgerColumnKind.builder().id(ledgerEx.getKind()).build());
        if(ledgerEx.getLocation() != null) saveLedger.setLocation(ledgerEx.getLocation());
        if(ledgerEx.getPayment() != saveLedger.getPayment().getId()) saveLedger.setPayment(LedgerColumnPayment.builder().id(ledgerEx.getPayment()).build());
        if(ledgerEx.getPrice() != null) saveLedger.setPrice(ledgerEx.getPrice());
        if(ledgerEx.getTitle() != null) saveLedger.setTitle(ledgerEx.getTitle());
        saveLedger.setUpdated(LocalDateTime.now());

        return ledgerRepository.save(saveLedger);
    }

    public void deleteLedger(HttpServletRequest request, LedgerEx ledgerEx) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        long id = ledgerEx.getId();
        Optional<Ledger> resultLedger = ledgerRepository.findLedgerFetch(id, userIdentity);
        if(!resultLedger.isPresent()){
            throw new Exception();
        }
        ledgerRepository.delete(resultLedger.get());
    }

    private Ledger ledgerByEx(LedgerEx ledgerEx){
        return Ledger.builder()
            .title(ledgerEx.getTitle())
            .price(ledgerEx.getPrice())
            .kind(LedgerColumnKind.builder().id(ledgerEx.getKind()).build())
            .location(ledgerEx.getLocation())
            .payment(LedgerColumnPayment.builder().id(ledgerEx.getPayment()).build())
            .description(ledgerEx.getDescription())
            .date(ledgerEx.getDate())
            .category(Category.builder().id(ledgerEx.getCategoryId()).build())
            .userIdentity(userIdentity)
            .build();
    }
}
