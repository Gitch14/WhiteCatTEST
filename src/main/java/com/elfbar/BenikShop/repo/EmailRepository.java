package com.elfbar.BenikShop.repo;

import com.elfbar.BenikShop.essences.Email;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EmailRepository extends CrudRepository<Email, Long> {

    List<Email> findByEmail(String email);

}
