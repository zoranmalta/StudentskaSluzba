package com.fax.StudentskaSluzba.service;

import com.fax.StudentskaSluzba.model.Engagement;

import java.util.List;

public interface EngagementService {
    List<Engagement> getEngagementsByCourse(Long courseId);
    List<Engagement> saveAll(List<Engagement> engagements);
    Engagement findOneById(Long id);
}
