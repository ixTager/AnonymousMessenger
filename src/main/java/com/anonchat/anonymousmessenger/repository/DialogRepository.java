package com.anonchat.anonymousmessenger.repository;


import com.anonchat.anonymousmessenger.entity.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, String> {

}
