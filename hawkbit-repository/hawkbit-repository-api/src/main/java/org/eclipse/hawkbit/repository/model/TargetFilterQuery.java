/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.model;

/**
 * Managed filter entity.
 * 
 * Supported operators.
 * <ul>
 * <li>Equal to : ==</li>
 * <li>Not equal to : !=</li>
 * <li>Less than : =lt= or <</li>
 * <li>Less than or equal to : =le= or <=</li>
 * <li>Greater than operator : =gt= or ></li>
 * <li>Greater than or equal to : =ge= or >=</li>
 * </ul>
 * Examples of RSQL expressions in both FIQL-like and alternative notation:
 * <ul>
 * <li>version==2.0.0</li>
 * <li>name==targetId1;description==plugAndPlay</li>
 * <li>name==targetId1 and description==plugAndPlay</li>
 * <li>name==targetId1;description==plugAndPlay</li>
 * <li>name==targetId1 and description==plugAndPlay</li>
 * <li>name==targetId1,description==plugAndPlay,updateStatus==UNKNOWN</li>
 * <li>name==targetId1 or description==plugAndPlay or updateStatus==UNKNOWN</li>
 * </ul>
 *
 */
public interface TargetFilterQuery extends TenantAwareBaseEntity {

    /**
     * @return name of the {@link TargetFilterQuery}.
     */
    String getName();

    /**
     * @param name
     *            of the {@link TargetFilterQuery}.
     */
    void setName(String name);

    /**
     * @return RSQL query
     */
    String getQuery();

    /**
     * @param query
     *            in RSQL notation.
     */
    void setQuery(String query);

    /**
     * @return the auto assign {@link DistributionSet} if given.
     */
    DistributionSet getAutoAssignDistributionSet();

    /**
     * @param distributionSet
     *                      the {@link DistributionSet} that should be assigned to a
     *                      target when this filter matches.
     */
    void setAutoAssignDistributionSet(DistributionSet distributionSet);

}
