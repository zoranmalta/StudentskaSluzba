package com.fax.StudentskaSluzba.service.serviceImpl;

import com.fax.StudentskaSluzba.model.Engagement;
import com.fax.StudentskaSluzba.repository.EngagementRepository;
import com.fax.StudentskaSluzba.service.EngagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngagementServiceImpl implements EngagementService {
    @Autowired
    private EngagementRepository engagementRepository;

    @Override
    public List<Engagement> getEngagementsByCourse(Long courseId) {
        return engagementRepository.fetchEngagementByCourse(courseId);
    }

    @Override
    public List<Engagement> saveAll(List<Engagement> engagements) {
        return engagementRepository.saveAll(engagements);
    }

    @Override
    public Engagement findOneById(Long id) {
        return engagementRepository.getOne(id);
    }

}
