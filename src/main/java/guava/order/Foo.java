package guava.order;

import javax.annotation.Nullable;

/**
 * <p>
 * </p>
 *
 * @author hukajie
 * @version V1.0
 * @Date 2017/6/17
 * @modify by user: {修改人}
 * @modify by reason:{方法名}:{原因}
 */
public class Foo {
    @Nullable String sortedBy;
    int noSortedby;

    public Foo(String sortedBy, int noSortedby){
        this.sortedBy = sortedBy;
        this.noSortedby = noSortedby;
    }

    @Override
    public String toString() {
        return "sortedBy:"+sortedBy+",nosortedBy:"+noSortedby;
    }
}
