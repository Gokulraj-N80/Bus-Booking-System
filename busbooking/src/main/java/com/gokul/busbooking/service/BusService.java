package com.gokul.busbooking.service;

import com.gokul.busbooking.entity.Bus;
import com.gokul.busbooking.repository.BusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    private final BusRepository busRepository;
    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }
    public List<Bus> searchBus(String source, String destination)
    {
        return busRepository.findBySourceAndDestination(source, destination);
    }

    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
    public Bus updateBus(Long id, Bus bus) {

        Bus existingBus = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        existingBus.setBusName(bus.getBusName());
        existingBus.setSource(bus.getSource());
        existingBus.setDestination(bus.getDestination());
        existingBus.setSeats(bus.getSeats());

        return busRepository.save(existingBus);
    }
    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }
    public Bus getBusById(Long id)
    {
        return busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus Not Found"));
    }
}