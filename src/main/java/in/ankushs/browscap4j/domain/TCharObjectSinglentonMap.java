package in.ankushs.browscap4j.domain;

import gnu.trove.function.TObjectFunction;
import gnu.trove.iterator.TCharObjectIterator;
import gnu.trove.map.TCharObjectMap;
import gnu.trove.procedure.TCharObjectProcedure;
import gnu.trove.procedure.TCharProcedure;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.set.TCharSet;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Ankush on 11/03/17.
 */
public class TCharObjectSinglentonMap implements TCharObjectMap {

    @Override
    public char getNoEntryKey() {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(char key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(char key) {
        return null;
    }

    @Override
    public Object put(char key, Object value) {
        return null;
    }

    @Override
    public Object putIfAbsent(char key, Object value) {
        return null;
    }

    @Override
    public Object remove(char key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void putAll(TCharObjectMap map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public TCharSet keySet() {
        return null;
    }

    @Override
    public char[] keys() {
        return new char[0];
    }

    @Override
    public char[] keys(char[] array) {
        return new char[0];
    }

    @Override
    public Collection valueCollection() {
        return null;
    }

    @Override
    public Object[] values() {
        return new Object[0];
    }

    @Override
    public Object[] values(Object[] array) {
        return new Object[0];
    }

    @Override
    public TCharObjectIterator iterator() {
        return null;
    }

    @Override
    public boolean forEachKey(TCharProcedure procedure) {
        return false;
    }

    @Override
    public boolean forEachValue(TObjectProcedure procedure) {
        return false;
    }

    @Override
    public boolean forEachEntry(TCharObjectProcedure procedure) {
        return false;
    }

    @Override
    public void transformValues(TObjectFunction function) {

    }

    @Override
    public boolean retainEntries(TCharObjectProcedure procedure) {
        return false;
    }
}
