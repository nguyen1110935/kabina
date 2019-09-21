package Kabina.Service;

import java.util.List;
import java.util.Optional;

import Kabina.Model.Bookingtemp;

public interface BookingtempService {
	List<Bookingtemp> findAll();
    void deleteById(Long id);
    Optional<Bookingtemp> findById (Long id);
}
