package lk.ijse.cafe.service.util;

import lk.ijse.cafe.dto.*;
import lk.ijse.cafe.entity.*;

import java.sql.Date;

public class Convertor {
    public ItemDTO fromItem(ItemEntity itemEntity){
        return new ItemDTO(itemEntity.getCode(),itemEntity.getDescription(),itemEntity.getUnit_price());

    }
    public ItemEntity toItem(ItemDTO itemDTO){
        return  new ItemEntity(itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnit_price());
    }

    public EmployeDTO fromEmploy(EmployeEntity employeEntity) {
        return new EmployeDTO(employeEntity.getId(), employeEntity.getName(), employeEntity.getAddress(), employeEntity.getEmail(), employeEntity.getContact_number(), employeEntity.getGender(), employeEntity.getType(), employeEntity.getSalary());
    }

    public EmployeEntity toEmploy(EmployeDTO employeDTO){
        return new EmployeEntity(employeDTO.getEmploye_id(), employeDTO.getName(), employeDTO.getAddress(), employeDTO.getEmail(), employeDTO.getContact_num(), employeDTO.getGender(), employeDTO.getType(), employeDTO.getSalary());
    }

    public CustomerDTO fromCustomer(CustomerEntity customerEntity){
        return new CustomerDTO(customerEntity.getCustomer_id(), customerEntity.getName());
    }

    public CustomerEntity toCustomer(CustomerDTO customerDTO){
        return new CustomerEntity(customerDTO.getCustomer_id(), customerDTO.getName());
    }

    public OrderDetailsDTO fromOrderDetails(OrderDetailsEntity orderDetailsEntity){
        return new OrderDetailsDTO(orderDetailsEntity.getOrder_id(),orderDetailsEntity.getItem_code(),orderDetailsEntity.getQty(),orderDetailsEntity.getUnitPrice(),String.valueOf(orderDetailsEntity.getDate()));
    }

    public OrderDetailsEntity toOrderDetails(OrderDetailsDTO orderDetailsDTO){
        System.out.println(orderDetailsDTO.getItem_code());
        return new OrderDetailsEntity(orderDetailsDTO.getOrder_id(),orderDetailsDTO.getItem_code(),orderDetailsDTO.getQty(),orderDetailsDTO.getUnitPrice(),String.valueOf(orderDetailsDTO.getDate()));
    }

    public OrderDTO fromOrder(OrderEntity orderEntity){
        return  new OrderDTO(orderEntity.getOrder_id(),orderEntity.getDate(),orderEntity.getCustomer_id());
    }

    public OrderEntity toOrder(OrderDTO orderDTO){
        return new OrderEntity(orderDTO.getOrder_id(), String.valueOf(orderDTO.getDate()) ,orderDTO.getCustomer_id());
    }

    public PaymentDTOS fromPayment(PaymentEntitys paymentEntitys){
         return  new PaymentDTOS(paymentEntitys.getId(), paymentEntitys.getDate(), paymentEntitys.getPrice(), paymentEntitys.getOrder_id(), paymentEntitys.getCustomer_id(), paymentEntitys.getCustomer_name());
    }
    public  PaymentEntitys toPayment(PaymentDTOS paymentDTOS){
        //return new PaymnetEntity(paymentDTOS.getId(),paymentDTOS.getDate(), paymentDTOS.getPrice(), paymentDTOS.getOrder_id(), paymentDTOS.getCustomer_id(), paymentDTOS.getCustomer_name());
    return new PaymentEntitys(paymentDTOS.getId(), paymentDTOS.getDate(), paymentDTOS.getPrice(), paymentDTOS.getOrder_id(), paymentDTOS.getCustomer_id(), paymentDTOS.getCustomer_name());
    }

    public StokeDTO froStoke(StokeEntity  stokeEntity){
        return new StokeDTO(stokeEntity.getStoke_id(),String.valueOf(stokeEntity.getDate()),stokeEntity.getSupplyer_id());
    }

    public StokeEntity toStoke(StokeDTO stokeDTO){
        return new StokeEntity(stokeDTO.getStoke_id(),String.valueOf(stokeDTO.getDate()),stokeDTO.getSupplyer_id());
    }

    public StokeDetailsDTO fromStokeDetails(StokeDetailsEntity stokeDetailsEntity){
        return new StokeDetailsDTO(stokeDetailsEntity.getStoke_id(),stokeDetailsEntity.getStoke_item_id(),stokeDetailsEntity.getUnitPrice(),stokeDetailsEntity.getQty());
    }

    public StokeDetailsEntity stokeDetails(StokeDetailsDTO stokeDetailsDTO){
        return new StokeDetailsEntity(stokeDetailsDTO.getStoke_id(),stokeDetailsDTO.getStoke_item_id(),stokeDetailsDTO.getUnitPrice(),stokeDetailsDTO.getQty());
    }

    public StokeItemsDTO fromStokeItems(StokeItemsEntity stokeItemsEntity){
        return new StokeItemsDTO(stokeItemsEntity.getId(),stokeItemsEntity.getDescription(),stokeItemsEntity.getUnitPrice(),stokeItemsEntity.getQty());
    }

    public StokeItemsEntity tostokeItems(StokeItemsDTO stokeItemsDTO){
        return new StokeItemsEntity(stokeItemsDTO.getId(),stokeItemsDTO.getDescription(),stokeItemsDTO.getUnitPrice(),stokeItemsDTO.getQty());
    }

    public SupployerDTO fromSupployer(SupployerEntity  supployerEntity){
        return new SupployerDTO(supployerEntity.getSupplyer_id(),supployerEntity.getName(),supployerEntity.getAddress());
    }

    public SupployerEntity toSupployer(SupployerDTO supployerDTO){
        return  new SupployerEntity(supployerDTO.getSupplyer_id(),supployerDTO.getName(),supployerDTO.getAddress());
    }

    public SystemUsesDTO fromSystemUses(SystemUsesEntity systemUsesEntity){
        return new SystemUsesDTO(systemUsesEntity.getSystem_user_id(),systemUsesEntity.getName(),systemUsesEntity.getAddress(),systemUsesEntity.getContact_number(),systemUsesEntity.getType(),systemUsesEntity.getEmail(),systemUsesEntity.getPassword());
    }

    public SystemUsesEntity toSystemUses(SystemUsesDTO systemUsesDTO){
        return new SystemUsesEntity(systemUsesDTO.getSystem_user_id(),systemUsesDTO.getName(),systemUsesDTO.getAddress(),systemUsesDTO.getContact_number(),systemUsesDTO.getType(),systemUsesDTO.getEmail(),systemUsesDTO.getPassword());
    }

}

