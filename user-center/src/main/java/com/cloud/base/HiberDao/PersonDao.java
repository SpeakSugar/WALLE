package com.cloud.base.HiberDao;

import com.cloud.base.model.Person;

import java.util.List;

public interface PersonDao {
    // 新增记录
    void save(Person person);
    // 修改记录
    void update(Person person);
    // 根据用户名查询
    Person findPersonByID(Integer id);
    // 查询所有记录
    List<Person> findPersonsNoPage();
    // 删除一条记录
    void delete(Person person);

}
