/**
 * This file is part of CloudML [ http://cloudml.org ]
 *
 * Copyright (C) 2012 - SINTEF ICT
 * Contact: Franck Chauvel <franck.chauvel@sintef.no>
 *
 * Module: root
 *
 * CloudML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * CloudML is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with CloudML. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package test.cloudml.indicators;

import org.cloudml.core.Component;
import org.cloudml.core.Deployment;
import org.cloudml.core.Property;
import org.cloudml.core.samples.SshClientServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import org.cloudml.indicators.Robustness;
import org.cloudml.indicators.Selection;
import org.cloudml.indicators.TrioExporter;

import static org.cloudml.core.builders.Commons.aVMInstance;
import static org.hamcrest.MatcherAssert.*;

import static org.hamcrest.Matchers.*;

/**
 * Test the computation of robustness.
 *
 * We assume here that TRIO's robustness calculations are correct and we
 * therefore only test for a correct generation of a Trio model out of a CloudML
 * deployment.
 */
@RunWith(JUnit4.class)
public class RobustnessTest {

    @Test
    public void robustnessOfOneClientOneServer() {
        final Deployment cloudml = SshClientServer.
                getOneClientConnectedToOneServer()
                .build();

        final Robustness robustness = Robustness.of(cloudml, Selection.INTERNAL, Selection.EXTERNAL);

        assertThat("Wrong robustness",
                   robustness.value(),
                   is(closeTo(0.125, TOLERANCE)));
    }

    @Test
    public void robustnessOfSelfRepairingOneClientOneServer() {
        final Deployment cloudml = SshClientServer.
                getOneClientConnectedToOneServer()
                .build();

        final Robustness robustness = Robustness.ofSelfRepairing(cloudml, Selection.INTERNAL, Selection.EXTERNAL);

        assertThat("Wrong robustness",
                   robustness.value(),
                   is(closeTo(0.125, TOLERANCE)));
    }
    
    
    @Test
    public void serviceRobustnessOfSelfRepairingOneClientOneServer() {
        final Deployment ssh = SshClientServer.
                getOneClientConnectedToOneServer()
                .build();

        final Component client = ssh.getComponents().firstNamed(SshClientServer.SSH_CLIENT);
        client.getProperties().add(new Property(TrioExporter.IS_SERVICE, "true"));
        
        final Component server = ssh.getComponents().firstNamed(SshClientServer.SSH_SERVER);
        server.getProperties().add(new Property(TrioExporter.IS_SERVICE, "true"));
        
        final Robustness internalRobustness = Robustness.ofSelfRepairing(ssh, Selection.INTERNAL, Selection.EXTERNAL);
        final Robustness serviceRobustness = Robustness.ofSelfRepairing(ssh, Selection.SERVICE, Selection.NOT_SERVICE);

        
        assertThat("Wrong robustness",
                   serviceRobustness.value(),
                   is(closeTo(internalRobustness.value(), TOLERANCE)));
    }

    @Test
    public void robustnessOfOneClientOneOfTwoServers() {
        final Deployment cloudml = SshClientServer.
                getOneClientConnectedToOneServer()
                .with(aVMInstance()
                        .named("VM3")
                        .ofType(SshClientServer.EC2_XLARGE_WINDOWS_7))
                .build();

        final Robustness robustness = Robustness.of(cloudml, Selection.INTERNAL, Selection.EXTERNAL);

        assertThat("Wrong robustness",
                   robustness.value(),
                   is(closeTo(2D/9, TOLERANCE)));
    }

    @Test
    public void robustnessOfSelfRepairingOneClientOneOfTwoServers() {
        final Deployment cloudml = SshClientServer.
                getOneClientConnectedToOneServer()
                .with(aVMInstance()
                        .named("VM3")
                        .ofType(SshClientServer.EC2_XLARGE_WINDOWS_7))
                .build();

        final Robustness robustness = Robustness.ofSelfRepairing(cloudml, Selection.INTERNAL, Selection.EXTERNAL);

        assertThat("Wrong robustness",
                   robustness.value(),
                   is(closeTo(7D / 18, TOLERANCE)));
    }

    private static final double TOLERANCE = 1e-2;

}
