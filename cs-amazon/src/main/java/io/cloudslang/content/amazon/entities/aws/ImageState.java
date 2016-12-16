/*******************************************************************************
 * (c) Copyright 2016 Hewlett-Packard Development Company, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *******************************************************************************/
package io.cloudslang.content.amazon.entities.aws;

import static io.cloudslang.content.amazon.entities.constants.Constants.Miscellaneous.NOT_RELEVANT;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * Created by Mihai Tusa.
 * 6/17/2016.
 */
public enum ImageState {
    AVAILABLE,
    PENDING,
    FAILED;

    public static String getValue(String input) {
        if (isBlank(input)) {
            return NOT_RELEVANT;
        }

        for (ImageState member : ImageState.values()) {
            if (member.name().equalsIgnoreCase(input)) {
                return member.name().toLowerCase();
            }
        }

        throw new RuntimeException("Unrecognized image state value: [" + input + "]. Valid values are: available, pending, failed.");
    }
}
