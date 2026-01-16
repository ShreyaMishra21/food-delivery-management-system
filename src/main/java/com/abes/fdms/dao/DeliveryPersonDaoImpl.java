package com.abes.fdms.dao;

import com.abes.fdms.dto.DeliveryPersonDto;
import com.abes.fdms.util.CollectionUtil;
import java.util.Map;

public class DeliveryPersonDaoImpl implements DeliveryPersonDao {
    @Override
    public Map<String, DeliveryPersonDto> getDeliveryPersons() {
        return CollectionUtil.deliveryPersons;
    }

    @Override
    public void addDeliveryPerson(DeliveryPersonDto dp) {
        CollectionUtil.deliveryPersons.put(dp.getId(), dp);
    }

    @Override
    public void removeDeliveryPerson(String id) {
        CollectionUtil.deliveryPersons.remove(id);
    }

    @Override
    public DeliveryPersonDto getDeliveryPersonById(String id) {
        return CollectionUtil.deliveryPersons.get(id);
    }
}
