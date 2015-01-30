package com.theta360.theta.data;

import com.theta360.util.Validators;

import java.util.HashMap;
import java.util.Map;

/**
 * Battery Level
 */
public enum BatteryLevel {
    /**
     * The battery level is full.
     */
    FULL((byte) 100),
    /**
     * The battery level is half.
     */
    HALF((byte) 67),
    /**
     * The battery level is near end.
     */
    NEAR_END((byte) 33),
    /**
     * The battery level is empty.
     */
    END((byte) 0);

    private final byte value;

    private BatteryLevel(byte value) {
        this.value = value;
    }

    // Getter

    public byte getValue() {
        return value;
    }

    // valueOf

    private static final Map<Byte, BatteryLevel> batteryLevelMap = new HashMap<>();

    static {
        for (BatteryLevel batteryLevel : BatteryLevel.values()) {
            batteryLevelMap.put(batteryLevel.value, batteryLevel);
        }
    }

    public static BatteryLevel valueOf(byte value) {
        Validators.validateNonNull("value", value);

        if (!batteryLevelMap.containsKey(value)) {
            throw new RuntimeException("Unknown BatteryLevel value: " + value);
        }

        return batteryLevelMap.get(value);
    }
}