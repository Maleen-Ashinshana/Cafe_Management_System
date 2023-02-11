package lk.ijse.cafe.service.custom.impl;

import lk.ijse.cafe.controller.DashBordFormController;
import lk.ijse.cafe.dao.custom.PaymentDAO;
import lk.ijse.cafe.dao.util.DAOFactory;
import lk.ijse.cafe.dao.util.DaoTypes;
import lk.ijse.cafe.db.DBConnection;
import lk.ijse.cafe.dto.PaymentDTO;
import lk.ijse.cafe.dto.PaymentDTOS;
import lk.ijse.cafe.service.custom.PaymentService;
import lk.ijse.cafe.service.exception.DuplicateException;
import lk.ijse.cafe.service.util.Convertor;

import java.sql.Connection;
import java.sql.SQLException;

public class PaymentServiceIMPL implements PaymentService {
    private  final PaymentDAO paymentDAO;
    private  final Convertor  convertor;
    private  final Connection connection;

    public PaymentServiceIMPL() throws SQLException, ClassNotFoundException {
        connection= DBConnection.getInstance().getConnection();
        paymentDAO= DAOFactory.getInstance().getDAO(connection, DaoTypes.PAYMENT);
        convertor=new Convertor();
    }

    @Override
    public PaymentDTOS savePayment(PaymentDTOS paymentDTOS) throws DuplicateException {
        System.out.println(paymentDTOS.getId()+"  utto");
        if (paymentDAO.existByPk(paymentDTOS.getId()))throw  new DuplicateException("Payment Made!");
        paymentDAO.save(convertor.toPayment(paymentDTOS));
        return paymentDTOS;
    }

    @Override
    public String generateNextId(String PrefId, String LsatId) {
        if (LsatId!=null){
            int newId=Integer.parseInt(LsatId.replace(PrefId,""))+1;
           return String.format(PrefId+"%0000000003d",newId);

        }else {
            return PrefId+000000000;
        }
    }

//    @Override
//    public PaymentDTO savePayment(PaymentDTO paymentDTO) throws DuplicateException {
//        System.out.println(paymentDTO.getId()+"utto");
//        if (paymentDAO.existByPk(paymentDTO.getId()))throw new DuplicateException("Payment made! ");
//        paymentDAO.save(convertor.toPayment(paymentDTO));
//        return paymentDTO;
//    }

}
