package com.example.demo.util.commonUtil;

import java.lang.reflect.Array;
import java.util.*;

public class CollectionUtil {
    private CollectionUtil() {
    }

    public static <T> String join(Iterable<T> collection, String conjunction) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;

        Object item;
        for(Iterator var4 = collection.iterator(); var4.hasNext(); sb.append(item)) {
            item = var4.next();
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }
        }

        return sb.toString();
    }

    public static <T> String join(T[] array, String conjunction) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        Object[] var4 = array;
        int var5 = array.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            T item = (T) var4[var6];
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(conjunction);
            }

            sb.append(item);
        }

        return sb.toString();
    }

    public static List<Map.Entry<Long, Long>> sortEntrySetToList(Set<Map.Entry<Long, Long>> set) {
        List<Map.Entry<Long, Long>> list = new LinkedList(set);
        Collections.sort(list, new Comparator<Map.Entry<Long, Long>>() {
            @Override
            public int compare(Map.Entry<Long, Long> o1, Map.Entry<Long, Long> o2) {
                if ((Long)o1.getValue() > (Long)o2.getValue()) {
                    return 1;
                } else {
                    return (Long)o1.getValue() < (Long)o2.getValue() ? -1 : 0;
                }
            }
        });
        return list;
    }

    public static <T> List<T> popPart(Stack<T> surplusAlaDatas, int partSize) {
        if (surplusAlaDatas != null && surplusAlaDatas.size() > 0) {
            List<T> currentAlaDatas = new ArrayList();
            int size = surplusAlaDatas.size();
            int i;
            if (size > partSize) {
                for(i = 0; i < partSize; ++i) {
                    currentAlaDatas.add(surplusAlaDatas.pop());
                }
            } else {
                for(i = 0; i < size; ++i) {
                    currentAlaDatas.add(surplusAlaDatas.pop());
                }
            }

            return currentAlaDatas;
        } else {
            return null;
        }
    }

    public static <T> List<T> popPart(Deque<T> surplusAlaDatas, int partSize) {
        if (surplusAlaDatas != null && surplusAlaDatas.size() > 0) {
            List<T> currentAlaDatas = new ArrayList();
            int size = surplusAlaDatas.size();
            int i;
            if (size > partSize) {
                for(i = 0; i < partSize; ++i) {
                    currentAlaDatas.add(surplusAlaDatas.pop());
                }
            } else {
                for(i = 0; i < size; ++i) {
                    currentAlaDatas.add(surplusAlaDatas.pop());
                }
            }

            return currentAlaDatas;
        } else {
            return null;
        }
    }

    public static <T, K> HashMap<T, K> newHashMap() {
        return new HashMap();
    }

    public static <T, K> HashMap<T, K> newHashMap(int size) {
        return new HashMap((int)((double)size / 0.75D));
    }

    public static <T> HashSet<T> newHashSet() {
        return new HashSet();
    }

    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... ts) {
        HashSet<T> set = new HashSet();
        Object[] var2 = ts;
        int var3 = ts.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            T t = (T) var2[var4];
            set.add(t);
        }

        return set;
    }

    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList();
    }

    public static <T> ArrayList<T> newArrayList(T... values) {
        return new ArrayList(Arrays.asList(values));
    }

    public static <T> T[] append(T[] buffer, T newElement) {
        T[] t = resize(buffer, buffer.length + 1, newElement.getClass());
        t[buffer.length] = newElement;
        return t;
    }

    public static <T> T[] resize(T[] buffer, int newSize, Class<?> componentType) {
        T[] newArray = newArray(componentType, newSize);
        System.arraycopy(buffer, 0, newArray, 0, buffer.length >= newSize ? newSize : buffer.length);
        return newArray;
    }

    public static <T> T[] newArray(Class<?> componentType, int newSize) {
        return (T[]) Array.newInstance(componentType, newSize);
    }

    public static <T> T[] resize(T[] buffer, int newSize) {
        return resize(buffer, newSize, buffer.getClass().getComponentType());
    }

    public static <T> T[] addAll(T[]... arrays) {
        if (arrays.length == 1) {
            return arrays[0];
        } else {
            int length = 0;
            Object[][] var2 = arrays;
            int var3 = arrays.length;

            int var4;
            for(var4 = 0; var4 < var3; ++var4) {
                T[] array = (T[]) var2[var4];
                if (array != null) {
                    length += array.length;
                }
            }

            T[] result = newArray(arrays.getClass().getComponentType().getComponentType(), length);
            length = 0;
            Object[][] var8 = arrays;
            var4 = arrays.length;

            for(int var9 = 0; var9 < var4; ++var9) {
                T[] array = (T[]) var8[var9];
                if (array != null) {
                    System.arraycopy(array, 0, result, length, array.length);
                    length += array.length;
                }
            }

            return result;
        }
    }

    public static <T> T[] clone(T[] array) {
        return array == null ? null : (T[]) array.clone();
    }

    public static int[] range(int excludedEnd) {
        return range(0, excludedEnd, 1);
    }

    public static int[] range(int includedStart, int excludedEnd) {
        return range(includedStart, excludedEnd, 1);
    }

    public static int[] range(int includedStart, int excludedEnd, int step) {
        int deviation;
        if (includedStart > excludedEnd) {
            deviation = includedStart;
            includedStart = excludedEnd;
            excludedEnd = deviation;
        }

        if (step <= 0) {
            step = 1;
        }

        deviation = excludedEnd - includedStart;
        int length = deviation / step;
        if (deviation % step != 0) {
            ++length;
        }

        int[] range = new int[length];

        for(int i = 0; i < length; ++i) {
            range[i] = includedStart;
            includedStart += step;
        }

        return range;
    }

    public static <T> List<T> sub(List<T> list, int start, int end) {
        if (list != null && !list.isEmpty()) {
            if (start < 0) {
                start = 0;
            }

            if (end < 0) {
                end = 0;
            }

            int size;
            if (start > end) {
                size = start;
                start = end;
                end = size;
            }

            size = list.size();
            if (end > size) {
                if (start >= size) {
                    return null;
                }

                end = size;
            }

            return list.subList(start, end);
        } else {
            return null;
        }
    }

    public static <T> List<T> sub(Collection<T> list, int start, int end) {
        return list != null && !list.isEmpty() ? sub((List)(new ArrayList(list)), start, end) : null;
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static <T> boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static <T, K> Map<T, K> zip(T[] keys, K[] values) {
        if (!isEmpty(keys) && !isEmpty(values)) {
            int size = Math.min(keys.length, values.length);
            Map<T, K> map = new HashMap((int)((double)size / 0.75D));

            for(int i = 0; i < size; ++i) {
                map.put(keys[i], values[i]);
            }

            return map;
        } else {
            return null;
        }
    }

//    public static Map<String, String> zip(String keys, String values, String delimiter) {
//        return zip((Object[]) ccs.enrising.common.utils.StringUtil.split(keys, delimiter), (Object[]) ccs.enrising.common.utils.StringUtil.split(values, delimiter));
//    }

    public static <T, K> Map<T, K> zip(Collection<T> keys, Collection<K> values) {
        if (!isEmpty(keys) && !isEmpty(values)) {
            List<T> keyList = new ArrayList(keys);
            List<K> valueList = new ArrayList(values);
            int size = Math.min(keys.size(), values.size());
            Map<T, K> map = new HashMap((int)((double)size / 0.75D));

            for(int i = 0; i < size; ++i) {
                map.put(keyList.get(i), valueList.get(i));
            }

            return map;
        } else {
            return null;
        }
    }

    public static <T> boolean contains(T[] array, T value) {
        Class<?> componetType = array.getClass().getComponentType();
        boolean isPrimitive = false;
        if (null != componetType) {
            isPrimitive = componetType.isPrimitive();
        }

        Object[] var4 = array;
        int var5 = array.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            T t = (T) var4[var6];
            if (t == value) {
                return true;
            }

            if (!isPrimitive && null != value && value.equals(t)) {
                return true;
            }
        }

        return false;
    }

    public static <T, K> HashMap<T, K> toMap(Collection<Map.Entry<T, K>> entryCollection) {
        HashMap<T, K> map = new HashMap();
        Iterator var2 = entryCollection.iterator();

        while(var2.hasNext()) {
            Map.Entry<T, K> entry = (Map.Entry)var2.next();
            map.put(entry.getKey(), entry.getValue());
        }

        return map;
    }

    public static <T> TreeSet<T> toTreeSet(Collection<T> collection, Comparator<T> comparator) {
        TreeSet<T> treeSet = new TreeSet(comparator);
        Iterator var3 = collection.iterator();

        while(var3.hasNext()) {
            T t = (T) var3.next();
            treeSet.add(t);
        }

        return treeSet;
    }

    public static <T> List<T> sort(Collection<T> collection, Comparator<T> comparator) {
        List<T> list = new ArrayList(collection);
        Collections.sort(list, comparator);
        return list;
    }

    public static Integer[] wrap(int... values) {
        int length = values.length;
        Integer[] array = new Integer[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Long[] wrap(long... values) {
        int length = values.length;
        Long[] array = new Long[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Character[] wrap(char... values) {
        int length = values.length;
        Character[] array = new Character[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Byte[] wrap(byte... values) {
        int length = values.length;
        Byte[] array = new Byte[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Short[] wrap(short... values) {
        int length = values.length;
        Short[] array = new Short[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Float[] wrap(float... values) {
        int length = values.length;
        Float[] array = new Float[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Double[] wrap(double... values) {
        int length = values.length;
        Double[] array = new Double[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static Boolean[] wrap(boolean... values) {
        int length = values.length;
        Boolean[] array = new Boolean[length];

        for(int i = 0; i < length; ++i) {
            array[i] = values[i];
        }

        return array;
    }

    public static boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }

    public static String toString(Object obj) {
        if (null == obj) {
            return null;
        } else if (isArray(obj)) {
            try {
                return Arrays.deepToString((Object[])((Object[])obj));
            } catch (Exception var3) {
                String className = obj.getClass().getComponentType().getName();
                if ("long".equals(className)) {
                    return Arrays.toString((long[])((long[])obj));
                } else if ("int".equals(className)) {
                    return Arrays.toString((int[])((int[])obj));
                } else if ("short".equals(className)) {
                    return Arrays.toString((short[])((short[])obj));
                } else if ("char".equals(className)) {
                    return Arrays.toString((char[])((char[])obj));
                } else if ("byte".equals(className)) {
                    return Arrays.toString((byte[])((byte[])obj));
                } else if ("boolean".equals(className)) {
                    return Arrays.toString((boolean[])((boolean[])obj));
                } else if ("float".equals(className)) {
                    return Arrays.toString((float[])((float[])obj));
                } else if ("double".equals(className)) {
                    return Arrays.toString((double[])((double[])obj));
                } else {
                    throw new RuntimeException(var3);
                }
            }
        } else {
            return obj.toString();
        }
    }
}
