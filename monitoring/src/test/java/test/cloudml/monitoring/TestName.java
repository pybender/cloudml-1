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
package test.cloudml.monitoring;

import junit.framework.TestCase;
import org.cloudml.core.ComponentInstance;
import org.cloudml.monitoring.status.NotificationSender;
import org.cloudml.mrt.Coordinator;

/**
 * Created by user on 08.07.14.
 */
public class TestName extends TestCase {
    public void test() {
        NotificationSender.updateUsingFacade("test", ComponentInstance.State.RUNNING,new Coordinator());
        Coordinator coordinator = new Coordinator("sample://sensApp");
        NotificationSender.updateUsingFacade("sensapp-sl1", ComponentInstance.State.RUNNING, coordinator);
        NotificationSender.updateUsingFacade("sensapp-sl1", ComponentInstance.State.RUNNING, coordinator);

    }
}

