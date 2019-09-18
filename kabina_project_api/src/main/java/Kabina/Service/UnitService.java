package Kabina.Service;

import java.util.List;

import Kabina.Model.Unit;

public interface UnitService {
	List<Unit> findAll();
	Unit findByUnitId(int unitId);

}
