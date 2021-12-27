package top.ooovo.framework.common.util.collection;

/**
 * * Set 工具类
 * @author: GJH
 * @date: 2021/12/6 21:03
 * @description:
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetUtils {

    public static <T> Set<T> asSet(T... objs) {
        return new HashSet<>(Arrays.asList(objs));
    }

}
