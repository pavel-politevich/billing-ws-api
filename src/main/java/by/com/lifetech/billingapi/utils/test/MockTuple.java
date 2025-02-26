package by.com.lifetech.billingapi.utils.test;

import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockTuple implements Tuple {
    private final Map<String, Object> data = new HashMap<>();

    public void set(String alias, Object value) {
        data.put(alias, value);
    }

    @Override
    public <X> X get(TupleElement<X> tupleElement) {
        return null;
    }

    @Override
    public <X> X get(String alias, Class<X> type) {
        return type.cast(data.get(alias));
    }

    @Override
    public Object get(String alias) {
        return data.get(alias);
    }

    @Override
    public <X> X get(int i, Class<X> type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object get(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public List<TupleElement<?>> getElements() {
        throw new UnsupportedOperationException();
    }
}
