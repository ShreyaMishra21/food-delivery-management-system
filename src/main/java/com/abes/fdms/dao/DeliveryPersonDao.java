package com.abes.fdms.dao;

import com.abes.fdms.dto.DeliveryPersonDto;
import java.util.Map;

public interface DeliveryPersonDao {
    Map<String, DeliveryPersonDto> getDeliveryPersons();
    void addDeliveryPerson(DeliveryPersonDto dp);
    void removeDeliveryPerson(String id);
    DeliveryPersonDto getDeliveryPersonById(String id);
}
