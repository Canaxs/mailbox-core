package com.mailbox.persistence.repository;

import com.mailbox.persistence.entity.Mails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailsRepository extends JpaRepository<Mails, Long> {
}
