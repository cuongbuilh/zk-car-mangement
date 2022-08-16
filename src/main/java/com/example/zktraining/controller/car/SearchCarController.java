package com.example.zktraining.controller.car;


import com.example.zktraining.dto.client.CarSearchDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.Map;
import java.util.Objects;

@VariableResolver(DelegatingVariableResolver.class)
@Slf4j
public class SearchCarController {
    @Wire
    @WireVariable
    Window modalDialog;

    @Listen("onClick = #closeBtn")
    public void showModal(Event e) {
        modalDialog.detach();
    }

    @Command
    public void close(@BindingParam("window") Window x) {
        x.detach();
    }

    @Getter
    @Setter
    private CarSearchDTO search;


    @Init
    public void init() {
        Map<?, ?> arg = Executions.getCurrent().getArg();
        log.info("args {}", arg);

        if (Objects.nonNull(arg) && arg.containsKey("search")) {
            search = (CarSearchDTO) arg.get("search");
        } else {
            search = new CarSearchDTO();
        }

    }

}
