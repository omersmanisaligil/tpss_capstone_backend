package com.tpss.ThirdPartySupplierSelection.dao;

import com.tpss.ThirdPartySupplierSelection.dto.ProviderDTO;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface ProviderDAOCustom{

    List<ProviderDTO> filterData(HashMap<String,Object> filters);

}
