/**
 * Logback: the reliable, generic, fast and flexible logging framework.
 * Copyright (C) 1999-2013, QOS.ch. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation.
 */
package ch.qos.logback.classic.net;

import java.net.InetAddress;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.net.SSLSocketAppenderBase;
import ch.qos.logback.core.spi.PreSerializationTransformer;

/**
 * A {@link SocketAppender} that supports SSL.
 * <p>
 * For more information on this appender, please refer to the online manual
 * at http://logback.qos.ch/manual/appenders.html#SSLSocketAppender
 * 
 * @author Carl Harris
 */
public class SSLSocketAppender extends SSLSocketAppenderBase<ILoggingEvent> {

  private final PreSerializationTransformer<ILoggingEvent> pst = 
      new LoggingEventPreSerializationTransformer();

  private boolean includeCallerData;
  
  public SSLSocketAppender() {
  }

  /**
   * Connects to remote server at <code>address</code> and <code>port</code>.
   */
  @Deprecated
  public SSLSocketAppender(String host, int port) {
    super(host, port);
  }

  /**
   * Connects to remote server at <code>address</code> and <code>port</code>.
   */
  @Deprecated
  public SSLSocketAppender(InetAddress address, int port) {
    super(address.getHostAddress(), port);
  }

  @Override
  protected void postProcessEvent(ILoggingEvent event) {
    if (includeCallerData) {
      event.getCallerData();
    }
  }

  public void setIncludeCallerData(boolean includeCallerData) {
    this.includeCallerData = includeCallerData;
  }
  
  public PreSerializationTransformer<ILoggingEvent> getPST() {
    return pst;
  }
   
}
