package com.example.zktraining.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver(DelegatingVariableResolver.class)
public class AddCarController extends SelectorComposer<Component> {
    @Wire
    Window window;
    @Listen("onClick = #closeBtn")
    public void showModal(Event event){
        window.detach();
    }
}
