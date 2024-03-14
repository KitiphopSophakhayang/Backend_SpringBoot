package com.testRestful.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testRestful.restful.entity.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity,Long> {
}
