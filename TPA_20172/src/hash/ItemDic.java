
package hash;


public class ItemDic {
    private Object k;
    private Object element;
    public int hash;
    public ItemDic(Object k, Object element) {
        this.k = k;
        this.element = element;
    }

    ItemDic(Object k, Object o, int h) {
        this.k = k;
        this.element = element;
        hash = h;
    }

    public Object getKey() {
        return k;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "NO_SUCH_KEY";
    }
    
    
}
