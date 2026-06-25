package com.gokul.busbooking.controller;

import com.gokul.busbooking.entity.Bus;
import com.gokul.busbooking.service.BusService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping
    public Bus addBus(@RequestBody Bus bus) {
        return busService.addBus(bus);
    }

    @GetMapping
    public List<Bus> getAllBuses() {
        return busService.getAllBuses();
    }

    @GetMapping("/{id}")
    public Bus getBusById(@PathVariable Long id) {
        return busService.getBusById(id);
    }

    @PutMapping("/{id}")
    public Bus updateBus(@PathVariable Long id,
                         @RequestBody Bus bus) {
        return busService.updateBus(id, bus);
    }

    @DeleteMapping("/{id}")
    public String deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return "Bus Deleted Successfully";
    }

    @GetMapping("/search")
    public List<Bus> searchBus(
            @RequestParam String source,
            @RequestParam String destination) {

        return busService.searchBus(source, destination);
    }
}