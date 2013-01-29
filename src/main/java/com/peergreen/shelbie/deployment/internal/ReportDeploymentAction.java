/**
 * Copyright 2012 Peergreen S.A.S.
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

import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.HandlerDeclaration;
import org.apache.felix.ipojo.annotations.Requires;
import org.apache.felix.service.command.CommandSession;
import org.fusesource.jansi.Ansi;

import com.peergreen.deployment.DeploymentService;

/**
 * Display the report of an artifact.
 */
@Component
@Command(name = "report",
         scope = "deployment",
         description = "Display report of an artifact deployed in the system.")
@HandlerDeclaration("<sh:command xmlns:sh='org.ow2.shelbie'/>")
public class ReportDeploymentAction implements Action {

    @Argument(name = "uri",
              required = true,
              description = "URI of the artifact to display")
    private String uri;

    @Requires
    private DeploymentService deploymentService;

    public Object execute(final CommandSession session) throws Exception {

        Ansi buffer = Ansi.ansi();

        buffer.a(deploymentService.getReport(uri));
        System.out.println(buffer.toString());
        return null;
    }

}