package lk.ijse.cafe.service.custom;


import lk.ijse.cafe.dto.PaymentDTO;
import lk.ijse.cafe.dto.PaymentDTOS;
import lk.ijse.cafe.service.SuperSevice;
import lk.ijse.cafe.service.exception.DuplicateException;

public interface PaymentService extends SuperSevice {
    //public PaymentDTO savePayment(PaymentDTO paymentDTO)throws DuplicateException;
    public PaymentDTOS savePayment(PaymentDTOS paymentDTOS) throws DuplicateException;
    public String generateNextId(String PrefId,String LsatId);
}
