/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.event.remote.entity;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.UUID;

import org.eclipse.hawkbit.repository.event.remote.entity.RolloutGroupCreatedEvent;
import org.eclipse.hawkbit.repository.event.remote.entity.RolloutGroupUpdatedEvent;
import org.eclipse.hawkbit.repository.jpa.model.JpaRollout;
import org.eclipse.hawkbit.repository.model.DistributionSet;
import org.eclipse.hawkbit.repository.model.Rollout;
import org.eclipse.hawkbit.repository.model.RolloutGroup;
import org.eclipse.hawkbit.repository.model.RolloutGroup.RolloutGroupSuccessCondition;
import org.eclipse.hawkbit.repository.model.RolloutGroupConditionBuilder;
import org.junit.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

/**
 * Test the remote entity events.
 */
@Features("Component Tests - Repository")
@Stories("Test RolloutGroupCreatedEvent and RolloutGroupUpdatedEvent")
public class RolloutGroupEventTest extends AbstractRemoteEntityEventTest<RolloutGroup> {

    @Test
    @Description("Verifies that the rollout group entity reloading by remote created event works")
    public void testRolloutGroupCreatedEvent() {
        final RolloutGroupCreatedEvent createdEvent = (RolloutGroupCreatedEvent) assertAndCreateRemoteEvent(
                RolloutGroupCreatedEvent.class);
        assertThat(createdEvent.getRolloutId()).isNotNull();
    }

    @Test
    @Description("Verifies that the rollout group entity reloading by remote updated event works")
    public void testRolloutGroupUpdatedEvent() {
        assertAndCreateRemoteEvent(RolloutGroupUpdatedEvent.class);
    }

    @Override
    protected RolloutGroup createEntity() {
        targetManagement.createTarget(entityFactory.generateTarget(UUID.randomUUID().toString()));
        final DistributionSet ds = distributionSetManagement
                .createDistributionSet(entityFactory.generateDistributionSet(UUID.randomUUID().toString(), "2",
                        "incomplete", distributionSetManagement.findDistributionSetTypeByKey("os"), null));

        final Rollout rollout = entityFactory.generateRollout();
        rollout.setName(UUID.randomUUID().toString());
        rollout.setTargetFilterQuery("controllerId==*");
        rollout.setDistributionSet(ds);

        final JpaRollout entity = (JpaRollout) rolloutManagement.createRollout(rollout, 10,
                new RolloutGroupConditionBuilder().successCondition(RolloutGroupSuccessCondition.THRESHOLD, "10")
                        .build());

        return rolloutManagement.findRolloutById(entity.getId()).getRolloutGroups().get(0);
    }

}
