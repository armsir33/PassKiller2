package se.midport.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import se.midport.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
