
package hash;


public class ItemDic {
    private Object k;
    private Object element;

    public ItemDic(Object k, Object element) {
        this.k = k;
        this.element = element;
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
    
    
}
