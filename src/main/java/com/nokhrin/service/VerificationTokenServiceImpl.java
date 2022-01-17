package com.nokhrin.service;

import com.nokhrin.dao.VerificationTokenRepository;
import com.nokhrin.domain.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {
    VerificationTokenRepository tokenRepository;
    @Autowired
    public  VerificationTokenServiceImpl( VerificationTokenRepository tokenRepository){
        this.tokenRepository=tokenRepository;
    }
    public void addVerificationToken(VerificationToken token){
        tokenRepository.save(token);
    }
    public void removeVerificationToken(VerificationToken token){
        tokenRepository.delete(token);
    }
    public VerificationToken getTockenById(long id){
        return tokenRepository.findById(id);
    }
    public  VerificationToken getTockenByTockenCode(String tokenCode){
        return  tokenRepository.findByToken(tokenCode);
    }
    public List<VerificationToken> getList(){
       return tokenRepository.findAll();
    }
}
