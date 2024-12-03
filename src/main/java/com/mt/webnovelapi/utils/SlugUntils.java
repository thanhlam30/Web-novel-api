package com.mt.webnovelapi.utils;

import java.text.Normalizer;

public class SlugUntils {
    public static String generateSlugFromName(String name){
        if (name == null || name.isEmpty()){
            return null;
        }
        name = name.replace("đ", "d").replace("Đ", "D");
        String slug = Normalizer.normalize(name, Normalizer.Form.NFD);
        slug = slug.toLowerCase().trim().replaceAll("[\\s+]", "-").replaceAll("[^a-z0-9-]", "");
        return slug;
    }
}
