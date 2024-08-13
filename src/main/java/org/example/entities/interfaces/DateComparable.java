package org.example.entities.interfaces;

public interface DateComparable {
    <T extends Reportable> int compareDate(T o);
}
