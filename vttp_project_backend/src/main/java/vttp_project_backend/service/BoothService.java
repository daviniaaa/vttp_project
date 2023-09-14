package vttp_project_backend.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import vttp_project_backend.exception.CustomException;
import vttp_project_backend.exception.ExistingBoothNameException;
import vttp_project_backend.models.BoothDetails;
import vttp_project_backend.repo.BoothRepository;


@Service
public class BoothService {
    @Autowired BoothRepository boothRepo;

    public BoothDetails createBooth(BoothDetails b, String eventId) {
        // ensure no duplicate name
        if (boothRepo.findBoothByName(b.getBoothName()).isPresent()) {
            throw new ExistingBoothNameException();
        }

        b.setEventDetailsId(eventId);

        // if existing id

        // if no existing id, create new UUID and ensure no duplicate id
        String boothId = UUID.randomUUID().toString().substring(0, 8);

        if (boothRepo.findBoothById(boothId).isPresent()) {
            createBooth(b, eventId);
        }
        b.setBoothId(boothId);

        boolean success = boothRepo.createBooth(b);

        if (!success) {
            throw new CustomException("Error creating booth", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return b;
    }
    
    public List<BoothDetails> getBooths(String id) {
        return boothRepo.getBooths(id);
    }
}
