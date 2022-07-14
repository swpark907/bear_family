package dragonb.bearfamily.backend.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dragonb.bearfamily.backend.model.Category;
import dragonb.bearfamily.backend.model.Ledger;
import dragonb.bearfamily.backend.model.LedgerMath;
import dragonb.bearfamily.backend.model.LedgerColumnKind;
import dragonb.bearfamily.backend.model.LedgerColumnPayment;
import dragonb.bearfamily.backend.model.LedgerDTO;
import dragonb.bearfamily.backend.repository.LedgerRepository;

@Service
public class LedgerService {

    @Autowired
    LedgerRepository ledgerRepository;
    
    private String userIdentity;

    public Ledger getLedger(HttpServletRequest request, LedgerDTO ledgerDTO) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        long id = ledgerDTO.getId();
        Optional<Ledger> resultLedger = ledgerRepository.findLedgerFetch(id, userIdentity);
        if(!resultLedger.isPresent()){
            throw new Exception();
        }
        else{
            return resultLedger.get();
        }
    }

    public List<Ledger> getLedgers(HttpServletRequest request, LedgerDTO ledgerDTO) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        return ledgerRepository.findLedgersFetch(userIdentity);
    }

    public Ledger postLedger(HttpServletRequest request, LedgerDTO ledgerDTO){
        userIdentity = CommonService.getUserIdentity(request);
        ledgerDTO.setUserIdentity(userIdentity);

        Ledger test = ledgerByEx(ledgerDTO);

        return ledgerRepository.save(test);
    }

    public Ledger putLedger(HttpServletRequest request, LedgerDTO ledgerDTO) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        long id = ledgerDTO.getId();
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

    public void deleteLedger(HttpServletRequest request, LedgerDTO ledgerDTO) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        long id = ledgerDTO.getId();
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
        userIdentity = CommonService.getUserIdentity(request);
        return ledgerRepository.findTop5ByUserIdentityOrderByPriceDesc(userIdentity);
    }

    public List<LedgerMath> getLedgersSumGroupByYear(HttpServletRequest request) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        return ledgerRepository.findLedgersSumGroupByYear(userIdentity);
    }

    public List<LedgerMath> getLedgersSumGroupByMonth(HttpServletRequest request) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        return ledgerRepository.findLedgersSumGroupByMonth(userIdentity);
    }

    public List<LedgerMath> getLedgersSumGroupByDate(HttpServletRequest request) throws Exception{
        userIdentity = CommonService.getUserIdentity(request);
        return ledgerRepository.findLedgersSumGroupByDate(userIdentity);
    }
}
