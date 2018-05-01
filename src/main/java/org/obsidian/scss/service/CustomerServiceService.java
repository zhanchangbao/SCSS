package org.obsidian.scss.service;

import org.obsidian.scss.entity.CustomerService;

import java.util.List;

/**
 * Created by mobing  on 2017/7/10.
 */
public interface CustomerServiceService {

    int insertCustomerService(String name,int groupId,String nickName,String employeeId,String autoMessage);

    int deleteCustomerService(int serviceId);

    int updateCustomerService(int serviceId,String name,int groupId,String nickName,
                              String employeeId,String autoMessage);

    List<CustomerService> selectAllCustomerService();

    List<CustomerService> selectCustomerServiceByGroup(int groupId);

    CustomerService selectCustomerServiceByServiceId(int serviceId);

    CustomerService selectCustomerServiceByEmployeeId(String employeeId);

    /**
     * create By cjn
     */
    int selectTotalServer();
    List<CustomerService> selectNotDimissionPerson();


    CustomerService selectByEIdAndPwd(String employeeId, String password);

    List<CustomerService> selectBySearchName(String name);
    int updateCustomDimission(int id);

}
