package com.vtw.pulsar.team.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vtw.pulsar.user.entity.User;

@Entity
@Table(name="TEAM_TEST")
@SequenceGenerator(
        name="SEQ_TEAM_GEN", //시퀀스 제너레이터 이름
        sequenceName="SEQ_TEAM", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
        )
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEAM_GEN")
    private long id;
    private String name;
    
//    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)  
   @Transient
   private long userCount;
    
   // @JsonManagedReference
    @JsonIgnore
	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY )  
    //@Transient
    //@org.hibernate.annotations.LazyCollection(
    //		org.hibernate.annotations.LazyCollectionOption.EXTRA
    //		)
    private List<User> users;  
    
	public Team() {
	}

	public Team(long id, String name, long userCount) {
		this.id = id;
		this.name = name;
		this.userCount = userCount;
	}
	
	public Team(Team team, long userCount) {
		this.id = team.id;
		this.name = team.name;
		this.userCount = userCount;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public long getUserCount() {
		return this.userCount;
	}

	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", userCount=" + getUserCount() + ", users=" + users + "]";
	}
}
