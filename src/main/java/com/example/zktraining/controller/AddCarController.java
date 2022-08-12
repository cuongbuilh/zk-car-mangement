package com.example.zktraining.controller;

import com.example.zktraining.dto.CarDTO;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver(DelegatingVariableResolver.class)
public class AddCarController extends SelectorComposer<Component> {
    @Wire
    Window window;

    @Getter
            @Setter
    CarDTO car;
    @Listen("onClick = #closeBtn")
    public void showModal(Event event){
        window.detach();
    }
}
