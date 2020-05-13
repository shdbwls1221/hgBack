package com.vtw.pulsar.schedule.tempIntf;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.vtw.pulsar.jpa.PageInfo;

public interface ConInterfaceService {
	
	// ** DB CRUD ** //
    public List<ConInterface> getConInterfaces(Specification<ConInterface> search, PageInfo pageInfo);
    
	public int getCount(Specification<ConInterface> search);
    
	public ConInterface getConInterface(String intfId);

	public ConInterface addConInterface(ConInterface conInterface);

	public ConInterface updateConInterface(ConInterface conInterface);

	public void deleteConInterface(String intfId);
	
}
