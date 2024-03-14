package com.testRestful.restful.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String filename;

    @Column(name = "content_type")
    private String contentType;

    @Lob
    @Column(name = "file", length = 1048576)
    private byte[] data;

    // @OneToOne(mappedBy = "fileEntity", cascade = CascadeType.ALL)
    // private Order order;
}
