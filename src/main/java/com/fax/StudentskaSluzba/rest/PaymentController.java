package com.fax.StudentskaSluzba.rest;

import com.fax.StudentskaSluzba.mapper.PaymentMapper;
import com.fax.StudentskaSluzba.model.Payment;
import com.fax.StudentskaSluzba.model.Student;
import com.fax.StudentskaSluzba.modeldto.PaymentDTO;
import com.fax.StudentskaSluzba.modeldto.StudentDTO;
import com.fax.StudentskaSluzba.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping( value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE )
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentMapper paymentMapper;

    @RequestMapping(value = "/student/{id}",method = RequestMethod.GET)
    public ResponseEntity<List<PaymentDTO>> getAllStudentsByCourse(@PathVariable("id") Long id){
       try {
           List<Payment> payments=paymentService.getPaymentsByStudentId(id);
           return new ResponseEntity<List<PaymentDTO>>(paymentMapper.toListPaymentDTO(payments)
                   , HttpStatus.OK);
       }catch (Exception e){
           e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public ResponseEntity<PaymentDTO> insertPayment(@RequestBody PaymentDTO paymentDTO){
        System.out.println("doslo sa fronta :" + paymentDTO.toString());
        Payment payment=paymentMapper.toPayment(paymentDTO);
        payment.getStudent().setId(paymentDTO.getStudent().getId());
        System.out.println("doslo prebaceno u Payment "+payment.toString());
        try{
            payment=paymentService.insertPayment(payment);
            return new ResponseEntity<>(paymentMapper.toPaymentDTO(payment),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
