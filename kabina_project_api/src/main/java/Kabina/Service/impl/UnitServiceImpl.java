package Kabina.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kabina.Model.Unit;
import Kabina.Repository.UnitRepository;
import Kabina.Service.UnitService;

@Service
public class UnitServiceImpl implements UnitService {
	@Autowired
	UnitRepository unitRepository;

	@Override
	public List<Unit> findAll() {
		// TODO Auto-generated method stub
		return unitRepository.findAll();
	}

	@Override
	public Unit findByUnitId(int unitId) {
		// TODO Auto-generated method stub
		return unitRepository.findByUnitId(unitId);
	}

}
