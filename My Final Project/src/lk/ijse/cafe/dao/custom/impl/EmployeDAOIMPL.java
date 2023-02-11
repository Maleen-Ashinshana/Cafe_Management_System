package lk.ijse.cafe.dao.custom.impl;

import lk.ijse.cafe.dao.custom.EmployeDAO;
import lk.ijse.cafe.dao.exception.ConstraintViolationException;
import lk.ijse.cafe.dto.EmployeDTO;
import lk.ijse.cafe.entity.EmployeEntity;
import lk.ijse.cafe.entity.ItemEntity;
import lk.ijse.cafe.service.exception.NotFoundException;
import lk.ijse.cafe.util.CrudUtil;
import org.apache.poi.hssf.record.formula.functions.T;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeDAOIMPL implements EmployeDAO {

    private final Connection connection;

    public EmployeDAOIMPL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public EmployeEntity save(EmployeEntity entity) throws ConstraintViolationException {

        try {
            if (CrudUtil.execute("INSERT INTO employe VALUES(?,?,?,?,?,?,?,?)",
                    entity.getId(),entity.getName(),entity.getAddress(),entity.getEmail(),entity.getContact_number(),entity.getGender(),entity.getType(),entity.getSalary()));
                return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public EmployeEntity update(EmployeEntity entity) throws ConstraintViolationException {
        try {
            if (CrudUtil.execute("Update Employe set name=?,address=?,email=?,contact_num=?,gender=?,type=?,salary=? where employe_id=?",
                   entity.getName(),entity.getAddress(),entity.getEmail(),entity.getContact_number(),entity.getGender(),entity.getType(),entity.getSalary(),entity.getId()));

                return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String code) throws NotFoundException {
        try {
            CrudUtil.execute("Delete From employe where employe_id= '"+code+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EmployeEntity> findAll() {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * from employe");
            return  getEmployeList(rst);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<EmployeEntity> findByPk(String pk) {
        try{
            ResultSet rst = CrudUtil.execute("SELECT * FROM employe WHERE employe_id=?", pk);
            if(rst.next()){
                return Optional.of(new EmployeEntity(rst.getString("employe_id"),rst.getString("name"),rst.getString("address"),rst.getString("email"),rst.getInt("contact_num"),rst.getString("gender"),rst.getString("type"), rst.getDouble("salary")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the book details");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existByPk(String pk) {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM employe WHERE employe_id=?", pk);
            return rst.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeEntity search(EmployeEntity entity) throws ConstraintViolationException {
        try {
            ResultSet rst=CrudUtil.execute("SELECT * FROM employe WHERE employe_id=?");
            if (rst.next()){
                return  new EmployeEntity(rst.getString("employe_id"),rst.getString("name"),rst.getString("address"),rst.getString("email"),rst.getInt("contact_num"),rst.getString("gender"),rst.getString("type"),rst.getDouble("salary"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public long count() {
        try {
            ResultSet rst=CrudUtil.execute("SELECT COUNT(item_code) AS count FROM items");
            rst.next();
            return rst.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private  List<EmployeEntity> getEmployeList(ResultSet rst){
        try {
            List<EmployeEntity> employeList=new ArrayList<>();
            while (rst.next()){
                EmployeEntity employeEntity=new EmployeEntity(rst.getString("employe_id"), rst.getNString("name"), rst.getNString("address"),rst.getString("email"), rst.getInt("contact_num"), rst.getString("gender"), rst.getString("type"), rst.getDouble("salary"));
                employeList.add(employeEntity);
            }
            return  employeList;
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }
}