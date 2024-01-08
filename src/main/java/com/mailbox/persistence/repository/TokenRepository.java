package com.mailbox.persistence.repository;

import com.mailbox.persistence.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token getTokenByToken(String token);

    Boolean existsByToken(String token);
}
