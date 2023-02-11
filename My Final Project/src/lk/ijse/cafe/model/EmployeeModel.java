//package lk.ijse.cafe.model;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import lk.ijse.cafe.db.DBConnection;
//import lk.ijse.cafe.to.Employee;
//import lk.ijse.cafe.util.CrudUtil;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class EmployeeModel {
//    public EmployeeModel() {
//
//    }
////    public static boolean add(Employee employee) throws SQLException, ClassNotFoundException {
//////        String sql="INSERT INTO Employe VALUES (?,?,?,?,?,?,?,?)";
//////        return CrudUtil.execute(sql,employee.getId(),employee.getName(),employee.getAddress(),employee.getEmail(),employee.getTel(),employee.getGender(),employee.getType(),employee.getSalary());
////
////      //  return  CrudUtil.execute("INSERT INTO Employe VALUES(?,?,?,?,?,?,?,?)",
//////        employee.getId(),
//////        employee.getName(),
//////        employee.getAddress(),
//////        employee.getEmail(),
//////        employee.getTel(),
//////        employee.getGender(),
//////        employee.getType(),
//////        employee.getSalary()
////         return CrudUtil.execute("INSERT INTO employe VALUES(?,?,?,?,?,?,?,?)",
////        employee.getId(),
////        employee.getName(),
////        employee.getAddress(),
////        employee.getEmail(),
////        employee.getTel(),
////        employee.getGender(),
////        employee.getType(),
////        employee.getSalary()
////                );
////    }
//    public static Employee searchEmployee(String id) throws SQLException, ClassNotFoundException {
//        String sql="SELECT * FROM Employe WHERE employe_id = ?";
//        ResultSet result=CrudUtil.execute(sql,id);
//        if (result.next()){
//            return new Employee(
//            result.getString(1),
//            result.getString(2),
//            result.getString(3),
//            result.getString(4),
//            result.getInt(5),
//            result.getString(6),
//            result.getString(7),
//            result.getDouble(8)
//                    );
//        }
//        return null;
//    }
//    public static boolean updateEmploye(Employee employee) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute("Update Employe set name=?,address=?,email=?,contact_num=?,gender=?,type=?,salary=? where employe_id=?",
//        employee.getName(),
//        employee.getAddress(),
//        employee.getEmail(),
//        employee.getTel(),
//        employee.getGender(),
//        employee.getType(),
//        employee.getSalary(),
//        employee.getId()
//                );
//    }
//
//    public static boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
//        return CrudUtil.execute("Delete From employe where employe_id='"+id+"'");
//    }
//
//    public static ObservableList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
//        String sql="select * from employe";
//        ResultSet result=CrudUtil.execute(sql);
//        ObservableList<Employee> employeeList= FXCollections.observableArrayList();
//        while (result.next()){
//            Employee employee=new Employee(result.getString("employe_id"),result.getString("name"),result.getString("address"),result.getString("email"),result.getInt("contact_num"),result.getString("gender"),result.getString("type"),result.getDouble("salary"));
//            employeeList.add(employee);
//        }
//        return employeeList;
//
//    }
//}
