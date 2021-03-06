/*
 * Copyright (C) 2015 theta4j project
 */

package org.theta4j.data;

import org.theta4j.util.Validators;

import java.util.HashMap;
import java.util.Map;

/**
 * The enum represents the shutter speed.
 */
public enum ShutterSpeed {
    /**
     * Auto
     */
    AUTO(0, 0),
    /**
     * 1/8000
     */
    SS_1_8000(1, 8000),
    /**
     * 1/6400
     */
    SS_1_6400(1, 6400),
    /**
     * 1/5000
     */
    SS_1_5000(1, 5000),
    /**
     * 1/4000
     */
    SS_1_4000(1, 4000),
    /**
     * 1/3200
     */
    SS_1_3200(1, 3200),
    /**
     * 1/2500
     */
    SS_1_2500(1, 2500),
    /**
     * 1/2000
     */
    SS_1_2000(1, 2000),
    /**
     * 1/1600
     */
    SS_1_1600(1, 1600),
    /**
     * 1/1250
     */
    SS_1_1250(1, 1250),
    /**
     * 1/1000
     */
    SS_1_1000(1, 1000),
    /**
     * 1/800
     */
    SS_1_800(1, 800),
    /**
     * 1/640
     */
    SS_1_640(1, 640),
    /**
     * 1/500
     */
    SS_1_500(1, 500),
    /**
     * 1/400
     */
    SS_1_400(1, 400),
    /**
     * 1/320
     */
    SS_1_320(1, 320),
    /**
     * 1/250
     */
    SS_1_250(1, 250),
    /**
     * 1/200
     */
    SS_1_200(1, 200),
    /**
     * 1/160
     */
    SS_1_160(1, 160),
    /**
     * 1/125
     */
    SS_1_125(1, 125),
    /**
     * 1/100
     */
    SS_1_100(1, 100),
    /**
     * 1/80
     */
    SS_1_80(1, 80),
    /**
     * 1/60
     */
    SS_1_60(1, 60),
    /**
     * 1/50
     */
    SS_1_50(1, 50),
    /**
     * 1/40
     */
    SS_1_40(1, 40),
    /**
     * 1_30
     */
    SS_1_30(1, 30),
    /**
     * 1/25
     */
    SS_1_25(1, 25),
    /**
     * 1/20
     */
    SS_1_20(1, 20),
    /**
     * 1/15
     */
    SS_1_15(1, 15),
    /**
     * 1/13
     */
    SS_1_13(1, 13),
    /**
     * 1/10
     */
    SS_1_10(1, 10),
    /**
     * 10/75
     */
    SS_10_75(10, 75);

    // Map for valueOf method

    private static final Map<Rational, ShutterSpeed> SHUTTER_SPEED_MAP = new HashMap<>();

    static {
        for (ShutterSpeed shutterSpeed : ShutterSpeed.values()) {
            SHUTTER_SPEED_MAP.put(shutterSpeed.value, shutterSpeed);
        }
    }

    // Property

    private final Rational value;

    // Constructor

    ShutterSpeed(long molecule, long denominator) {
        this.value = new Rational(molecule, denominator);
    }

    // Getter

    /**
     * Returns the integer value according THETA API v1.
     */
    public Rational value() {
        return value;
    }

    // valueOf

    /**
     * Returns the shutter speed enum from the integer value defined by THETA API v1.
     */
    public static ShutterSpeed valueOf(Rational value) {
        Validators.notNull("value", value);

        if (!SHUTTER_SPEED_MAP.containsKey(value)) {
            throw new IllegalArgumentException("Unknown ShutterSpeed value: " + value);
        }

        return SHUTTER_SPEED_MAP.get(value);
    }
}
