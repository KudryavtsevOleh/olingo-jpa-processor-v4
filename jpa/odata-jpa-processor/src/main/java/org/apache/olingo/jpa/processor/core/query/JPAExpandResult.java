package org.apache.olingo.jpa.processor.core.query;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.Tuple;

import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.jpa.metadata.core.edm.mapper.impl.JPAAssociationPath;
import org.apache.olingo.server.api.ODataApplicationException;

/**
 * Builds a hierarchy of expand results. One instance contains on the on hand of the result itself, a map which has the
 * join columns values of the parent as its key and on the other hand a map that point the results of the next expand.
 * The join columns are concatenated in the order they are stored in the corresponding Association Path.
 * @author Oliver Grande
 *
 */
public final class JPAExpandResult {

  private final HashMap<JPAAssociationPath, JPAExpandResult> childrenResult;
  private final Map<String, List<Tuple>> result;
  private final Long count;

  public JPAExpandResult(Map<String, List<Tuple>> result, Long count) {
    super();
    childrenResult = new HashMap<JPAAssociationPath, JPAExpandResult>();
    this.result = result;
    this.count = count;
  }

  public boolean hasChildren() {
    return !childrenResult.isEmpty();
  }

  public Map<String, List<Tuple>> getAllResults() {
    return result;
  }

  public void putChildren(Map<JPAAssociationPath, JPAExpandResult> childResults) throws ODataApplicationException {
    for (JPAAssociationPath child : childResults.keySet()) {
      if (childrenResult.get(child) != null)
        throw new ODataApplicationException("Double execution of $expand", HttpStatusCode.INTERNAL_SERVER_ERROR
            .ordinal(),
            Locale.ENGLISH);
    }
    childrenResult.putAll(childResults);
  }

  public List<Tuple> getResult(String key) {
    return result.get(key);
  }

  public HashMap<JPAAssociationPath, JPAExpandResult> getChildren() {
    return childrenResult;
  }

  public boolean hasCount() {
    return count != null;
  }

  public Integer getCount() {
    return count != null ? Integer.valueOf(count.intValue()) : null;
  }
}