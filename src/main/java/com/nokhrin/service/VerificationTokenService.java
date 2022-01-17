package com.nokhrin.service;

import com.nokhrin.domain.VerificationToken;

import java.util.List;

public interface VerificationTokenService {

    void addVerificationToken(VerificationToken token);
    void removeVerificationToken(VerificationToken token);
    VerificationToken getTockenById(long id);
    VerificationToken getTockenByTockenCode(String tokenCode);
    List<VerificationToken> getList();
}
