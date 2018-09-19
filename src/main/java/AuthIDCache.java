

import java.util.LinkedHashMap;

/**
* @author husan
* @Date 2014-9-26
* @description: AuthID缓存：不考虑并发造成的缓存失效。LRU算法最大容量10万，缓存有效时间20分钟
*/

public class AuthIDCache {

    private static AuthIDCache cache = new AuthIDCache();

    /*
     * 缓存最大容量 20万,maybe20M momory
     */
    private final int capacity = 200000;

    /*
     * 缓存有效时间 20分钟
     */
    private final long expireTime = 20 * 60 * 1000;

    private final LRULinkedHashMap<String, Long> map;

    private AuthIDCache() {
        map = new LRULinkedHashMap<String, Long>(capacity);
    }

    public boolean hasAuthID(String authId) {
        Long timeStap = map.get(authId);
        if (timeStap == null) {
            return false;
        }
        long now = System.currentTimeMillis();
        if ((timeStap + expireTime) > now) {
            return true;
        } else {
            // 缓存失效
            map.remove(authId);
            return false;
        }
    }

    public void setAuthID(String authId) {
        map.put(authId, System.currentTimeMillis());

    }

    public static AuthIDCache getInstance() {
        return cache;
    }

    /*
     * public static void main(String[] args) throws Exception { Random random = new Random();
     * Runtime rt = Runtime.getRuntime();
     * System.out.println(String.format("Total memory = %s, Free memeory=%s,used memory=%s",
     * rt.totalMemory(), rt .freeMemory(), (rt.totalMemory() - rt.freeMemory()))); for (int i = 0; i
     * < 10000000; i++) { getInstance().setAuthID(String.valueOf(random.nextInt(1000000))); }
     * System.out.println(String.format("Total memory = %s, Free memeory=%s,used memory=%s",
     * rt.totalMemory(), rt .freeMemory(), (rt.totalMemory() - rt.freeMemory()))); }
     */

}

class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    /*
     * 缓存初始大小
     */
    private static final int INIT_SIZE = 1024 * 8;

    private final int capacity;

    LRULinkedHashMap(int capacity) {
        super(INIT_SIZE);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        // TODO Auto-generated method stub
        return size() > capacity;
    }

}