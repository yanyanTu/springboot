package com.spring.recipes.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.util.StringUtils;

import javax.swing.*;

public class JsoupUtil {

    private static final Whitelist whiteList = Whitelist.basicWithImages();

    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    static {
        whiteList.addAttributes(":all", "style");
    }

    public static String clean(String name) {
        if( !StringUtils.isEmpty(name)){
            name = name.trim();
        }
        return Jsoup.clean(name, "", whiteList, outputSettings);
    }
}
