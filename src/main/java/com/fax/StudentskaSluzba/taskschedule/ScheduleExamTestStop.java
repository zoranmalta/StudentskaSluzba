package com.fax.StudentskaSluzba.taskschedule;

import com.fax.StudentskaSluzba.mapper.ExamTestMapper;
import com.fax.StudentskaSluzba.modeldto.ExamTestDTO;
import com.fax.StudentskaSluzba.service.ExamTestService;
import com.fax.StudentskaSluzba.service.serviceImpl.WebSocketSerivce;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ScheduleExamTestStop implements Runnable {

    private ExamTestService examTestService;
    private ExamTestDTO examTestDTO;
    private ExamTestMapper examTestMapper;
    private WebSocketSerivce webSocketSerivce;

    @Override
    public void run() {
        try {
            examTestDTO.setDostupno(true);
            examTestDTO.setZavrseno(true);
            System.out.println("Objekat za update(zavrsen test): "+examTestDTO);
            examTestService.save(examTestMapper.toExamTest(examTestDTO));
            webSocketSerivce.sendMessages(examTestDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
