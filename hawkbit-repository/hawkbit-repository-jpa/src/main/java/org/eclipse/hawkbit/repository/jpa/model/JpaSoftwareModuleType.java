/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.repository.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.hawkbit.repository.model.SoftwareModuleType;

/**
 * Type of a software modules.
 *
 */
@Entity
@Table(name = "sp_software_module_type", indexes = {
        @Index(name = "sp_idx_software_module_type_01", columnList = "tenant,deleted"),
        @Index(name = "sp_idx_software_module_type_prim", columnList = "tenant,id") }, uniqueConstraints = {
                @UniqueConstraint(columnNames = { "type_key", "tenant" }, name = "uk_smt_type_key"),
                @UniqueConstraint(columnNames = { "name", "tenant" }, name = "uk_smt_name") })
// exception squid:S2160 - BaseEntity equals/hashcode is handling correctly for
// sub entities
@SuppressWarnings("squid:S2160")
public class JpaSoftwareModuleType extends AbstractJpaNamedEntity implements SoftwareModuleType {
    private static final long serialVersionUID = 1L;

    @Column(name = "type_key", nullable = false, length = 64)
    @Size(max = 64)
    @NotNull
    private String key;

    @Column(name = "max_ds_assignments", nullable = false)
    @Min(1)
    private int maxAssignments;

    @Column(name = "colour", nullable = true, length = 16)
    @Size(max = 16)
    private String colour;

    @Column(name = "deleted")
    private boolean deleted;

    /**
     * Constructor.
     *
     * @param key
     *            of the type
     * @param name
     *            of the type
     * @param description
     *            of the type
     * @param maxAssignments
     *            assignments to a DS
     */
    public JpaSoftwareModuleType(final String key, final String name, final String description,
            final int maxAssignments) {
        this(key, name, description, maxAssignments, null);
    }

    /**
     * Constructor.
     *
     * @param key
     *            of the type
     * @param name
     *            of the type
     * @param description
     *            of the type
     * @param maxAssignments
     *            assignments to a DS
     * @param colour
     *            of the type. It will be null by default
     */
    public JpaSoftwareModuleType(final String key, final String name, final String description,
            final int maxAssignments, final String colour) {
        super();
        this.key = key;
        this.maxAssignments = maxAssignments;
        setDescription(description);
        setName(name);
        this.colour = colour;
    }

    /**
     * Default Constructor for JPA.
     */
    public JpaSoftwareModuleType() {
        // Default Constructor for JPA.
    }

    @Override
    public void setMaxAssignments(final int maxAssignments) {
        this.maxAssignments = maxAssignments;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public int getMaxAssignments() {
        return maxAssignments;
    }

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public void setColour(final String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "SoftwareModuleType [key=" + key + ", getName()=" + getName() + ", getId()=" + getId() + "]";
    }

    @Override
    public void setKey(final String key) {
        this.key = key;
    }
}
