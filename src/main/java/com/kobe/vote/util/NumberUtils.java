package com.kobe.vote.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author: kobe.wu
 * @since : 15-1-10
 */
public class NumberUtils {

    public static int parseQuietly(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

    public static List<Integer> toIntList(String str) {
        if (StringUtils.isEmpty(str)) {
            return Lists.newArrayList();
        }
        List<String> itemList = Splitter.on(",").splitToList(str);
        if (CollectionUtils.isEmpty(itemList)) {
            return Lists.newArrayList();
        }
        List<Integer> result = Lists.newArrayList();
        for (String item : itemList) {
            int intV = parseQuietly(item);
            if (intV == -1) {
                continue;
            }
            result.add(intV);
        }
        return result;
    }
}
