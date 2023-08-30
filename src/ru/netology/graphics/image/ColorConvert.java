package ru.netology.graphics.image;

import java.util.HashMap;

public class ColorConvert implements TextColorSchema {
    @Override
    public char convert(int color) {
        HashMap<Integer, Character> imageHashmap = new HashMap<>();
        imageHashmap.put(0, '$');
        imageHashmap.put(1, '@');
        imageHashmap.put(2, '%');
        imageHashmap.put(3, '*');
        imageHashmap.put(4, '+');
        imageHashmap.put(5, '~');
        imageHashmap.put(6, '_');
        imageHashmap.put(7, '.');
        return imageHashmap.get(color / 32);
    }
}
