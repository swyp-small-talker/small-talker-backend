package com.swygbr.backend.repository;

import com.swygbr.backend.entity.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Userinfo, Long> {
}
