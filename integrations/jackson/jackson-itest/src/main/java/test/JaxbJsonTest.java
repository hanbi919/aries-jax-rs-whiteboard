/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test;

import org.junit.Test;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import test.types.TestHelper;
import test.types.TestJaxbJson;

import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertTrue;

public class JaxbJsonTest extends TestHelper {

    @Test
    public void testJSONPEndpoint() {
        WebTarget webTarget = createDefaultTarget().path("jaxbjson");

        registerAddon(new TestJaxbJson());

        String response = webTarget.request().get(String.class);

        assertTrue(response.contains("value"));
    }

    @Test
    public void testJSONPEndpointRequireExtension() {
        WebTarget webTarget = createDefaultTarget().path("jaxbjson");

        registerAddon(
            new TestJaxbJson(),
            JaxrsWhiteboardConstants.JAX_RS_EXTENSION_SELECT,
            "(osgi.jaxrs.media.type=application/json)");

        String response = webTarget.request().get(String.class);

        assertTrue(response.contains("value"));
    }

}
