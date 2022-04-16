package com.data.civil.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comm_logs", indexes = {

})
public class CommLog {

    @Id
    @SequenceGenerator(name = "seq_comm_log", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_comm_log")
    @Column(name = "id")
    private Long id;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "access_from")
    private String accessFrom;

    @Lob
    @Column(name = "request", columnDefinition = "text")
    private String request;

    @Lob
    @Column(name = "response", columnDefinition = "text")
    private String response;

}
