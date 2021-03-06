/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.event.remote.entity;

import org.eclipse.hawkbit.repository.event.remote.entity.DistributionSetCreatedEvent;
import org.eclipse.hawkbit.repository.event.remote.entity.DistributionSetUpdateEvent;
import org.eclipse.hawkbit.repository.model.DistributionSet;
import org.junit.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

/**
 * Test the remote entity events.
 */
@Features("Component Tests - Repository")
@Stories("Test DistributionSetCreatedEvent and DistributionSetUpdateEvent")
public class DistributionSetEventTest extends AbstractRemoteEntityEventTest<DistributionSet> {

    @Test
    @Description("Verifies that the distribution set entity reloading by remote created event works")
    public void testDistributionSetCreatedEvent() {
        assertAndCreateRemoteEvent(DistributionSetCreatedEvent.class);
    }

    @Test
    @Description("Verifies that the distribution set entity reloading by remote updated event works")
    public void testDistributionSetUpdateEvent() {
        assertAndCreateRemoteEvent(DistributionSetUpdateEvent.class);
    }

    @Override
    protected DistributionSet createEntity() {
        return distributionSetManagement.createDistributionSet(entityFactory.generateDistributionSet("incomplete", "2",
                "incomplete", distributionSetManagement.findDistributionSetTypeByKey("os"), null));
    }

}
