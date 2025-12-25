package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DeviceCatalogItem;
import com.example.demo.repository.DeviceCatalogItemRepository;
import com.example.demo.service.DeviceCatalogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceCatalogServiceImpl implements DeviceCatalogService {

    private final DeviceCatalogItemRepository deviceRepo;

    public DeviceCatalogServiceImpl(DeviceCatalogItemRepository deviceRepo) {
        this.deviceRepo = deviceRepo;
    }

    @Override
    public DeviceCatalogItem createItem(DeviceCatalogItem item) {
        if (item.getMaxAllowedPerEmployee() == null || item.getMaxAllowedPerEmployee() <= 0) {
            throw new BadRequestException("maxAllowedPerEmployee");
        }
        if (deviceRepo.findByDeviceCode(item.getDeviceCode()).isPresent()) {
            throw new BadRequestException("exists");
        }
        return deviceRepo.save(item);
    }

    @Override
    public DeviceCatalogItem updateActiveStatus(Long id, boolean active) {
        DeviceCatalogItem device = deviceRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));
        device.setActive(active);
        return deviceRepo.save(device);
    }

    @Override
    public List<DeviceCatalogItem> getAllItems() {
        return deviceRepo.findAll();
    }
}
