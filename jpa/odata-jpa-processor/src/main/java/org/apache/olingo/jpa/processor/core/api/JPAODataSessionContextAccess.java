package org.apache.olingo.jpa.processor.core.api;

import java.util.List;

import org.apache.olingo.jpa.metadata.api.JPAEdmProvider;
import org.apache.olingo.jpa.processor.core.filter.JPAOperationConverter;
import org.apache.olingo.server.api.debug.DebugSupport;
import org.apache.olingo.server.api.edmx.EdmxReference;

public interface JPAODataSessionContextAccess {
  public List<EdmxReference> getReferences();

  public DebugSupport getDebugSupport();

  public JPAOperationConverter getOperationConverter();

  public JPAEdmProvider getEdmProvider();

  public JPAODataDatabaseProcessor getDatabaseProcessor();
}