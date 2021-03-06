/**
 * Copyright 2013 Peergreen S.A.S.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.peergreen.shelbie.deployment.internal;

import com.peergreen.deployment.model.ArtifactModelManager;
import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.HandlerDeclaration;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.service.command.CommandSession;

import java.net.URI;

/**
 * Display the report of an artifact.
 */
@Component
@Command(name = "list",
         scope = "deployment",
         description = "List managed artifacts in the system.")
@HandlerDeclaration("<sh:command xmlns:sh='org.ow2.shelbie'/>")
public class ListManagedArtifactsAction implements Action {

    @Requires
    private ArtifactModelManager artifactModelManager;

    public Object execute(final CommandSession session) throws Exception {

        for (URI uri : artifactModelManager.getDeployedRootURIs()) {
            System.out.printf("  %s%n", uri);
        }

        return null;
    }

}