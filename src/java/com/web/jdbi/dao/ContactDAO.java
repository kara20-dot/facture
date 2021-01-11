package com.web.jdbi.dao;

import com.web.jdbi.mapper.ContactMapper;
import com.web.jdbi.dto.ContactDTO;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface ContactDAO {

    @Mapper(ContactMapper.class)
    @SqlQuery("select * from contact where id = :id")
    ContactDTO getContactById(@Bind("id") int id);

    @GetGeneratedKeys
    @SqlUpdate("insert into contact (id, firstName, lastName, phone) values (NULL, :firstName, :lastName, :phone)")
    int createContact(@Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("phone") String phone);

    @SqlUpdate("update contact set firstName = :firstName, lastName = :lastName, phone = :phone where id = :id")
    void updateContact(@Bind("id") int id, @Bind("firstName") String firstName, @Bind("lastName") String lastName, @Bind("phone") String phone);

    @SqlUpdate("delete from contact where id = :id")
    void deleteContact(@Bind("id") int id);

    @Mapper(ContactMapper.class)
    @SqlQuery("select * from contact where id > 0 ORDER BY id DESC")
    List<ContactDTO> findAll();
}
