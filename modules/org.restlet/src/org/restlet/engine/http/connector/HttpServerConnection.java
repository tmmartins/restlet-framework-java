/**
 * Copyright 2005-2009 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package org.restlet.engine.http.connector;

import java.io.IOException;
import java.net.Socket;
import java.security.Principal;

import org.restlet.Context;
import org.restlet.Server;
import org.restlet.data.Parameter;
import org.restlet.engine.ConnectorHelper;
import org.restlet.representation.Representation;
import org.restlet.util.Series;

/**
 * HTTP server connector for the default HTTP connector.
 * 
 * @author Jerome Louvel
 */
public class HttpServerConnection extends ServerConnection {

    /**
     * Constructor.
     * 
     * @param helper
     *            The parent helper.
     * @param socket
     *            The associated socket.
     * @throws IOException
     */
    public HttpServerConnection(ConnectorHelper<Server> helper, Socket socket)
            throws IOException {
        super(helper, socket);
    }

    @Override
    protected ConnectedRequest createRequest(Context context,
            ServerConnection connection, String methodName, String resourceUri,
            String version, Series<Parameter> headers, Representation entity,
            boolean confidential, Principal userPrincipal) {
        return new ConnectedRequest(getHelper().getContext(), this, methodName,
                resourceUri, version, headers, createRequestEntity(headers),
                false, null);
    }
}