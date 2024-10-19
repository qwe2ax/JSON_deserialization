package org.example.services.interfaces;

import java.util.List;
import java.util.Set;

public interface DataFilter<T> {

    List<T> removeDuplicateData(List<T> entities);

    List<T> transferInactiveData(List<T> entities, Set<String> closedIds);

    List<T> removeInactiveData(List<T> entities, Set<String> closedIds);

}
