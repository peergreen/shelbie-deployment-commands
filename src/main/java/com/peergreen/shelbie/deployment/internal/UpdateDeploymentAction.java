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

import java.net.URI;
import java.util.Arrays;

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.HandlerDeclaration;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.service.command.CommandSession;
import org.fusesource.jansi.Ansi;

import com.peergreen.deployment.Artifact;
import com.peergreen.deployment.ArtifactBuilder;
import com.peergreen.deployment.ArtifactProcessRequest;
import com.peergreen.deployment.DeploymentMode;
import com.peergreen.deployment.DeploymentService;
import com.peergreen.deployment.report.DeploymentStatusReport;

/**
 * Display the report of an artifact.
 */
@Component
@Command(name = "update-artifact",
         scope = "deployment",
         description = "Update the given artifact in the system.")
@HandlerDeclaration("<sh:command xmlns:sh='org.ow2.shelbie'/>")
public class UpdateDeploymentAction implements Action {

    @Argument(name = "uri",
              required = true,
              description = "URI of the artifact to update")
    private String uri;

    @Requires
    private DeploymentService deploymentService;

    @Requires
    private ArtifactBuilder artifactBuilder;


    public Object execute(final CommandSession session) throws Exception {

        Ansi buffer = Ansi.ansi();

        Artifact artifact = artifactBuilder.build(uri.toString(), new URI(uri));
        ArtifactProcessRequest artifactProcessRequest = new ArtifactProcessRequest(artifact);
        artifactProcessRequest.setDeploymentMode(DeploymentMode.UPDATE);
        DeploymentStatusReport report = deploymentService.process(Arrays.asList(artifactProcessRequest));

        buffer.a(report.shortReport());
        System.out.println(buffer.toString());
        return null;
    }

}