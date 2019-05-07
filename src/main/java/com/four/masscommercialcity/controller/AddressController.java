package com.four.masscommercialcity.controller;

import com.four.masscommercialcity.Mapper.AddressMapper;
import com.four.masscommercialcity.bean.Address;
import com.four.masscommercialcity.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class AddressController {
    @Autowired
    AddressMapper addressMapper;

    @PostMapping("/address")
    public String addAddress(Address address, HttpSession session, Map<String, Object> maps) {
        User user = (User) session.getAttribute("user");
        address.setUid(user.getUid());
        addressMapper.addAddress(address);

        ArrayList<Address> addresses = addressMapper.selectAddress(user.getUid());
        maps.put("address", addresses);
        return "/order/order";
    }+
}
