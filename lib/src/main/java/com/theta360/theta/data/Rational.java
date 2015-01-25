package com.theta360.theta.data;

import com.theta360.ptp.io.PtpInputStream;
import com.theta360.ptp.type.UINT32;
import com.theta360.util.ByteUtils;
import com.theta360.util.Validators;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Exif2.3 standard RATIONAL
 */
public class Rational implements Comparable<Rational> {
    private final long molecule;
    private final long denominator;
    private final byte[] bytes;

    // Constructor

    public Rational(long molecule, long denominator) {
        if (molecule < 0) {
            throw new IllegalArgumentException();
        }

        if (denominator == 0) {
            throw new ArithmeticException();
        } else if (denominator < 0) {
            throw new IllegalArgumentException();
        }

        this.molecule = molecule;
        this.denominator = denominator;

        this.bytes = ByteUtils.join(
                new UINT32(molecule).bytes(),
                new UINT32(denominator).bytes()
        );
    }

    // Static Factory Method

    public static Rational valueOf(byte[] bytes) {
        Validators.validateNonNull("bytes", bytes);

        if (bytes.length < UINT32.SIZE + UINT32.SIZE) {
            throw new IllegalArgumentException();
        }

        try (
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                PtpInputStream pis = new PtpInputStream(bais)
        ) {
            UINT32 molecule = pis.readUINT32();
            UINT32 denominator = pis.readUINT32();

            return new Rational(molecule.longValue(), denominator.longValue());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    // Getter

    public long getMolecule() {
        return molecule;
    }

    public long getDenominator() {
        return denominator;
    }

    public byte[] bytes() {
        return bytes.clone();
    }

    // Comparable

    @Override
    public int compareTo(Rational o) {
        if (molecule == o.molecule && denominator == o.denominator) {
            return 0;
        }

        return Double.compare(
                (double) molecule / denominator,
                (double) o.molecule / o.denominator);
    }

    // Basic Method

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rational rational = (Rational) o;

        if (denominator != rational.denominator) return false;
        if (molecule != rational.molecule) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (molecule ^ (molecule >>> 32));
        result = 31 * result + (int) (denominator ^ (denominator >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Rational{" + molecule + "/" + denominator + "}";
    }
}
