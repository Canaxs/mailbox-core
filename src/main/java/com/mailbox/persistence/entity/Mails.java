package com.mailbox.persistence.entity;

import com.mailbox.enums.MailType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Entity
@Table(name = "mails")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Mails extends BaseEntity {

    @NotNull
    private Long userId;

    private String mailTitle;

    private String mailSubject;

    @Enumerated(EnumType.STRING)
    private MailType mailType;

    private String mailSender;
}
