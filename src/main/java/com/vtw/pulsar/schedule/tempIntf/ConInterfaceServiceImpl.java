package com.vtw.pulsar.schedule.tempIntf;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.vtw.pulsar.jpa.PageInfo;

@Service
public class ConInterfaceServiceImpl implements ConInterfaceService {
	
	private final ConInterfaceRepository conInterfaceRepository;
	
	ConInterfaceServiceImpl(ConInterfaceRepository conInterfaceRepository) {
		this.conInterfaceRepository = conInterfaceRepository;
	}

	@Override
	public List<ConInterface> getConInterfaces(Specification<ConInterface> search, PageInfo pageInfo) {

		return (List<ConInterface>) conInterfaceRepository.findAll(search, pageInfo.toPageable("intfId")).getContent();
	}

	@Override
	public int getCount(Specification<ConInterface> search) {

		return (int) conInterfaceRepository.count(search);
	}

	@Override
	public ConInterface getConInterface(String intfId) {

		return conInterfaceRepository.findById(intfId).get();
	}

	@Override
	public ConInterface addConInterface(ConInterface conInterface) {

		return conInterfaceRepository.save(conInterface);
	}

	@Override
	public ConInterface updateConInterface(ConInterface conInterface) {

		return conInterfaceRepository.save(conInterface);
	}

	@Override
	public void deleteConInterface(String intfId) {

		conInterfaceRepository.deleteById(intfId);
	}

}
