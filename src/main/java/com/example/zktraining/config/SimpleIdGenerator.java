package com.example.zktraining.config;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.sys.IdGenerator;

public class SimpleIdGenerator implements IdGenerator {
    @Override
    public String nextComponentUuid(Desktop desktop, Component comp, ComponentInfo compInfo) {
        int i = Integer.parseInt(desktop.getAttribute(INDEX_KEY).toString());
        i++;// Start from 1
        desktop.setAttribute(INDEX_KEY, String.valueOf(i));

        return PREFIX + i;
    }

    @Override
    public String nextPageUuid(Page page) {
        return null;
    }

    @Override
    public String nextDesktopId(Desktop desktop) {
        System.out.println("new Desktop");
        if (desktop.getAttribute(INDEX_KEY) == null) {
            desktop.setAttribute(INDEX_KEY, "0");
        }
        return null;
    }
    private static final String PREFIX = "zk_comp_";
    private static final String INDEX_KEY = "Id_Num";

    public String nextComponentUuid(Desktop desktop, Component comp) {
        int i = Integer.parseInt(desktop.getAttribute(INDEX_KEY).toString());
        i++;// Start from 1
        desktop.setAttribute(INDEX_KEY, String.valueOf(i));

        return PREFIX + i;
    }

}