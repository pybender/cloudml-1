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
/*
 */

package test.cloudml.core.builders;

import org.cloudml.core.ProvidedExecutionPlatform;
import org.cloudml.core.RequiredExecutionPlatform;
import org.cloudml.core.WithResources;
import org.cloudml.core.builders.RequiredExecutionPlatformBuilder;
import org.cloudml.core.builders.WithResourcesBuilder;
import org.junit.Test;

import static org.cloudml.core.builders.Commons.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class RequiredExecutionPlatformBuilderTest extends WithResourcesBuilderTest {

    
    @Override
    public WithResourcesBuilder<? extends WithResources, ? extends WithResourcesBuilder<?, ?>> aSampleWithResourceBuilder() {
        return aSampleRequiredExecutionPlatformBuilder();
    }

    private RequiredExecutionPlatformBuilder aSampleRequiredExecutionPlatformBuilder() {
        return aRequiredExecutionPlatform();
    }

    @Test
    public void providedExecutionPlatformHaveNoOfferingByDefault() {
        final RequiredExecutionPlatform sut = aSampleRequiredExecutionPlatformBuilder().build();

        assertThat("no offering", sut.getDemands().isEmpty());
    }

    @Test
    public void providedExecutionPlatformWithOneOffer() {
        final RequiredExecutionPlatform sut = aSampleRequiredExecutionPlatformBuilder()
                .demanding("OS", "Linux")
                .build();

        assertThat("1 offer", sut.getDemands().size(), is(equalTo(1)));
        assertThat("os is linux", sut.getDemands().contains("OS", "Linux"));
    }
}
