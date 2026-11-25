package com.companyname.springapp.web.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.web.servlet.ModelAndView;

import com.companyname.springapp.business.SpringappBusinessConfig;
import com.companyname.springapp.web.SpringappWebConfig;

@SpringJUnitWebConfig(classes = { SpringappBusinessConfig.class, SpringappWebConfig.class })
class InventoryControllerTests {

    @Autowired
    private InventoryController controller;

    @Test
    @SuppressWarnings("unchecked")
    void testHandleRequestView() {
        ModelAndView modelAndView = controller.handleRequest();
        assertEquals("hello", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        Map<String, Object> modelMap = (Map<String, Object>) modelAndView.getModel().get("model");
        String nowValue = (String) modelMap.get("now");
        assertNotNull(nowValue);
    }
}