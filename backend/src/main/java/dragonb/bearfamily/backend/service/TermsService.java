package dragonb.bearfamily.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dragonb.bearfamily.backend.model.terms.Terms;
import dragonb.bearfamily.backend.repository.TermsRepository;

@Service
public class TermsService {
    
    @Autowired
    TermsRepository termsRepository;

    public Terms getTerms(Terms terms) throws Exception{
        Optional<Terms> resultTerms = termsRepository.findById(terms.getId());
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

    public Terms postTerms(Terms terms) throws Exception{
        return termsRepository.save(terms);
    }

    public Terms putTerms(Terms terms) throws Exception{
        Optional<Terms> resultTerms = termsRepository.findById(terms.getId());
        if(!resultTerms.isPresent()){
            throw new Exception();
        }

        Terms saveTerms = resultTerms.get();
        if(terms.getTitle() != null) saveTerms.setTitle(terms.getTitle());
        if(terms.getContent() != null) saveTerms.setContent(terms.getContent());
        if(terms.isRequired() != saveTerms.isRequired()) saveTerms.setRequired(terms.isRequired());
        Terms test = termsRepository.save(saveTerms);

        return test;
    }

    public void deleteTerms(Terms Terms) throws Exception{
        termsRepository.deleteById(Terms.getId());
    }
}
