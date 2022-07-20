package dragonb.bearfamily.backend.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dragonb.bearfamily.backend.model.terms.Terms;
import dragonb.bearfamily.backend.model.terms.TermsDTO;
import dragonb.bearfamily.backend.repository.TermsRepository;

@Service
public class TermsService {
    
    @Autowired
    TermsRepository termsRepository;

    @Autowired
    CommonService commonService;

    public Terms getTerms(Long id) throws Exception{
        Optional<Terms> resultTerms = termsRepository.findById(id);
        if(!resultTerms.isPresent()){
            throw new Exception();
        }
        else{
            return resultTerms.get();
        }
    }

    public List<Terms> getTermss() throws Exception{
        return termsRepository.findAll();
    }

    public Terms postTerms(TermsDTO termsDTO, HttpServletRequest request) throws Exception{
        if(!commonService.isAdmin(request)){
            throw new Exception();
        }

        return termsRepository.save(Terms.builder()
        .title(termsDTO.getTitle())
        .content(termsDTO.getContent())
        .required(termsDTO.isRequired())
        .build());
    }

    public Terms putTerms(TermsDTO termsDTO, Long id, HttpServletRequest request) throws Exception{
        if(!commonService.isAdmin(request)){
            throw new Exception();
        }

        Optional<Terms> resultTerms = termsRepository.findById(id);
        if(!resultTerms.isPresent()){
            throw new Exception();
        }

        Terms saveTerms = resultTerms.get();
        if(termsDTO.getTitle() != null) saveTerms.setTitle(termsDTO.getTitle());
        if(termsDTO.getContent() != null) saveTerms.setContent(termsDTO.getContent());
        if(termsDTO.isRequired() != saveTerms.isRequired()) saveTerms.setRequired(termsDTO.isRequired());
        Terms test = termsRepository.save(saveTerms);

        return test;
    }

    public void deleteTerms(Long id, HttpServletRequest request) throws Exception{
        if(!commonService.isAdmin(request)){
            throw new Exception();
        }

        termsRepository.deleteById(id);
    }
}
