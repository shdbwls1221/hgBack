package com.vtw.pulsar.team.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vtw.pulsar.team.entity.Team;
import com.vtw.pulsar.user.entity.User;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long>, JpaRepository<Team, Long>, JpaSpecificationExecutor<Team>{
	
	
	@Query(
			"select new com.vtw.pulsar.team.entity.Team(t.id, t.name, count(u) as userCount) "
			+ "from Team t left join t.users u  "
			+ "where (:name is null or t.name like concat('%', :name, '%')) "
			+ "group by t.id, t.name"
			)
	//@Query(value = "SELECT new com.vtw.pulsar.team.entity.Team(t.id as id , t.name as name, size(t.users) as userCount) FROM Team t order by userCount desc")
	//@Query("SELECT t.id as id , t.name as name, t.users.size as userCount from Team t")
	Page<Team> findAll(@Param("name") String name, Pageable page);
	
//	@Query("SELECT new com.vtw.pulsar.team.entity.Team(t.id as id , t.name as name, count(u) as userCount) "
//			+ "FROM Team t left join t.users u where t.name = 'a' group by t.id")
	//@Query(value = "SELECT new com.vtw.pulsar.team.entity.Team(t.id as id , t.name as name, size(t.users) as userCount) FROM Team t")
//	Page<Team> getTeams(@Param("name") String name, Pageable page);
}
