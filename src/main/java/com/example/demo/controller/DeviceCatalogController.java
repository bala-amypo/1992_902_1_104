package com.example.demo.controller;

import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceCatalogController {

    private final DeviceCatalogService service;

    public DeviceCatalogController(DeviceCatalogService service) {
        this.service = service;
    }

    // Create device catalog item
    @PostMapping
    public DeviceCatalogItem createDevice(@RequestBody DeviceCatalogItem item) {
        return service.createItem(item);
    }

    // Get all device items
    @GetMapping
    public List<DeviceCatalogItem> getAllDevices() {
        return service.getAllItems();
    }
}
